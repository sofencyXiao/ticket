package com.sofency.ticket.dto;

import com.sofency.ticket.pojo.Actor;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author sofency
 * @date 2020/4/29 20:21
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class IsVotingDTO {
    private String activityName;//活动名字
    private Date beginTime;//活动开始时间
    private Date endTime;//活动结束时间
    private int voteNum;//参与投票人数
    private List<Actor> actors;//所有参赛活动的人员的信息
}
