package com.sofency.ticket.exception;

import com.sofency.ticket.dto.ResultMsg;

/**
 * @author sofency
 * @date 2020/5/9 14:05
 * @package IntelliJ IDEA
 * @description
 */
public class UnKnowException extends RuntimeException {
    private String message;
    private Integer code;
    public UnKnowException(ResultMsg resultMsg){
        this.message=resultMsg.getMsg();
        this.code=resultMsg.getStatus();
    }

    public String getMessage(){
        return this.message;
    }

    public Integer getCode() {
        return code;
    }
}
