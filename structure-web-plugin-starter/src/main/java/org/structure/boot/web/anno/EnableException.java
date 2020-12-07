package org.structure.boot.web.anno;

import org.springframework.context.annotation.Import;
import org.structure.boot.web.filter.BadRequestExceptionHandler;
import org.structure.boot.web.filter.GlobalControllerAdvice;

import java.lang.annotation.*;

/**
 * @version V1.0.0
 * @Title: EnableException
 * @Package org.structure.boot.web.anno
 * @Description: 开启公共异常
 * @author: chuck
 * @date: 2020/6/3 14:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({GlobalControllerAdvice.class})
public @interface EnableException {

}
