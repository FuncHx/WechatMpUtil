package com.wechat.mp.controller;

import com.alibaba.fastjson2.JSON;
import com.wechat.web.util.Response;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@Slf4j
@RestController
@RequestMapping("/mp/menu")
public class MenuController {

    @Autowired
    private WxMpService wxMpService;

    @PreAuthorize("@hx.hasAuth('wx:menu:create')")
    @PostMapping("create")
    public Response createMenu(@RequestBody HashMap menu) throws WxErrorException {
        log.info("创建菜单 : {}", JSON.toJSONString(menu));
        String s = wxMpService.getMenuService().menuCreate(JSON.toJSONString(menu));
        return Response.ok().data(s);
    }

    @PreAuthorize("@hx.hasAuth('wx:menu:list')")
    @GetMapping("list")
    public Response listMenu() throws WxErrorException {
        WxMpMenu selfMenuInfo = wxMpService.getMenuService().menuGet();
        return Response.ok().data(selfMenuInfo.getMenu());
    }



    @PreAuthorize("@hx.hasAuth('wx:menu:delete')")
    @GetMapping("deleteMenu")
    public String deleteMenu() throws WxErrorException {
        wxMpService.getMenuService().menuDelete();
        return "ok";
    }

}
