package com.cyriii.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cyriii.common.ResultMessage;
import com.cyriii.entity.InStoreInfo;
import com.cyriii.entity.PageVO;
import com.cyriii.service.InStoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 入库信息
 */
@RestController
public class InStoreInfoController {

    @Autowired
    private InStoreInfoService inStoreInfoService;

    @GetMapping("/store/in/{id}")
    public ResultMessage getById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        InStoreInfo inStoreInfo = inStoreInfoService.getById(id);
        resultMessage.setData(inStoreInfo);
        return resultMessage;
    }

    @PostMapping("/store/in")
    public ResultMessage save(@RequestBody InStoreInfo inStoreInfo){
        ResultMessage resultMessage = new ResultMessage();
        inStoreInfoService.saveInStoreInfo(inStoreInfo);
        return resultMessage;
    }

    @PostMapping("/store/ins")
    public ResultMessage page(@RequestBody PageVO page){
        ResultMessage resultMessage = new ResultMessage();
        IPage<InStoreInfo> ipage = inStoreInfoService.page(page);
        resultMessage.setData(ipage);
        return resultMessage;
    }

    @PutMapping("/store/in")
    public ResultMessage updateById(@RequestBody InStoreInfo inStoreInfo){
        ResultMessage resultMessage = new ResultMessage();
        try {
            inStoreInfoService.updateInStoreInfoById(inStoreInfo);
        } catch (Exception e) {
            resultMessage.setCode(ResultMessage.ERROR_CODE).setMessage(e.getMessage());
        }
        return resultMessage;
    }

    @DeleteMapping("/store/in/{id}")
    public ResultMessage removeById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        try {
            inStoreInfoService.removeInStoreInfoById(id);
        } catch (Exception e) {
            resultMessage.setCode(ResultMessage.ERROR_CODE).setMessage(e.getMessage());
        }
        return resultMessage;
    }

}
