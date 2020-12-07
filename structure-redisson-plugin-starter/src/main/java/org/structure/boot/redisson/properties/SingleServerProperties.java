package org.structure.boot.redisson.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Title: SingleServerProperties
 * @Package com.mogu.redisson.properties
 * @Description: 单节点配置属性
 * @date: 2019/7/8 11:54
 * @Version V1.0.0
 */
@Setter
@Getter
@ToString
public class SingleServerProperties {
    private String address;
    private Integer subscriptionConnectionMinimumIdleSize = 1;
    private Integer subscriptionConnectionPoolSize = 50;
    private Integer connectionMinimumIdleSize = 32;
    private Integer connectionPoolSize = 64;
    private Long dnsMonitoringInterval = 5000L;
}
