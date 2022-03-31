package com.huawei.ibooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huawei.ibooking.pojo.StudyRoom;
import com.huawei.ibooking.pojo.other.RespPageBean;
import com.huawei.ibooking.pojo.other.StudyRoomCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author qxy
 * @Date 2022/3/25 20:53
 * @Version 1.0
 */
public interface StudyRoomMapper extends BaseMapper<StudyRoom> {
    /**
     * 查询符合条件的StudyRoom
     * @param condition
     * @return
     */
    //在sql语句执行之前去获取，并改为分页查询的sql语句
    IPage<StudyRoom> getStudyRoomsByConditions(Page<StudyRoom> page, @Param("condition") StudyRoomCondition condition);
}
