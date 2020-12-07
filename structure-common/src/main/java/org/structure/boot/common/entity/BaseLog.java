package org.structure.boot.common.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.structure.boot.common.enums.LogEnums;

/**
 * @version V1.0.0
 * @Title: BaseLog
 * @Package org.structure.boot.web.entity
 * @Description: ${TODO} 此类的用途
 * @author: chuck
 * @date: 2020/6/16 14:05
 */
@Getter
@Setter
@ToString
public class BaseLog {

    private LogEnums type;

    private String targetMethod;

    private Object args;

    private String beginTime;

    private String endTime;

    private Long timeDiff;

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}
