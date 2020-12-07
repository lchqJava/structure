package org.structure.boot.mybatis.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ManagerTargerInvoke {

    Class<?> target() default Object.class;

    String method() default "";

}
