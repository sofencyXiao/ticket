package com.sofency.ticket.dto;

import lombok.Data;

/**
 * @author sofency
 * @date 2020/4/19 7:21
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class AuthorizeCode {
    private String js_code;
    private String appid="wxe481069a6acda26d";
    private String secret="0fb6a738d20e983d76769dacdc8d2317";
    private String grant_type="authorization_code";
}
