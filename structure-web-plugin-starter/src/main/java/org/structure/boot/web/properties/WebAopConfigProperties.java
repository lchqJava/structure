package org.structure.boot.web.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0.0
 * @Title: WebAopConfigProperties
 * @Package  org.structure.boot.web.properties;
 * @Description: aop 配置
 * @author: chuck
 * @date: 2020/6/3 12:31
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties("structure.web.aop")
public class WebAopConfigProperties {

    /**
     * 公共配置
     */
    private String expression;

}
