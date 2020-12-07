package org.structure.boot.redisson.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0.0
 * @Title: SentinelProperties
 * @Package org.structure.boot.redisson.properties
 * @Description: 哨兵模式
 * @author: chuck
 * @date: 2020/6/23 12:25
 */
@Getter
@Setter
@ToString
public class SentinelProperties extends MultipleServerProperties {
    private List<String> sentinelAddresses = new ArrayList();
    private String masterName;
    private int scanInterval = 1000;
}
