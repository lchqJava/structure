package org.structure.boot.mybatis.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;
import org.structure.boot.mybatis.enums.MapperType;
import org.structure.boot.mybatis.annotation.ScanManager;
import org.structure.boot.mybatis.configuration.ManagerConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
public class RegistrarManager implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private static final Logger logger = LoggerFactory.getLogger(RegistrarManager.class);
    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes annAttr = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(ScanManager.class.getName()));
        String[] values = annAttr.getStringArray("value");
        ClassManagerPathBeanDefinitionScanner scanner = new ClassManagerPathBeanDefinitionScanner(beanDefinitionRegistry);
        //判断不为空
        if (resourceLoader != null) {
            scanner.setResourceLoader(resourceLoader);
        }
        List<String> basePackages = new ArrayList<>();
        basePackages.addAll(Arrays.asList(values));
        for (String pkg : annAttr.getStringArray("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        MapperType mapperType = (MapperType)annAttr.get("mapperType");
        ManagerConfiguration.type = mapperType;
        ManagerConfiguration.setBaseScanPackage(basePackages.get(0));
        logger.info("do scan Manager basePackages ：{}", basePackages);
        scanner.doScan(StringUtils.toStringArray(basePackages));
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
