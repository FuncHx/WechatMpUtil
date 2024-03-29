package com.wechat.web.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SysMenu extends BaseEntity {
    /*
    菜单名称
     */
    private String menuName;
    /*
    显示顺序
     */
    private Integer orderNum;
    /*
    父菜单ID
     */
    private Integer parentId;
    /*
    菜单类型
     */
    private String menuType;

    /*
    菜单显示状态 0显示 1隐藏
     */
    private String visible;

    /*
    菜单状态 0正常 1 停用
     */

    private String status;

    /*
    权限标识
     */
    private String perms;
    /*
    菜单图标
     */
    private String icon;
    /*
    备注
     */
    private String remark;
    /**
     * 路由路径
     */
    private String path;
    /**
     * 组件文件路径
     */
    private String component;

    /** 子菜单 */
    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<SysMenu>();
}
