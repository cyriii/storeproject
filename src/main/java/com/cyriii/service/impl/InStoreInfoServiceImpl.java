package com.cyriii.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.InStoreInfoDao;
import com.cyriii.entity.GoodInfo;
import com.cyriii.entity.InStoreInfo;
import com.cyriii.entity.PageVO;
import com.cyriii.entity.StoreInfo;
import com.cyriii.service.InStoreInfoService;
import com.cyriii.service.StoreInfoService;
import com.cyriii.utils.UUIDUtils;
import com.cyriii.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InStoreInfoServiceImpl extends ServiceImpl<InStoreInfoDao, InStoreInfo> implements InStoreInfoService {

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private StoreInfoService storeInfoService;

    @Override
    public boolean save(InStoreInfo entity) {
        // 判断库存信息是否存在
        QueryWrapper<StoreInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("good_id", entity.getGoodId());
        StoreInfo storeInfo = storeInfoService.getOne(queryWrapper);
        if(storeInfo == null){
            // 库存信息不存在 新建
            storeInfo = new StoreInfo();
            storeInfo
                    .setId(UUIDUtils.getUUID())
                    .setGoodId(entity.getGoodId())
                    .setStoreNum(entity.getSupplyNum());
            storeInfoService.save(storeInfo);
        } else {
            // 库存信息存在修改库存量
            storeInfo.setStoreNum(storeInfo.getStoreNum().add(entity.getSupplyNum()));
            storeInfoService.updateById(storeInfo);
        }

        entity
                .setId(UUIDUtils.getUUID())
                .setUserId(userUtils.currentUserId())
                .setCreateDate(new Date());
        return super.save(entity);
    }
}
