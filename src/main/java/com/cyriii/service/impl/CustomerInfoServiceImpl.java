package com.cyriii.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.CustomerInfoDao;
import com.cyriii.entity.CustomerInfo;
import com.cyriii.entity.OutStoreInfo;
import com.cyriii.entity.PageVO;
import com.cyriii.service.CustomerInfoService;
import com.cyriii.service.OutStoreInfoService;
import com.cyriii.utils.UUIDUtils;
import com.cyriii.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoDao, CustomerInfo> implements CustomerInfoService {

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private OutStoreInfoService outStoreInfoService;

    @Override
    public IPage<CustomerInfo> page(PageVO page) {
        QueryWrapper<CustomerInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userUtils.currentUserId());
        if(page.getParams().get("name") != null  && StringUtils.isNotEmpty(String.valueOf(page.getParams().get("name"))))
            queryWrapper.like("name", page.getParams().get("name"));
        return super.page(new Page<>(page.getCurrent(), page.getSize()), queryWrapper);
    }

    @Override
    public boolean save(CustomerInfo entity) {
        entity
                .setId(UUIDUtils.getUUID())
                .setUserId(userUtils.currentUserId())
                .setCreateDate(new Date());
        return super.save(entity);
    }

    @Override
    public boolean removeById(String id) throws Exception {
        // 查询入库信息中是否存在
        QueryWrapper<OutStoreInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_id", id);
        int count = outStoreInfoService.count(queryWrapper);
        if(count > 0){
            throw new Exception("出入库信息中存在此信息，无法删除");
        }
        return super.removeById(id);
    }
}
