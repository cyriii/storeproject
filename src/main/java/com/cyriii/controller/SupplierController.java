package com.cyriii.controller;

import com.cyriii.common.ResultMessage;
import com.cyriii.entity.SupplierInfo;
import com.cyriii.service.SupplierInfoService;
import com.cyriii.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    private SupplierInfoService supplierInfoService;

    @GetMapping("/supplier/{id}")
    public ResultMessage getById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        SupplierInfo supplierInfo = supplierInfoService.getById(id);
        resultMessage.setData(supplierInfo);
        return resultMessage;
    }

    @GetMapping("/suppliers")
    public ResultMessage list(){
        ResultMessage resultMessage = new ResultMessage();
        List<SupplierInfo> customerInfoList= supplierInfoService.list();
        resultMessage.setData(customerInfoList);
        return resultMessage;
    }

    @PostMapping("/supplier")
    public ResultMessage insert(@RequestBody SupplierInfo supplierInfo){
        ResultMessage resultMessage = new ResultMessage();
        supplierInfo.setId(UUIDUtils.getUUID());
        supplierInfoService.save(supplierInfo);
        return resultMessage;
    }

    @PutMapping("/supplier")
    public ResultMessage update(@RequestBody SupplierInfo supplierInfo){
        ResultMessage resultMessage = new ResultMessage();
        supplierInfoService.updateById(supplierInfo);
        return resultMessage;
    }

    @DeleteMapping("/supplier/{id}")
    public ResultMessage deleteById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        supplierInfoService.removeById(id);
        return resultMessage;
    }

}
