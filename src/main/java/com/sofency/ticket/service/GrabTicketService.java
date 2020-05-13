package com.sofency.ticket.service;

import com.sofency.ticket.dto.GetGrabActivityDTO;
import com.sofency.ticket.dto.GrabInfoDTO;
import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.pojo.*;
import java.util.List;

/**
 * @author sofency
 * @date 2020/4/27 20:18
 * @package IntelliJ IDEA
 * @description
 */
public interface GrabTicketService {

    //发送抢票的活动 并且根据结束的时间存储到缓存中
    public ResultMsg grabTicket(GrabTicket grabTicket);

    //根据抢票的id获取抢票的信息
    public GrabTicket selectByPrimaryKey(int grabId);

    //退票的活动
    public ResultMsg backTicket(String studentId,int grabId);

    /**
     *
     * @return 首页可以抢票的活动列表
     */
    public List<GetGrabActivityDTO> getGrabTicketList();
    //根据id获取票的详细信息
    public GrabInfoDTO getGrabInfoDTO(String studentId,int grabId);

    //开始抢票
    public  ResultMsg grabTicket(int grabId,String studentId);
}
