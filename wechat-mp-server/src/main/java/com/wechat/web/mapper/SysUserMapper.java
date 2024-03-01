package com.wechat.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wechat.web.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
