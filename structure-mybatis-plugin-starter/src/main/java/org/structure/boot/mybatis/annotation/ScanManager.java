package org.structure.boot.mybatis.annotation;

import org.springframework.context.annotation.Import;
import org.structure.boot.mybatis.core.ManageListenerRefreshedEvent;
import org.structure.boot.mybatis.core.RegistrarManager;
import org.structure.boot.mybatis.core.factory.ManagerBeanFactory;
import org.structure.boot.mybatis.enums.MapperType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = {RegistrarManager.class, ManagerBeanFactory.class, ManageListenerRefreshedEvent.class})
public @interface ScanManager {

    /**
     * @return
     */
    String[] value() default {};

    /**
     * @fields mapper类型
     * @author chuck
     */
    MapperType mapperType() default MapperType.TK_MAPPER;

    /**
     * 扫描包
     *
     * @return
     */
    String[] basePackages() default {};

}
