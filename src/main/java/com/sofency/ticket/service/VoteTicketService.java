package com.sofency.ticket.service;

import com.sofency.ticket.dto.*;
import java.util.List;

/**
 * @author sofency
 * @date 2020/4/27 21:39
 * @package IntelliJ IDEA
 * @description
 */
public interface VoteTicketService {
    //发起投票活动
    public ResultMsg voteTicket(VoteActivity voteActivity);

    //获取可以投票的活动  对于查询类的数据放到缓存中
    public List<GetVoteActivityDTO> getVoteActivityDTOS();

    //根据活动的编号获取所要投票的情况
    public VoteInfoDTO getVoteInfo(int activityId);

    public ResultMsg vote(int activityId,String voteStuId,int actorId);
}
