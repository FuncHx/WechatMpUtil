package com.wechat.mp.controller;


import com.wechat.mp.service.WxUserService;
import com.wechat.web.util.Response;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.tag.WxTagListUser;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户标签
 */
@RestController
@RequestMapping("/mp/tag")
public class TagController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxUserService wxUserService;

    /**
     * 获取所有已经创建的用户标签
     * @return
     * @throws WxErrorException
     */
    @GetMapping("/list")
    public Response list() throws WxErrorException {
        List<WxUserTag> wxUserTags = wxMpService.getUserTagService().tagGet();
        return Response.ok().data(wxUserTags);
    }

    /**
     * 新建用户标签
     * @param data   标签名称
     * @return
     * @throws WxErrorException
     */
    @PostMapping("/create")
    public Response create(@RequestBody Map<String, Object> data) throws WxErrorException {
        WxUserTag wxUserTag = wxMpService.getUserTagService().tagCreate(String.valueOf(data.get("name")));
        return Response.ok().data(wxUserTag);
    }

    /**
     * 删除标签
     * @param id 标签ID
     * @return
     * @throws WxErrorException
     */
    @GetMapping("/delete")
    public Response delete(@RequestParam("id") Long id) throws WxErrorException {
        Boolean aBoolean = wxMpService.getUserTagService().tagDelete(id);
        return Response.ok().message("标签删除成功！");
    }

    /**
     * 修改标签
     * @param data   标签信息<id, name>
     * @return
     * @throws WxErrorException
     */
    @PostMapping("/update")
    public Response update(@RequestBody Map<String, Object> data) throws WxErrorException {
        Boolean aBoolean = wxMpService.getUserTagService().tagUpdate(Long.parseLong(String.valueOf(data.get("id"))), String.valueOf(data.get("name")));
        return Response.ok().message("标签修改成功！");
    }

    /**
     * 获取标签下所有用户
     * @param id   标签信息
     * @param nextOpenid   标签信息
     * @return
     * @throws WxErrorException
     */
    @GetMapping("/getTagUserList")
    public Response getTagUserList(@RequestParam Long id, @RequestParam(defaultValue = "") String nextOpenid) throws WxErrorException {
        WxTagListUser wxTagListUser = wxMpService.getUserTagService().tagListUser(id, nextOpenid);
        return Response.ok().data(wxTagListUser);
    }

    /**
     * 批量为用户打标签
     * @param data
     * @return
     * @throws WxErrorException
     */
    @PostMapping("/batchtagging")
    public Response batchTagging(@RequestBody Map<String, Object> data) throws WxErrorException {
        ArrayList<String> openidList = (ArrayList<String>) data.get("openidList");
        String[] idList = new String[openidList.size()];
        idList = openidList.toArray(idList);
        ArrayList<Integer> ids = (ArrayList<Integer>) data.get("id");
        ArrayList<Integer> ids2 = new ArrayList<>();
        for (Integer id : ids) {
            boolean b = wxMpService.getUserTagService().batchTagging(Long.parseLong(String.valueOf(id)), idList);
            ids2.add(id);
        }
        wxUserService.batchTagging(openidList, ids2);
        return Response.ok();
    }

    /**
     * 批量为用户取消标签
     * @param data
     * @return
     * @throws WxErrorException
     */
    @PostMapping("/batchuntagging")
    public Response batchUnTagging(@RequestBody Map<String, Object> data) throws WxErrorException {
        ArrayList<String> openidList = (ArrayList<String>) data.get("openidList");
        String[] idList = new String[openidList.size()];
        idList = openidList.toArray(idList);
        ArrayList<Integer> ids = (ArrayList<Integer>) data.get("id");
        ArrayList<Integer> ids2 = new ArrayList<>();
        for (Integer id : ids) {
            boolean b = wxMpService.getUserTagService().batchUntagging(Long.parseLong(String.valueOf(id)), idList);
            ids2.add(id);
        }
        wxUserService.batchUnTagging(openidList, ids2);
        return Response.ok();
    }

}
