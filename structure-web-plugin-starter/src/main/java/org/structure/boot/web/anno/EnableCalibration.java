package org.structure.boot.web.anno;

import org.springframework.context.annotation.Import;
import org.structure.boot.web.filter.BadRequestExceptionHandler;

import java.lang.annotation.*;

/**
 * @version V1.0.0
 * @Title: EnableCalibration
 * @Package org.structure.boot.web.anno
 * @Description: 开启参数校验
 * @author: chuck
 * @date: 2020/6/3 14:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(BadRequestExceptionHandler.class)
public @interface EnableCalibration {
}
