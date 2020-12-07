package org.structure.boot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * 性别枚举
 */
@AllArgsConstructor
@Getter
public enum GenderEnum {
    /**
     * N 未知0  M 男1 F 女2
     */
    N(0),
    M(1),
    F(2);
    private int num;


}
