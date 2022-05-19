package com.huawei.ibooking.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huawei.ibooking.mapper.BookingMapper;
import com.huawei.ibooking.mapper.SeatMapper;
import com.huawei.ibooking.pojo.Booking;
import com.huawei.ibooking.pojo.Seat;
import com.huawei.ibooking.pojo.other.RespPageBean;
import com.huawei.ibooking.pojo.other.SeatCondition;
import com.huawei.ibooking.service.Bookingservice;
import com.huawei.ibooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author qxy
 * @Date 2022/3/26 2:10
 * @Version 1.0
 */
@Service
public class BookingServiceImpl extends ServiceImpl<BookingMapper, Booking> implements Bookingservice {

    @Autowired
    private BookingMapper bookingMapper;



    @Override
    public RespPageBean getAllBookings(Integer page, Integer size) {
        IPage<Booking> bookingIPage = new Page<>(page,size);
        bookingIPage = bookingMapper.getAllBookingByPage(bookingIPage);
//        return bookingMapper.selectList(null);
        return new RespPageBean(bookingIPage.getRecords().size(),bookingIPage.getRecords());
    }

    @Override
    public int getNumBySeatAndTime(int seatId, LocalDateTime startLocalDate, LocalDateTime endLocalDate) {
        return bookingMapper.getNumBySeatAndTime(seatId,startLocalDate,endLocalDate);
    }

    @Override
    public int getNumByStuAndTime(int stuId, LocalDateTime startLocalDate, LocalDateTime endLocalDate) {
        return bookingMapper.getNumByStuAndTime(stuId,startLocalDate,endLocalDate);
    }

    /**
     * 分页查询某个用户的历史记录
     * @param page
     * @param size
     * @param stuId
     * @return
     */
    @Override
    public RespPageBean getBookingsByStuId(Integer page, Integer size, int stuId) {
        IPage<Booking> bookingIPage = new Page<>(page,size);
        bookingIPage = bookingMapper.getBookingsByStuId(bookingIPage,stuId);
        return new RespPageBean(bookingIPage.getRecords().size(),bookingIPage.getRecords());
    }


}
