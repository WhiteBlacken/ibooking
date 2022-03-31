package com.huawei.ibooking.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huawei.ibooking.mapper.StudyRoomMapper;
import com.huawei.ibooking.pojo.StudyRoom;
import com.huawei.ibooking.pojo.other.RespPageBean;
import com.huawei.ibooking.pojo.other.StudyRoomCondition;
import com.huawei.ibooking.service.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author qxy
 * @Date 2022/3/25 20:52
 * @Version 1.0
 */
@Service
public class StudyRoomServiceImpl extends ServiceImpl<StudyRoomMapper, StudyRoom> implements StudyRoomService {

    @Autowired
    private StudyRoomMapper studyRoomMapper;

    /**
     * 获取所有的studyRoom（分页）
     * @param page
     * @param size
     * @return
     */
    @Override
    public RespPageBean getAllStudyRoom(Integer page, Integer size) {
        //开启分页
        IPage<StudyRoom> roomPage = new Page<>(page,size);
        roomPage = studyRoomMapper.selectPage(roomPage,null);
        return new RespPageBean(roomPage.getRecords().size(),roomPage.getRecords());

    }

    /**
     * 根据条件查询studyRoom（分页）
     * @param studyRoomCondition
     * @return
     */
    @Override
    public RespPageBean getStudyRoomsByConditions(Integer page,Integer size,StudyRoomCondition studyRoomCondition) {
        //开启分页
        Page<StudyRoom> roomPage = new Page<>(page,size);
        IPage<StudyRoom> iPage =  studyRoomMapper.getStudyRoomsByConditions(roomPage,studyRoomCondition);
        return new RespPageBean(iPage.getRecords().size(), iPage.getRecords());
    }
}
