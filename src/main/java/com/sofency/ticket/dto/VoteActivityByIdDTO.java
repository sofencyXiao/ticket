package com.sofency.ticket.dto;

import lombok.Data;

/**
 * @author sofency
 * @date 2020/4/29 18:36
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class VoteActivityByIdDTO {
    private int activityId;//活动号
    private String voteStuId;//投票人的学号
    private String isVotedStuId;//被投票人的学号
}
