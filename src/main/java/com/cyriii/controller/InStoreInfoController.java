package com.cyriii.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        inStoreInfoService.save(inStoreInfo);
        return resultMessage;
    }

    @PostMapping("/store/ins")
    public ResultMessage page(@RequestBody PageVO page){
        ResultMessage resultMessage = new ResultMessage();
        IPage<InStoreInfo> ipage = inStoreInfoService.page(new Page<>(page.getCurrent(), page.getSize()));
        resultMessage.setData(ipage);
        return resultMessage;
    }

    @PutMapping("/store/in")
    public ResultMessage updateById(@RequestBody InStoreInfo inStoreInfo){
        ResultMessage resultMessage = new ResultMessage();
        inStoreInfoService.updateById(inStoreInfo);
        return resultMessage;
    }


    @DeleteMapping("/store/in/{id}")
    public ResultMessage removeById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        inStoreInfoService.removeById(id);
        return resultMessage;
    }

}
