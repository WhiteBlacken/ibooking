package com.huawei.ibooking.controller;

import com.huawei.ibooking.mapper.BookingMapper;
import com.huawei.ibooking.pojo.Booking;
import com.huawei.ibooking.pojo.Seat;
import com.huawei.ibooking.pojo.StudyRoom;
import com.huawei.ibooking.pojo.other.RespBean;
import com.huawei.ibooking.pojo.other.RespPageBean;
import com.huawei.ibooking.service.Bookingservice;
import com.huawei.ibooking.service.SeatService;
import com.huawei.ibooking.service.StudyRoomService;
import com.huawei.ibooking.utils.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author qxy
 * @Date 2022/5/17 21:57
 * @Version 1.0
 */

@Api(tags = "预定座位")
@RestController
@RequestMapping("/booking")
public class BookingController {



    private final Bookingservice bookingservice;
    private final SeatService seatService;
    private final StudyRoomService studyRoomService;

    @Autowired
    public BookingController(Bookingservice bookingservice,SeatService seatService,StudyRoomService studyRoomService){
        this.bookingservice = bookingservice;
        this.seatService = seatService;
        this.studyRoomService = studyRoomService;
    }
//    @Autowired
//    private Bookingservice bookingservice;
//    @Autowired
//    private SeatService seatService;
//    @Autowired
//    private StudyRoomService studyRoomService;

    @ApiOperation(value = "获得所有的预定记录（分页）")
    @GetMapping("/")
    public RespPageBean getAllBookings(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "3") Integer size) {
        return bookingservice.getAllBookings(page, size);
    }


    @ApiOperation(value = "预定某一座位")
    @GetMapping("/add")
    public RespBean addBooking(@RequestParam int seatId,
                               @RequestParam String beginDate,
                               @RequestParam int hour,
                               @RequestParam int type) {
        //拿到session中的stuId
//        int stuId = (int)request.getSession().getAttribute("stuId");
        int stuId = 2;
        //将日期进行格式转换
        LocalDateTime startLocalDate = TimeUtils.stringToLocalDateTime(beginDate);
        //最高可预定四个小时
        if(hour>4)hour=4;
        LocalDateTime endLocalDate = startLocalDate.plusHours(hour);


        /**
         * 1. 检查预定是否合法
         */
        RespBean validBean = isBookingValid(stuId, seatId, startLocalDate, endLocalDate);
        if (validBean.getCode() == 500) return validBean;
        /**
         * 2. 进行预定
         */
        Booking booking = new Booking(seatId, stuId,type);
        booking.setBeginDate(startLocalDate);
        booking.setEndDate(endLocalDate);
        if (bookingservice.save(booking)) {
            return RespBean.success("预定成功");
        }
        return RespBean.error("预定失败");

    }

    @ApiOperation(value="查看某一用户历史预定(分页)")
    @GetMapping("/history")
    public RespPageBean getHistory(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "3") Integer size,
                               HttpServletRequest request){
        //拿到session中的stuId
        //int stuId = (int)request.getSession().getAttribute("stuId");
        int stuId = 2;
        return  bookingservice.getBookingsByStuId(page,size,stuId);

    }

    /***
     * 验证预定的合法性
     * @param stuId
     * @param seatId
     * @param startLocalDate
     * @param endLocalDate
     */
    public RespBean isBookingValid(int stuId, int seatId, LocalDateTime startLocalDate, LocalDateTime endLocalDate) {
        /**
         * 1. 检查该座位是否开放
         */
        System.out.println("seatId:"+seatId);
        System.out.println("stuId:"+stuId);
        System.out.println("start:"+startLocalDate);
        System.out.println("end:"+endLocalDate);
        try{
            System.out.println("执行了");
            Seat seat = seatService.getById(seatId);

            StudyRoom studyRoom = studyRoomService.getById(seat.getStudyRoomId());
            if (seat.getStatus()*studyRoom.getStatus() == 0) {
                return RespBean.error("该座位未开放");
            }
        }catch(Exception e){
            return RespBean.error("该座位不存在!");
        }

        //检查时间上是否开放
        /**
         * 2. 检查座位该时间段是否被占用
         * 时间段是否重叠（第1段的起始时间小于第2段的结束时间,且第1段的结束时间大于第2段的起始时间）
         */
        int bookingOfSeat = bookingservice.getNumBySeatAndTime(seatId, startLocalDate, endLocalDate);
        System.out.println("bookingOfSeat:" + bookingOfSeat);
        if (bookingOfSeat > 0) {
            return RespBean.error("该座位已被占用");
        }
        /**
         * 3. 检查该用户在该时间段是否已有预约
         */
        int bookingOfStu = bookingservice.getNumByStuAndTime(stuId, startLocalDate, endLocalDate);
        System.out.println("bookingOfStu:" + bookingOfSeat);
        if (bookingOfStu > 0) {
            return RespBean.error("您在该时间段已有其他预约");
        }
        //上述检查通过，即为valid
        return RespBean.success(null);

    }




}
