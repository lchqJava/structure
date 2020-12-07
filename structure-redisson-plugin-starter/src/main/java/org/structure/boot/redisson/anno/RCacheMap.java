package org.structure.boot.redisson.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @version V1.0.0
 * @Title: RMapCache
 * @Package org.structure.boot.redisson.anno
 * @Description: 读取map集合中的缓存
 * @author: chuck
 * @date: 2020/4/10 10:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RCacheMap {
    //mapKey
    String mapKey() default "";
    //list 关联情况下的KEY
    CList list() default @CList;
    //key
    String key() default "";
    //时间配置
    //是否有时效果
    boolean isTime() default false;
    //时间限制
    long time() default 0L;
    //时间类型
    TimeUnit timeType() default TimeUnit.MINUTES;
}
