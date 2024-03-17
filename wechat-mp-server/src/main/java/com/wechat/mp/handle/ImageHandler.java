package com.wechat.mp.handle;

import com.wechat.mp.util.DownloadFile;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.util.Map;


/**
 * 图片消息处理类
 */
@Slf4j
@Component
public class ImageHandler implements WxMpMessageHandler {

    @Value("${file.uploadFolder}")
    private String filePath;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
        if (sessionManager.getSession(wxMessage.getFromUser()).getAttribute("type").equals("magic-image") &&
                wxMessage.getMsgType().equals(WxConsts.MassMsgType.IMAGE)) {
            String imgUrl = wxMessage.getPicUrl();
            try {
                // 保存用户发送的图片至本地
                DownloadFile.download(imgUrl, filePath + wxMessage.getFromUser() + ".jpg");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            sessionManager.getSession(wxMessage.getFromUser()).removeAttribute("type");
            // 创建需要用户授权网页链接
            WxOAuth2Service oAuth2Service = wxMpService.getOAuth2Service();
            String callbackUrl = "http://112.74.72.22/mp/callback";
            String url = oAuth2Service.buildAuthorizationUrl(callbackUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
            return WxMpXmlOutMessage.TEXT().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                    .content("请点击该链接：\n" + url).build();
        }
        // 原样返回收到的图片
        return WxMpXmlOutMessage.IMAGE().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                .mediaId(wxMessage.getMediaId()).build();
    }
}
