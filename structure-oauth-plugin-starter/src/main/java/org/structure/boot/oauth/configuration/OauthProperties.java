package org.structure.boot.oauth.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Administrator
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "structure.oauth")
public class OauthProperties {
    private String jksPath;
    private String password;
    private String keyPair;
}
