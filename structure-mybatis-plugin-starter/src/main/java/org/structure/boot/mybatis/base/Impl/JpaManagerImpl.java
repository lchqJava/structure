package org.structure.boot.mybatis.base.Impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.structure.boot.mybatis.base.BaseManager;
import org.structure.boot.mybatis.base.interfaces.IBaseManager;
import org.structure.boot.mybatis.common.ReqPage;
import org.structure.boot.mybatis.common.StructurePageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaManagerImpl<T> extends BaseManager implements IBaseManager<T,String> {

    private JpaRepository<T,String> jpaMapper;

    @Override
    public void setMapper(Object mapper) {
        if (mapper instanceof JpaRepository) {
            jpaMapper = (JpaRepository) mapper;
        }else {
            return;
        }
        super.setMapper(mapper);
    }

    @Override
    public T insert(T t) {
        return jpaMapper.save(t);
    }

    @Override
    public Integer insertList(List<T> ts) {
        List<T> ts1 = jpaMapper.saveAll(ts);
        return ts1.size();
    }

    @Override
    public T selectById(String s) {
        Optional<T> byId = jpaMapper.findById(s);
        return byId.get();
    }

    @Override
    public List<T> selectByIds(List<String> strings) {
        return jpaMapper.findAllById(strings);
    }

    @Override
    public List<T> selectCondition(Object object) {
        Example example = Example.of(object);
        List all = jpaMapper.findAll(example);
        return all;
    }

    @Override
    public T selectOne(T t) {
        Example example = Example.of(t);
        Optional<T> one = jpaMapper.findOne(example);
        return one.get();
    }

    @Override
    public List<T> selectByPo(T t) {
        Example example = Example.of(t);
        return jpaMapper.findAll(example);
    }

    @Override
    public Integer updatePoById(T t) {
        T save = jpaMapper.save(t);
        if (null != save) {
            return 1;
        }
        return 0;
    }

    @Override
    public Integer updatePoByCondition(T t, Object condition) {
        Example example = Example.of(condition);
        List<T> all = jpaMapper.findAll(example);
        List<T> saveList = new ArrayList<>();
        for (T tt:all) {
            JSONObject tJson = JSONObject.parseObject(JSONObject.toJSONString(t));
            JSONObject ttJson = JSONObject.parseObject(JSONObject.toJSONString(tt));
            for (String key :tJson.keySet()) {
                if (tJson.get(key) != null) {
                    ttJson.put(key,tJson.get(key));
                }
            }
            Object object = JSONObject.parseObject(ttJson.toJSONString(), t.getClass());
           saveList.add((T)object);
        }
        List<T> ts = jpaMapper.saveAll(saveList);
        return ts.size();
    }

    @Override
    public Integer deleteById(String s) {
        jpaMapper.deleteById(s);
        return 1;
    }

    @Override
    public Integer deleteByIds(List<String> strings) {
        int row = 0;
        for (String id:strings) {
            jpaMapper.deleteById(id);
            row ++;
        }
        return row;
    }

    @Override
    public Integer findCountByPo(T t) {
        Example<T> example = Example.of(t);
        long count = jpaMapper.count(example);
        return Integer.parseInt(String.valueOf(count));
    }

    @Override
    public StructurePageInfo<T> findPageByPo(T t, ReqPage reqPage) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = new PageRequest(reqPage.getPage() - 1, reqPage.getRows(), sort);
        Example<T> example = Example.of(t);
        Page<T> list = jpaMapper.findAll(example,pageable);
        StructurePageInfo structurePageInfo = new StructurePageInfo(list.getContent());
        structurePageInfo.setPages(list.getTotalPages());
        structurePageInfo.setTotal(list.getTotalElements());
        return structurePageInfo;
    }

    @Override
    public StructurePageInfo<T> findPageByQueryPage(ReqPage reqPage) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = new PageRequest(reqPage.getPage() - 1, reqPage.getRows(), sort);
        Example parameter = (Example)reqPage.getParameter();
        Page<T> list = jpaMapper.findAll(parameter,pageable);
        StructurePageInfo structurePageInfo = new StructurePageInfo(list.getContent());
        structurePageInfo.setPages(list.getTotalPages());
        structurePageInfo.setTotal(list.getTotalElements());
        return structurePageInfo;
    }
}
