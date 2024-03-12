package com.wechat.web.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role_menu_relation")
public class RoleMenuRelation {
    private Integer id;
    private Integer roleId;
    private Integer menuId;
}
