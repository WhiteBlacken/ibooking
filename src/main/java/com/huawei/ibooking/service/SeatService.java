package com.huawei.ibooking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huawei.ibooking.pojo.Seat;
import com.huawei.ibooking.pojo.other.RespPageBean;
import com.huawei.ibooking.pojo.other.SeatCondition;

/**
 * @Author qxy
 * @Date 2022/3/26 2:10
 * @Version 1.0
 */
public interface SeatService extends IService<Seat> {
    RespPageBean getAllSeats(Integer page, Integer size);

    RespPageBean getSeatsByCondition(Integer page, Integer size, SeatCondition seatCondition);

}
