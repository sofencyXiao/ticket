package com.sofency.ticket.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author sofency
 * @date 2020/4/27 22:32
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class StudentDTO {
    private String studentId;//学号
    private String activityName;//活动名称
    private int grabId;//抢票的活动
    private String communityName;//承办方
    private String name;//姓名
    private Date endTime;//活动结束时间
}
