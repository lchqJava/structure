package org.structure.boot.mybatis.core.factory;

import org.structure.boot.mybatis.base.BaseManager;
import org.structure.boot.mybatis.base.Impl.JpaManagerImpl;
import org.structure.boot.mybatis.base.Impl.PlusManagerImpl;
import org.structure.boot.mybatis.base.Impl.TkManagerImpl;
import org.structure.boot.mybatis.enums.MapperType;
import org.structure.boot.mybatis.configuration.ManagerConfiguration;

public class ManagerImplFactory {

    public static BaseManager baseManager(MapperType mapperType) {
        MapperType defaultType = ManagerConfiguration.type;
        if (mapperType == MapperType.NONE) {
            mapperType = (null == defaultType) ? MapperType.TK_MAPPER : defaultType;
        }
        switch (mapperType) {
            case JPA:
                return new JpaManagerImpl<>();
            case TK_MAPPER:
                return new TkManagerImpl<>();
            case MY_BATIS_PLUS:
                return new PlusManagerImpl<>();
            default:
                return new TkManagerImpl();
        }
    }
}
