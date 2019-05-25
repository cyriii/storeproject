package com.cyriii.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cyriii.common.ResultMessage;
import com.cyriii.entity.PageVO;
import com.cyriii.entity.SupplierInfo;
import com.cyriii.service.SupplierInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 供货商信息
 */
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

    @PostMapping("/suppliers")
    public ResultMessage list(@RequestBody PageVO page){
        ResultMessage resultMessage = new ResultMessage();
        IPage<SupplierInfo> iPage = supplierInfoService.page(page);
        resultMessage.setData(iPage);
        return resultMessage;
    }

    @PostMapping("/supplier")
    public ResultMessage save(@RequestBody SupplierInfo supplierInfo){
        ResultMessage resultMessage = new ResultMessage();
        supplierInfoService.save(supplierInfo);
        return resultMessage;
    }

    @PutMapping("/supplier")
    public ResultMessage updateById(@RequestBody SupplierInfo supplierInfo){
        ResultMessage resultMessage = new ResultMessage();
        supplierInfoService.updateById(supplierInfo);
        return resultMessage;
    }

    @DeleteMapping("/supplier/{id}")
    public ResultMessage removeById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        try {
            supplierInfoService.removeById(id);
        } catch (Exception e) {
            resultMessage.setCode(ResultMessage.ERROR_CODE).setMessage(e.getMessage());
        }
        return resultMessage;
    }

}
