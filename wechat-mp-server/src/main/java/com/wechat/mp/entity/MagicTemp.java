package com.wechat.mp.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class MagicTemp implements Serializable {
    private String headerImg;
    private String nikeName;
    private String uploadImg;
    private String codeImage;
}
