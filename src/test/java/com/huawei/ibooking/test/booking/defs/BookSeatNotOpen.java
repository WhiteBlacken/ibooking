package com.huawei.ibooking.test.booking.defs;

import com.huawei.ibooking.controller.BookingController;
import com.huawei.ibooking.pojo.Seat;
import com.huawei.ibooking.pojo.StudyRoom;
import com.huawei.ibooking.pojo.other.RespBean;
import com.huawei.ibooking.service.Bookingservice;
import com.huawei.ibooking.service.SeatService;
import com.huawei.ibooking.service.StudyRoomService;
import com.huawei.ibooking.utils.TimeUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.tomcat.jni.Local;
import org.apache.tomcat.jni.Time;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
/**
 * @Author qxy
 * @Date 2022/5/20 2:27
 * @Version 1.0
 */
public class BookSeatNotOpen {

    private MockMvc mockMvc;

    @Mock
    public Bookingservice bookingservice;
    @Mock
    public SeatService seatService;
    @Mock
    public StudyRoomService studyRoomService;

    @Mock
    public BookingController bookingController;

    int stuId;
    int seatId;
    int hour;
    String beginDate;
    String endDate;

    Seat seat;
    StudyRoom studyRoom;


    @Given("the userId is {int},the id of seat to reserved is {int},")
    public void the_user_id_is_the_id_of_seat_to_reserved_is(Integer stuId, Integer seatId) {
        // Write code here that turns the phrase above into concrete actions
        this.stuId = stuId;
        this.seatId = seatId;
    }
    @Given("the beginTime is {string},the reserve hour is {int}")
    public void the_begin_time_is_the_reserve_hour_is(String beginDate, Integer hour) {
        // Write code here that turns the phrase above into concrete actions
//        System.out.println("beiginDate:"+beginDate);
        this.beginDate = beginDate;
        this.hour = hour;
        this.endDate = TimeUtils.stringToLocalDateTime(beginDate).plusHours(hour).toString();
    }
    @Given("the seat is not open")
    public void the_seat_is_not_open() {
        // Write code here that turns the phrase above into concrete actions
        this.seat = new Seat(seatId,"1",2,0);
        this.studyRoom = new StudyRoom(2,"1","2",0,7,22);

    }
    @Then("the status of reserved result is {int},message is {string}")
    public void the_status_of_reserved_result_is_message_is(Integer int1, String string) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new BookingController(bookingservice,seatService,studyRoomService))
                .setControllerAdvice()
                .build();
        System.out.println("beiginDate:"+this.beginDate);
        doReturn(new RespBean(500,"该座位未开放",null))
                .when(bookingController)
                .isBookingValid(stuId,seatId, TimeUtils.stringToLocalDateTime(beginDate),TimeUtils.stringToLocalDateTime(beginDate).plusHours(hour));
        System.out.println("seatId:"+seatId);
        System.out.println("stuId:"+stuId);
        System.out.println("start:"+TimeUtils.stringToLocalDateTime(beginDate));
        System.out.println("end:"+TimeUtils.stringToLocalDateTime(beginDate).plusHours(hour));
        System.out.println(bookingController.isBookingValid(stuId,seatId, TimeUtils.stringToLocalDateTime(beginDate),TimeUtils.stringToLocalDateTime(beginDate).plusHours(hour)));

        doReturn(seat).when(seatService).getById(seatId);
        doReturn(studyRoom).when(studyRoomService).getById(studyRoom.getId());

        System.out.println(seatService.getById(seatId));
        System.out.println(studyRoomService.getById(studyRoom.getId()));
        //Act&&Assert
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/booking/add")
                .accept(MediaType.APPLICATION_JSON)
                .param("seatId", String.valueOf(seatId))
                .param("beginDate", beginDate)
                .param("hour", String.valueOf(hour))
                .param("type", String.valueOf(1)));
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":500,\"message\":\"该座位未开放\",\"obj\":null}"));;
    }



}
