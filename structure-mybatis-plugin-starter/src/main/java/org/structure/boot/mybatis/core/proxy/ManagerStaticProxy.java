package org.structure.boot.mybatis.core.proxy;

import org.structure.boot.mybatis.exception.ManagerException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0.0
 * @Title: ManagerAgency
 * @Package com.structure.boot.manager.core.proxy
 * @Description: 静态代理
 * @author: lcq
 * @date: 2019/9/11 16:25
 */
public class ManagerStaticProxy {

    public static Map<String, ManagerClass> getManagerClassMap() {
        return managerClassMap;
    }

    private static Map<String, ManagerClass> managerClassMap = new HashMap<>();

    public static Object invoke(String target,Method method,Object[] args) {
        ManagerClass managerClass = managerClassMap.get(target);
        Object beanObject = managerClass.getBeanObject();
        try {
            Method targetMethod = beanObject.getClass().getMethod(method.getName(),method.getParameterTypes());
            Object invoke = targetMethod.invoke(beanObject, args);
            return invoke;
        } catch (ManagerException ce){
            throw ce;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof ManagerException){
                throw (ManagerException)e.getTargetException();
            }
            throw (RuntimeException)e.getTargetException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object invoke(String target, String method, Object[] args) {
        ManagerClass managerClass = managerClassMap.get(target);
        Object beanObject = managerClass.getBeanObject();
        Method invokeMethod = managerClass.getMethodMap().get(method);
        try {
            Object invoke = invokeMethod.invoke(beanObject, args);
            return invoke;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
