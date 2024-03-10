package com.wechat.web.domain.entity;


import lombok.Data;

@Data
public class Role  extends BaseEntity{
    private Integer id;
    private String roleName;
    private String role;
}
