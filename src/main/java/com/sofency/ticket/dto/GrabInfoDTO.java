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
    private String activityName;
    private String communityName;
    private int ticketLevel;//当前剩余票数
    private Date endTime;//活动剩余时间
    private Student student;//当前学生的信息
    private int grabId;//抢票的ID
}
