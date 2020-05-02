package com.sofency.ticket.enums;

/**
 * @author sofency
 * @date 2020/5/2 16:15
 * @package IntelliJ IDEA
 * @description
 */
public enum Status {
    AVAILABLE((byte) 1),
    NO_AVAILABLE((byte) 0);
    private byte code;

    public byte getCode() {
        return code;
    }

    Status(byte code) {
        this.code = code;
    }
}
