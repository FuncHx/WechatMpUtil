package com.wechat.mp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MagicController {

    @GetMapping("magicQrCode")
    public String magicQrCode(@RequestParam("nikeName") String nikeName,
                              @RequestParam("headerImgUrl") String headerImgUrl,
                              @RequestParam("sex") Integer sex) {
        StringBuilder s = new StringBuilder();
        s.append("nikeName:");
        s.append(nikeName);
        s.append("\n");
        s.append("headerImgUrl:");
        s.append(headerImgUrl);
        s.append("\n");
        s.append("sex:");
        s.append(sex);
        return s.toString();
    }
}
