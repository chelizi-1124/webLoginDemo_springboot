package com.luas.utils;

import com.luas.Properties.LoginCodeProperties;
import com.luas.enumeration.LoginCodeEnum;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import lombok.Data;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Objects;

@Data
@Component
public class LoginCodeUtil {

    @Autowired
    private LoginCodeProperties loginCode;


    /**
     * 获取验证码生产类
     * @return
     */
    public Captcha getCaptcha(){
        if(Objects.isNull(loginCode)){
            loginCode = new LoginCodeProperties();
            if(Objects.isNull(loginCode.getCodeType())){
                loginCode.setCodeType(LoginCodeEnum.ARITHMETIC);
            }

        }
        return switchCaptcha(loginCode);
    }

    /**
     * 依据配置信息生产验证码
     * @param loginCodeProperties
     * @return
     */
    private Captcha switchCaptcha(LoginCodeProperties loginCodeProperties){
        Captcha captcha = null;
        synchronized (this){
            switch (loginCodeProperties.getCodeType()){
                case ARITHMETIC:
                    captcha = new FixedArithmeticCaptcha(loginCodeProperties.getWidth(), loginCodeProperties.getHeight());
                    captcha.setLen(loginCodeProperties.getLength());
                    break;
                case CHINESE:
                    captcha = new ChineseCaptcha(loginCodeProperties.getWidth(), loginCodeProperties.getHeight());
                    captcha.setLen(loginCodeProperties.getLength());
                    break;
                case CHINESE_GIF:
                    captcha = new ChineseGifCaptcha(loginCodeProperties.getWidth(), loginCodeProperties.getHeight());
                    captcha.setLen(loginCodeProperties.getLength());
                    break;
                case GIF:
                    captcha = new GifCaptcha(loginCodeProperties.getWidth(), loginCodeProperties.getHeight());
                    captcha.setLen(loginCodeProperties.getLength());
                    break;
                case SPEC:
                    captcha = new SpecCaptcha(loginCodeProperties.getWidth(), loginCodeProperties.getHeight());
                    captcha.setLen(loginCodeProperties.getLength());
                default:
                    System.out.println("验证码配置信息错误！正确配置查看 LoginCodeEnum ");

            }
        }
        if(StringUtils.isNotBlank(loginCodeProperties.getFontName())){
            captcha.setFont(new Font(loginCodeProperties.getFontName(),Font.PLAIN, loginCodeProperties.getFontSize()));
        }
        return captcha;
    }

    static  class FixedArithmeticCaptcha extends ArithmeticCaptcha {
        public FixedArithmeticCaptcha(int width,int height){
            super(width,height);
        }

        @Override
        protected char[] alphas() {
            // 生成随机数字和运算符
            int n1 = num(1, 10), n2 = num(1, 10);
            int opt = num(3);

            // 计算结果
            int res = new int[]{n1 + n2, n1 - n2, n1 * n2}[opt];
            // 转换为字符运算符
            char optChar = "+-x".charAt(opt);

            this.setArithmeticString(String.format("%s%c%s=?", n1, optChar, n2));
            this.chars = String.valueOf(res);

            return chars.toCharArray();
        }
    }
}