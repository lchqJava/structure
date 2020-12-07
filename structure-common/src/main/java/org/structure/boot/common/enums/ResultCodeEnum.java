package org.structure.boot.common.enums;

import lombok.Getter;

/**
 * @author LCQ
 * @version 1.0.0
 * 返回结果code枚举
 * @date 2019-10-20
 */
@Getter
public enum ResultCodeEnum {
    /**
     * 验证成功
     */
    SUCCESS("OK", "SUCCESS"),
    FAIL("内部业务错误", "FAIL"),
    NOT_FOUND("资源不存在", "NOT_FOUND"),
    FALLBACK("断路", "FALLBACK"),
    UNAUTHORIZED("验证失败", "UNAUTHORIZED"),
    VERIFICATION_FAILED("格式校验失败", "VERIFICATION_FAILED"),
    ERR("异常", "ERR");

    private String msg;
    private String code;

    ResultCodeEnum(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }
}
