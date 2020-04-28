package com.sofency.ticket.controller;

import com.sofency.ticket.dto.BackTicketDTO;
import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.dto.VoteActivity;
import com.sofency.ticket.service.VoteTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author sofency
 * @date 2020/4/16 8:54
 * @package IntelliJ IDEA
 * @description  投票的控制类
 */
@RestController
public class VoteController {
    private VoteTicketService voteTicketService;
    @Autowired
    public VoteController(VoteTicketService voteTicketService) {
        this.voteTicketService = voteTicketService;
    }

    //发起投票活动
    @RequestMapping("/sendVoteTicket")
    public ResultMsg vote(VoteActivity voteActivity){
        ResultMsg resultMsg = voteTicketService.voteTicket(voteActivity);
        return resultMsg;
    }
}
