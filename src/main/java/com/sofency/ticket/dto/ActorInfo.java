package com.sofency.ticket.dto;

import lombok.Data;

/**
 * @author sofency
 * @date 2020/4/28 11:42
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class ActorInfo {
    private int actorId;//参与者的id
    private int tickets;//所获票数
    private String name;//名字
}
