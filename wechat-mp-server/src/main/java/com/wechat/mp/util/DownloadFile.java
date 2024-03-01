package com.wechat.mp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 */
public class DownloadFile {

    /**
     * 下载文件
     * @param sourceURL
     * @param destFilename
     * @throws Exception
     */
    public static void download(String sourceURL, String destFilename) throws Exception {
        // 构造URL
        URL url = new URL(sourceURL);
        URLConnection con = url.openConnection();
        con.setConnectTimeout(3 * 1000);

        // 输入流
        InputStream is = con.getInputStream();
        byte[] bs = new byte[1024];
        int len;

        // 输出流
        File file = new File(destFilename);
        OutputStream os = new FileOutputStream(file);
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }

        // 关闭链接
        os.close();
        is.close();
    }

}
