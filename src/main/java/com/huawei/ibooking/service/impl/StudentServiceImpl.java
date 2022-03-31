package com.huawei.ibooking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huawei.ibooking.mapper.StudentMapper;
import com.huawei.ibooking.pojo.Student;
import com.huawei.ibooking.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author qxy
 * @Date 2022/3/25 17:05
 * @Version 1.0
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {


}