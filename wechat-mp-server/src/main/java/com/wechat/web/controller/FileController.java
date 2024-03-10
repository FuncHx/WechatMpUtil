package com.wechat.web.controller;

import com.wechat.web.domain.entity.CustomUser;
import com.wechat.web.service.SysUserService;
import com.wechat.web.util.FileUtils;
import com.wechat.web.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Autowired
    private SysUserService userService;

    @PostMapping("upload")
    public Response uploadFile(MultipartFile file, HttpServletRequest req) throws IOException {
        String fileName = FileUtils.saveFile(file, uploadFolder);
        String imageUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()  + staticAccessPath.replace("**", "") + fileName;
        return Response.ok().data(imageUrl).message("上传成功！");
    }

}
