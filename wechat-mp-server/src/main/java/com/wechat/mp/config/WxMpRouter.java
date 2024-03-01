package com.wechat.mp.config;


import com.wechat.mp.handle.*;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WxMpRouter {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private TextHandler textHandler;

    @Autowired
    private ImageHandler imageHandler;

    @Autowired
    private SubscribeHandler subscribeHandler;

    @Autowired
    private UnSubscribeHandler unSubscribeHandler;

    @Autowired
    private MagicHandler magicHandler;

    @Bean
    public WxMpMessageRouter messageRouter() {
        // 创建消息路由
        final WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);
        // 添加文本消息路由
        router.rule().async(false).msgType(WxConsts.XmlMsgType.TEXT).handler(textHandler).end();
        // 添加图片消息路由
        router.rule().async(false).msgType(WxConsts.XmlMsgType.IMAGE).handler(imageHandler).end();
        //添加magicEvent消息路由
        router.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .eventKey("magic").handler(magicHandler).end();
        // 添加用户关注信息路由
        router.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.SUBSCRIBE).handler(subscribeHandler).end();
        // 添加用户取消关注信息路由
        router.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.UNSUBSCRIBE).handler(unSubscribeHandler).end();
        return router;
    }

}
