package org.structure.boot.mybatis.exception;

import lombok.*;

/**
 * @version V1.0.0
 * @Title: ManagerException
 * @Package org.structure.boot.mybatis.plugin.exception
 * @Description: manager 异常
 * @author: chuck
 * @date: 2020/6/2 11:40
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ManagerException extends RuntimeException {
    protected String code;
    protected String msg;
}
