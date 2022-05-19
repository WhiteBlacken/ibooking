package com.huawei.ibooking.controller;

import com.huawei.ibooking.pojo.StudyRoom;
import com.huawei.ibooking.pojo.other.RespBean;
import com.huawei.ibooking.pojo.other.RespPageBean;
import com.huawei.ibooking.pojo.other.StudyRoomCondition;
import com.huawei.ibooking.service.StudyRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author qxy
 * 学生可以执行查询相关操作，管理员可以执行所有操作
 * @Date 2022/3/25 20:42
 * @Version 1.0
 */

@Api(tags = "管理自习室")
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

    @ApiOperation(value = "新增自习室-管理员")
    @PostMapping("/")
    public RespBean addStudyRoom(@RequestBody StudyRoom studyRoom) {
        //如果beginHour/endHour均为0，则为没指定,重新指定为默认值
        if(studyRoom.getBeginHour()==0&&studyRoom.getEndHour()==0){
            studyRoom.setBeginHour(7);
            studyRoom.setEndHour(22);
        }
        /**
         * 1. 验证新增的合法性
         */
        RespBean validBean = isStudyRoomValid(studyRoom);
        if(validBean.getCode()==500)return validBean;

        /**
         * 2. 新增
         */
        if (studyRoomService.save(studyRoom)) {
            return RespBean.success("自习室添加成功");
        }
        return RespBean.error("添加自习室失败");
    }


    @ApiOperation(value = "修改自习室信息-管理员")
    @PutMapping("/")
    public RespBean updateStudyRoom(@RequestBody StudyRoom studyRoom) {
        if (studyRoomService.updateById(studyRoom)) {
            return RespBean.success("自习室信息修改成功");
        }
        return RespBean.error("自习室信息修改失败");
    }

    @ApiOperation(value = "删除自习室-管理员")
    @DeleteMapping("/{id}")
    public RespBean deleteStudyRoom(@PathVariable Integer id) {
        if (studyRoomService.removeById(id)) {
            return RespBean.success("自习室删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "设置自习室开放时间-管理员")
    @PutMapping("/date/{id}")
    public RespBean setOpenDate(@PathVariable Integer id,
                                int beginHour,
                                int endHour) {
        /**
         * 1. 验证开放时间是否合法
         */
        RespBean validBean = isTimeValid(beginHour, endHour);
        if(validBean.getCode()==500)return validBean;
        /**
         * 2. 修改开放时间
         */
        try{
            StudyRoom studyRoom = studyRoomService.getById(id);
            studyRoom.setBeginHour(beginHour);
            studyRoom.setEndHour(endHour);

            if (studyRoomService.updateById(studyRoom)) {
                return RespBean.success("时间设置成功");
            }
            return RespBean.error("时间设置失败");
        }catch (Exception e){
            return RespBean.error("该自习室不存在！");
        }

    }

    @ApiOperation(value = "改变自习室状态（开放，关闭）-管理员")
    @PutMapping("/status/{id}")
    public RespBean changeStudyRoomStatus(@PathVariable Integer id,
                                          int status){
        /**
         * status
         * 0:不开放
         * 1:开放
         * 只有开放时，开放时间才有意义
         */
        try{
            //1. 获取要修改的对象
            StudyRoom studyRoom = studyRoomService.getById(id);
            //2. 设置状态
            studyRoom.setStatus(status);
            //3. 进行修改
            if (studyRoomService.updateById(studyRoom)) {
                return RespBean.success("状态修改成功");
            }
            return RespBean.error("状态修改失败");
        }catch(Exception e){
            return RespBean.error("该自习室不存在！");
        }

    }

    @ApiOperation(value = "根据条件查询自习室(分页)")
    @GetMapping("/condition")
    public RespPageBean getStudyRoomsByConditions(@RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "3") Integer size,
                                                  StudyRoomCondition studyRoomCondition){
        return studyRoomService.getStudyRoomsByConditions(page,size,studyRoomCondition);
    }

    private RespBean isStudyRoomValid(StudyRoom studyRoom) {
        /**
         * 1. 验证building+classRoomNum是否与已添加的重复
         */
        int cnt = studyRoomService.getCntByBNumAndCNum(studyRoom.getBuildingNum(),studyRoom.getClassRoomNum());
        if(cnt>0){
            return RespBean.error("该自习室已存在！");
        }
        /**
         * 2. 验证开放时间
         * 2.1 起始时间要小于结束时间 （通宵开放时，0-24）
         */
        System.out.println("beginHour:"+studyRoom.getBeginHour());
        System.out.println("endHour:"+studyRoom.getEndHour());

        return isTimeValid(studyRoom.getBeginHour(),studyRoom.getEndHour());
    }

    /**
     * 验证时间是否符合要求
     * @param beginHour
     * @param endHour
     * @return
     */
    private RespBean isTimeValid(int beginHour,int endHour){
        if(isHourInValid(beginHour) || isHourInValid(endHour)){
            return RespBean.error("开始时间和结束时间的范围应该为0-24之间！");
        }
        if(beginHour>=endHour){
            return RespBean.error("开放的起始时间要小于结束时间！");
        }
        return RespBean.success(null);
    }

    /**
     * 验证小时是否在0-24之间
     * @param hour
     * @return
     */
    private boolean isHourInValid(int hour) {
        return hour < 0 || hour > 24;
    }


}
