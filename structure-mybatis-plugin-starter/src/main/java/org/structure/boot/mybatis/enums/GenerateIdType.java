package org.structure.boot.mybatis.enums;

/**
 * @Title: GenerateIdType
 * @Package com.structure.boot.mybatis.enums
 * @Description: 生成ID的方式
 * @author: lcq
 * @date: 2019/2/26 17:54
 * @Version V1.0.0
 */
public enum GenerateIdType {
    NONE,
    UUID,
    SNOWFLAKE,
    ;

    GenerateIdType() {
    }
}
