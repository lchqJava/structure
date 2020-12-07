package org.structure.boot.redisson.anno;

import org.redisson.api.SortOrder;

import java.lang.annotation.*;

/**
 * @version V1.0.0
 * @Title: RCache
 * @Package org.structure.boot.redisson.anno
 * @Description: 读缓存注解默认降序执行结构为 sort desc + 分页值存key 基础
 * @author: chuck
 * @date: 2020/4/7 12:15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RListCache {
    //key
    String key() default "";
    //设置分页的大小
    String page() default "1" ;
    //默认大小如果是集合需要设置大小
    String size() default "20" ;
    //排序
    SortOrder sort() default SortOrder.DESC;
    //和map关联的KEY
    String mapKey() default "";
    //读取集合的data类型是data 还是key
    CList.ListType value() default CList.ListType.DATA;
}
