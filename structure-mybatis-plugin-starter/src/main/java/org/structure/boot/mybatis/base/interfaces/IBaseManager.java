package org.structure.boot.mybatis.base.interfaces;

import org.structure.boot.mybatis.common.ReqPage;
import org.structure.boot.mybatis.common.StructurePageInfo;

import java.util.List;

/**
 * @version V1.0.0
 * @Title: IBaseManager
 * @Package com.moomking.auxiliary.common.base.interfaces
 * @Description: manager Base
 * @author: liuChuanqiang
 * @date: 2019/8/22 15:16
 */
public interface IBaseManager<Po, ID> {

    /**
     * @param po
     * @return Po
     * @throws
     * @throws
     * @Title insert
     * @Description 通过PO进行插入
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/8/23 11:13
     * @version v1.0.0
     **/
    Po insert(Po po);

    /**
     * @param poList
     * @return int
     * @throws
     * @throws
     * @Title insertList
     * @Description 插入一个列表
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/8/23 11:14
     * @version v1.0.0
     **/
    Integer insertList(List<Po> poList);

    /**
     * @param id
     * @return Po
     * @throws
     * @throws
     * @Title selectById
     * @Description 通过ID查询 返回一条唯一的结果
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/8/22 15:49
     * @version v1.0.0
     **/
    Po selectById(ID id);

    /**
     * @param ids
     * @return java.util.List<Po>
     * @throws
     * @throws
     * @Title selectByIds
     * @Description 通过ids查询listPo
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/8/23 11:16
     * @version v1.0.0
     **/
    List<Po> selectByIds(List<ID> ids);

    /**
     * 万能条件查询
     * @param object
     * @return
     */
    List<Po> selectCondition(Object object);

    /**
     * @param po
     * @return Po
     * @throws
     * @throws
     * @Title selectOne
     * @Description 通过PO查询 返回一条结果
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/8/22 15:49
     * @version v1.0.0
     **/
    Po selectOne(Po po);

    /**
     * @param po
     * @return java.util.List<Po>
     * @throws
     * @throws
     * @Title selectByPo
     * @Description 通过PO查询
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/8/22 15:49
     * @version v1.0.0
     **/
    List<Po> selectByPo(Po po);


    /**
     * @param po
     * @return Po
     * @throws
     * @throws
     * @Title updateByPo
     * @Description 通过po修改
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/8/22 15:50
     * @version v1.0.0
     **/
    Integer updatePoById(Po po);

    /**
     * @param po
     * @param condition
     * @return int
     * @throws
     * @throws
     * @Title updatePoByCondition
     * @Description 通过条件修改
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/8/22 15:50
     * @version v1.0.0
     **/
    Integer updatePoByCondition(Po po, Object condition);

    /**
     * @param id
     * @return int
     * @throws
     * @throws
     * @Title deleteById
     * @Description 通过ID删除
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/8/22 15:50
     * @version v1.0.0
     **/
    Integer deleteById(ID id);

    /**
     * @param ids
     * @return int
     * @throws
     * @throws
     * @Title deleteByIds
     * @Description 通过IDS进行删除
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/8/23 11:15
     * @version v1.0.0
     **/
    Integer deleteByIds(List<ID> ids);

    /**
     * @Title  findCountByPo
     * @Description 通过PO查询数量
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/12/23 12:57
     * @param po
     * @version v1.0.0
     * @exception
     * @throws
     * @return java.lang.Integer
     **/
    Integer findCountByPo(Po po);

    /**
     * @Title  findPageByPo
     * @Description 单表查询时间分页
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/12/23 12:59
     * @param po
     * @param reqPage
     * @version v1.0.0
     * @exception
     * @throws
     * @return java.util.List<Po>
     **/
    default StructurePageInfo<Po> findPageByPo(Po po, ReqPage reqPage){return null;}

    /**
     * @Title  findPageByQueryPage
     * @Description 多表分页或公共分页 需要手动实现 mapper 接口
     * @author liuChuanqiang
     * @updateAuthor liuChuanqiang
     * @Date 2019/12/24 13:50
     * @param reqPage
     * @version v1.0.0
     * @exception
     * @throws
     * @return com.mogu.starter.persistence.mapper.DreamacPageInfo<Po>
     **/
    StructurePageInfo<Po> findPageByQueryPage(ReqPage reqPage);

}
