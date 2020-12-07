package org.structure.boot.mybatis.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Setter;

import javax.validation.Valid;
import java.util.Date;

/**
 * @Title: ReqPage
 * @Package com.structure.boot.common.object
 * @Description: 分页入参
 * @author: chuck
 * @date: 2019/12/17 17:40
 * @Version V1.0.0
 */
@Setter
public class ReqPage<T> {
    private Integer page;
    private Integer rows;
    private Date queryTimestamp;
    @JSONField(serialize=false)
    private String queryType;
    @Valid
    private T parameter;

    public Integer getPage() {
        return page;
    }

    public Integer getRows() {
        return rows;
    }

    public Date getQueryTimestamp() {
        if(this.queryTimestamp == null){
            return new Date();
        }
        return queryTimestamp;
    }

    public T getParameter() {
        return parameter;
    }

    public String getQueryType() {
        return queryType;
    }
}
