package org.structure.boot.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @Title: Properties
 * @Package org.structure.boot.web.properties
 * @Description: 验证配置
 * @author: siy
 * @date: 2019/12/9 11:23
 * @Version V1.0.0
 */

@ConfigurationProperties(prefix = "structure.security")
public class SecurityProperties {
    private Map<String, List<String>> antMatchers;

    public Map<String, List<String>> getAntMatchers() {
        return antMatchers;
    }

    public void setAntMatchers(Map<String, List<String>> antMatchers) {
        this.antMatchers = antMatchers;
    }
}
