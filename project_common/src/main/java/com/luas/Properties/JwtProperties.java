
package com.luas.Properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "login.jwt")
@Data
public class JwtProperties {
    /**
     * 管理人员生产jwt令牌相关配置
     */
    private String adminSecretKey;
    private long adminTtl;   //使用 long 类型来表示时间间隔（或时间跨度）通常是为了表示毫秒数。long 类型在 Java 中可以表示比较大的整数值，适合用来表示时间跨度，特别是以毫秒为单位的时间。
    private String adminTokenName;
}
