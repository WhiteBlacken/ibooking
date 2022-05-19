package com.huawei.ibooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huawei.ibooking.pojo.Booking;
import com.huawei.ibooking.pojo.StudyRoom;
import com.huawei.ibooking.pojo.other.StudyRoomCondition;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * @Author qxy
 * @Date 2022/3/25 20:53
 * @Version 1.0
 */
public interface BookingMapper extends BaseMapper<Booking> {

    IPage<Booking> getAllBookingByPage(IPage<Booking> bookingIPage);

    int getNumBySeatAndTime(int seatId, LocalDateTime beginDate, LocalDateTime endDate);

    int getNumByStuAndTime(int stuId, LocalDateTime beginDate, LocalDateTime endDate);

    IPage<Booking> getBookingsByStuId(IPage<Booking> bookingIPage, int stuId);
}
