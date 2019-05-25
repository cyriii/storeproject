package com.cyriii.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.StoreInfoDao;
import com.cyriii.entity.PageVO;
import com.cyriii.entity.StoreInfo;
import com.cyriii.entity.StoreInfoVO;
import com.cyriii.service.StoreInfoService;
import com.cyriii.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreInfoStoreServiceImpl extends ServiceImpl<StoreInfoDao, StoreInfo> implements StoreInfoService {

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private StoreInfoDao storeInfoDao;

    @Override
    public IPage<StoreInfoVO> page(PageVO page){
        page.getParams().put("userId", userUtils.currentUserId());
        return storeInfoDao.page(new Page<>(page.getCurrent(), page.getSize()), page.getParams());
    }

}
