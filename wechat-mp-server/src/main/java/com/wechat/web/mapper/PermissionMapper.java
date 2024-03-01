package com.wechat.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wechat.web.domain.Permission;
import com.wechat.web.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "PermissionMapper")
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> selectListByUserId(Integer id);
    List<Role> selectRoleListByUserId(Integer id);

}
