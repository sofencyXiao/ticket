package com.sofency.ticket.dto;

import lombok.Data;

import java.util.List;

/**
 * @author sofency
 * @date 2020/5/2 17:41
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class WapGetVoteActivityDTO {
    private List<GetVoteActivityDTO> getVoteActivityDTOS;
    private ResultMsg resultMsg;
}
