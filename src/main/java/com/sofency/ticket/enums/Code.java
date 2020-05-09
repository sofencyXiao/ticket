package com.sofency.ticket.enums;

/**
 * @author sofency
 * @date 2020/4/27 19:57
 * @package IntelliJ IDEA
 * @description
 */
public enum  Code {
    NEEDS_ARGS(404,"需要参数"),
    EXCEPTION_UN_KNOW(404,"未知错误"),
    GET_VOTE_SUCCESS(200,"获取票的列表成功"),
    GET_VOTE_FAIL(404,"获取表的列表失败"),
    SEARCH_SUCCESS(200,"查找成功"),
    SEARCH_FAIL(404,"查找失败"),
    VOTE_SUCCESS(200,"投票成功"),
    VOTE_FAIL(404,"投票失败"),
    GRAB_SUCCESS(200,"抢票成功"),
    GRAB_FAIL(404,"抢票失败"),
    DELETE_SUCCESS(200,"退票成功"),
    DELETE_FAIL(404,"退票失败"),
    SEND_VOTE_SUCCESS(200,"发起投票成功"),
    SEND_VOTE_FAIL(404,"发起投票失败"),
    LOGIN_SUCCESS(200,"登录成功"),
    LOGIN_FAIL(404,"登录失败"),
    REGISTER_SUCCESS(200,"注册成功"),
    REGISTER_FAIL(404,"注册失败"),
    HAS_BUY(404,"已经购买过"),
    CAN_BUY(200,"可以购买"),
    CAN_NOT_BUY(404,"可以购买"),
    CHANGE_SUCCESS(200,"修改成功"),
    CHANGE_FAIL(404,"修改失败");
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
