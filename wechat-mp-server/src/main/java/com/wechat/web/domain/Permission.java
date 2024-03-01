package com.wechat.web.domain;

import lombok.Data;

@Data
public class Permission extends BaseEntity{

    private Integer id;
    private String authority;
    private String authorityComment;
}
