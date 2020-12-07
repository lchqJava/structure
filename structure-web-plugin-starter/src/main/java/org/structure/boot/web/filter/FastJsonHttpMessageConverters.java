package org.structure.boot.web.filter;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.structure.boot.web.anno.EnableFastJsonHttpConberters;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0.0
 * @Title: FastJsonHttpMessageConverters
 * @Package com.structure.boot.starter.web.config
 * @Description: Json转换配置
 * @author: chuck
 * @date: 2020/4/17 14:40
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FastJsonHttpMessageConverters implements ImportSelector {

    private boolean longToString;

    private boolean nullShowValue;

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        if (nullShowValue) {
            //3、空字段显示
            fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        }
        //4、 获取全局序列化配置
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        //5、设置long转string
        if (longToString) {
            serializeConfig.put(Long.class, ToStringSerializer.instance);
            serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        }
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

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        MultiValueMap<String, Object> allAnnotationAttributes = annotationMetadata.getAllAnnotationAttributes(EnableFastJsonHttpConberters.class.getName());
        longToString = (boolean) allAnnotationAttributes.getFirst("longToString");
        nullShowValue = (boolean) allAnnotationAttributes.getFirst("nullShowValue");
        return new String[0];
    }
}
