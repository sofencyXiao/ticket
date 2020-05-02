package com.sofency.ticket.dto;

import lombok.Data;

import java.util.List;

/**
 * @author sofency
 * @date 2020/5/2 15:05
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class WapActivityInfoDTO {
    private List<ActivityInfoDTO> activityInfoDTOS;
    private ResultMsg resultMsg;
}
