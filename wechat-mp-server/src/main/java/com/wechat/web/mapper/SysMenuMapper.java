package com.wechat.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wechat.web.domain.entity.Role;
import com.wechat.web.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "PermissionMapper")
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectListByUserId(Integer id);
    List<Role> selectRoleListByUserId(Integer id);

    List<SysMenu> selectListMenuInfoByUserId(Integer id);

}
