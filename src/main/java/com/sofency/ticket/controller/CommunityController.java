package com.sofency.ticket.controller;

import com.sofency.ticket.dto.ActivityInfoDTO;
import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.dto.WapActivityInfoDTO;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.exception.UnKnowException;
import com.sofency.ticket.pojo.*;
import com.sofency.ticket.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    public ResultMsg login(Community community, HttpSession session){

        ResultMsg resultMsg = new ResultMsg();
        if(community.getCommunityAccount()==null&&community.getPassword()==null){
            resultMsg.setStatus(Code.EXCEPTION_UN_KNOW.getCode());
            resultMsg.setMsg(Code.EXCEPTION_UN_KNOW.getMessage());
            throw new UnKnowException(resultMsg);
        }
        Community community1 = communityService.getCommunityByAccount(community.getCommunityAccount());
        session.setAttribute("communityId",community1.getCommunityId());
        resultMsg = communityService.login(community);
        return resultMsg;
    }

    //注册社团的接口
    @RequestMapping("/registerCommunity")
    public ResultMsg registerOrChange(Community community){
        ResultMsg register = communityService.registerOrChange(community);
        return register;
    }

    //根据社团的账户 获取所创办的活动
    @RequestMapping("/getAllActivity")
    public WapActivityInfoDTO getActivityInfo(String communityAccount){
        WapActivityInfoDTO wapActivityInfoDTO = new WapActivityInfoDTO();
        List<ActivityInfoDTO> activity = communityService.getActivity(communityAccount);
        ResultMsg resultMsg = new ResultMsg();
        if(activity==null){
            wapActivityInfoDTO.setActivityInfoDTOS(null);
            resultMsg.setStatus(Code.SEARCH_FAIL.getCode());
            resultMsg.setMsg(Code.SEARCH_FAIL.getMessage());
        }else{
            wapActivityInfoDTO.setActivityInfoDTOS(activity);
            resultMsg.setStatus(Code.SEARCH_SUCCESS.getCode());
            resultMsg.setMsg(Code.SEARCH_SUCCESS.getMessage());
        }
        wapActivityInfoDTO.setResultMsg(resultMsg);
        return wapActivityInfoDTO;
    }
}
