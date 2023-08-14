package com.luas.Properties;

import com.luas.enumeration.LoginCodeEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "login.code")
@Data
public class LoginCodeProperties {

    /**
     * 验证码配置
     */
    private LoginCodeEnum codeType;
    /**
     * 验证码有效期 分钟
     */
    private Long expiration;
    /**
     * 验证码内容长度
     */
    private int length;
    /**
     * 验证码宽度
     */
    private int width;
    /**
     * 验证码高度
     */
    private int height;
    /**
     * 验证码字体
     */
    private String fontName;
    /**
     * 字体大小
     */
    private int fontSize;

    /**
     * 验证码前缀
     */
    private String codeKey;


}
