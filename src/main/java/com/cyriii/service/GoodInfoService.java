package com.cyriii.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyriii.entity.GoodInfo;
import com.cyriii.entity.PageVO;

public interface GoodInfoService extends IService<GoodInfo> {
    boolean removeById(String id) throws Exception;

    IPage<GoodInfo> page(PageVO page);
}
