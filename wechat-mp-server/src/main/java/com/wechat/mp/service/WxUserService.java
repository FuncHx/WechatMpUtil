package com.wechat.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wechat.mp.entity.WxUser;

import java.util.ArrayList;

public interface WxUserService extends IService<WxUser> {

    boolean batchTagging(ArrayList<String> openIds, ArrayList<Integer>  tagIdList);

    boolean batchUnTagging(ArrayList<String> openIds, ArrayList<Integer>  tagIdList);

}
