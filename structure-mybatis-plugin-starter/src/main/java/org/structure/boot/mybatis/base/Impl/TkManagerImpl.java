package org.structure.boot.mybatis.base.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import org.structure.boot.mybatis.base.BaseManager;
import org.structure.boot.mybatis.base.interfaces.IBaseManager;
import org.structure.boot.mybatis.common.ReqPage;
import org.structure.boot.mybatis.common.StructurePageInfo;
import org.structure.boot.mybatis.mapper.TkMapper;
import org.structure.boot.mybatis.utils.IdUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @version V1.0.0
 * @Title: TkManagerImpl
 * @Package com.structure.boot.manager.base.Impl
 * @Description: Manager 实现类
 * @author: liuChuanqiang
 * @date: 2019/9/10 15:41
 */
public class TkManagerImpl<T> extends BaseManager implements IBaseManager<T, String> {

    private TkMapper<T> tkMapper;

    @Override
    public void setMapper(Object mapper) {
        if (mapper instanceof TkMapper) {
            tkMapper = (TkMapper) mapper;
        }else {
            return;
        }
        super.setMapper(mapper);
    }

    @Override
    public T insert(T o) {
        int insert = tkMapper.insert(o);
        return o;
    }

    @Override
    public Integer insertList(List<T> objects) {
        int i = tkMapper.insertList(objects);
        return i;
    }

    @Override
    public T selectById(String s) {
        return (T)tkMapper.selectByPrimaryKey(s);
    }

    @Override
    public List<T> selectByIds(List<String> strings) {
        List list = tkMapper.selectByIds(IdUtils.toString(strings));
        return list;
    }

    @Override
    public List<T> selectCondition(Object object) {
        List list = tkMapper.selectByCondition(object);
        return list;
    }

    @Override
    public T selectOne(T o) {
        T result = (T)tkMapper.selectOne(o);
        return result;
    }

    @Override
    public List<T> selectByPo(T o) {
        List select = tkMapper.select(o);
        return select;
    }

    @Override
    public Integer updatePoById(T o) {
        return tkMapper.updateByPrimaryKeySelective(o);
    }

    @Override
    public Integer updatePoByCondition(T o, Object condition) {
        return tkMapper.updateByConditionSelective(o, condition);
    }

    @Override
    public Integer deleteById(String s) {
        return tkMapper.deleteByPrimaryKey(s);
    }

    @Override
    public Integer deleteByIds(List<String> strings) {
        int i = tkMapper.deleteByIds(IdUtils.toString(strings));
        return i;
    }

    @Override
    public Integer findCountByPo(T o) {
        return tkMapper.selectCount(o);
    }

    @Override
    public StructurePageInfo findPageByPo(T o, ReqPage reqPage) {
        PageHelper.startPage(reqPage.getPage(),reqPage.getRows());
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(o));
        Example example = new Example(o.getClass());
        Example.Criteria criteria = example.createCriteria();
        for (String key :jsonObject.keySet()) {
            criteria.andEqualTo(key,jsonObject.get(key));
        }
        criteria.andLessThanOrEqualTo("createTime",reqPage.getQueryTimestamp());
        example.orderBy("createTime").desc();
        List list = tkMapper.selectByCondition(example);
        StructurePageInfo structurePageInfo = new StructurePageInfo(list);
        return structurePageInfo;
    }

    @Override
    public StructurePageInfo<T> findPageByQueryPage(ReqPage reqPage) {
        PageHelper.startPage(reqPage.getPage(),reqPage.getRows());
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(reqPage.getParameter()));
        jsonObject.put("queryTimestamp",reqPage.getQueryTimestamp());
        List pageByQueryPage = tkMapper.findPageByQueryPage(jsonObject);
        StructurePageInfo structurePageInfo = new StructurePageInfo(pageByQueryPage);
        return structurePageInfo;
    }
}