package com.wechat.mp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.mp.entity.WxUser;
import com.wechat.mp.mapper.WxUserMapper;
import com.wechat.mp.service.WxUserService;
import org.springframework.stereotype.Service;

@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService {
}
