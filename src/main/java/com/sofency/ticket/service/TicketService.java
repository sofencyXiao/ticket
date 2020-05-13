package com.sofency.ticket.service;


import java.util.List;

/**
 * @author sofency
 * @date 2020/4/27 23:01
 * @package IntelliJ IDEA
 * @description
 */
public interface TicketService {

    //票务的信息
    public List<Integer> getGrabId(String studentId);
}
