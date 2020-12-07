package org.structure.boot.redisson.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/**
 * @version V1.0.0
 * @Title: MasterSlaveProperties
 * @Package org.structure.boot.redisson.properties
 * @Description: 主从模式
 * @author: chuck
 * @date: 2020/6/23 12:28
 */
@Getter
@Setter
@ToString
public class MasterSlaveProperties extends MultipleServerProperties{
    private Set<String> slaveAddresses = new HashSet();
    private String masterAddress;
}
