package org.structure.boot.redisson.anno;

import java.lang.annotation.*;

/**
 * @version V1.0.0
 * @Title: ECache
 * @Package org.structure.boot.redisson.anno
 * @Description: 写缓存注解
 * @author: chuck
 * @date: 2020/4/7 12:17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WCache {
    //keyName
    String key() default "";
    //是否對象緩存
    boolean isObjCache() default true;
    //更新集合配置
    CList list() default @CList;
    //Map 配置
    CMap map() default @CMap;
    //时间配置
    CTime time() default @CTime;





}
