package com.cyriii.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 出库信息
 */
@Data
public class OutStoreInfoVO {

    private String id;

    /**
     * 客户名称
     */
    private String customerName;

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
     * 出货数量
     */
    private BigDecimal demandNum;

    /**
     * 出货单价
     */
    private BigDecimal demandUnivalence;

    /**
     * 单位
     */
    private String unit;

    /**
     *
     */
    private Date demandDate;
}
