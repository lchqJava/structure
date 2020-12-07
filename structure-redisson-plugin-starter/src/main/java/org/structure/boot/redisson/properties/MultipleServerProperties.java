package org.structure.boot.redisson.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.redisson.config.ReadMode;
import org.redisson.config.SubscriptionMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title: MultipleServerProperties
 * @Package com.mogu.redisson.properties
 * @Description: 多节点配置属性
 * @date: 2019/7/8 11:51
 * @Version V1.0.0
 */
@Getter
@Setter
@ToString
public class MultipleServerProperties {
    private String loadBalancer = "org.redisson.connection.balancer.RoundRobinLoadBalancer";
    private Integer slaveConnectionMinimumIdleSize = 32;
    private Integer slaveConnectionPoolSize = 64;
    private Integer failedSlaveReconnectionInterval = 3000;
    private Integer failedSlaveCheckInterval = 180000;
    private Integer masterConnectionMinimumIdleSize = 32;
    private Integer masterConnectionPoolSize = 64;
    private ReadMode readMode = ReadMode.SLAVE;
    private SubscriptionMode subscriptionMode = SubscriptionMode.SLAVE;
    private Integer subscriptionConnectionMinimumIdleSize = 1;
    private Integer subscriptionConnectionPoolSize = 50;
    private Long dnsMonitoringInterval = 5000L;

}
