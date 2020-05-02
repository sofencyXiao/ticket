package com.sofency.ticket.dto;

import lombok.Data;

import java.util.List;

/**
 * @author sofency
 * @date 2020/5/2 15:58
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class WapGetGrabActivityDTO {
    private List<GetGrabActivityDTO> GetGrabActivityDTO;
    private ResultMsg resultMsg;
}
