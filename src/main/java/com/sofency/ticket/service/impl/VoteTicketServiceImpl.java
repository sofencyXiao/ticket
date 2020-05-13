package com.sofency.ticket.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sofency.ticket.dto.*;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.enums.Constants;
import com.sofency.ticket.mapper.ActorMapper;
import com.sofency.ticket.mapper.VoteStudentMapper;
import com.sofency.ticket.mapper.VoteTicketMapper;
import com.sofency.ticket.pojo.Actor;
import com.sofency.ticket.pojo.ActorExample;
import com.sofency.ticket.pojo.VoteStudent;
import com.sofency.ticket.pojo.VoteTicket;
import com.sofency.ticket.service.VoteTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sofency
 * @date 2020/4/27 21:39
 * @package IntelliJ IDEA
 * @description
 */
public class VoteTicketServiceImpl implements VoteTicketService {

    private VoteTicketMapper voteTicketMapper;
    private ActorMapper actorMapper;
    private VoteStudentMapper voteStudentMapper;
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    public VoteTicketServiceImpl(VoteTicketMapper voteTicketMapper, ActorMapper actorMapper,
                                 RedisTemplate<String,Object> redisTemplate,
                                 VoteStudentMapper voteStudentMapper) {
        this.voteTicketMapper = voteTicketMapper;
        this.actorMapper=actorMapper;
        this.redisTemplate = redisTemplate;
        this.voteStudentMapper = voteStudentMapper;
    }

    //发起投票活动
    @Transactional(rollbackFor = {Exception.class})
    public ResultMsg voteTicket(VoteActivity voteActivity){
        ResultMsg resultMsg = new ResultMsg();
        try {
            //获取所有参与的学生
            List<Actor> actors = voteActivity.getActors();
            VoteTicket voteTicket = new VoteTicket();
            voteTicket.setActivityName(voteActivity.getActivityName());
            voteTicket.setBeginTime(voteActivity.getBeginTime());
            voteTicket.setEndTime(voteActivity.getEndTime());

            int insert = voteTicketMapper.insert(voteTicket);//返回插入的结果
            if(insert>0){
                //如果出现没有插入的同学再插一边
                actors.stream().forEach(actor -> {
                    actor.setActivityId(insert);
                    actorMapper.insert(actor);
                });
                //存储到缓存中
                //将存储的结构序列化处理并且存储到缓存中
                String voteTicketJson = JSONObject.toJSONString(voteActivity);
                //设置截止时间
                Long lastTime = voteActivity.getEndTime().getTime()-System.currentTimeMillis();
                redisTemplate.opsForValue().set(Constants.VOTE_TICKET +insert,voteTicketJson,lastTime);

                resultMsg.setMsg(Code.SEND_VOTE_SUCCESS.getMessage());
                resultMsg.setStatus(Code.SEND_VOTE_SUCCESS.getCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        resultMsg.setMsg(Code.SEND_VOTE_FAIL.getMessage());
        resultMsg.setStatus(Code.SEND_VOTE_FAIL.getCode());
        return resultMsg;
    }

    //获取可以投票的活动  对于查询类的数据放到缓存中
    public List<GetVoteActivityDTO> getVoteActivityDTOS(){
        Set<String> keys = redisTemplate.keys(Constants.VOTE_TICKET +"*");
        List<GetVoteActivityDTO> list = new ArrayList<>();
        for(String  key:keys){
            VoteTicket voteTicket = JSON.parseObject(String.valueOf(redisTemplate.opsForValue().get(key)),VoteTicket.class);
            GetVoteActivityDTO getVoteActivityDTO = new GetVoteActivityDTO();
            getVoteActivityDTO.setActivityId(voteTicket.getActivityId());
            getVoteActivityDTO.setActivityName(voteTicket.getActivityName());
            list.add(getVoteActivityDTO);
        }
        return list;
    }

    //根据活动的编号获取所要投票的情况
    public VoteInfoDTO getVoteInfo(int activityId){
        VoteTicket voteTicket = JSON.parseObject(String.valueOf(redisTemplate.opsForValue().get(Constants.VOTE_TICKET +activityId)),
                VoteTicket.class);
        VoteInfoDTO voteInfoDTO = new VoteInfoDTO();
        voteInfoDTO.setEndTime(voteTicket.getEndTime());//设置过期的时间

        ActorExample example = new ActorExample();
        example.createCriteria().andActivityIdEqualTo(activityId);

        List<Actor> actors = actorMapper.selectByExample(example);
        List<ActorInfo> list = new ArrayList<>();
        AtomicInteger cnt= new AtomicInteger();
        actors.stream().forEach(actor -> {
            ActorInfo actorInfo = new ActorInfo();
            actorInfo.setActorId(cnt.getAndIncrement());
            actorInfo.setName(actor.getName());
            actorInfo.setTickets(actor.getGetVoted());
            list.add(actorInfo);
        });
        voteInfoDTO.setActorInfoList(list);
        return voteInfoDTO;
    }


    /**
     *  private int activityId;//活动号
     *  private String voteStuId;//投票人的学号
     *  private String isVotedStuId;//被投票人的学号
     * @return
     */
    @Transactional
    public ResultMsg vote(int activityId,String voteStuId,int actorId){
        //业务逻辑
        //被投票人的获票数量增加  注意使用数据库的行级锁
        ResultMsg resultMsg = new ResultMsg();
        //搜索被投票人的编号 写入到voteStudent表中 增加投票的数量
        int i = actorMapper.addVoted(actorId);//返回值表示插入影响的行数

        if(i>0){
            VoteStudent voteStudent = new VoteStudent();
            voteStudent.setActivityId(activityId);
            voteStudent.setActorId(actorId);
            voteStudent.setGmtCreate(new Date(System.currentTimeMillis()));
            voteStudent.setStudentId(voteStuId);
            int insert = voteStudentMapper.insert(voteStudent);
            if(insert>0){
                resultMsg.setStatus(Code.VOTE_SUCCESS.getCode());
                resultMsg.setMsg(Code.VOTE_SUCCESS.getMessage());
                return resultMsg;
            }
        }
        resultMsg.setStatus(Code.VOTE_FAIL.getCode());
        resultMsg.setMsg(Code.VOTE_FAIL.getMessage());
        return resultMsg;
    }
}
