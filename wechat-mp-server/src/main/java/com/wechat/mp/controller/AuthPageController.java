package com.wechat.mp.controller;


import com.wechat.mp.entity.MagicTemp;
import com.wechat.mp.util.DownloadFile;
import com.wechat.mp.util.QRCodeParameter;
import com.wechat.mp.util.QRCodeUtil;
import com.wechat.web.util.RedisUtil;
import com.wechat.web.util.Response;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequestMapping("/mp")
public class AuthPageController {


    @Autowired
    private RedisUtil redisUtil;

    @Value("${file.uploadFolder}")
    private String filePath;

    @Autowired
    private WxMpService wxMpService;
    /**
     * 构造网页授权url
     *
     * @return
     */
    @GetMapping("authPage")
    public String authPage() {
        WxOAuth2Service wxOAuth2Service = wxMpService.getOAuth2Service();
        String callBackUrl = "https://funchx.mynatapp.cc/callback";
        String url = wxOAuth2Service.buildAuthorizationUrl(callBackUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
        return url;
    }


    /**
     * 用户确认授权后的回调处理
     */
    @GetMapping("callback")
    public RedirectView  callback(String code) throws Exception {
        WxOAuth2Service oAuth2Service = wxMpService.getOAuth2Service();
        // 利用code获取accessToken
        WxOAuth2AccessToken accessToken = oAuth2Service.getAccessToken(code);
        // 利用accessToken获取用户信息
        WxOAuth2UserInfo userInfo = oAuth2Service.getUserInfo(accessToken, null);
        MagicTemp magicTemp = new MagicTemp();
        DownloadFile.download(userInfo.getHeadImgUrl(), filePath + "code" + userInfo.getOpenid() + ".jpg");
        QRCodeParameter qrCodeParameter = new QRCodeParameter();
        qrCodeParameter.setQrcodeUrlPrefix("https://112.74.72.22/index#/magic?openId=" + userInfo.getOpenid());
        String s = QRCodeUtil.generateQRCodeByBase64(qrCodeParameter, filePath + "code" + userInfo.getOpenid() + ".jpg");
        magicTemp.setUploadImg("/static/" + userInfo.getOpenid() + ".jpg");
        magicTemp.setHeaderImg(userInfo.getHeadImgUrl());
        magicTemp.setNikeName(userInfo.getNickname());
        magicTemp.setCodeImage(s);
        redisUtil.set(userInfo.getOpenid(), magicTemp);
        redisUtil.expire(userInfo.getOpenid(), 3600);
        ModelAndView modelAndView = new ModelAndView();

        return new RedirectView("/index#/qrcode?openId="+userInfo.getOpenid());
    }

    @GetMapping("getUserInfo")
    @ResponseBody
    public Response getUserInfo(@RequestParam String openId) {
        MagicTemp o = (MagicTemp)redisUtil.get(openId);
        return Response.ok().data(o);
    }

}
