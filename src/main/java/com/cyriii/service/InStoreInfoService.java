package com.cyriii.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyriii.entity.InStoreInfo;
import com.cyriii.entity.InStoreInfoVO;
import com.cyriii.entity.PageVO;

public interface InStoreInfoService extends IService<InStoreInfo> {

    boolean saveInStoreInfo(InStoreInfo entity);

    boolean updateInStoreInfoById(InStoreInfo entity) throws Exception;

    boolean removeInStoreInfoById(String id) throws Exception;

    IPage<InStoreInfoVO> page(PageVO page);
}
