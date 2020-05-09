package com.sofency.ticket.controller;

import com.sofency.ticket.dto.*;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.mapper.ActorMapper;
import com.sofency.ticket.pojo.Actor;
import com.sofency.ticket.pojo.ActorExample;
import com.sofency.ticket.service.VoteTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
    private ActorMapper actorMapper;
    @Autowired
    public VoteController(VoteTicketService voteTicketService,
                          ActorMapper actorMapper) {
        this.voteTicketService = voteTicketService;
        this.actorMapper = actorMapper;
    }

    //发起投票活动
    @RequestMapping("/sendVoteTicket")
    public ResultMsg vote(VoteActivity voteActivity, HttpSession session){
        Integer communityId = (Integer) session.getAttribute("communityId");
        //更该 从session里面获取社团的id
        voteActivity.setCommunityId(communityId);
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
    public WapVoteInfoDTO getVoteById(int activityId){
        WapVoteInfoDTO wapVoteInfoDTO = new WapVoteInfoDTO();
        //获取活动的信息
        VoteInfoDTO voteInfo = voteTicketService.getVoteInfo(activityId);
        ResultMsg resultMsg = new ResultMsg();
        if(voteInfo==null){
            resultMsg.setStatus(Code.GET_VOTE_FAIL.getCode());
            resultMsg.setMsg(Code.GET_VOTE_FAIL.getMessage());
            wapVoteInfoDTO.setVoteInfoDTO(null);
        }else {
            resultMsg.setStatus(Code.GET_VOTE_SUCCESS.getCode());
            resultMsg.setMsg(Code.GET_VOTE_SUCCESS.getMessage());
            wapVoteInfoDTO.setVoteInfoDTO(voteInfo);
        }
        wapVoteInfoDTO.setResultMsg(resultMsg);
        return wapVoteInfoDTO;
    }

    //开始投票
    @RequestMapping("/vote")
    public ResultMsg voteToStudentID(VoteActivityByIdDTO voteActivityByIdDTO){
        Integer activityId = voteActivityByIdDTO.getActivityId();//活动号
        String voteStuId = voteActivityByIdDTO.getVoteStuId();//投票人的学号
        String isVoteStuId = voteActivityByIdDTO.getIsVotedStuId();//被投票人的学号
        ActorExample actorExample = new ActorExample();
        actorExample.createCriteria().andStuIdEqualTo(isVoteStuId);
        List<Actor> actors = actorMapper.selectByExample(actorExample);
        ResultMsg resultMsg = new ResultMsg();
        if(actors.size()!=0){
            resultMsg = voteTicketService.vote(activityId, voteStuId, actors.get(0).getActorId());
            return resultMsg;
        }
        resultMsg.setStatus(Code.VOTE_FAIL.getCode());
        resultMsg.setMsg(Code.VOTE_FAIL.getMessage());
        return resultMsg;
    }
}
