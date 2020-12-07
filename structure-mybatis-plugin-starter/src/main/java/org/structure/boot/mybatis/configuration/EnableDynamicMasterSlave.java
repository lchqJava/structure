package org.structure.boot.mybatis.configuration;

import com.baomidou.dynamic.datasource.plugin.MasterSlaveAutoRoutingPlugin;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0.0
 * @Title: EnableDynamicMasterSlave
 * @Package org.structure.boot.demo.mybatisplus.config
 * @Description: 动态主从数据源
 * @author: chuck
 * @date: 2020/5/29 16:26
 */
public class EnableDynamicMasterSlave {

    @Bean
    @ConditionalOnMissingBean(MasterSlaveAutoRoutingPlugin.class)
    public MasterSlaveAutoRoutingPlugin masterSlaveAutoRoutingPlugin(){
        return new MasterSlaveAutoRoutingPlugin();
    }
}
