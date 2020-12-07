package org.structure.boot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数字枚举类
 *
 * @author CHUCK
 */
@Getter
@AllArgsConstructor
public enum NumberEnum {

    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9);

    private int value;

}
