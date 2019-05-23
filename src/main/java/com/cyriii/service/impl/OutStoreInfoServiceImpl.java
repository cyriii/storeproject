package com.cyriii.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.OutStoreInfoDao;
import com.cyriii.entity.OutStoreInfo;
import com.cyriii.entity.StoreInfo;
import com.cyriii.service.OutStoreInfoService;
import com.cyriii.service.StoreInfoService;
import com.cyriii.utils.UUIDUtils;
import com.cyriii.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OutStoreInfoServiceImpl extends ServiceImpl<OutStoreInfoDao, OutStoreInfo> implements OutStoreInfoService {

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private StoreInfoService storeInfoService;

    @Override
    public boolean saveOutStoreInfo(OutStoreInfo entity) throws Exception {

        // 判断库存量够不够
        QueryWrapper<StoreInfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("good_id", entity.getGoodId());
        StoreInfo storeInfo = storeInfoService.getOne(queryWrapper);

        if(storeInfo == null || storeInfo.getStoreNum().compareTo(entity.getDemandNum()) == -1){
            throw new Exception("库存量不足");
        }

        entity
                .setId(UUIDUtils.getUUID())
                .setUserId(userUtils.currentUserId())
                .setCreateDate(new Date());
        return super.save(entity);
    }
}
