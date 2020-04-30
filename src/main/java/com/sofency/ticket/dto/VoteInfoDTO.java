package com.sofency.ticket.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author sofency
 * @date 2020/4/28 11:40
 * @package IntelliJ IDEA
 * @description  投票的信息
 */
@Data
public class VoteInfoDTO {
    private Date endTime;
    private List<ActorInfo> actorInfoList;
}
