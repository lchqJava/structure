package org.structure.boot.mybatis.configuration;


import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;
import org.structure.boot.mybatis.enums.MapperType;

import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0.0
 * @Title: AutoManagerConfiguration
 * @Package com.moomking.starter.manager.configuration
 * @Description: 配置类
 * @author: liuChuanqiang
 * @date: 2019/9/10 15:22
 */
public class ManagerConfiguration {

    public static String getBaseScanPackage() {
        return baseScanPackage;
    }

    public static void setBaseScanPackage(String baseScanPackage) {
        ManagerConfiguration.baseScanPackage = baseScanPackage;
    }

    private static String baseScanPackage;

    public static Map<String, Object> getMapperMap() {
        return mapperMap;
    }

    public static Map<String, Object> mapperMap = new HashMap<>();

    public static MapperType type;

    /**
     * 获取资源的路径
     *
     * @return
     */
    public static String getLocationPattern() {
        return ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + ClassUtils.convertClassNameToResourcePath(baseScanPackage) + "/**/*.class";
    }
}
