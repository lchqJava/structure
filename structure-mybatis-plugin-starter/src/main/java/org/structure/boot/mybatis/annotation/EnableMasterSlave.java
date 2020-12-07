package org.structure.boot.mybatis.annotation;

import org.springframework.context.annotation.Import;
import org.structure.boot.mybatis.configuration.EnableDynamicMasterSlave;

import java.lang.annotation.*;

/**
 * @author LCQ
 * @desc 动态主从数据源
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(EnableDynamicMasterSlave.class)
public @interface EnableMasterSlave {

}
