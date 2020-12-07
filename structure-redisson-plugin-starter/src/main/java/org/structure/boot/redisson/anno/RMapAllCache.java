package org.structure.boot.redisson.anno;

import java.lang.annotation.*;

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
public @interface RMapAllCache {
    //mapKey
    String mapKey() default "";
    // keys
    String keys() default "";
    //key name 用于补偿
    String keyName()default "";
    //时间设置
    CTime time() default @CTime();
}
