package org.structure.boot.resource.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @Title: Properties
 * @Package org.structure.boot.web.properties
 * @Description: 验证配置
 * @author: chuck
 * @date: 2019/12/9 11:23
 * @Version V1.0.0
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "structure.security")
public class SecurityProperties {
    private Map<String, List<String>> antMatchers;
}
