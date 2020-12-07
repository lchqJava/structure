package org.structure.boot.redisson.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0.0
 * @Title: CacheProperties
 * @Package org.structure.boot.redisson.properties
 * @Description: 缓存配置
 * @author: chuck
 * @date: 2020/6/5 11:44
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties("structure.redisson.cache")
public class CacheProperties {

    private String keyGroupName;
}
