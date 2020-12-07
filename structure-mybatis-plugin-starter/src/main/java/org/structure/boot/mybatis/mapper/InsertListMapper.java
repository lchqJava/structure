package org.structure.boot.mybatis.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.structure.boot.mybatis.mapper.provider.InsertListSpecialProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface InsertListMapper<T> {
    @InsertProvider(
            type = InsertListSpecialProvider.class,
            method = "dynamicSQL"
    )
    int insertList(List<? extends T> var1);
}
