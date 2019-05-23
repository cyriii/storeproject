package com.cyriii.entity;

import java.math.BigDecimal;
import java.util.Date;

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
    private BigDecimal supplyNum;

    /**
     * 出货单价
     */
    private BigDecimal supplyUnivalence;

    /**
     * 出货日期
     */
    private Date supplyDate;

}
