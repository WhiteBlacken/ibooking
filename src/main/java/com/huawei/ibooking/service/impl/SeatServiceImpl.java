package com.huawei.ibooking.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huawei.ibooking.mapper.SeatMapper;
import com.huawei.ibooking.pojo.Seat;
import com.huawei.ibooking.pojo.other.RespPageBean;
import com.huawei.ibooking.pojo.other.SeatCondition;
import com.huawei.ibooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author qxy
 * @Date 2022/3/26 2:10
 * @Version 1.0
 */
@Service
public class SeatServiceImpl extends ServiceImpl<SeatMapper, Seat> implements SeatService {

    @Autowired
    private SeatMapper seatMapper;

    /**
     * 查询所有的座位（分页）
     * @param page
     * @param size
     * @return
     */
    @Override
    public RespPageBean getAllSeats(Integer page, Integer size) {
        IPage<Seat> seatIPage = new Page<>(page,size);
        seatIPage = seatMapper.getAllSeatByPage(seatIPage);
        return new RespPageBean(seatIPage.getRecords().size(),seatIPage.getRecords());
    }

    /**
     * 根据条件查询座位（分页）
     * @param page
     * @param size
     * @param seatCondition
     * @return
     */
    @Override
    public RespPageBean getSeatsByCondition(Integer page, Integer size, SeatCondition seatCondition) {
        Page<Seat> seatPage = new Page<>(page,size);
        IPage<Seat> iPage = seatMapper.getSeatsByConditions(seatPage,seatCondition);
        return new RespPageBean(iPage.getRecords().size(),iPage.getRecords());
    }


}
