package org.structure.boot.mybatis.base.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import org.structure.boot.mybatis.common.ReqPage;
import org.structure.boot.mybatis.common.StructurePageInfo;
import org.structure.boot.mybatis.mapper.PlusMapper;
import org.structure.boot.mybatis.base.BaseManager;
import org.structure.boot.mybatis.base.interfaces.IBaseManager;

import java.util.List;

public class PlusManagerImpl<T> extends BaseManager implements IBaseManager<T,String> {

    private PlusMapper<T> plusMapper;

    @Override
    public void setMapper(Object mapper) {
        if (mapper instanceof PlusMapper) {
            plusMapper = (PlusMapper) mapper;
        }else {
            return;
        }
        super.setMapper(mapper);
    }
    @Override
    public T insert(T t) {
        int insert = plusMapper.insert(t);
        return t;
    }

    @Override
    public Integer insertList(List<T> ts) {
        int i = 0;
        for (T t: ts) {
            i += plusMapper.insert(t);
        }
        return i;
    }

    @Override
    public T selectById(String s) {
        return (T)plusMapper.selectById(s);
    }

    @Override
    public List<T> selectByIds(List<String> strings) {
        List list = plusMapper.selectBatchIds(strings);
        return list;
    }

    @Override
    public List<T> selectCondition(Object object) {
        return plusMapper.selectList((QueryWrapper)object);
    }

    @Override
    public T selectOne(T t) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(t);
        Object o = plusMapper.selectOne(queryWrapper);
        return (T)o;
    }

    @Override
    public List<T> selectByPo(T t) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(t);
        List list = plusMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public Integer updatePoById(T t) {
        int i = plusMapper.updateById(t);
        return i;
    }

    @Override
    public Integer updatePoByCondition(T t, Object condition) {
        int update = plusMapper.update(t, (UpdateWrapper) condition);
        return update;
    }

    @Override
    public Integer deleteById(String s) {
        int i = plusMapper.deleteById(s);
        return i;
    }

    @Override
    public Integer deleteByIds(List<String> strings) {
        int i = plusMapper.deleteBatchIds(strings);
        return i;
    }

    @Override
    public Integer findCountByPo(T t) {
        QueryWrapper<T> queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(t);
        Integer count = plusMapper.selectCount(queryWrapper);
        return count;
    }

    @Override
    public StructurePageInfo<T> findPageByPo(T t, ReqPage reqPage) {
        PageHelper.startPage(reqPage.getPage(),reqPage.getRows());
        QueryWrapper<T> queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(t);
        queryWrapper.le("create_time",reqPage.getQueryTimestamp());
        queryWrapper.orderByDesc("create_time");
        List list = plusMapper.selectList(queryWrapper);
        StructurePageInfo structurePageInfo = new StructurePageInfo(list);
        return structurePageInfo;
    }

    @Override
    public StructurePageInfo<T> findPageByQueryPage(ReqPage reqPage) {
        PageHelper.startPage(reqPage.getPage(),reqPage.getRows());
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(reqPage.getParameter()));
        jsonObject.put("queryTimestamp",reqPage.getQueryTimestamp());
        List pageByQueryPage = plusMapper.findPageByQueryPage(jsonObject);
        StructurePageInfo structurePageInfo = new StructurePageInfo(pageByQueryPage);
        return structurePageInfo;
    }
}
