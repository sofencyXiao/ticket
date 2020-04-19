package com.sofency.ticket.dto;

import lombok.Data;

/**
 * @author sofency
 * @date 2020/4/19 7:19
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class SessionCode{
    private Integer id;//用户的主键
    private String openId;
    private String sessionKey;
    private String token;
}