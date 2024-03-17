package com.wechat.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    //后台页面
    @RequestMapping("/index")
    public String backPage() {
        return "index";
    }

    //前台页面
    @RequestMapping("/front/{frontPage}")
    public String frontPage(@PathVariable String frontPage) {
        return "/front/" + frontPage;
    }

}
