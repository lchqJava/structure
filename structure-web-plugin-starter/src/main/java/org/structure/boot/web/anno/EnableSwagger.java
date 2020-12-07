package org.structure.boot.web.anno;

import org.springframework.context.annotation.Import;
import org.structure.boot.web.config.Swagger2;

import java.lang.annotation.*;

/**
 * @version V1.0.0
 * @Title: EnableSwagger
 * @Package org.structure.boot.web.anno
 * @Description: 开启 Swagger2
 * @author: chuck
 * @date: 2020/6/3 14:49
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(Swagger2.class)
public @interface EnableSwagger {
}
