package com.sofency.ticket.dto;

import com.sofency.ticket.pojo.Actor;
import com.sofency.ticket.pojo.Student;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author sofency
 * @date 2020/4/27 21:07
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class VoteActivity implements Serializable {
    private String activityName;
    private int communityId;//发起活动的社团号
    private Date beginTime;
    private Date endTime;
    private List<Actor> actors;
}
