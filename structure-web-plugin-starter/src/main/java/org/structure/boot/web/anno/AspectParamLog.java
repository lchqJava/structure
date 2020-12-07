package org.structure.boot.web.anno;

import java.lang.annotation.*;

/**
 * @Title: AspectParamAnno
 * @Package com.structure.boot.aop.starter.anno
 * @Description: 参数记录切面注解
 * @date: 2019/6/14 12:59
 * @Version V1.0.0
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectParamLog  {

}
