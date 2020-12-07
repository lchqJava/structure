package org.structure.boot.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: UpdateTime
 * @Package com.structure.boot.mybatis.annotations
 * @Description: 修改时间注解
 * @author: lcq
 * @date: 2019/1/15 15:52
 * @Version V1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UpdateTime {
}
