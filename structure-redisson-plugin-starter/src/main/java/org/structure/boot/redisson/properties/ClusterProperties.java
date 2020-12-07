package org.structure.boot.redisson.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0.0
 * @Title: ClusterProperties
 * @Package org.structure.boot.redisson.properties
 * @Description: 集群
 * @author: chuck
 * @date: 2020/6/23 12:23
 */
@Getter
@Setter
@ToString
public class ClusterProperties extends MultipleServerProperties {
    private Map<String, String> natMap = Collections.emptyMap();
    private List<String> nodeAddresses = new ArrayList();
}
