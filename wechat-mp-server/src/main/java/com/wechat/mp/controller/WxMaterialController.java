package com.wechat.mp.controller;

import com.wechat.mp.entity.ImageManager;
import com.wechat.web.util.FileUtils;
import com.wechat.web.util.Response;
import me.chanjar.weixin.common.error.WxErrorException;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMaterialService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信素材
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/mp")
public class WxMaterialController {

    private final WxMpService wxService;

    /**
     * 上传非图文微信素材
     * @param mulFile
     * @param mediaType
     * @return
     */
    @PostMapping("/materialFileUpload")
    //	@PreAuthorize("@ss.hasPermi('wxmp:wxmaterial:add')")
    public Response materialFileUpload(@RequestParam("file") MultipartFile mulFile,
                                       @RequestParam("title") String title,
                                       @RequestParam("introduction") String introduction,
                                       @RequestParam("mediaType") String mediaType) {
        try {
            WxMpMaterial material = new WxMpMaterial();
            material.setName(mulFile.getOriginalFilename());
            if(WxConsts.MediaFileType.VIDEO.equals(mediaType)){
                material.setVideoTitle(title);
                material.setVideoIntroduction(introduction);
            }
            File file = FileUtils.multipartFileToFile(mulFile);
            material.setFile(file);
            WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
            WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpMaterialService.materialFileUpload(mediaType,material);
            WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem wxMpMaterialFileBatchGetResult = new WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem();
            wxMpMaterialFileBatchGetResult.setName(file.getName());
            wxMpMaterialFileBatchGetResult.setMediaId(wxMpMaterialUploadResult.getMediaId());
            wxMpMaterialFileBatchGetResult.setUrl(wxMpMaterialUploadResult.getUrl());
            return Response.ok().data(wxMpMaterialFileBatchGetResult);
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("上传非图文微信素材失败" + e);
            return Response.error().message(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传失败", e);
            return Response.error().message(e.getLocalizedMessage());
        }
    }

    /**
     * 上传图文消息内的图片获取URL
     * @param mulFile
     * @return
     */
    @PostMapping("/newsImgUpload")
    //	@PreAuthorize("@ss.hasPermi('wxmp:wxmaterial:add')")
    public String newsImgUpload(@RequestParam("file") MultipartFile mulFile) throws Exception {
        File file = FileUtils.multipartFileToFile(mulFile);
        WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
        WxMediaImgUploadResult wxMediaImgUploadResult = wxMpMaterialService.mediaImgUpload(file);
        Map<Object, Object> responseData = new HashMap<>();
        responseData.put("link", wxMediaImgUploadResult.getUrl());
        return JSONUtil.toJsonStr(responseData);
    }

    /**
     * 通过id删除微信素材
     * @param
     * @return R
     */
    @DeleteMapping
    @PreAuthorize("@hx.hasPermi('wxmp:wxmaterial:del')")
    public Response materialDel(String id){
        WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
        try {
            return  Response.ok().data(wxMpMaterialService.materialDelete(id));
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("删除微信素材失败", e);
            return Response.error().message(e.getMessage());
        }
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param type
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@hx.hasPermi('wxmp:wxmaterial:index')")
    public Response getWxMaterialPage(Page page, String type) {
        try {
            WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
            int count = (int)page.getSize();
            int offset = (int)page.getCurrent()*count-count;
            if(WxConsts.MaterialType.NEWS.equals(type)){
                return  Response.ok().data(wxMpMaterialService.materialNewsBatchGet(offset,count));
            }else{
                return  Response.ok().data(wxMpMaterialService.materialFileBatchGet(type,offset,count));
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("查询素材失败", e);
            return Response.error().message(e.getMessage());
        }
    }

    /**
     * 分页查询2
     * @param type
     * @return
     */
    @GetMapping("/page-manager")
    public String getWxMaterialPageManager(Integer count, Integer offset, String type) throws WxErrorException {
        List<ImageManager> listImageManager = new ArrayList<>();
        WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
        List<WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem> list = wxMpMaterialService.materialFileBatchGet(type,offset,count).getItems();
        list.forEach(wxMaterialFileBatchGetNewsItem -> {
            ImageManager imageManager = new ImageManager();
            imageManager.setName(wxMaterialFileBatchGetNewsItem.getMediaId());
            imageManager.setUrl(wxMaterialFileBatchGetNewsItem.getUrl());
            imageManager.setThumb(wxMaterialFileBatchGetNewsItem.getUrl());
            listImageManager.add(imageManager);
        });
        return JSONUtil.toJsonStr(listImageManager);
    }

    /**
     * 获取微信视频素材
     * @param
     * @return R
     */
    @GetMapping("/materialVideo")
    @PreAuthorize("@hx.hasPermi('wxmp:wxmaterial:get')")
    public Response getMaterialVideo(String mediaId){
        WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
        try {
            return  Response.ok().data(wxMpMaterialService.materialVideoInfo(mediaId));
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("获取微信视频素材失败", e);
            return Response.error().message(e.getMessage());
        }
    }

    /**
     * 获取微信素材直接文件
     * @param
     * @return R
     */
    @GetMapping("/materialOther")
    @PreAuthorize("@hx.hasPermi('wxmp:wxmaterial:get')")
    public ResponseEntity<byte[]> getMaterialOther(String mediaId, String fileName) throws Exception {
        try {
            WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
            //获取文件
            InputStream is = wxMpMaterialService.materialImageOrVoiceDownload(mediaId);
            byte[] body = new byte[is.available()];
            is.read(body);
            HttpHeaders headers = new HttpHeaders();
            //设置文件类型
            headers.add("Content-Disposition", "attchement;filename=" +  URLEncoder.encode(fileName, "UTF-8"));
            headers.add("Content-Type", "application/octet-stream");
            HttpStatus statusCode = HttpStatus.OK;
            //返回数据
            ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
            return entity;
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("获取微信素材直接文件失败", e);
            return null;
        }
    }

    /**
     * 获取微信临时素材直接文件
     * @param
     * @return R
     */
    @GetMapping("/tempMaterialOther")
//    @PreAuthorize("@hx.hasAuth('wxmp:wxmsg:index')")
    public ResponseEntity<byte[]> getTempMaterialOther(String mediaId, String fileName) throws Exception {
        try {
            WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
            //获取文件
            InputStream is = new FileInputStream(wxMpMaterialService.mediaDownload(mediaId));
            byte[] body = new byte[is.available()];
            is.read(body);
            HttpHeaders headers = new HttpHeaders();
            //设置文件类型
            headers.add("Content-Disposition", "attchement;filename=" +  URLEncoder.encode(fileName, "UTF-8"));
            headers.add("Content-Type", "application/octet-stream");
            HttpStatus statusCode = HttpStatus.OK;
            //返回数据
            ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
            return entity;
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("获取微信素材直接文件失败", e);
            return null;
        }
    }
}

