package com.wechat.mp.util;


import lombok.Data;

@Data
public class QRCodeParameter {
    private String qrcodeUrlPrefix;
    private String qrcodeInitValue;
    private Long houseId;
    private String output;

}
