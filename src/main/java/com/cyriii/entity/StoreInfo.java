package com.cyriii.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 库存信息表
 */
@Data
@Accessors(chain = true)
public class StoreInfo {

    private String id;

    /**
     * 商品信息
     */
    private String goodId;

    /**
     * 库存数量
     */
    private BigDecimal storeNum;

}
