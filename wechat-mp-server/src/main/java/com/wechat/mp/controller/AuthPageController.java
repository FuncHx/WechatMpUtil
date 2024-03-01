package com.wechat.mp.controller;


import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
public class AuthPageController {

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
    public String callback(String code) throws WxErrorException {
        WxOAuth2Service oAuth2Service = wxMpService.getOAuth2Service();
        // 利用code获取accessToken
        WxOAuth2AccessToken accessToken = oAuth2Service.getAccessToken(code);
        // 利用accessToken获取用户信息
        WxOAuth2UserInfo userInfo = oAuth2Service.getUserInfo(accessToken, null);
        System.out.println(userInfo);
        log.info(userInfo.toString());
        return "redirect:/magicQrCode?nikeName="
                + userInfo.getNickname() + "&headerImgUrl=" + userInfo.getHeadImgUrl()
                + "&sex=" + userInfo.getSex();
    }

}
