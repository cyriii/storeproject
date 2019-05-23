package com.cyriii.controller;

import com.cyriii.common.ResultMessage;
import com.cyriii.entity.OutStoreInfo;
import com.cyriii.service.OutStoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 出库信息
 */
@RestController
public class OutStoreInfoController {

    @Autowired
    private OutStoreInfoService outStoreInfoService;

    @GetMapping("/store/out/{id}")
    public ResultMessage getById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        OutStoreInfo outStoreInfo = outStoreInfoService.getById(id);
        resultMessage.setData(outStoreInfo);
        return resultMessage;
    }

    @PostMapping("/store/out")
    public ResultMessage save(@RequestBody OutStoreInfo outStoreInfo){
        ResultMessage resultMessage = new ResultMessage();
        try {
            outStoreInfoService.saveOutStoreInfo(outStoreInfo);
        } catch (Exception e){
            resultMessage.setCode(ResultMessage.ERROR_CODE).setMessage(e.getMessage());
        }
        return resultMessage;
    }

}
