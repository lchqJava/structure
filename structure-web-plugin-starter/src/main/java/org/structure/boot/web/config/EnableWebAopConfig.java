package org.structure.boot.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;



/**
 * @version V1.0.0
 * @Title: EnableWebAopConfig
 * @Package org.structure.boot.web.config
 * @Description: aop 配置
 * @author: chuck
 * @date: 2020/6/3 12:05
 */
@Slf4j
@Configuration
@ConditionalOnProperty(value = "structure.web.aop",matchIfMissing = true)
@ImportResource(locations = {"classpath:structure-boot-aop.xml"})
public class EnableWebAopConfig {

}
