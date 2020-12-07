package org.structure.boot.redisson.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @version V1.0.0
 * @Title: RTime
 * @Package org.structure.boot.redisson.anno
 * @Description: 缓存时间策略配置
 * @author: chuck
 * @date: 2020/4/10 16:52
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CTime{
    //是否有时效果
    boolean isTime() default false;
    //时间限制
    long time() default 0L;
    //时间类型
    TimeUnit timeType() default TimeUnit.MINUTES;
}