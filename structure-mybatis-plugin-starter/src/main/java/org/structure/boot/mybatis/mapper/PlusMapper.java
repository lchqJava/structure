package org.structure.boot.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface PlusMapper<T> extends BaseMapper<T> {

    int insertList(List<T> ts);

    List<T> findPageByQueryPage(Map map);
}
