package org.structure.boot.web.anno;

import org.springframework.context.annotation.Import;
import org.structure.boot.web.aop.ParamLogsAspect;
import org.structure.boot.web.config.EnableWebAopConfig;

import java.lang.annotation.*;

/**
 * @version V1.0.0
 * @Title: EnableWebAopLog
 * @Package org.structure.boot.web.anno
 * @Description: 开启webAop的log需要配置路径
 * @author: chuck
 * @date: 2020/6/3 11:29
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({EnableWebAopConfig.class, ParamLogsAspect.class})
public @interface EnableWebAopLog {

}
