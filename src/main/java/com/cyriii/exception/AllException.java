package com.cyriii.exception;

import com.cyriii.common.ResultMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class AllException {

    @ExceptionHandler(value = Exception.class)
    public ResultMessage defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception{
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setCode(ResultMessage.ERROR_CODE).setMessage("请求失败，请检查路径或参数名是否有误");
        return resultMessage;
    }

}
