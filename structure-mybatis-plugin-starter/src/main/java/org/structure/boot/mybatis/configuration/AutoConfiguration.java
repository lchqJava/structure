package org.structure.boot.mybatis.configuration;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.structure.boot.mybatis.plugin.OverWritePluginParameter;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * @version V1.0.0
 * @Title: AutoManagerConfiguration
 * @Package com.moomking.starter.manager.configuration
 * @Description: 自动装载配置类
 * @author: liuChuanqiang
 * @date: 2019/9/10 15:22
 */
@Configuration
@ImportAutoConfiguration(value = {MybatisProperties.class})
@ConditionalOnClass(value = {OverWritePluginParameter.class})
public class AutoConfiguration {

    @Resource
    private MybatisProperties mybatisProperties;

    @Bean
    @ConditionalOnMissingBean(Snowflake.class)
    public Snowflake snowflake(){
        Integer dataCenter = mybatisProperties.getDataCenter();
        Integer machine = mybatisProperties.getMachine();
        dataCenter = (null == dataCenter)? 0 : dataCenter;
        machine = (null == machine)? 0 : machine;
        return IdUtil.createSnowflake(machine,dataCenter);
    }

    @Bean
    @ConditionalOnMissingBean(OverWritePluginParameter.class)
    public OverWritePluginParameter overWritePluginParameter(){
        return new OverWritePluginParameter();
    }

    @Bean
    @ConditionalOnMissingBean(Interceptor.class)
    public Interceptor getPageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties p = new Properties();
        p.setProperty("helperDialect", mybatisProperties.getPage().getHelperDialect());
        p.setProperty("params", mybatisProperties.getPage().getParams());
        //分页尺寸为0时查询所有纪录不再执行分页
        p.setProperty("pageSizeZero", mybatisProperties.getPage().getPageSizeZero());
        //页码<=0 查询第一页，页码>=总页数查询最后一页
        p.setProperty("reasonable", mybatisProperties.getPage().getReasonable());
        //支持通过 Mapper 接口参数来传递分页参数
        p.setProperty("supportMethodsArguments", mybatisProperties.getPage().getSupportMethodsArguments());
        pageInterceptor.setProperties(p);
        return pageInterceptor;
    }

}
