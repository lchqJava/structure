package org.structure.boot.redis.annotation;

import java.lang.annotation.*;

/**
 * @version V1.0.0
 * @Title: RedisLock
 * @Package org.structure.boot.redis.annotation
 * @Description: 分布式锁注解
 * @author: chuck
 * @date: 2020/6/4 11:40
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {

    /** 锁的资源，redis的key*/
    String value() default "default";

    /** 持锁时间,单位毫秒*/
    long keepMills() default 30000;

    /** 当获取失败时候动作*/
    LockFailAction  action() default LockFailAction.CONTINUE;

    enum LockFailAction{
        /** 放弃 */
        GIVEUP,
        /** 继续 */
        CONTINUE;
    }
    /** 重试的间隔时间,设置GIVEUP忽略此项*/
    long sleepMills() default 200;

    /** 重试次数*/
    int retryTimes() default 5;

}
