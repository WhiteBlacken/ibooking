package com.huawei.ibooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huawei.ibooking.pojo.Seat;
import com.huawei.ibooking.pojo.other.SeatCondition;
import org.apache.ibatis.annotations.Param;

/**
 * @Author qxy
 * @Date 2022/3/26 2:11
 * @Version 1.0
 */
public interface SeatMapper extends BaseMapper<Seat> {
    IPage<Seat> getAllSeatByPage(IPage<Seat> seatIPage);

    IPage<Seat> getSeatsByConditions(IPage<Seat> seatPage, @Param("condition") SeatCondition condition);

}
