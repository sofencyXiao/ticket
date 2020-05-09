package com.sofency.ticket.dto;

import com.sofency.ticket.pojo.Student;
import lombok.Data;

import java.util.Date;

/**
 * @author sofency
 * @date 2020/4/28 12:28
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class GrabInfoDTO {
    private String activityName;//活动的名字
    private String communityName;//社团名字
    private int ticketLevel;//当前剩余票数
    private Date endTime;//活动剩余时间
    private Date beginTime;//开始时间
    private Student student;//当前学生的信息
    private int grabId;//抢票的ID
}
