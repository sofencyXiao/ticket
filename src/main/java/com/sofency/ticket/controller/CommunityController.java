package com.sofency.ticket.controller;

import com.sofency.ticket.dto.ActivityInfoDTO;
import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.pojo.Community;
import com.sofency.ticket.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sofency
 * @date 2020/4/27 20:43
 * @package IntelliJ IDEA
 * @description
 */
@RestController
public class CommunityController {
    CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @RequestMapping("/login")
    public ResultMsg login(Community community){
       ResultMsg resultMsg = communityService.login(community);
       return resultMsg;
    }

    //注册社团的接口
    @RequestMapping("/registerCommunity")
    public ResultMsg registerOrChange(Community community){
        ResultMsg register = communityService.registerOrChange(community);
        return register;
    }

    //根据社团的id 获取所创办的活动
    @RequestMapping("/getAllActivity")
    public List<ActivityInfoDTO> getActivityInfo(int communityId){
        return null;
    }


}
