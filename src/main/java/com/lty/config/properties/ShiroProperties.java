package com.lty.config.properties;

import lombok.Data;

@Data
public class ShiroProperties {

    private String projectName;
    private String anonUrl;

    /**
     * token默认有效时间 1天
     */
    private Long jwtTimeOut = 86400L;


}
