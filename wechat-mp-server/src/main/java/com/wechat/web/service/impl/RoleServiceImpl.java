package com.wechat.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.web.domain.entity.Role;
import com.wechat.web.mapper.RoleMapper;
import com.wechat.web.service.RoleService;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
