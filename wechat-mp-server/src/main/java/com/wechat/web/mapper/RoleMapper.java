package com.wechat.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wechat.web.domain.entity.Role;
import com.wechat.web.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    ArrayList<SysMenu> selectMenuById(Integer id);

}
