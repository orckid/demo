package com.orc.demo.common;

/**
 * @author orcki
 */
public enum LogOptEnum {

    /**
     * 添加add
     */
    A(1,"添加"),
    /**
     * 查询query
     */
    Q(2, "查询"),
    /**
     * 修改update
     */
    U(3, "修改"),
    /**
     * 删除delete
     */
    D(4, "删除");



    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    LogOptEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
