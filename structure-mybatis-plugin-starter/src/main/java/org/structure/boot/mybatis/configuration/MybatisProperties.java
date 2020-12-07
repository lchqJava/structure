package org.structure.boot.mybatis.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.structure.boot.mybatis.enums.GenerateIdType;


/**
 * @Title: MybatisProperties
 * @Package com.structure.boot.mybatis.config
 * @Description: mybatis 配置
 * @author: lcq
 * @date: 2019/1/15 15:51
 * @Version V1.0.0
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "structure.mybatis.plugin")
@ImportAutoConfiguration(value = PageHelperProperties.class)
public class MybatisProperties {

    private PageHelperProperties page = new PageHelperProperties();

    private GenerateIdType generateIdType = GenerateIdType.NONE;

    private Integer dataCenter = 0;

    private Integer machine = 0;


}