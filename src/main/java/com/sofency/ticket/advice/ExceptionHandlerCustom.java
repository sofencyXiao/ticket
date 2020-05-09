package com.sofency.ticket.advice;

import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.enums.Code;
import com.sofency.ticket.exception.UnKnowException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

/**
 * @author sofency
 * @date 2020/5/9 15:50
 * @package IntelliJ IDEA
 * @description
 */
@ControllerAdvice
public class ExceptionHandlerCustom {
    @ExceptionHandler(UnKnowException.class)
    @ResponseBody
    ResultMsg handlerControllerException() throws IOException {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setMsg(Code.NEEDS_ARGS.getMessage());
        resultMsg.setStatus(Code.NEEDS_ARGS.getCode());
        return resultMsg;
    }
}
