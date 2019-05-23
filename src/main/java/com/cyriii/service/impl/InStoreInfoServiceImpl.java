package com.cyriii.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.InStoreInfoDao;
import com.cyriii.entity.InStoreInfo;
import com.cyriii.entity.PageVO;
import com.cyriii.entity.StoreInfo;
import com.cyriii.service.InStoreInfoService;
import com.cyriii.service.StoreInfoService;
import com.cyriii.utils.UUIDUtils;
import com.cyriii.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class InStoreInfoServiceImpl extends ServiceImpl<InStoreInfoDao, InStoreInfo> implements InStoreInfoService {

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private StoreInfoService storeInfoService;

    @Override
    public boolean saveInStoreInfo(InStoreInfo entity) {
        // 判断库存信息是否存在
        QueryWrapper<StoreInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("good_id", entity.getGoodId());
        StoreInfo storeInfo = storeInfoService.getOne(queryWrapper);
        if(storeInfo == null){
            // 库存信息不存在 新建
            storeInfo = new StoreInfo();
            storeInfo
                    .setId(UUIDUtils.getUUID())
                    .setGoodId(entity.getGoodId())
                    .setStoreNum(entity.getSupplyNum());
            storeInfoService.save(storeInfo);
        } else {
            // 库存信息存在修改库存量
            storeInfo.setStoreNum(storeInfo.getStoreNum().add(entity.getSupplyNum()));
            storeInfoService.updateById(storeInfo);
        }

        entity
                .setId(UUIDUtils.getUUID())
                .setUserId(userUtils.currentUserId())
                .setCreateDate(new Date());
        return super.save(entity);
    }


    @Override
    public boolean updateInStoreInfoById(InStoreInfo entity) throws Exception {
        if(StringUtils.isEmpty(entity.getId())){
            throw new Exception("id不允许为空");
        }

        // 查询原始信息
        InStoreInfo inStoreInfoTemp = super.getById(entity.getId());


        // 商品种类是否改变
        if(StringUtils.equals(entity.getGoodId(), inStoreInfoTemp.getGoodId())){
            // 没有发生改变
            QueryWrapper<StoreInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("good_id", entity.getGoodId());
            StoreInfo storeInfo = storeInfoService.getOne(queryWrapper);
            BigDecimal changeNum = entity.getSupplyNum().subtract(inStoreInfoTemp.getSupplyNum());

            storeInfo.setStoreNum(storeInfo.getStoreNum().add(changeNum));
            if(storeInfo.getStoreNum().compareTo(BigDecimal.ZERO) == -1){
                throw new Exception("数量有误，库存量小于0");
            }

            // 修改库存
            storeInfoService.updateById(storeInfo);
        } else {
            // 商品种类发生了改变

            QueryWrapper<StoreInfo> oldQueryWrapper = new QueryWrapper<>();

            oldQueryWrapper.eq("good_id", inStoreInfoTemp.getGoodId());    // 查询旧商品库存
            StoreInfo oldStoreInfo = storeInfoService.getOne(oldQueryWrapper);
            oldStoreInfo.setStoreNum(oldStoreInfo.getStoreNum().subtract(inStoreInfoTemp.getSupplyNum())); // 修改数量
            if(oldStoreInfo.getStoreNum().compareTo(BigDecimal.ZERO) == -1){
                throw new Exception("数量有误，库存量小于0");
            }
            storeInfoService.updateById(oldStoreInfo);

            QueryWrapper<StoreInfo> newQueryWrapper = new QueryWrapper<>();
            newQueryWrapper.eq("good_id", entity.getGoodId());
            StoreInfo newStoreInfo = storeInfoService.getOne(newQueryWrapper);
            if(newStoreInfo == null){
                newStoreInfo = new StoreInfo();
                newStoreInfo
                        .setId(UUIDUtils.getUUID())
                        .setStoreNum(entity.getSupplyNum())
                        .setGoodId(entity.getGoodId());
                storeInfoService.save(newStoreInfo);
            } else {
                newStoreInfo.setStoreNum(newStoreInfo.getStoreNum().add(entity.getSupplyNum()));
            }
            storeInfoService.updateById(newStoreInfo);
        }

        return super.updateById(entity);
    }

    @Override
    public boolean removeInStoreInfoById(String id) throws Exception {
        // 查询原始信息
        InStoreInfo inStoreInfo = super.getById(id);

        // 查询库存信息
        QueryWrapper<StoreInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("good_id", inStoreInfo.getGoodId());
        StoreInfo storeInfo = storeInfoService.getOne(queryWrapper);

        // 修改库存信息
        storeInfo.setStoreNum(storeInfo.getStoreNum().subtract(inStoreInfo.getSupplyNum()));
        if(storeInfo.getStoreNum().compareTo(BigDecimal.ZERO) == -1){
            throw new Exception("库存数量小于0，禁止删除");
        }
        storeInfoService.updateById(storeInfo);
        return super.removeById(id);
    }

    @Override
    public IPage<InStoreInfo> page(PageVO page){
        QueryWrapper<InStoreInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userUtils.currentUserId());
        queryWrapper.orderByDesc("create_date");
        return super.page(new Page<>(page.getCurrent(), page.getSize()), queryWrapper);
    }
}
