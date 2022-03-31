package com.huawei.ibooking.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huawei.ibooking.pojo.StudyRoom;
import com.huawei.ibooking.pojo.other.RespBean;
import com.huawei.ibooking.pojo.other.RespPageBean;
import com.huawei.ibooking.pojo.other.StudyRoomCondition;
import com.huawei.ibooking.service.StudyRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author qxy
 * @Date 2022/3/25 20:42
 * @Version 1.0
 */

@Api(tags = "管理自习室-管理员")
@RestController
@RequestMapping("/studyroom")
public class StudyRoomController {

    @Autowired
    private StudyRoomService studyRoomService;

    @ApiOperation(value = "查看所有自习室(分页)")
    @GetMapping("/")
    public RespPageBean getAllStudyRoom(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "1") Integer size) {

        return studyRoomService.getAllStudyRoom(page,size);
    }

    @ApiOperation(value = "新增自习室")
    @PostMapping("/")
    public RespBean addStudyRoom(@RequestBody StudyRoom studyRoom) {
        if (studyRoomService.save(studyRoom)) {
            return RespBean.success("自习室添加成功");
        }
        return RespBean.error("添加自习室失败");
    }

    @ApiOperation(value = "修改自习室信息")
    @PutMapping("/")
    public RespBean updateStudyRoom(@RequestBody StudyRoom studyRoom) {
        if (studyRoomService.updateById(studyRoom)) {
            return RespBean.success("自习室信息修改成功");
        }
        return RespBean.error("自习室信息修改失败");
    }

    @ApiOperation(value = "删除自习室")
    @DeleteMapping("/{id}")
    public RespBean deleteStudyRoom(@PathVariable Integer id) {
        if (studyRoomService.removeById(id)) {
            return RespBean.success("自习室删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "设置自习室开放时间")
    @PutMapping("/date/{id}")
    public RespBean setOpenDate(@PathVariable Integer id,
                                String beginDate,
                                String endDate) {
        //1. 将日期进行格式转换
        LocalDateTime startLocalDate = stringToLocalDateTime(beginDate);
        LocalDateTime endLocalDate = stringToLocalDateTime(endDate);
        //2. 获取要修改的对象
        StudyRoom studyRoom = studyRoomService.getById(id);
        //3. 设置时间
        studyRoom.setBeginDate(startLocalDate);
        studyRoom.setEndDate(endLocalDate);
        System.out.println("studyRoom:" + studyRoom);
        //4. 进行修改
        if (studyRoomService.updateById(studyRoom)) {
            return RespBean.success("时间设置成功");
        }
        return RespBean.error("时间设置失败");
    }

    @ApiOperation(value = "改变自习室状态（开放，关闭）")
    @PutMapping("/status/{id}")
    public RespBean changeStudyRoomStatus(@PathVariable Integer id,
                                          int status){
        /**
         * status
         * 0:不开放
         * 1:开放
         * 只有开放时，开放时间才有意义
         */
        //1. 获取要修改的对象
        StudyRoom studyRoom = studyRoomService.getById(id);
        //2. 设置状态
        studyRoom.setStatus(status);
        //3. 进行修改
        if (studyRoomService.updateById(studyRoom)) {
            return RespBean.success("状态修改成功");
        }
        return RespBean.error("状态修改失败");
    }

    @ApiOperation(value = "根据条件查询自习室(分页)")
    @GetMapping("/condition")
    public RespPageBean getStudyRoomsByConditions(@RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "1") Integer size,
                                                  StudyRoomCondition studyRoomCondition){
        if(studyRoomCondition.getStatus()==1)studyRoomCondition.setIsValid(Boolean.TRUE);
        System.out.println("StudyRoomCondition:"+studyRoomCondition);
        return studyRoomService.getStudyRoomsByConditions(page,size,studyRoomCondition);
    }

    /**
     * String转LocalDateTime
     */
    private LocalDateTime stringToLocalDateTime(String s) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(s, df);
    }
}
