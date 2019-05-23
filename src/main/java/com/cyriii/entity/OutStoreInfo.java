package com.cyriii.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 出库信息
 */
@Data
@Accessors(chain = true)
public class OutStoreInfo {

    private String id;

    /**
     * 客户ID
     */
    private String customerId;

    /**
     * 商品ID
     */
    private String goodId;

    /**
     * 出货数量
     */
    private BigDecimal demandNum;

    /**
     * 出货单价
     */
    private BigDecimal demandUnivalence;

    /**
     * 出货日期
     */
    private Date demandDate;

    /**
     * 所属用户id
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createDate;

}
