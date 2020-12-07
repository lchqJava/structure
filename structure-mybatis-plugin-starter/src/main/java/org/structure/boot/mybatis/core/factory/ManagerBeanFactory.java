package org.structure.boot.mybatis.core.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.structure.boot.mybatis.enums.MapperType;
import org.structure.boot.mybatis.annotation.Manager;
import org.structure.boot.mybatis.configuration.ManagerConfiguration;
import org.structure.boot.mybatis.core.proxy.ManagerProxy;

import java.lang.reflect.Proxy;


/**
 * @author Administrator
 */
public class ManagerBeanFactory implements BeanFactoryPostProcessor, ResourceLoaderAware {

    private static final Logger logger = LoggerFactory.getLogger(ManagerBeanFactory.class);

    protected MetadataReaderFactory metadataReaderFactory;

    protected ResourcePatternResolver resourcePatternResolver;

    protected ResourceLoader resourceLoader;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        try {
            Resource[] classResources = this.resourcePatternResolver.getResources(ManagerConfiguration.getLocationPattern());
            for (Resource classResource : classResources) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(classResource);
                String className = metadataReader.getClassMetadata().getClassName();
                Class<?> clazz = ClassUtils.forName(className, configurableListableBeanFactory.getBeanClassLoader());
                if (clazz != null) {
                    Manager manager = clazz.getAnnotation(Manager.class);
                    if (null != manager) {
                        String beanName = StringUtils.uncapitalize(clazz.getSimpleName());
                        String mapperName = StringUtils.uncapitalize(manager.mapper().getSimpleName());
                        String managerBeanName = StringUtils.uncapitalize(manager.value().getSimpleName());
                        MapperType mapperType = manager.mapperType();
                        logger.info("register Proxy beanName :{},managerBeanName :{},mapperName:{},mapperType: {}", beanName, managerBeanName, mapperName,mapperType);
                        configurableListableBeanFactory.registerSingleton(beanName, getInstance(clazz, managerBeanName, mapperName,mapperType));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.metadataReaderFactory = new SimpleMetadataReaderFactory(resourceLoader);
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    }

    /**
     * 利用动态代理获取对应实例对象
     *
     * @param clazz
     * @return
     */
    public Object getInstance(Class<?> clazz, String object, String mapperName, MapperType mapperType) {
        ManagerProxy invocationHandler = new ManagerProxy(object, mapperName,mapperType);
        Object newProxyInstance = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, invocationHandler);
        return newProxyInstance;
    }
}
