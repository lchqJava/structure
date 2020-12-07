package org.structure.boot.mybatis.utils;

import tk.mybatis.mapper.entity.Example;

public class ExampleUtils {

    public static Example getExample(Class clazz) {
        Example example = new Example(clazz);
        return example;
    }

    public static Example addAndEqualTo(Example example, String property, Object value) {
        example.and(example.createCriteria().andEqualTo(property, value));
        return example;
    }

    public static Example addAndBetween(Example example, String property, Object value1, Object value2) {
        example.and(example.createCriteria().andBetween(property, value1, value2));
        return example;
    }

    public static Example addOrAndEqualTo(Example example, String property, Object value) {
        example.or(example.createCriteria().andEqualTo(property, value));
        return example;
    }

}
