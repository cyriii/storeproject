package com.cyriii.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 入库信息
 */
@Data
@Accessors(chain = true)
public class InStoreInfo {

    private String id;

    /**
     * 供货商ID
     */
    private String supplierId;

    /**
     * 商品ID
     */
    private String goodId;

    /**
     * 进货数量
     */
    private BigDecimal supplyNum;

    /**
     * 进货单价
     */
    private BigDecimal supplyUnivalence;

    /**
     * 进货日期
     */
    private Date supplyDate;

    /**
     * 所属用户id
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createDate;


}
