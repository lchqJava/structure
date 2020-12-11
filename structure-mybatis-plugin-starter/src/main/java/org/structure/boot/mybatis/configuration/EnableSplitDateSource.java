package org.structure.boot.mybatis.configuration;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.structure.boot.mybatis.plugin.SplitDateSourcePlugin;

public class EnableSplitDateSource {

    @Bean
    @ConditionalOnMissingBean(SplitDateSourcePlugin.class)
    public Interceptor splitDateSourcePlugin() {
        return new SplitDateSourcePlugin();
    }
}
