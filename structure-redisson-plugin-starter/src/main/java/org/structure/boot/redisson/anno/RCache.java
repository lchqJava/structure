package org.structure.boot.redisson.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @version V1.0.0
 * @Title: RCache
 * @Package org.structure.boot.redisson.anno
 * @Description: 读缓存注解 默认读对象缓存支持写操作过程
 * @author: chuck
 * @date: 2020/4/7 12:15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RCache {
    //key
    String key() default "";
    //设置默认时长
    long time() default 1;
    //设置单位
    TimeUnit timeType() default TimeUnit.DAYS;

}
