package com.cyriii.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.GoodInfoDao;
import com.cyriii.entity.GoodInfo;
import com.cyriii.entity.InStoreInfo;
import com.cyriii.entity.PageVO;
import com.cyriii.entity.StoreInfo;
import com.cyriii.service.GoodInfoService;
import com.cyriii.service.InStoreInfoService;
import com.cyriii.service.StoreInfoService;
import com.cyriii.utils.UUIDUtils;
import com.cyriii.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

@Service
public class GoodInfoServiceImpl extends ServiceImpl<GoodInfoDao, GoodInfo> implements GoodInfoService{

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private InStoreInfoService inStoreInfoService;

    @Override
    public boolean save(GoodInfo entity) {
        entity
                .setId(UUIDUtils.getUUID())
                .setUserId(userUtils.currentUserId())
                .setCreateDate(new Date());
        return super.save(entity);
    }

    @Override
    public boolean removeById(String id) throws Exception {
        // 查询入库信息中是否存在
        QueryWrapper<InStoreInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("good_id", id);
        int count = inStoreInfoService.count(queryWrapper);
        if(count > 0){
            throw new Exception("出入库信息中存在此信息，无法删除");
        }
        return super.removeById(id);
    }

    @Override
    public IPage<GoodInfo> page(PageVO page) {
        QueryWrapper<GoodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userUtils.currentUserId());
        if(page.getParams().get("name") != null  && StringUtils.isNotEmpty(String.valueOf(page.getParams().get("name"))))
            queryWrapper.like("name", page.getParams().get("name"));
        queryWrapper.orderByDesc("create_date");
        return super.page(new Page<>(page.getCurrent(), page.getSize()), queryWrapper);
    }


}
