package org.structure.boot.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @version V1.0.0
 * @Title: ControllerLog
 * @Package org.structure.boot.web.entity
 * @Description: controller日志实体
 * @author: chuck
 * @date: 2020/6/16 12:56
 */
@Getter
@Setter
@ToString
public class ControllerLog extends BaseLog {

    private String method;

    private String url;

    private String ipAddress;
}
