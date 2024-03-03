package com.wechat.web.controller;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.codec.Base64;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.wechat.web.util.RedisUtil;
import com.wechat.web.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.code.kaptcha.Producer;

@RestController
@Slf4j
public class KaptchaController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    public String getCheckCode() {
        return CheckCode;
    }

    public void setCheckCode(String checkCode) {
        CheckCode = checkCode;
    }

    private String CheckCode;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public Response getCode(HttpServletResponse response) throws IOException
    {
        // 保存验证码信息
        String uuid = UUID.randomUUID().toString();
        String verifyKey = "captcha_codes:" + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        capStr = code = captchaProducer.createText();
        this.setCheckCode(capStr);
        image = captchaProducer.createImage(capStr);

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return Response.error().message("验证码生成错误");
        }
        Response ajax = Response.ok();
        HashMap result = new HashMap();
        redisUtil.set(uuid, capStr);
        redisUtil.expire(uuid, 300);
        result.put("uuid", uuid);
        result.put("img", Base64.encode(os.toByteArray()));
        ajax.data(result);
        return ajax;
    }

}
