package com.cyriii.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.StoreInfoDao;
import com.cyriii.entity.StoreInfo;
import com.cyriii.service.StoreInfoService;
import org.springframework.stereotype.Service;

@Service
public class StoreInfoStoreServiceImpl extends ServiceImpl<StoreInfoDao, StoreInfo> implements StoreInfoService {
}
