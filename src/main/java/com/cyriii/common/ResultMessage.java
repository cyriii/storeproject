package com.cyriii.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors( chain = true)
public class ResultMessage {

    /**
     * 自定义错误状态码
     */
    public static final String SUCCESS_CODE = "000000";
    public static final String ERROR_CODE = "000001";

    /**
     * 返回状态码
     */
    private String code = SUCCESS_CODE;

    /**
     * 返回数据内容
     */
    private Object data;

    /**
     * 返回跳转url
     */
    private String refreshUrl;

    /**
     * 返回提示信息
     */
    private String message;

}
