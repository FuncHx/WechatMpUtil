package com.wechat.web.domain.vo;

import com.wechat.web.domain.entity.SysUser;
import lombok.Data;

@Data
public class LoginUser extends SysUser {

    private String code;

    private String uuid;

}
