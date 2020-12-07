package org.structure.boot.mybatis.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version V1.0.0
 * @Title: IdUtils
 * @Package com.structure.boot.manager.utils
 * @Description: ID 的工具类
 * @author: liuChuanqiang
 * @date: 2019/9/11 12:05
 */
public class IdUtils {

    public static String toString(List<String> ids) {
        String idsStr = ids.stream().collect(Collectors.joining(","));
        return idsStr;
    }

    public static void setMid(Object o, Object id) {
        try {
            Field mid = o.getClass().getDeclaredField("mid");
            mid.setAccessible(true);
            mid.set(o, id);
            System.out.println("id = " + id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setId(Object o, Object id) {
        try {
            Field mid = o.getClass().getDeclaredField("id");
            mid.setAccessible(true);
            mid.set(o, id);
            System.out.println("id = " + id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
