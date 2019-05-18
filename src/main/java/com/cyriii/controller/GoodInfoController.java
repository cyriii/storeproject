package com.cyriii.controller;

import com.cyriii.common.ResultMessage;
import com.cyriii.entity.GoodInfo;
import com.cyriii.service.GoodInfoService;
import com.cyriii.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品信息
 */
@RestController
public class GoodInfoController {

    @Autowired
    private GoodInfoService goodInfoService;

    @GetMapping("/good/{id}")
    public ResultMessage getById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        GoodInfo goodInfo = goodInfoService.getById(id);
        resultMessage.setData(goodInfo);
        return resultMessage;
    }

    @GetMapping("/goods")
    public ResultMessage list(){
        ResultMessage resultMessage = new ResultMessage();
        List<GoodInfo> goodInfoList = goodInfoService.list();
        resultMessage.setData(goodInfoList);
        return resultMessage;
    }

    @PostMapping("/good")
    public ResultMessage insert(@RequestBody GoodInfo goodInfo){
        ResultMessage resultMessage = new ResultMessage();
        goodInfo.setId(UUIDUtils.getUUID());
        goodInfoService.save(goodInfo);
        return resultMessage;
    }

    @PutMapping("/good")
    public ResultMessage update(@RequestBody GoodInfo goodInfo){
        ResultMessage resultMessage = new ResultMessage();
        goodInfoService.updateById(goodInfo);
        return resultMessage;
    }

    @DeleteMapping("/good/{id}")
    public ResultMessage deleteById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        goodInfoService.removeById(id);
        return resultMessage;
    }

}
