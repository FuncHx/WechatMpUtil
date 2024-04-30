package com.wechat.mp.controller;


import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.PageList;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wechat.mp.entity.WxUser;
import com.wechat.mp.service.WxUserService;
import com.wechat.web.util.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Slf4j
@RestController
@RequestMapping("/mp/user")
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private WxMpService wxMpService;

    @PostMapping("/list")
    public Response list(@RequestParam("current") Integer currentPage,
                         @RequestParam("size") Integer size,
                         @RequestBody WxUser wxUser) {
        LambdaQueryWrapper<WxUser> wxUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        wxUserLambdaQueryWrapper.like(!ObjectUtils.isEmpty(wxUser.getNickName()), WxUser::getNickName, wxUser.getNickName());
        wxUserLambdaQueryWrapper.eq(!ObjectUtils.isEmpty(wxUser.getCity()), WxUser::getCity, wxUser.getCity());
        wxUserLambdaQueryWrapper.eq(!ObjectUtils.isEmpty(wxUser.getSubscribe()), WxUser::getSubscribe, wxUser.getSubscribe());
        wxUserLambdaQueryWrapper.eq(!ObjectUtils.isEmpty(wxUser.getCountry()), WxUser::getCountry, wxUser.getCountry());
        wxUserLambdaQueryWrapper.eq(!ObjectUtils.isEmpty(wxUser.getSex()), WxUser::getSex, wxUser.getSex());
        wxUserLambdaQueryWrapper.eq(!ObjectUtils.isEmpty(wxUser.getOpenId()), WxUser::getOpenId, wxUser.getOpenId());
        wxUserLambdaQueryWrapper.eq(!ObjectUtils.isEmpty(wxUser.getSubscribeScene()), WxUser::getSubscribeScene, wxUser.getSubscribeScene());
        if (ObjectUtils.isNotEmpty(wxUser.getTagidList())){
            if (wxUser.getTagidList().size() > 0) {
                Integer tagId = wxUser.getTagidList().get(0);
                wxUserLambdaQueryWrapper.eq(WxUser::getTagidList, "[" + tagId + "]")
                        .or()
                        .like(WxUser::getTagidList, "," + tagId + ",")
                        .or()
                        .likeRight(WxUser::getTagidList, "[" + tagId + ",")
                        .or()
                        .likeLeft(WxUser::getTagidList, "," + tagId + "]");
            }
        }
        Page<WxUser> wxUserPage = new Page<>(currentPage, size);
        Page<WxUser> page = wxUserService.page(wxUserPage, wxUserLambdaQueryWrapper);
        System.out.println(page.getRecords());
        return Response.ok().data(page);
    }

    /**
     * 修改微信用户备注
     * @param wxUser
     * @return
     */
    @PostMapping("/remark")
    public Response remark(@RequestBody WxUser wxUser) throws WxErrorException {
        wxMpService.getUserService().userUpdateRemark(wxUser.getOpenId(), wxUser.getRemark());
        boolean b = wxUserService.updateById(wxUser);
        return Response.ok().message("备注修改成功");
    }

}
