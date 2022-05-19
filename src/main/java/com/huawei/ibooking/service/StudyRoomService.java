package com.huawei.ibooking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huawei.ibooking.pojo.StudyRoom;
import com.huawei.ibooking.pojo.other.RespPageBean;
import com.huawei.ibooking.pojo.other.StudyRoomCondition;

import java.util.List;

/**
 * @Author qxy
 * @Date 2022/3/25 20:51
 * @Version 1.0
 */
public interface StudyRoomService extends IService<StudyRoom> {
    /**
     * 根据条件查询studyRoom（分页）
     * @param studyRoomCondition
     * @return
     */
    RespPageBean getStudyRoomsByConditions(Integer page,Integer size,StudyRoomCondition studyRoomCondition);

    /**
     * 获取所有的studyRoom（分页）
     * @param page
     * @param size
     * @return
     */
    RespPageBean getAllStudyRoom(Integer page, Integer size);

    int getCntByBNumAndCNum(String buildingNum, String classRoomNum);
}
