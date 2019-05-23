package com.cyriii.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyriii.entity.PageVO;
import com.cyriii.entity.SupplierInfo;

public interface SupplierInfoService extends IService<SupplierInfo> {
    boolean removeById(String id) throws Exception;

    IPage<SupplierInfo> page(PageVO page);
}
