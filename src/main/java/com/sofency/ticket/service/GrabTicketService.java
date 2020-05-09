package com.sofency.ticket.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sofency.ticket.dto.*;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.enums.Constants;
import com.sofency.ticket.enums.Status;
import com.sofency.ticket.mapper.GrabTicketMapper;
import com.sofency.ticket.mapper.StudentMapper;
import com.sofency.ticket.mapper.TicketMapper;
import com.sofency.ticket.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
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
    private CommunityService communityService;
    @Autowired
    public GrabTicketService(GrabTicketMapper grabTicketMapper,
                             TicketMapper ticketMapper,RedisTemplate<String,Object> redisTemplate,
                             StudentMapper studentMapper,
                             CommunityService communityService) {
        this.grabTicketMapper = grabTicketMapper;
        this.ticketMapper = ticketMapper;
        this.redisTemplate=redisTemplate;
        this.studentMapper = studentMapper;
        this.communityService = communityService;
    }

    //发送抢票的活动 并且根据结束的时间存储到缓存中
    public ResultMsg grabTicket(GrabTicket grabTicket){
        int result = grabTicketMapper.insert(grabTicket);
        //记录插入的情况
        ResultMsg resultMsg = new ResultMsg();
        if(result>0){
            grabTicket.setGrapId(result);//这是主键
            //将数据序列化处理
            String jsonGrabTicket = JSONObject.toJSONString(grabTicket);
            //缓存有效的时间
            Long lastTime = grabTicket.getBeginTime().getTime()-System.currentTimeMillis();
            //从缓存中设置活动的有效期
            redisTemplate.opsForValue().set(Constants.GRAB_TICKET+result,jsonGrabTicket,lastTime);
            //设置票数到缓存
            redisTemplate.opsForValue().set(Constants.GRAB_TICKETS+result,grabTicket.getActivityTicket(),lastTime);

            //封装结果
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
        GrabTicket grabTicket = JSON.parseObject(String.valueOf(redisTemplate.opsForValue().get(Constants.GRAB_TICKET + grabId)),GrabTicket.class);
        return grabTicket;
    }

    //退票的活动
    public ResultMsg backTicket(String studentId,int grabId){
        TicketKey ticketKey = new TicketKey();
        ticketKey.setGrapId(grabId);
        ticketKey.setStuId(studentId);
        ResultMsg resultMsg = new ResultMsg();
        //mybatis 删除成功返回影响的行数
        int i = ticketMapper.deleteByPrimaryKey(ticketKey);//删除购票的行为
        //定时任务将票数更新到数据库
        if(i>0){//删除成功
            //直接更新到数据库
            int i1 = grabTicketMapper.addTicket(grabId);//数据库的行级锁控制
            if(i1>0){//判断影响的行数  如果大于0的话就说明更新成功
                resultMsg.setMsg(Code.DELETE_SUCCESS.getMessage());
                resultMsg.setStatus(Code.DELETE_SUCCESS.getCode());
                //并且将剩余的票数加一处理;
                redisTemplate.opsForValue().increment(Constants.GRAB_TICKETS +grabId,1);
            }else{
                resultMsg.setMsg(Code.DELETE_FAIL.getMessage());
                resultMsg.setStatus(Code.CHANGE_FAIL.getCode());
            }
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

            GrabTicket grabTicket = grabTicketMapper.selectByPrimaryKey(grabId);//根据主键获取抢票的活动信息
            grabInfoDTO.setGrabId(grabTicket.getGrapId());//储存抢票号
            grabInfoDTO.setBeginTime(grabTicket.getBeginTime());//储存开始时间
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

    //开始抢票
    @Transactional
    public  ResultMsg grabTicket(int grabId,String studentId){
        ResultMsg resultMsg = new ResultMsg();
        //首先查找该用户是否已经抢过票
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andGrapIdEqualTo(grabId).andStuIdEqualTo(studentId);
        List<Ticket> tickets = ticketMapper.selectByExample(ticketExample);
        //如果没有抢过票则可以进行抢票的操作
        if(tickets.size()==0){
//          封装出票的信息
            Ticket ticket = new Ticket();
            ticket.setGrapId(grabId);
            ticket.setStuId(studentId);
            ticket.setBuyTime(new Date(System.currentTimeMillis()));

            try{
                //从redis中拿取数据
                Long ticket1 = redisTemplate.opsForValue().increment(Constants.GRAB_TICKETS +grabId, -1);//库存减1
                if(ticket1>0){
                    //数据库插入票的信息
                    ticketMapper.insert(ticket);//假设插入成功
                    //票的库存减1
                    grabTicketMapper.decrTicket(grabId);
                    resultMsg.setStatus(Code.CAN_BUY.getCode());
                    resultMsg.setMsg(Code.CAN_BUY.getMessage());
                }else{
                    resultMsg.setStatus(Code.CAN_NOT_BUY.getCode());
                    resultMsg.setMsg(Code.CAN_NOT_BUY.getMessage());
                }
            }catch (Exception e){
                redisTemplate.opsForValue().increment(Constants.GRAB_TICKETS +grabId,1);
                resultMsg.setStatus(Code.EXCEPTION_UN_KNOW.getCode());
                resultMsg.setMsg(Code.EXCEPTION_UN_KNOW.getMessage());
                e.printStackTrace();
            }
        }else{
            resultMsg.setStatus(Code.HAS_BUY.getCode());
            resultMsg.setMsg(Code.HAS_BUY.getMessage());
        }
        return resultMsg;
    }
}
