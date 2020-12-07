package org.structure.boot.mybatis.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.structure.boot.mybatis.annotation.Manager;
import org.structure.boot.mybatis.configuration.ManagerConfiguration;
import org.structure.boot.mybatis.core.proxy.ManagerClass;
import org.structure.boot.mybatis.core.proxy.ManagerStaticProxy;


/**
 * @author Administrator
 */
public class ManageListenerRefreshedEvent implements ApplicationListener<ContextRefreshedEvent>, ResourceLoaderAware {
    private static final Logger logger = LoggerFactory.getLogger(ManageListenerRefreshedEvent.class);
    private MetadataReaderFactory metadataReaderFactory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            Resource[] classResources = event.getApplicationContext().getResources(ManagerConfiguration.getLocationPattern());
            for (Resource classResource : classResources) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(classResource);
                String className = metadataReader.getClassMetadata().getClassName();
                Class<?> aClass = ClassUtils.forName(className, event.getApplicationContext().getClassLoader());
                if (aClass != null) {
                    Manager manager = aClass.getAnnotation(Manager.class);
                    if (null != manager) {
                        String mapperName = StringUtils.uncapitalize(manager.mapper().getSimpleName());
                        Object mapperBean = event.getApplicationContext().getBean(mapperName);
                        ManagerConfiguration.getMapperMap().put(mapperName, mapperBean);
                        String managerBeanName = StringUtils.uncapitalize(manager.value().getSimpleName());
                        ManagerClass managerClass = new ManagerClass();
                        Object bean = event.getApplicationContext().getBean(managerBeanName);
                        managerClass.setBeanName(managerBeanName);
                        managerClass.setBeanObject(bean);
                        Map<String, Method> stringMethodHashMap = new HashMap<>();
                        Method[] methods = bean.getClass().getMethods();
                        for (int i = 0; i < methods.length; i++) {
                            stringMethodHashMap.put(methods[i].getName(), methods[i]);
                        }
                        managerClass.setMethodMap(stringMethodHashMap);
                        ManagerStaticProxy.getManagerClassMap().put(managerBeanName, managerClass);
                        logger.info("add static proxy is name :{},and mapperName:{}", managerBeanName, mapperName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.metadataReaderFactory = new SimpleMetadataReaderFactory(resourceLoader);
    }
}
