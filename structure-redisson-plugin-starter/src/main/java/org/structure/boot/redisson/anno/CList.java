package org.structure.boot.redisson.anno;

import java.lang.annotation.*;

/**
 * @version V1.0.0
 * @Title: RList
 * @Package org.structure.boot.redisson.anno
 * @Description: 对redisList存储结构封装list缓存注解 可以搭配 map结构和对象结构混合使用
 * @author: chuck
 * @date: 2020/4/10 16:50
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CList {
    boolean isList() default false;
    //更新集合的key
    String listKeyName() default "";
    //集合类型
    ListType value() default ListType.DATA;
    //和map关联的KEY
    String mapKey() default "";
    //集合长度
    int size() default 200;
    //列表的时效设置
    CTime time() default @CTime();

    enum  ListType {
        //不太建议只存储KEY -- 双向没有时效限制时可以使用key 或者说时效比较长在定时更新范围内
        KEY(),
        DATA(),
        MAP(),
    }
}
