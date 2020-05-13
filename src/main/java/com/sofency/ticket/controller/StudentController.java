package com.sofency.ticket.controller;

import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.dto.StudentDTO;
import com.sofency.ticket.dto.WapStudentListDTO;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.pojo.Community;
import com.sofency.ticket.pojo.GrabTicket;
import com.sofency.ticket.pojo.Student;
import com.sofency.ticket.service.CommunityService;
import com.sofency.ticket.service.impl.GrabTicketServiceImpl;
import com.sofency.ticket.service.impl.StudentServiceImpl;
import com.sofency.ticket.service.impl.TicketServiceImpl;
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
    StudentServiceImpl studentService;
    TicketServiceImpl ticketService;
    CommunityService communityService;
    GrabTicketServiceImpl grabTicketService;
    @Autowired
    public StudentController(StudentServiceImpl studentService, TicketServiceImpl ticketService,
                             CommunityService communityService,
                             GrabTicketServiceImpl grabTicketService) {

        this.studentService = studentService;
        this.ticketService = ticketService;
        this.communityService= communityService;
        this.grabTicketService = grabTicketService;
    }

    //加载用户的信息 如果没有用户的信息 提醒用户进行注册信息
    @RequestMapping("/getInfo")
    public WapStudentListDTO getInfo(String openId, HttpSession session){
        WapStudentListDTO wapStudentListDTO = new WapStudentListDTO();
        ResultMsg resultMsg = new ResultMsg();
        try {
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
            wapStudentListDTO.setStudentDTOList(list);
            resultMsg.setMsg(Code.SEARCH_SUCCESS.getMessage());
            resultMsg.setStatus(Code.GRAB_SUCCESS.getCode());
        }catch (Exception e){
            resultMsg.setMsg(Code.SEARCH_FAIL.getMessage());
            resultMsg.setStatus(Code.SEARCH_FAIL.getCode());
            wapStudentListDTO.setStudentDTOList(null);
            System.out.println("出现错误在StudentController");
            e.printStackTrace();
        }
        wapStudentListDTO.setResultMsg(resultMsg);
        return wapStudentListDTO;
    }

    //学生的注册
    @RequestMapping("/registerStudent")
    public ResultMsg registerStudent(Student student){
        ResultMsg register = studentService.register(student);
        return register;
    }
}
