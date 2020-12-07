package org.structure.boot.mybatis.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @Title: ResPage
 * @Package com.structure.boot.common.object
 * @Description: 分页出参
 * @author: chuck
 * @date: 2019/12/17 17:40
 * @Version V1.0.0
 */
@Getter
@Setter
public class ResPage<T> {
    private Integer page;
    private Integer rows;
    private Long total;
    //@JsonFormat(timezone = "GMT+8")
    private Date queryTimestamp;
    private List<T> list;

    public <N> ResPage<N> copyResPage(List list){
        ResPage resPage = new ResPage<N>();
        resPage.setPage(this.page);
        resPage.setRows(this.rows);
        resPage.setTotal(this.total);
        resPage.setQueryTimestamp(this.queryTimestamp);
        resPage.setList(list);
        return resPage;
    }

}
