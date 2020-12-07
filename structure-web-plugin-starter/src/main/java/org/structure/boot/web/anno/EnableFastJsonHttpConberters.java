package org.structure.boot.web.anno;

import org.springframework.context.annotation.Import;
import org.structure.boot.web.filter.FastJsonHttpMessageConverters;

import java.lang.annotation.*;

/**
 * @version V1.0.0
 * @Title: EnableFastJsonHttpConberters
 * @Package org.structure.boot.web.anno
 * @Description: 开启fastJson出参转换
 * @author: chuck
 * @date: 2020/6/3 14:15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(FastJsonHttpMessageConverters.class)
public @interface EnableFastJsonHttpConberters {

    boolean longToString() default false;

    boolean nullShowValue() default false;


}
