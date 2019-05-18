package com.cyriii.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.GoodInfoDao;
import com.cyriii.entity.GoodInfo;
import com.cyriii.service.GoodInfoService;
import org.springframework.stereotype.Service;

@Service
public class GoodInfoServiceImpl extends ServiceImpl<GoodInfoDao, GoodInfo> implements GoodInfoService{

}
