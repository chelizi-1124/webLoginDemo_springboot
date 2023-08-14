package com.luas.controller;

import cn.hutool.core.util.IdUtil;
import com.luas.enumeration.LoginCodeEnum;
import com.luas.result.Result;
import com.luas.utils.LoginCodeUtil;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping
public class LoginCodeController {

    @Autowired
    private LoginCodeUtil loginCodeUtil;

    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("/code")
    public Result getCode(){

        Captcha captcha = loginCodeUtil.getCaptcha();
        String uuid = "code-key-"+ IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if(captcha.getCharType()-1 == LoginCodeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")){
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisTemplate.opsForValue().set(uuid,captchaValue,loginCodeUtil.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // 验证码信息
        Map<String,Object> imgResult = new HashMap<String,Object>(2){{
            put("img",captcha.toBase64());
            put("uuid",uuid);
        }};
        return Result.success(imgResult);

    }
}
