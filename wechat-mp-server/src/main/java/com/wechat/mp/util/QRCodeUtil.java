package com.wechat.mp.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.wechat.web.util.Base64;
import com.wechat.web.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Slf4j
public class QRCodeUtil {
    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度（嵌入的小图片的宽度）
    private static final int WIDTH = 60;
    // LOGO高度（嵌入的小图片的高度）
    private static final int HEIGHT = 60;

    // 生成二维码图片，返回生成好的图片路径
    public String generateQRCode(QRCodeParameter qrcodeParameters, String logoImgPath) {
        String text = generateUrl4QRCode(qrcodeParameters);
        log.info("text:" + text);

        // 生成的二维码图片存储路径
        String destPath = qrcodeParameters.getOutput();
        log.info(" QRCode path ,destPath :{}", destPath);
        File qrCodefile = new File(destPath);
        if (qrCodefile.exists()) qrCodefile.delete();

        try {
            _generateQRCode(text, logoImgPath, destPath, true);
        } catch (Exception e) {
            log.error("generateQRCode ，occurred exception ");
            throw new RuntimeException("生成二维码失败");
        }
        return destPath;
    }




    // url编码处理. http://aaa.b.com?id=100 url进行字符编码处理
    public static String urlEncoder(String urlPrefix, List<NameValuePair> parameters) {
        String charset = "utf-8";
        String result = URLEncodedUtils.format(parameters, charset);
        String url = urlPrefix + "?" + result;
        return url;
    }
    /**
     * 生成二维码的公网url地址。扫码了二维码跳转到的目的地址
     * @param qrcodeParameters
     * @return
     */
    public static String generateUrl4QRCode(QRCodeParameter qrcodeParameters) {
        String urlPrefix = qrcodeParameters.getQrcodeUrlPrefix();
        String houseIdValue = String.valueOf(qrcodeParameters.getHouseId());
        String ordinalValue = qrcodeParameters.getQrcodeInitValue();

        List<NameValuePair> parameters = new ArrayList<>();
        BasicNameValuePair item = new BasicNameValuePair("houseId", houseIdValue);
        parameters.add(item);

        item = new BasicNameValuePair("ordinal", ordinalValue);
        parameters.add(item);

        String url = urlEncoder(urlPrefix, parameters);
        log.info("url: {}", url);
        return url;
    }


    // 生成二维码图片
    public static void _generateQRCode(String content, String logoImgPath, String destPath, boolean needCompress) throws Exception {
        BufferedImage image = createImage(content, logoImgPath, needCompress);
        mkdirs(destPath);
        // String file = new Random().nextInt(99999999)+".jpg";
        // ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }

    /**
     * 生成二维码图片
     * @param content	二维码内容
     * @param logoImgPath	二维码图片上要嵌入的其他图片路径
     * @param needCompress	是否要压缩，即嵌入的图片是否要压缩嵌入到二维码上面
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String logoImgPath, boolean needCompress) throws Exception {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (logoImgPath == null || "".equals(logoImgPath)) {
            return image;
        }
        // 插入图片
        insertImage(image, logoImgPath, needCompress);
        return image;
    }


    // 两张图片嵌入在一起
    private static void insertImage(BufferedImage source, String logoImgPath, boolean needCompress) throws Exception {
        File file = new File(logoImgPath);
        if (!file.exists()) {
            System.err.println("" + logoImgPath + "   该文件不存在！");
            log.error("" + logoImgPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(logoImgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }


    // 创建目录
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    public static void main(String[] args) {
        QRCodeUtil test = new QRCodeUtil();
        QRCodeParameter parameter = new QRCodeParameter();
        parameter.setHouseId(101L);
        parameter.setQrcodeInitValue("index");
        parameter.setQrcodeUrlPrefix("http://192.168.3.16:8080/qrcode?openId=o_vvO6fcAXtcDdbYVJMLkO6Z-UeQ");
        parameter.setOutput("./test.jpg");
        String logoImgPath = "C:\\Users\\cyh\\Desktop\\profile.jpg";
        // 生成二维码
        String base64 = test.generateQRCode(parameter, logoImgPath);
        System.out.println(base64);
    }

    public static String generateQRCodeByBase64(QRCodeParameter parameter, String logoImgPath) {
        String text = parameter.getQrcodeUrlPrefix();
        try {
            BufferedImage image = createImage(text, logoImgPath, true);
            // 转换流信息写出
            FastByteArrayOutputStream os = new FastByteArrayOutputStream();
            try
            {
                ImageIO.write(image, "jpg", os);
            }
            catch (IOException e)
            {
                log.error(e.toString());
            }
            return Base64.encode(os.toByteArray());
        } catch (Exception e) {
            log.error("generateQRCode ，occurred exception ");
            throw new RuntimeException("生成二维码失败");
        }
    }


}
