package com.sofency.ticket.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sofency.ticket.dto.ActivityInfoDTO;
import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.mapper.CommunityMapper;
import com.sofency.ticket.mapper.GrabTicketMapper;
import com.sofency.ticket.mapper.VoteTicketMapper;
import com.sofency.ticket.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author sofency
 * @date 2020/4/19 8:16
 * @package IntelliJ IDEA
 * @description
 */

@Service
public class CommunityService {
    private CommunityMapper communityMapper;
    private VoteTicketMapper voteTicketMapper;
    private GrabTicketMapper grabTicketMapper;
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    public CommunityService(CommunityMapper communityMapper,
                            VoteTicketMapper voteTicketMapper,
                            GrabTicketMapper grabTicketMapper,
                            RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate= redisTemplate;
        this.communityMapper = communityMapper;
        this.voteTicketMapper=voteTicketMapper;
        this.grabTicketMapper = grabTicketMapper;
    }

    //社团的登录
    public ResultMsg login(Community community){
        String account = community.getCommunityAccount();//获取社团的账户信息
        String password = community.getPassword();
        ResultMsg resultMsg  = new ResultMsg();

        CommunityExample example = new CommunityExample();
        example.createCriteria().andCommunityAccountEqualTo(account);

        List<Community> communities = communityMapper.selectByExample(example);
        if(communities!=null&&communities.size()!=0){//说明找到了用户的信息
            if(communities.get(0).getPassword().equals(password)){
                resultMsg.setMsg(Code.LOGIN_SUCCESS.getMessage());
                resultMsg.setStatus(Code.LOGIN_SUCCESS.getCode());
            }
        }else{
            resultMsg.setMsg(Code.LOGIN_FAIL.getMessage());
            resultMsg.setStatus(Code.LOGIN_FAIL.getCode());
        }
        return resultMsg;
    }

    //注册社团
    public ResultMsg registerOrChange(Community community){
        String communityAccount = community.getCommunityAccount();
        ResultMsg resultMsg = new ResultMsg();
        if(communityAccount==null||communityAccount==""){//说明是插入
            //随机生成的时间戳账户
            String account = String.valueOf(System.currentTimeMillis());
            community.setCommunityAccount(account);
            int result = communityMapper.insert(community);
            if(result<=0){
                resultMsg.setMsg(Code.REGISTER_FAIL.getMessage());
                resultMsg.setStatus(Code.REGISTER_FAIL.getCode());
            }else{
                resultMsg.setMsg(Code.REGISTER_SUCCESS.getMessage());
                resultMsg.setStatus(Code.REGISTER_SUCCESS.getCode());
                resultMsg.setCommunityAccount(account);
                String jsonCommunity = JSONObject.toJSONString(community);
                //缓存处理
                redisTemplate.opsForValue().set("community::"+result,jsonCommunity);
            }
        }else{//说明是修改 修改的时候填上id
            //构建搜索条件
            CommunityExample example = new CommunityExample();
            example.createCriteria().andCommunityAccountEqualTo(communityAccount);
            //搜索账户的信息  update 返回的是受影响的行数
            int i = communityMapper.updateByExampleSelective(community, example);

            if(i>0){//说明修改成功
                redisTemplate.opsForValue().set("community::"+community.getCommunityId(),community);
                resultMsg.setMsg(Code.CHANGE_SUCCESS.getMessage());
                resultMsg.setStatus(Code.CHANGE_SUCCESS.getCode());
            }else{//说明修改失败
                resultMsg.setMsg(Code.CHANGE_FAIL.getMessage());
                resultMsg.setStatus(Code.CHANGE_FAIL.getCode());
            }
        }
        return resultMsg;
    }

    //根据社团账户找到在注册的社团中
    public Community getCommunityById(int communityId){
        //从缓存中拿取社团的信息
        Community community = JSON.parseObject(String.valueOf(redisTemplate.opsForValue().get("community::" + communityId)),
                Community.class);

        if(community==null){
            Community communities = communityMapper.selectByPrimaryKey(communityId);
            if(communities!=null){
                return communities;
            }
        }
        return community;
    }

    public List<ActivityInfoDTO> getActivity(int communityId){
        //使用读写锁进行操作同一个列表
        List<ActivityInfoDTO> activityInfoDTOS = new CopyOnWriteArrayList<>();//用来存储返回的值
        Long currentDate = System.currentTimeMillis();//记录当前的时间

        //投票的线程
        Callable<Boolean> voteTicketThread = () -> {
            //根据社团的id在投票的活动中找取投票的活动
            try {
                VoteTicketExample voteTicketExample = new VoteTicketExample();
                voteTicketExample.createCriteria().andCommunityIdEqualTo(communityId);
                List<VoteTicket> voteTickets = voteTicketMapper.selectByExample(voteTicketExample);//获取投票的活动
                //流式处理
                voteTickets.stream().forEach(voteTicket -> {
                    ActivityInfoDTO activityInfoDTO = new ActivityInfoDTO();
                    activityInfoDTO.setName(voteTicket.getActivityName());
                    activityInfoDTO.setGrabOrVoteId(voteTicket.getActivityId());
                    boolean flag = voteTicket.getEndTime().getTime() > currentDate ? true : false;//true 标识已经结束
                    activityInfoDTO.setOver(flag);
                    activityInfoDTO.setFlag(true);//true表示是投票 false是抢票
                    activityInfoDTOS.add(activityInfoDTO);
                });
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        };

        //抢票的线程
        Callable<Boolean> grabTicketThread = ()->{
            try {
                //根据社团的id在抢票的活动中找取抢票的活动
                GrabTicketExample grabTicketExample = new GrabTicketExample();
                grabTicketExample.createCriteria().andCommunityIdEqualTo(communityId);
                List<GrabTicket> grabTickets = grabTicketMapper.selectByExample(grabTicketExample);

                grabTickets.stream().forEach(grabTicket -> {
                    ActivityInfoDTO activityInfoDTO = new ActivityInfoDTO();
                    activityInfoDTO.setName(grabTicket.getActivityName());
                    activityInfoDTO.setGrabOrVoteId(grabTicket.getGrapId());
                    boolean flag = grabTicket.getBeginTime().getTime()>currentDate?true:false;//true 标识已经结束
                    activityInfoDTO.setOver(flag);
                    activityInfoDTO.setFlag(false);//true表示是投票 false是抢票
                    activityInfoDTOS.add(activityInfoDTO);
                });
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(2);
        FutureTask<Boolean> voteTicketFutureTask = new FutureTask(voteTicketThread);
        FutureTask<Boolean> grabTicketFutureTask = new FutureTask(grabTicketThread);
        service.submit(voteTicketFutureTask);
        service.submit(grabTicketFutureTask);

        Boolean voteTicketResult;//存储投票查找的结果
        Boolean grabTicketResult;//存储抢票查找的结果
        try {
            voteTicketResult= voteTicketFutureTask.get();
            grabTicketResult = grabTicketFutureTask.get();
            if(voteTicketResult&&grabTicketResult){
                return activityInfoDTOS;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
