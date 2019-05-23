package com.cyriii.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyriii.entity.OutStoreInfo;

public interface OutStoreInfoService extends IService<OutStoreInfo> {

    boolean saveOutStoreInfo(OutStoreInfo entity) throws Exception;
}
