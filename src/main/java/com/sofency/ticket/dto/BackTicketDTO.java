package com.sofency.ticket.dto;

import lombok.Data;

/**
 * @author sofency
 * @date 2020/4/28 7:59
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class BackTicketDTO {
    private int grabId;//抢票的ID
    private String studentId;//退票的学号
    private String openId;//退票的openId;//备用
}
