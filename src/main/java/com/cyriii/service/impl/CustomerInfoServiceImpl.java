package com.cyriii.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.CustomerInfoDao;
import com.cyriii.entity.CustomerInfo;
import com.cyriii.service.CustomerInfoService;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoDao, CustomerInfo> implements CustomerInfoService {
}
