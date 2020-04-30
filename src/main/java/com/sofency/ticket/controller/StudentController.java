package com.sofency.ticket.controller;

import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.dto.StudentDTO;
import com.sofency.ticket.pojo.Community;
import com.sofency.ticket.pojo.GrabTicket;
import com.sofency.ticket.pojo.Student;
import com.sofency.ticket.service.CommunityService;
import com.sofency.ticket.service.GrabTicketService;
import com.sofency.ticket.service.StudentService;
import com.sofency.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sofency
 * @date 2020/4/19 7:16
 * @package IntelliJ IDEA
 * @description  主要获取用户的openId
 */
@ResponseBody  //表明返回值就是json
@Controller  //表明控制类
public class StudentController {
    StudentService studentService;
    TicketService ticketService;
    CommunityService communityService;
    GrabTicketService grabTicketService;
    @Autowired
    public StudentController(StudentService studentService, TicketService ticketService,
                             CommunityService communityService,
                             GrabTicketService grabTicketService) {

        this.studentService = studentService;
        this.ticketService = ticketService;
        this.communityService= communityService;
        this.grabTicketService = grabTicketService;
    }

    //加载用户的信息 如果没有用户的信息 提醒用户进行注册信息
    @RequestMapping("/getInfo")
    public List<StudentDTO> getInfo(String openId, HttpSession session){
        //首先根据OpenId获取用户的信息
        Student student = studentService.getInfoByOpenId(openId);
        String studentId = student.getStudentId();
        //存储到会话中
        session.setAttribute("studentId",studentId);

        String name = student.getName();
        //根据用户的信息操作获取抢到的票的信息
        List<Integer> grabIds = ticketService.getGrabId(studentId);
        List<StudentDTO> list = new ArrayList<>();

        for(Integer grabId:grabIds){
            GrabTicket grabTicket = grabTicketService.selectByPrimaryKey(grabId);
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setGrabId(grabId);
            studentDTO.setStudentId(studentId);
            studentDTO.setActivityName(grabTicket.getActivityName());
            studentDTO.setEndTime(grabTicket.getActivityStartTime());
            studentDTO.setName(name);
            //获取主办方的名字
            Community community = communityService.getCommunityById(grabTicket.getCommunityId());
            studentDTO.setActivityName(community.getCommunityName());
            list.add(studentDTO);
        }
        return list;
    }

    //学生的注册
    @RequestMapping("/registerStudent")
    public ResultMsg registerStudent(Student student){
        return null;
    }




}
