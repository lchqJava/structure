package org.structure.boot.redis.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.structure.boot.redis.lock.DistributedLock;
import org.structure.boot.redis.lock.RedisDistributedLock;

/**
 * @version V1.0.0
 * @Title: DistributedLockAutoConfiguration
 * @Package org.structure.boot.redis.configuration
 * @Description: redis 锁配置
 * @author: chuck
 * @date: 2020/6/4 11:43
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@Import(DistributedLockAspectConfiguration.class)
public class DistributedLockAutoConfiguration {

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public DistributedLock redisDistributedLock(RedisTemplate redisTemplate){
        return new RedisDistributedLock(redisTemplate);
    }
    @Bean
    public RedisTemplate setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        return redisTemplate;
    }
}
