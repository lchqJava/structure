package org.structure.boot.mybatis.annotation;


import org.structure.boot.mybatis.enums.MapperType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Manager {

    Class<? extends Object> value();

    Class<? extends Object> mapper();

    MapperType mapperType() default MapperType.NONE;

}
