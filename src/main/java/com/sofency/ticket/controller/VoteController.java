package com.sofency.ticket.controller;

import com.sofency.ticket.dto.*;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.pojo.Actor;
import com.sofency.ticket.service.VoteTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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


    //获取所有的投票的活动
    @RequestMapping("/getAllVoteActivity")
    public WapGetVoteActivityDTO  getVoteActivityDTOS(){
        WapGetVoteActivityDTO wapGetVoteActivityDTO = new WapGetVoteActivityDTO();
        List<GetVoteActivityDTO> voteActivityDTOS = voteTicketService.getVoteActivityDTOS();
        ResultMsg resultMsg = new ResultMsg();
        if(voteActivityDTOS==null){
            resultMsg.setMsg(Code.GET_VOTE_FAIL.getMessage());
            resultMsg.setStatus(Code.GET_VOTE_FAIL.getCode());
            wapGetVoteActivityDTO.setGetVoteActivityDTOS(null);
        }else{
            resultMsg.setMsg(Code.GET_VOTE_SUCCESS.getMessage());
            resultMsg.setStatus(Code.GET_VOTE_SUCCESS.getCode());
            wapGetVoteActivityDTO.setGetVoteActivityDTOS(voteActivityDTOS);
        }
        wapGetVoteActivityDTO.setResultMsg(resultMsg);
        return wapGetVoteActivityDTO;
    }

    //根据活动的id活动所有参与活动的成员
    @RequestMapping("/getVoteById")
    public List<VoteInfoDTO> getVoteById(int activityId){
        return null;
    }


    //开始投票
    @RequestMapping("/vote")
    public ResultMsg voteToStudentID(VoteActivityByIdDTO voteActivityByIdDTO){
        return null;
    }


    //投票结束的信息
    //统计所有参赛人员的票数
    @RequestMapping("/voteEnd")
    public List<IsVotingDTO> voteEnd(IdAndCommunityId ddAndCommunityId){
        return null;
    }
}
