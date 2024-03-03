package com.wechat.mp.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class MenuController {

    @Autowired
    private WxMpService wxMpService;

    @PreAuthorize("@xh.hasAuth('menu:create')")
    @GetMapping("/createMenu")
    public String createMenu() throws WxErrorException {
        String json="{\"button\": [{\"name\": \"Magic\",\"type\": \"click\",\"key\": \"magic\"},{\"name\": \"授权登录\",\"type\": \"view\",\"url\": \"https://funchx.mynatapp.cc/authPage\"}]}";
        // 获取菜单信息
        WxMpGetSelfMenuInfoResult selfMenuInfo = wxMpService.getMenuService().getSelfMenuInfo();
        log.info("selfMenuInfo : {}", selfMenuInfo);
        return wxMpService.getMenuService().menuCreate(json);
    }

    @GetMapping("deleteMenu")
    public String deleteMenu() throws WxErrorException {
        wxMpService.getMenuService().menuDelete();
        return "ok";
    }

}
