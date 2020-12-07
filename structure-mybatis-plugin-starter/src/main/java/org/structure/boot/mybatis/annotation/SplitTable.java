package org.structure.boot.mybatis.annotation;

import org.structure.boot.mybatis.enums.SplitTableEnum;

import java.lang.annotation.*;

/**
 * @Title: TableSeg
 * @Package com.structure.boot.mybatis.annotations
 * @Description: 分表注解
 * @author: lcq
 * @date: 2019/1/15 15:51
 * @Version V1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SplitTable {
    /**
     * 表名
     *
     * @return
     */
    String tableName();

    /**
     * 分表方式，如 time：表示按时间分表
     * key：表示按字段取模分表
     *
     * @return
     */
    SplitTableEnum splitType();

    /**
     * 根据什么字段分表
     *
     * @return
     */
    String splitBy();

    /**
     * 分表参数，分表方式为time: 表示用于分表的参数名（如 dateInterval）
     * 分表方式为key: 表示取模的基数（如 %32 ，%64）
     *
     * @return
     */
    int splitParam() default 64;
}
