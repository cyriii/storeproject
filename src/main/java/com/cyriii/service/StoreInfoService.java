package com.cyriii.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyriii.entity.PageVO;
import com.cyriii.entity.StoreInfo;
import com.cyriii.entity.StoreInfoVO;

public interface StoreInfoService extends IService<StoreInfo> {
    IPage<StoreInfoVO> page(PageVO page);
}
