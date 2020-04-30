package com.sofency.ticket.dto;

import lombok.Data;

/**
 * @author sofency
 * @date 2020/4/29 19:04
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class ActivityInfoDTO {
    private String name;//活动的名字
    private boolean flag;//标记是否为投票还是抢票
    private boolean isOver;//活动是否结束
    private int grabOrVoteId;//活动号
}
