package org.structure.boot.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.structure.boot.web.properties.ConfigProperties;

@Configuration
@ConditionalOnClass(value = {ConfigProperties.class})
public class AutoConfiguration {

}
