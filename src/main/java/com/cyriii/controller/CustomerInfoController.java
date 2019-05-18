package com.cyriii.controller;

import com.cyriii.common.ResultMessage;
import com.cyriii.entity.CustomerInfo;
import com.cyriii.service.CustomerInfoService;
import com.cyriii.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerInfoController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @GetMapping("/customer/{id}")
    public ResultMessage getById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        CustomerInfo customerInfo = customerInfoService.getById(id);
        resultMessage.setData(customerInfo);
        return resultMessage;
    }

    @GetMapping("/customers")
    public ResultMessage list(){
        ResultMessage resultMessage = new ResultMessage();
        List<CustomerInfo> customerInfoList= customerInfoService.list();
        resultMessage.setData(customerInfoList);
        return resultMessage;
    }

    @PostMapping("/customer")
    public ResultMessage insert(@RequestBody CustomerInfo customerInfo){
        ResultMessage resultMessage = new ResultMessage();
        customerInfo.setId(UUIDUtils.getUUID());
        customerInfoService.save(customerInfo);
        return resultMessage;
    }

    @PutMapping("/customer")
    public ResultMessage update(@RequestBody CustomerInfo customerInfo){
        ResultMessage resultMessage = new ResultMessage();
        customerInfoService.updateById(customerInfo);
        return resultMessage;
    }

    @DeleteMapping("/customer/{id}")
    public ResultMessage deleteById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        customerInfoService.removeById(id);
        return resultMessage;
    }

}
