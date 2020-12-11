package org.structure.boot.web.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.structure.boot.web.properties.ConfigProperties;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnClass(value = {ConfigProperties.class})
public class AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(HttpMessageConverters.class)
    @ConditionalOnProperty(value = "structure.web.http-message-converters.fast-json",matchIfMissing = true)
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //3、空字段显示
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        //4、 获取全局序列化配置
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        //5、设置long转string
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(serializeConfig);
        //6、处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        //7、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //8、将convert添加到converters中
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }


}
