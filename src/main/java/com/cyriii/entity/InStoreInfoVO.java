package com.cyriii.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 入库信息
 */
@Data
public class InStoreInfoVO {

    private String id;

    /**
     * 供货商名称
     */
    private String supplierName;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 联系电话
     */
    private String telNumber;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品数量
     */
    private BigDecimal supplyNum;

    /**
     * 商品单价
     */
    private BigDecimal supplyUnivalence;

    /**
     * 单位
     */
    private String unit;

}
