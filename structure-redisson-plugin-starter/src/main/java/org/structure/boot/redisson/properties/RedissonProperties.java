package org.structure.boot.redisson.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.redisson.config.Config;
import org.redisson.config.SslProvider;
import org.redisson.config.TransportMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.structure.boot.redisson.enumerate.LockModelEnum;

import java.net.URI;


@ConfigurationProperties(prefix = "structure.redisson")
@Getter
@Setter
@ToString
public class RedissonProperties {

    private String model;

    private Integer threads;
    private Integer nettyThreads;
    private String codec = "org.redisson.codec.JsonJacksonCodec";
    private TransportMode transportMode = TransportMode.NIO;
    private Integer database = 0;

    private Integer idleConnectionTimeout = 10000;
    private Integer pingTimeout = 1000;
    private Integer connectTimeout = 10000;
    private Integer timeout = 3000;
    private Integer retryAttempts = 3;
    private Integer retryInterval = 1500;
    private String password;
    private Integer subscriptionsPerConnection = 5;
    private String clientName;
    private Boolean sslEnableEndpointIdentification = true;
    private SslProvider sslProvider = SslProvider.JDK;
    private String sslTruststore;
    private String sslTruststorePassword;
    private String sslKeystore;
    private String sslKeystorePassword;
    private Integer pingConnectionInterval = 0;
    private Boolean keepAlive = false;
    private Boolean tcpNoDelay = false;

    private Boolean referenceEnabled = true;
    private Long lockWatchdogTimeout = 30000L;
    private Boolean keepPubSubOrder = true;
    private Boolean decodeInExecutor = false;
    private Boolean useScriptCache = false;
    private Integer minCleanUpDelay = 5;
    private Integer maxCleanUpDelay = 1800;

    @NestedConfigurationProperty
    private SingleServerProperties single;

    @NestedConfigurationProperty
    private SentinelProperties sentinel;

    @NestedConfigurationProperty
    private ClusterProperties cluster;

    @NestedConfigurationProperty
    private ReplicatedProperties replicated;

    @NestedConfigurationProperty
    private MasterSlaveProperties masterSlave;

    /**
     * @fields 锁的模式 如果不设置 单个key默认可重入锁 多个key默认联锁
     */
    private LockModelEnum lockModel;
    /**
     * @fields 等待加锁超时时间 -1一直等待
     */
    private Long attemptTimeout = 10000L;
    /**
     * @fields 数据缓存时间 默认30分钟
     */
    private Long dataValidTime = 1000 * 60 * 30L;

    public void setLockModel(LockModelEnum lockModel) {
        this.lockModel = lockModel;
    }


}
