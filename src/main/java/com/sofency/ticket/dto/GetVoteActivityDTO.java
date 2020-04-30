package com.sofency.ticket.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author sofency
 * @date 2020/4/28 8:18
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class GetVoteActivityDTO {
    private int activityId;
    private String activityName;
    private String activityImgUrl;
}
