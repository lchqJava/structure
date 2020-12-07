package org.structure.boot.oauth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: CHUCK
 * @date: 2020/11/12 10:23
 * @description: 认证服务业务异常枚举类 01XXXX
 */
@Getter
@AllArgsConstructor
public enum ErrCodeEnum {
    ERR_NOT_PWD("010001", "密码不能为空"),
    ERR_NOT_USER("010002", "用户不存在"),
    ERR_PASSWORD("010003", "用户名或密码不正确"),
    ERR_UNLOCKED("010004", "用户被锁定"),
    ERR_ENABLED("010005", "用户被禁用"),
    ERR_UNEXPIRED("010006", "用户过期"),
    ERR_NOT_LOGIN_TYPE("010007", "登录不存在类型"),
    ERR_NOT_CLIENT("010008", "资源服务器错误"),
    ERR_NOT_POST("010009", "Authentication method not supported:"),
    ;

    private String code;

    private String message;
}
