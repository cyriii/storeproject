package com.cyriii.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cyriii.common.ResultMessage;
import com.cyriii.entity.PageVO;
import com.cyriii.entity.StoreInfoVO;
import com.cyriii.service.StoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreInfoController {

    @Autowired
    private StoreInfoService storeInfoService;

    @PostMapping("/store")
    public ResultMessage page(PageVO page){
        ResultMessage resultMessage = new ResultMessage();
        IPage<StoreInfoVO> iPage = storeInfoService.page(page);
        resultMessage.setData(iPage);
        return resultMessage;
    }
}
