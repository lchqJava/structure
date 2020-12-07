package org.structure.boot.redisson.anno;

import java.lang.annotation.*;

/**
 * @version V1.0.0
 * @Title: RMap
 * @Package org.structure.boot.redisson.anno
 * @Description: 对redis-Map存储结构封装map缓存注解 可以搭配 list结构和对象结构混合使用
 * @author: chuck
 * @date: 2020/4/10 16:53
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CMap {
    //更新Map的key
    String mapKey() default "";
    //是否存入map集合中
    boolean isMap() default false;
    //Map的时效设置
    CTime time() default @CTime();
}