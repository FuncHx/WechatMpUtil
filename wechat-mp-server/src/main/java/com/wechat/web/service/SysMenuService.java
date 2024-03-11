package com.wechat.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wechat.web.domain.entity.SysMenu;
import com.wechat.web.domain.entity.TreeSelect;
import com.wechat.web.domain.vo.RouterVo;

import java.util.List;


public interface SysMenuService extends IService<SysMenu> {

    List<RouterVo> buildMenus(List<SysMenu> menus);

    public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus);

    List<SysMenu> selectMenuTreeByUserId(Integer userId);

    List<SysMenu> selectMenuTree(SysMenu sysMenu);
}
