package com.sofency.ticket.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author sofency
 * @date 2020/4/29 19:40
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class GrabDetailDTO {
    private String activityName;//活动名字
    private Date activityStartTime;//活动开始时间
    private Date beginTime;
    private int tickets;//票的发布总数
    private int grabTickets;//发票数量
    private int signIn;//签到人数
    private int signBack;//签退人数
}
