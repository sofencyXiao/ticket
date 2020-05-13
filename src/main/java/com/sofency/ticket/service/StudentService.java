package com.sofency.ticket.service;

import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.pojo.Student;


/**
 * @author sofency
 * @date 2020/4/27 22:39
 * @package IntelliJ IDEA
 * @description
 */
//学生的业务信息
public interface StudentService {

    //根据openId获取用户的信息
    public Student getInfoByOpenId(String openId);
    //注册学生的信息
    public ResultMsg register(Student student);

}
