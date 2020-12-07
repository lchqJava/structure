package org.structure.boot.common.enums;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {

    /**
     * 未知异常
     */
    UNKNOWN_EXCEPTION("未知异常"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR("系统错误"),
    /**
     * 第三方错误
     */
    THIRD_PARTY_ERROR("第三方异常"),
    /**
     * 逻辑异常
     */
    LOGIC_ERROR("业务异常"),

    DREAMAC_EXCEPTION("自定义异常");

    private String errorType;

    ErrorCodeEnum(String errorType) {
        this.errorType = errorType;
    }
}
