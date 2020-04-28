package com.sofency.ticket.service;

import com.alibaba.fastjson.JSONObject;
import com.sofency.ticket.dto.GetVoteActivityDTO;
import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.dto.VoteActivity;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.mapper.ActorMapper;
import com.sofency.ticket.mapper.StudentMapper;
import com.sofency.ticket.mapper.VoteTicketMapper;
import com.sofency.ticket.pojo.Actor;
import com.sofency.ticket.pojo.Student;
import com.sofency.ticket.pojo.VoteTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sofency
 * @date 2020/4/27 21:39
 * @package IntelliJ IDEA
 * @description
 */
@Service
public class VoteTicketService {

    private VoteTicketMapper voteTicketMapper;
    private ActorMapper actorMapper;
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    public VoteTicketService(VoteTicketMapper voteTicketMapper,ActorMapper actorMapper,
                             RedisTemplate<String,Object> redisTemplate) {
        this.voteTicketMapper = voteTicketMapper;
        this.actorMapper=actorMapper;
        this.redisTemplate = redisTemplate;
    }

    //发起投票活动
    @Transactional(rollbackFor = {Exception.class})
    public ResultMsg voteTicket(VoteActivity voteActivity){

        ResultMsg resultMsg = new ResultMsg();
        try {
            List<Actor> actors = voteActivity.getActors();//获取所有参与的学生
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
                redisTemplate.opsForValue().set("voteTicket::"+insert,voteTicketJson,lastTime);

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
        return null;
    }

}
