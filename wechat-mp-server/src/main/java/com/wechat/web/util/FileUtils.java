package com.wechat.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import cn.hutool.core.lang.UUID;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件工具类
 */
@Slf4j
public class FileUtils {

    /**
     * 将文件保存到本地
     * @param file
     * @return
     * @throws IOException
     */
    public static String saveFile(MultipartFile file, String path) throws IOException {
        // 生成新的文件名
        String fileName = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String realPath = path  + fileName;
        System.out.println(realPath);
        File dest = new File(realPath);
        // 判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        // 保存文件
        file.transferTo(dest);
        return fileName;
    }

    /**
     * 删除文件
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 将MultipartFile转为File
     * @param mulFile
     * @return
     */
    public static File multipartFileToFile(MultipartFile mulFile) throws IOException {
        InputStream ins = mulFile.getInputStream();
        String fileName = mulFile.getOriginalFilename();
        String prefix = getFileNameNoEx(fileName)+ UUID.fastUUID();
        String suffix = "."+getExtensionName(fileName);
        File toFile = File.createTempFile(prefix,suffix);
        OutputStream os = new FileOutputStream(toFile);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
        return toFile;
    }

    /**
     * 获取文件扩展名
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 获取不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }


}
