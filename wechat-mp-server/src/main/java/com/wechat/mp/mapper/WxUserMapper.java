package com.wechat.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wechat.mp.entity.WxUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxUserMapper extends BaseMapper<WxUser> {
}
