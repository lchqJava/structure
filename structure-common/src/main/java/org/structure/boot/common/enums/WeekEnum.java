package org.structure.boot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 星期枚举
 *
 * @author CHUCK
 */
@Getter
@AllArgsConstructor
public enum WeekEnum {
    Monday("周一", 1, "Mon"),
    Tuesday("周二", 2, "Tues"),
    Wednesday("周三", 3, "Wed"),
    Thursday("周四", 4, "Thur"),
    Friday("周五", 5, "Fri"),
    Saturday("周六", 6, "Sat"),
    Sunday("周日", 0, "Sun");
    private String week;
    private int num;
    private String ab;
}
