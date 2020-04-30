package com.sofency.ticket.dto;

import lombok.Data;

/**
 * @author sofency
 * @date 2020/4/29 20:00
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class IdAndCommunityId {
    private int Id;
    private int communityId;
    private byte status;//状态
}
