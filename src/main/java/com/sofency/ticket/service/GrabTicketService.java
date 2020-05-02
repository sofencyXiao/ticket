package com.sofency.ticket.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sofency.ticket.dto.BackTicketDTO;
import com.sofency.ticket.dto.GetGrabActivityDTO;
import com.sofency.ticket.dto.GrabInfoDTO;
import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.mapper.GrabTicketMapper;
import com.sofency.ticket.mapper.StudentMapper;
import com.sofency.ticket.mapper.TicketMapper;
import com.sofency.ticket.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author sofency
 * @date 2020/4/27 20:18
 * @package IntelliJ IDEA
 * @description
 */
@Service
public class GrabTicketService {

    private GrabTicketMapper grabTicketMapper;
    private TicketMapper ticketMapper;
    private RedisTemplate<String,Object> redisTemplate;
    private StudentMapper studentMapper;
    private GrabTicketService grabTicketService;
    private CommunityService communityService;
    @Autowired
    public GrabTicketService(GrabTicketMapper grabTicketMapper,
                             TicketMapper ticketMapper,RedisTemplate<String,Object> redisTemplate,
                             StudentMapper studentMapper,GrabTicketService grabTicketService,
                             CommunityService communityService) {
        this.grabTicketMapper = grabTicketMapper;
        this.ticketMapper = ticketMapper;
        this.redisTemplate=redisTemplate;
        this.studentMapper = studentMapper;
        this.grabTicketService = grabTicketService;
        this.communityService = communityService;
    }

    //发送抢票的活动
    public ResultMsg grabTicket(GrabTicket grabTicket){
        int result = grabTicketMapper.insert(grabTicket);

        //记录插入的情况
        ResultMsg resultMsg = new ResultMsg();
        if(result>0){
            grabTicket.setGrapId(result);//这是主键
            //将数据序列化处理
            String jsonGrabTicket = JSONObject.toJSONString(grabTicket);
            //设置缓存
            Long lastTime = grabTicket.getBeginTime().getTime()-System.currentTimeMillis();
            redisTemplate.opsForValue().set("grabTicket::"+result,jsonGrabTicket,lastTime);
            redisTemplate.opsForValue().set("grabTickets::"+result,grabTicket.getActivityTicket());

            resultMsg.setMsg(Code.GRAB_SUCCESS.getMessage());
            resultMsg.setStatus(Code.GRAB_SUCCESS.getCode());
        }else{
            resultMsg.setMsg(Code.GRAB_FAIL.getMessage());
            resultMsg.setStatus(Code.GRAB_FAIL.getCode());
        }
        return resultMsg;
    }
    //根据抢票的id获取抢票的信息
    public GrabTicket selectByPrimaryKey(int grabId){
        //从缓存中获取
        GrabTicket grabTicket = JSON.parseObject(String.valueOf(redisTemplate.opsForValue().get("grabTicket::" + grabId)),GrabTicket.class);
        if(grabTicket==null){
            GrabTicket grabTicket1 = grabTicketMapper.selectByPrimaryKey(grabId);
            return grabTicket1;
        }
        return grabTicket;
    }

    //退票的活动
    public ResultMsg backTicket(String studentId,int grabId){

        TicketKey ticketKey = new TicketKey();
        ticketKey.setGrapId(grabId);
        ticketKey.setStuId(studentId);
        ResultMsg resultMsg = new ResultMsg();
        //mybatis 删除成功返回影响的行数
        int i = ticketMapper.deleteByPrimaryKey(ticketKey);

        //并且将剩余的票数加一处理;
        redisTemplate.opsForValue().increment("grabTickets::"+grabId,1);
        //定时任务将票数更新到数据库

        if(i>0){//删除成功
            resultMsg.setMsg(Code.DELETE_SUCCESS.getMessage());
            resultMsg.setStatus(Code.DELETE_SUCCESS.getCode());
        }else{
            resultMsg.setMsg(Code.DELETE_FAIL.getMessage());
            resultMsg.setStatus(Code.CHANGE_FAIL.getCode());
        }
        return resultMsg;
    }

    /**
     *
     * @return 首页可以抢票的活动列表
     */
    public List<GetGrabActivityDTO> getGrabTicketList(){
        try {
            Set<String> keys = redisTemplate.keys("grabTickets::*");//获取所有满足的key
            List<GetGrabActivityDTO> list = new ArrayList<>();
            for (String key : keys){
                GetGrabActivityDTO getGrabActivityDTO = new GetGrabActivityDTO();
                GrabTicket grabTicket = (GrabTicket) redisTemplate.opsForValue().get(key);
                getGrabActivityDTO.setActivityName(grabTicket.getActivityName());
                getGrabActivityDTO.setGrabId(grabTicket.getGrapId());
                list.add(getGrabActivityDTO);
            }
            return list;
        }catch (Exception e){
            System.out.println("grabTicketService出现错误");
            e.printStackTrace();
            return null;
        }
    }


    //根据id获取票的详细信息
    public GrabInfoDTO getGrabInfoDTO(String studentId,int grabId){
        try{
            GrabInfoDTO grabInfoDTO = new GrabInfoDTO();
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andStudentIdEqualTo(studentId);
            List<Student> students = studentMapper.selectByExample(studentExample);
            grabInfoDTO.setStudent(students.get(0));//储存学生的信息

            GrabTicket grabTicket = grabTicketService.selectByPrimaryKey(grabId);//根据主键获取抢票的活动信息
            grabInfoDTO.setGrabId(grabTicket.getGrapId());//储存抢票号
            grabInfoDTO.setActivityName(grabTicket.getActivityName());//储存活动的名字
            grabInfoDTO.setEndTime(grabTicket.getBeginTime());//设置截止时间
            grabInfoDTO.setTicketLevel(grabTicket.getActivityTicketLevel());//待优化

            int communityId = grabTicket.getCommunityId();
            Community communityById = communityService.getCommunityById(communityId);
            grabInfoDTO.setCommunityName(communityById.getCommunityName());//设置社团的名字
            return grabInfoDTO;
        }catch (Exception e){
            System.out.println("grabTicketService出现错误");
            e.printStackTrace();
            return null;
        }
    }
}
