package com.sofency.ticket.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.mapper.StudentMapper;
import com.sofency.ticket.pojo.Student;
import com.sofency.ticket.pojo.StudentExample;
import com.sofency.ticket.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sofency
 * @date 2020/4/27 22:39
 * @package IntelliJ IDEA
 * @description
 */
//学生的业务信息
public class StudentServiceImpl implements StudentService {

    private StudentMapper studentMapper;
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, RedisTemplate<String,Object> redisTemplate) {
        this.studentMapper = studentMapper;
        this.redisTemplate=redisTemplate;
    }

    //根据openId获取用户的信息
    public Student getInfoByOpenId(String openId){
        Student student = JSON.parseObject(String.valueOf(redisTemplate.opsForValue().get("student::" + openId)),
                Student.class);
        if(student!=null){
            return student;
        }else{
            StudentExample example = new StudentExample();
            example.createCriteria().andOpenidEqualTo(openId);
            List<Student> students = studentMapper.selectByExample(example);
            if(students!=null&&students.size()!=0){
                return students.get(0);
            }
        }
        return null;
    }

    //注册学生的信息
    public ResultMsg register(Student student){
        //暂时不处理 返回值的情况
        studentMapper.insert(student);
        String jsonStudent = JSONObject.toJSONString(student);
        redisTemplate.opsForValue().set("student::"+student.getOpenid(),jsonStudent);
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setMsg(Code.REGISTER_SUCCESS.getMessage());
        resultMsg.setStatus(Code.REGISTER_SUCCESS.getCode());
        return resultMsg;
    }

}
