package com.cyriii.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cyriii.common.ResultMessage;
import com.cyriii.entity.OutStoreInfo;
import com.cyriii.entity.OutStoreInfoVO;
import com.cyriii.entity.PageVO;
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

    @PutMapping("/store/out")
    public ResultMessage updateById(@RequestBody OutStoreInfo outStoreInfo){
        ResultMessage resultMessage = new ResultMessage();
        try {
            outStoreInfoService.updateOutStoreInfoById(outStoreInfo);
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage.setCode(ResultMessage.ERROR_CODE).setMessage(e.getMessage());
        }

        return resultMessage;
    }

    @DeleteMapping("/store/out/{id}")
    public ResultMessage removeById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        try {
            outStoreInfoService.removeOutStoreInfoById(id);
        } catch (Exception e) {
            resultMessage.setCode(ResultMessage.ERROR_CODE).setMessage(e.getMessage());
        }
        return resultMessage;
    }

    @PostMapping("/store/outs")
    public ResultMessage page(@RequestBody PageVO page){
        ResultMessage resultMessage = new ResultMessage();
        IPage<OutStoreInfoVO> iPage = outStoreInfoService.page(page);
        resultMessage.setData(iPage);
        return resultMessage;
    }
}
