package com.wechat.web.domain;


import lombok.Data;

@Data
public class Role  extends BaseEntity{
    private Integer id;
    private String roleName;
}
