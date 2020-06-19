package com.lty.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "one")
public class SystemProperties {

    private ShiroProperties shiro = new ShiroProperties();

    private boolean openAopLog = true;

}
