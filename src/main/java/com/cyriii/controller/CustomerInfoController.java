package com.cyriii.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cyriii.common.ResultMessage;
import com.cyriii.entity.CustomerInfo;
import com.cyriii.entity.PageVO;
import com.cyriii.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 客户信息
 */
@RestController
public class CustomerInfoController {

    @Autowired
    private CustomerInfoService customerInfoService;

    /**
     * 根据ID查询客户信息
     * @param id
     * @return
     */
    @GetMapping("/customer/{id}")
    public ResultMessage getById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        CustomerInfo customerInfo = customerInfoService.getById(id);
        resultMessage.setData(customerInfo);
        return resultMessage;
    }

    /**
     * 查询列表
     * @return
     */
    @PostMapping("/customers")
    public ResultMessage page(@RequestBody PageVO page){
        ResultMessage resultMessage = new ResultMessage();
        IPage<CustomerInfo> customerInfoList= customerInfoService.page(page);
        resultMessage.setData(customerInfoList);
        return resultMessage;
    }

    /**
     * 新增
     * @param customerInfo
     * @return
     */
    @PostMapping("/customer")
    public ResultMessage save(@RequestBody CustomerInfo customerInfo){
        ResultMessage resultMessage = new ResultMessage();
        customerInfoService.save(customerInfo);
        return resultMessage;
    }

    @PutMapping("/customer")
    public ResultMessage updateById(@RequestBody CustomerInfo customerInfo){
        ResultMessage resultMessage = new ResultMessage();
        customerInfoService.updateById(customerInfo);
        return resultMessage;
    }

    @DeleteMapping("/customer/{id}")
    public ResultMessage removeById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        try {
            customerInfoService.removeById(id);
        } catch (Exception e) {
            resultMessage.setCode(ResultMessage.ERROR_CODE).setMessage(e.getMessage());
        }
        return resultMessage;
    }

}
