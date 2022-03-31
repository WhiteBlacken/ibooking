package com.huawei.ibooking.controller;

import com.huawei.ibooking.pojo.Student;
import com.huawei.ibooking.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author qxy
 * @Date 2022/3/25 17:02
 * @Version 1.0
 */
@Api(tags = "学生管理-管理员")
@RestController
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "获取所有的学生")
    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentService.list();
    }
}
