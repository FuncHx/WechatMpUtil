package com.wechat.mp.handle;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用户关注
 */
@Slf4j
@Component
public class SubscribeHandler implements WxMpMessageHandler {

    @Autowired
    private WxUserService wxUserService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        LambdaQueryWrapper<WxUser> wxUserQueryWrapper = new LambdaQueryWrapper<>();
        wxUserQueryWrapper.eq(WxUser::getOpenId, wxMpXmlMessage.getFromUser());
        WxUser one = wxUserService.getOne(wxUserQueryWrapper);
        WxMpUser wxMpUser = wxMpService.getUserService().userInfo(wxMpXmlMessage.getFromUser());
        // 判断用户之前是否关注过
        if (ObjectUtils.isEmpty(one)) {
            WxUser wxUser = new WxUser();
            wxUser.setOpenId(wxMpXmlMessage.getFromUser());
            wxUser.setCity(wxMpXmlMessage.getCity());
            wxUser.setProvince(wxMpXmlMessage.getProvince());
            wxUser.setSubscribe("1");
            wxUser.setAppType("2");
            wxUser.setSex(wxMpXmlMessage.getSex());
            wxUser.setSubscribeNum(1);
            wxUser.setNickName("");
            wxUser.setCountry(wxMpXmlMessage.getCountry());
            wxUserService.save(wxUser);
        }else {
            one.setSubscribeNum(one.getSubscribeNum() + 1);
            wxUserService.updateById(one);
        }
        return WxMpXmlOutMessage.TEXT()
                .fromUser(wxMpXmlMessage.getToUser())
                .toUser(wxMpXmlMessage.getFromUser())
                .content("感谢关注").build();
    }
}
