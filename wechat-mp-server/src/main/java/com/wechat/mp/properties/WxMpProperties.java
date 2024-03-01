package com.wechat.mp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wx.mp")
public class WxMpProperties {
    private String appId;
    private String secret;
    private String token;
    private String aesKey;
}
