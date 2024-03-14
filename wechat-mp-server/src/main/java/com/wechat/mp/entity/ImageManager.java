package com.wechat.mp.entity;

import lombok.Data;

/**
 * imageManager
 */
@Data
public class ImageManager{
    private static final long serialVersionUID = 1L;

    private String url;
    private String thumb;
    private String tag;
    private String name;
    private Integer id;
}
