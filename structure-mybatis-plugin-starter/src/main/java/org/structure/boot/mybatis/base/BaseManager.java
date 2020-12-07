package org.structure.boot.mybatis.base;


public class BaseManager {

    protected String mapperName;

    protected Object mapper;

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public Object getMapper() {
        return mapper;
    }

    public void setMapper(Object mapper) {
        this.mapper = mapper;
    }
}
