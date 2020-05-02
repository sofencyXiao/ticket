package com.sofency.ticket.controller;
import com.sofency.ticket.dto.*;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.mapper.StudentMapper;
import com.sofency.ticket.pojo.*;
import com.sofency.ticket.service.CommunityService;
import com.sofency.ticket.service.GrabTicketService;
import com.sofency.ticket.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author sofency
 * @date 2020/4/16 8:54
 * @package IntelliJ IDEA
 * @description   抢票的控制类  返回json 对象
 */
@RestController
public class GrabTicketController {

    private GrabTicketService grabTicketService;
    private StudentService studentService;
    @Autowired
    public GrabTicketController(GrabTicketService grabTicketService,
                                StudentService studentService) {
        this.grabTicketService = grabTicketService;
        this.studentService = studentService;
    }

    //发起抢票活动
    @RequestMapping("/sendGrabTicket")
    public ResultMsg sendGrab(GrabTicket grabTicket){
        int tickets = grabTicket.getActivityTicket();//获取要发布的票数
        grabTicket.setActivityTicketLevel(tickets);//设置剩余票的数量
        //活动的状态置为1; 表示还没过期
        grabTicket.setStatus((byte) 1);
        //开始插入到数据库
        ResultMsg resultMsg = grabTicketService.grabTicket(grabTicket);
        return resultMsg;
    }

    //退票的活动
    @RequestMapping("/backTicket")
    public ResultMsg backTicket(BackTicketDTO backTicketDTO){
        //退票活动
        String studentId = backTicketDTO.getStudentId();//获取学生的抢票活动
        int grabId = backTicketDTO.getGrabId();//获取抢票活动的id
        //退票的业务
        ResultMsg resultMsg = grabTicketService.backTicket(studentId, grabId);
        return resultMsg;
    }

    //根据抢票活动的id查询活动的详情
    @RequestMapping("/getInfoById")
    public WapGrabInfoDTO getDetailInfo(int grabId, HttpSession session){
        WapGrabInfoDTO wapGrabInfoDTO = new WapGrabInfoDTO();//整体返回的数据包括状态
        String studentId = (String) session.getAttribute("studentId");//从会话中拿取学号
        GrabInfoDTO grabInfoDTO = grabTicketService.getGrabInfoDTO(studentId, grabId);
        ResultMsg resultMsg = new ResultMsg();
        if(grabInfoDTO==null){
            resultMsg.setMsg(Code.SEARCH_FAIL.getMessage());
            resultMsg.setStatus(Code.SEARCH_FAIL.getCode());
            wapGrabInfoDTO.setGrabInfoDTO(null);
        }else{
            resultMsg.setMsg(Code.SEARCH_SUCCESS.getMessage());
            resultMsg.setStatus(Code.SEARCH_SUCCESS.getCode());
            wapGrabInfoDTO.setGrabInfoDTO(grabInfoDTO);
        }
        wapGrabInfoDTO.setResultMsg(resultMsg);
        return wapGrabInfoDTO;
    }


    //获取所有的抢票活动
    @RequestMapping("/getAllGrabActivity")
    public WapGetGrabActivityDTO  getGrabActivityDTOS(){
        WapGetGrabActivityDTO wapGetGrabActivityDTO = new WapGetGrabActivityDTO();
        List<GetGrabActivityDTO> allGrabDTO = grabTicketService.getGrabTicketList();
        ResultMsg resultMsg = new ResultMsg();
        if(allGrabDTO==null){
            resultMsg.setStatus(Code.SEARCH_FAIL.getCode());
            resultMsg.setMsg(Code.SEARCH_FAIL.getMessage());
            wapGetGrabActivityDTO.setGetGrabActivityDTO(null);
        }else{
            resultMsg.setStatus(Code.SEARCH_SUCCESS.getCode());
            resultMsg.setMsg(Code.SEARCH_SUCCESS.getMessage());
            wapGetGrabActivityDTO.setGetGrabActivityDTO(allGrabDTO);
        }
        wapGetGrabActivityDTO.setResultMsg(resultMsg);
        return wapGetGrabActivityDTO;
    }

    //开始抢票
    @RequestMapping("/startGrabTicket")
    public ResultMsg startGrabTicket(int  grabId){
        return  null;
    }
}
