package com.cyriii.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.OutStoreInfoDao;
import com.cyriii.entity.OutStoreInfo;
import com.cyriii.entity.PageVO;
import com.cyriii.entity.StoreInfo;
import com.cyriii.service.OutStoreInfoService;
import com.cyriii.service.StoreInfoService;
import com.cyriii.utils.UUIDUtils;
import com.cyriii.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OutStoreInfoServiceImpl extends ServiceImpl<OutStoreInfoDao, OutStoreInfo> implements OutStoreInfoService {

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private StoreInfoService storeInfoService;

    @Override
    public boolean saveOutStoreInfo(OutStoreInfo entity) throws Exception {

        // 判断库存量够不够
        QueryWrapper<StoreInfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("good_id", entity.getGoodId());
        StoreInfo storeInfo = storeInfoService.getOne(queryWrapper);

        if(storeInfo == null || storeInfo.getStoreNum().compareTo(entity.getDemandNum()) == -1){
            throw new Exception("库存量不足");
        }
        storeInfo.setStoreNum(storeInfo.getStoreNum().subtract(entity.getDemandNum()));
        storeInfoService.updateById(storeInfo);

        entity
                .setId(UUIDUtils.getUUID())
                .setUserId(userUtils.currentUserId())
                .setCreateDate(new Date());
        return super.save(entity);
    }

    @Override
    public boolean updateOutStoreInfoById(OutStoreInfo entity) throws Exception {
        if(StringUtils.isEmpty(entity.getId())){
            throw new Exception("id不允许为空");
        }

        // 原始信息
        OutStoreInfo outStoreInfoTemp = super.getById(entity.getId());

        // 商品数量是否改变
        if(StringUtils.equals(entity.getGoodId(), outStoreInfoTemp.getGoodId())){
            // 未发生改变
            QueryWrapper<StoreInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("good_id", entity.getGoodId());
            StoreInfo storeInfo = storeInfoService.getOne(queryWrapper);
            BigDecimal changeNum = outStoreInfoTemp.getDemandNum().subtract(entity.getDemandNum());
            storeInfo.setStoreNum(storeInfo.getStoreNum().add(changeNum));
            if(storeInfo.getStoreNum().compareTo(BigDecimal.ZERO) == -1){
                throw new Exception("数量有误，库存量小于0");
            }
            // 修改库存
            storeInfoService.updateById(storeInfo);
        } else {
            // 发生了改变
            QueryWrapper<StoreInfo> newQueryWrapper = new QueryWrapper<>();
            newQueryWrapper.eq("good_id", entity.getGoodId());
            StoreInfo newStoreInfo = storeInfoService.getOne(newQueryWrapper);
            if(newStoreInfo == null || newStoreInfo.getStoreNum().compareTo(entity.getDemandNum()) == -1){
                throw new Exception("库存量不足");
            }
            newStoreInfo.setStoreNum(newStoreInfo.getStoreNum().subtract(entity.getDemandNum()));
            storeInfoService.updateById(newStoreInfo);

            QueryWrapper<StoreInfo> oldQueryWrapper = new QueryWrapper<>();
            oldQueryWrapper.eq("good_id", outStoreInfoTemp.getGoodId());
            StoreInfo oldStoreInfo = storeInfoService.getOne(oldQueryWrapper);
            oldStoreInfo.setStoreNum(oldStoreInfo.getStoreNum().add(outStoreInfoTemp.getDemandNum()));
            storeInfoService.updateById(oldStoreInfo);
        }

        return super.updateById(entity);
    }

    @Override
    public boolean removeOutStoreInfoById(String id) throws Exception{
        // 查询原始信息
        OutStoreInfo outStoreInfo = super.getById(id);

        // 查询库存信息
        QueryWrapper<StoreInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("good_id", outStoreInfo.getGoodId());
        StoreInfo storeInfo = storeInfoService.getOne(queryWrapper);

        // 修改库存信息
        storeInfo.setStoreNum(storeInfo.getStoreNum().add(outStoreInfo.getDemandNum()));
        if(storeInfo.getStoreNum().compareTo(BigDecimal.ZERO) == -1){
            throw new Exception("库存数量小于0，禁止删除");
        }
        storeInfoService.updateById(storeInfo);

        return super.removeById(id);
    }

    @Override
    public IPage<OutStoreInfo> page(PageVO page){
        QueryWrapper<OutStoreInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userUtils.currentUserId());
        queryWrapper.orderByDesc("create_date");
        return super.page(new Page<>(page.getCurrent(), page.getSize()), queryWrapper);
    }
}
