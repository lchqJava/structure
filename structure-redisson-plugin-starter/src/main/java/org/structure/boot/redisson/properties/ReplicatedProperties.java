package org.structure.boot.redisson.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0.0
 * @Title: ReplicatedProperties
 * @Package org.structure.boot.redisson.properties
 * @Description: 云托管
 * @author: chuck
 * @date: 2020/6/23 12:29
 */
@Getter
@Setter
@ToString
public class ReplicatedProperties extends MultipleServerProperties {
    private List<String> nodeAddresses = new ArrayList();
    private int scanInterval = 1000;
}
