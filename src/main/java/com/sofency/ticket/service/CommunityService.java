package com.sofency.ticket.service;

import com.sofency.ticket.dto.ActivityInfoDTO;
import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.pojo.*;

import java.util.List;

/**
 * @author sofency
 * @date 2020/4/19 8:16
 * @package IntelliJ IDEA
 * @description
 */

public interface CommunityService {

    //社团的登录  主要的逻辑就是 根据提交的账户的密码,然后根据账户获取数据库里面的密码和现在的密码进行比较
    public ResultMsg login(Community community);

    //注册社团
    public ResultMsg registerOrChange(Community community);

    //根据社团账户找到在注册的社团中
    public Community getCommunityByAccount(String account);
    //根据活动号查询活动的信息
    public Community getCommunityById(int communityId);

    //获取社团所创办的投票和抢票的活动
    public List<ActivityInfoDTO> getActivity(String communityAccount);
}
