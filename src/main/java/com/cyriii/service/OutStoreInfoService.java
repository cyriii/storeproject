package com.cyriii.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyriii.entity.OutStoreInfo;
import com.cyriii.entity.PageVO;

public interface OutStoreInfoService extends IService<OutStoreInfo> {

    boolean saveOutStoreInfo(OutStoreInfo entity) throws Exception;

    boolean updateOutStoreInfoById(OutStoreInfo entity) throws Exception;

    boolean removeOutStoreInfoById(String id) throws Exception;

    IPage<OutStoreInfo> page(PageVO page);
}
