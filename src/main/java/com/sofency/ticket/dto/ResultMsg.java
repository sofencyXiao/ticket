package com.sofency.ticket.dto;

import lombok.Data;

/**
 * @author sofency
 * @date 2020/4/19 7:57
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class ResultMsg {
    private int status;//状态码
    private String msg;//返回的消息
    private String communityAccount;//账号
}
