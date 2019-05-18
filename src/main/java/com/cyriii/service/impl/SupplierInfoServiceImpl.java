package com.cyriii.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.SupplierInfoDao;
import com.cyriii.entity.SupplierInfo;
import com.cyriii.service.SupplierInfoService;
import org.springframework.stereotype.Service;

@Service
public class SupplierInfoServiceImpl extends ServiceImpl<SupplierInfoDao, SupplierInfo> implements SupplierInfoService {
}
