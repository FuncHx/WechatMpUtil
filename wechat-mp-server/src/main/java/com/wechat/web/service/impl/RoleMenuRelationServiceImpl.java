package com.wechat.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.web.domain.entity.RoleMenuRelation;
import com.wechat.web.mapper.RoleMenuRelationMapper;
import com.wechat.web.service.RoleMenuRelationService;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuRelationServiceImpl extends ServiceImpl<RoleMenuRelationMapper, RoleMenuRelation> implements RoleMenuRelationService {
}
