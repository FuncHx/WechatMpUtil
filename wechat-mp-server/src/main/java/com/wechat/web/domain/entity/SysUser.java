package com.wechat.web.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;


@Data
public class SysUser extends BaseEntity{

    private static final long serialVersionUID = 1L;


    /** 用户账号 */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @TableField(value = "user_name")
    private String username;

    /** 用户昵称 */
    private String nickName;

    /** 用户邮箱 */
    private String email;

    /** 手机号码 */
    private String phonenumber;

    /** 用户性别（0男 1女 2未知） */
    private String sex;

    /** 头像地址 */
    private String avatar;

    /** 密码 */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /** 帐号状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String loginIp;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loginDate;

    @TableField(exist = false)
    private ArrayList<String> permission;

    @TableField(exist = false)
    private ArrayList<String> roles;
}
