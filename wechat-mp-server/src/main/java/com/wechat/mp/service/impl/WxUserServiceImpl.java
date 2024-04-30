package com.wechat.mp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.mp.entity.WxUser;
import com.wechat.mp.mapper.WxUserMapper;
import com.wechat.mp.service.WxUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService {

    @Autowired
    private WxUserMapper wxUserMapper;

    /**
     * 批量修改用户标签数据
     * @param openIds
     * @param tagIdList
     * @return
     */
    @Override
    public boolean batchTagging(ArrayList<String> openIds, ArrayList<Integer>  tagIdList) {
        for (String openId : openIds) {
            LambdaQueryWrapper<WxUser> wxUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
            wxUserLambdaQueryWrapper.eq(WxUser::getOpenId, openId);
            WxUser wxUser = wxUserMapper.selectOne(wxUserLambdaQueryWrapper);
            ArrayList<Integer> tagidList1 = wxUser.getTagidList();
            if (ObjectUtils.isEmpty(tagidList1)) {
                wxUser.setTagidList(tagIdList);
            }else {
                for (Integer tagId : tagIdList) {
                    if (tagidList1.indexOf(tagId) < 0) {
                        tagidList1.add(tagId);
                    }
                }
            }
            wxUserMapper.updateById(wxUser);
        }
        return true;
    }

    @Override
    public boolean batchUnTagging(ArrayList<String> openIds, ArrayList<Integer> tagIdList) {
        for (String openId : openIds) {
            LambdaQueryWrapper<WxUser> wxUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
            wxUserLambdaQueryWrapper.eq(WxUser::getOpenId, openId);
            WxUser wxUser = wxUserMapper.selectOne(wxUserLambdaQueryWrapper);
            ArrayList<Integer> tagidList1 = wxUser.getTagidList();
            System.out.println(tagidList1);
            for (Integer tagId : tagIdList) {
                System.out.println(tagidList1.indexOf(tagId));
                if (tagidList1.indexOf(tagId) >= 0) {
                    tagidList1.remove(tagId);
                    System.out.println("remove");
                }
            }
            wxUserMapper.updateById(wxUser);
        }
        return true;
    }
}
