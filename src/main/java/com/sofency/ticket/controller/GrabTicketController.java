package com.sofency.ticket.controller;
import com.sofency.ticket.dto.BackTicketDTO;
import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.pojo.GrabTicket;
import com.sofency.ticket.service.GrabTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sofency
 * @date 2020/4/16 8:54
 * @package IntelliJ IDEA
 * @description   抢票的控制类  返回json 对象
 */
@RestController
public class GrabTicketController {

    private GrabTicketService grabTicketService;
    @Autowired
    public GrabTicketController(GrabTicketService grabTicketService) {
        this.grabTicketService = grabTicketService;
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
}
