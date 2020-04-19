package com.sofency.ticket.service;

import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.mapper.CommunityMapper;
import com.sofency.ticket.pojo.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sofency
 * @date 2020/4/19 8:16
 * @package IntelliJ IDEA
 * @description
 */

@Service
public class CommunityService {
    private CommunityMapper communityMapper;
    @Autowired
    public CommunityService(CommunityMapper communityMapper){
        this.communityMapper = communityMapper;
    }

    public ResultMsg register(Community community){
        String account = String.valueOf(System.currentTimeMillis());
        community.setCommunityAccount(account);
        int result = communityMapper.insert(community);
        ResultMsg resultMsg = new ResultMsg();
        if(result<=0){
            resultMsg.setMsg("注册失败");
            resultMsg.setStatus(404);
        }else{
            resultMsg.setMsg("注册成功");
            resultMsg.setStatus(200);
            resultMsg.setCommunityAccount(account);
        }
        return resultMsg;
    }
}
