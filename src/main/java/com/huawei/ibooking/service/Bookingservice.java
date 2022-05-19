package com.huawei.ibooking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huawei.ibooking.pojo.Booking;
import com.huawei.ibooking.pojo.Seat;
import com.huawei.ibooking.pojo.other.RespPageBean;

import java.time.LocalDateTime;

/**
 * @Author qxy
 * @Date 2022/5/17 22:04
 * @Version 1.0
 */
public interface Bookingservice extends IService<Booking> {

    RespPageBean getAllBookings(Integer page, Integer size);


    int getNumBySeatAndTime(int seatId, LocalDateTime startLocalDate, LocalDateTime endLocalDate);

    int getNumByStuAndTime(int stuId, LocalDateTime startLocalDate, LocalDateTime endLocalDate);

    RespPageBean getBookingsByStuId(Integer page, Integer size, int stuId);
}
