package org.structure.boot.mybatis.annotation;

import org.springframework.context.annotation.Import;
import org.structure.boot.mybatis.configuration.EnableSplitDateSource;

import java.lang.annotation.*;

/**
 * @author LCQ
 * @desc 开启分表功能
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(EnableSplitDateSource.class)
public @interface EnableSplitTable {

}
