package org.structure.boot.mybatis.core.proxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @version V1.0.0
 * @Title: ManagerClass
 * @Package com.structure.boot.manager.core.proxy
 * @Description:
 * @author: lcq
 * @date: 2019/9/11 16:20
 */
public class ManagerClass {

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Object getBeanObject() {
        return beanObject;
    }

    public void setBeanObject(Object beanObject) {
        this.beanObject = beanObject;
    }

    public Map<String, Method> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, Method> methodMap) {
        this.methodMap = methodMap;
    }

    private String beanName;

    private Object beanObject;

    private Map<String, Method> methodMap;

}
