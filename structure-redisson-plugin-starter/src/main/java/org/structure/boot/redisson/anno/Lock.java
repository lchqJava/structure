package org.structure.boot.redisson.anno;

import org.structure.boot.redisson.enumerate.LockModelEnum;

import java.lang.annotation.*;

/**
 *
 * @Title:  Lock
 * @Package com.mogu.redisson.anno
 * @Description: 锁注解
 * @date:   2019/9/3 14:50
 * @Version V1.0.0
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {
    /**
     * @fields 锁的模式:如果不设置,自动模式,当参数只有一个.使用 REENTRANT 参数多个 MULTIPLE
     */
    LockModelEnum lockModel() default LockModelEnum.AUTO;

    /**
     * @fields 如果keys有多个,如果不设置,则使用 联锁
     */
    String[] keys() default {};

    /**
     * @fields 锁超时时间,默认30000毫秒(可在配置文件全局设置)
     */
    long lockWatchdogTimeout() default 30000;

    /**
     * @fields 等待加锁超时时间,默认10000毫秒 -1 则表示一直等待(可在配置文件全局设置)
     */
    long attemptTimeout() default 10000;
}
