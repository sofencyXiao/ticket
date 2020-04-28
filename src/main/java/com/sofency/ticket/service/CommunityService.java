package com.sofency.ticket.service;

import com.alibaba.fastjson.JSONObject;
import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.mapper.CommunityMapper;
import com.sofency.ticket.pojo.Community;
import com.sofency.ticket.pojo.CommunityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sofency
 * @date 2020/4/19 8:16
 * @package IntelliJ IDEA
 * @description
 */

@Service
public class CommunityService {
    private CommunityMapper communityMapper;
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    public CommunityService(CommunityMapper communityMapper,
                            RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate= redisTemplate;
        this.communityMapper = communityMapper;
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
                redisTemplate.opsForValue().set("community::"+community.getCommunityAccount(),jsonCommunity);
            }
        }else{//说明是修改
            //构建搜索条件
            CommunityExample example = new CommunityExample();
            example.createCriteria().andCommunityAccountEqualTo(communityAccount);
            //搜索账户的信息  update 返回的是受影响的行数
            int i = communityMapper.updateByExampleSelective(community, example);

            if(i>0){//说明修改成功
                redisTemplate.opsForValue().set("community::"+communityAccount,community);
                resultMsg.setMsg(Code.CHANGE_SUCCESS.getMessage());
                resultMsg.setStatus(Code.CHANGE_SUCCESS.getCode());
            }else{//说明修改失败
                resultMsg.setMsg(Code.CHANGE_FAIL.getMessage());
                resultMsg.setStatus(Code.CHANGE_FAIL.getCode());
            }
        }
        return resultMsg;
    }

    //根据社团ID找到在注册的社团中
    public Community getCommunityById(String communityAccount){
        Community community = (Community) redisTemplate.opsForValue().get("community::" + communityAccount);
        if(community==null){
            CommunityExample example = new CommunityExample();
            example.createCriteria().andCommunityAccountEqualTo(communityAccount);
            List<Community> communities = communityMapper.selectByExample(example);
            if(communities!=null&&communities.size()!=0){
                return communities.get(0);
            }
        }
        return community;
    }
}
