package org.structure.boot.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Title: VerificationFailedMsg
 * @Package com.structure.boot.common.object.vo
 * @Description: 校验失败信息实体
 * @author: lcq
 * @date: 2019/1/14 17:29
 * @Version V1.0.0
 */
@Getter
@Setter
@ToString
public class VerificationFailedMsg {
    private String field;
    private String errorMessage;
}