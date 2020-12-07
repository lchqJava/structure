package org.structure.boot.web.anno;

import org.springframework.context.annotation.Import;
import org.structure.boot.web.config.Swagger2;
import org.structure.boot.web.filter.BadRequestExceptionHandler;
import org.structure.boot.web.filter.GlobalControllerAdvice;

import java.lang.annotation.*;

/**
 * @version V1.0.0
 * @Title: EnableWebPluginAll
 * @Package org.structure.boot.web.anno
 * @Description: ALL
 * @author: chuck
 * @date: 2020/6/3 14:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = {Swagger2.class
        , BadRequestExceptionHandler.class
        , GlobalControllerAdvice.class
        })
public @interface EnableWebPluginAll {
}
