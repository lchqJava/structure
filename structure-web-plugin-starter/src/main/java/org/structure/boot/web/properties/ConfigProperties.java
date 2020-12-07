package org.structure.boot.web.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @version V1.0.0
 * @Title: ConfigProperties
 * @Package org.structure.boot.web.config
 * @Description: structure web 的配置
 * @author: chuck
 * @date: 2020/6/3 12:29
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(value = "structure.web")
public class ConfigProperties {

    private WebAopConfigProperties aop;

}
