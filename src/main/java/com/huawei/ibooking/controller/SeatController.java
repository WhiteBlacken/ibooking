package com.huawei.ibooking.controller;

import com.huawei.ibooking.pojo.Seat;
import com.huawei.ibooking.pojo.other.RespBean;
import com.huawei.ibooking.pojo.other.RespPageBean;
import com.huawei.ibooking.pojo.other.SeatCondition;
import com.huawei.ibooking.service.SeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author qxy
 * @Date 2022/3/26 2:08
 * @Version 1.0
 */
@Api(tags = "座位管理")
@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;


    @ApiOperation(value = "查看所有座位(分页)")
    @GetMapping("/")
    public RespPageBean getAllSeats(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "3") Integer size) {
        System.out.println(seatService.getAllSeats(page, size));
        return seatService.getAllSeats(page, size);
    }

    @ApiOperation(value = "新增座位-管理员")
    @PostMapping("/")
    public RespBean addSeat(@RequestBody Seat seat) {
        if (seatService.save(seat)) {
            return RespBean.success("座位添加成功");
        }
        return RespBean.error("座位添加失败");
    }

    @ApiOperation(value = "修改座位信息-管理员")
    @PutMapping("/")
    public RespBean updateSeat(@RequestBody Seat seat) {
        try{
            if (seatService.updateById(seat)) {
                return RespBean.success("座位信息修改成功");
            }
            return RespBean.error("座位信息修改失败");
        }catch (Exception e){
            return RespBean.error("该座位不存在!");
        }

    }

    @ApiOperation(value = "删除座位-管理员")
    @DeleteMapping("/{id}")
    public RespBean deleteSeat(@PathVariable Integer id) {
        try{
            if (seatService.removeById(id)) {
                return RespBean.success("删除座位成功");
            }
            return RespBean.error("删除座位失败");
        }catch(Exception e){
            return RespBean.error("该座位不存在!");
        }

    }

    @ApiOperation(value = "修改座位状态（开放、关闭）-管理员")
    @GetMapping("/status/{id}")
    public RespBean changeStatusOfSeat(@PathVariable Integer id, int status) {
        try{
            Seat seat = seatService.getById(id);
            seat.setStatus(status);
            if (seatService.updateById(seat)) {
                return RespBean.success("修改座位状态成功");
            }
            return RespBean.error("修改座位状态失败");
        }catch(Exception e){
            return RespBean.error("该座位不存在!");
        }

    }

    @ApiOperation(value = "根据条件查询座位(分页)")
    @GetMapping("/condition")
    public RespPageBean getSeatsByCondition(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "1") Integer size,
                                            SeatCondition seatCondition) {
        System.out.println("seatCondition:" + seatCondition);
        return seatService.getSeatsByCondition(page, size, seatCondition);
    }


}
