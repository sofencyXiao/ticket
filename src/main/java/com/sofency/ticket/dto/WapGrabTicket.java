package com.sofency.ticket.dto;

import com.sofency.ticket.pojo.GrabTicket;
import lombok.Data;

/**
 * @author sofency
 * @date 2020/5/3 9:49
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class WapGrabTicket {
    private GrabTicket grabTicket;
    private ResultMsg resultMsg;
}
