package com.wechat.mp.handle;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wechat.mp.entity.WxUser;
import com.wechat.mp.service.WxUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 用户取消关注
 */
@Slf4j
@Component
public class UnSubscribeHandler implements WxMpMessageHandler {

    @Autowired
    private WxUserService wxUserService;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        WxMpUser wxMpUser = wxMpService.getUserService().userInfo(wxMpXmlMessage.getFromUser());
        LambdaQueryWrapper<WxUser> wxUserQueryWrapper = new LambdaQueryWrapper<>();
        wxUserQueryWrapper.eq(WxUser::getOpenId, wxMpXmlMessage.getFromUser());
        WxUser wxUser = wxUserService.getOne(wxUserQueryWrapper);
        wxUser.setSubscribe("0");
        wxUser.setCancelSubscribeTime(LocalDateTime.now());
        wxUserService.updateById(wxUser);
        return WxMpXmlOutMessage.TEXT()
                .fromUser(wxMpXmlMessage.getToUser())
                .toUser(wxMpXmlMessage.getFromUser())
                .content("人生漫漫，江湖再见！")
                .build();
    }
}
