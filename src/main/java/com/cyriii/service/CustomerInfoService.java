package com.cyriii.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyriii.entity.CustomerInfo;
import com.cyriii.entity.PageVO;

public interface CustomerInfoService extends IService<CustomerInfo> {
    IPage<CustomerInfo> page(PageVO page);

    boolean removeById(String id) throws Exception;
}
