package com.sofency.ticket.controller;

import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.mapper.CommunityMapper;
import com.sofency.ticket.pojo.Community;
import com.sofency.ticket.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sofency
 * @date 2020/4/16 8:55
 * @package IntelliJ IDEA
 * @description  社团用户注册的controller类
 */
@RestController
public class RegisterController {
    private CommunityService communityService;
    @Autowired
    public RegisterController(CommunityService communityService){
        this.communityService = communityService;
    }

    //注册社团的接口
    @RequestMapping("/registerCommunity")
    public ResultMsg register(Community community){
        ResultMsg register = communityService.register(community);
        return register;
    }
}
