package com.wechat.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wechat.web.domain.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
