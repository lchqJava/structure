package org.structure.boot.mybatis.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: PageHelperProperties
 * @Package com.structure.boot.mybatis.config
 * @Description: 分页配置
 * @author: lcq
 * @date: 2019/1/15 15:51
 * @Version V1.0.0
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties("structure.mybatis.plugin.page")
public class PageHelperProperties {
    private String helperDialect = "mysql";
    private String reasonable ="true";
    private String pageSizeZero = "true";
    private String supportMethodsArguments = "true";
    private String params = "count=countSql";
}
