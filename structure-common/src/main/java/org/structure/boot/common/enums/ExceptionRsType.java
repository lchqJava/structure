package org.structure.boot.common.enums;

/**
 * @Title: ExceptionRsType
 * @Package org.structure.boot.web.enums;
 * @Description: 异常返回枚举
 * @date: 2019/11/27 11:35
 * @Version V1.0.0
 */
public enum ExceptionRsType {
    /**
     * 用户未登录
     */
    NOT_LOGGED_IN("NOT_LOGGED_IN", "10000"),
    /**
     * 无效TOKEN
     */
    INVALID_AUTHENTICATION("INVALID_AUTHENTICATION", "10001"),
    /**
     * 权限被拒绝
     */
    PERMISSION_DENIED("PERMISSION_DENIED", "10002"),
    ;

    private String msg;
    private String code;

    ExceptionRsType(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
