package com.cyriii.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cyriii.common.ResultMessage;
import com.cyriii.entity.GoodInfo;
import com.cyriii.entity.PageVO;
import com.cyriii.service.GoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品信息
 */
@RestController
public class GoodInfoController {

    @Autowired
    private GoodInfoService goodInfoService;

    /**
     * 根据ID或许商品详情信息
     * @param id
     * @return
     */
    @GetMapping("/good/{id}")
    public ResultMessage getById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        GoodInfo goodInfo = goodInfoService.getById(id);
        resultMessage.setData(goodInfo);
        return resultMessage;
    }

    /**
     * 分页查询商品信息
     * @param page
     * @return
     */
    @PostMapping("/goods")
    public ResultMessage page(@RequestBody PageVO page){
        ResultMessage resultMessage = new ResultMessage();
        IPage<GoodInfo> iPage = goodInfoService.page(page);
        resultMessage.setData(iPage);
        return resultMessage;
    }

    /**
     * 新增商品
     * @param goodInfo
     * @return
     */
    @PostMapping("/good")
    public ResultMessage save(@RequestBody GoodInfo goodInfo){
        ResultMessage resultMessage = new ResultMessage();
        goodInfoService.save(goodInfo);
        return resultMessage;
    }

    @PutMapping("/good")
    public ResultMessage updateById(@RequestBody GoodInfo goodInfo){
        ResultMessage resultMessage = new ResultMessage();
        goodInfoService.updateById(goodInfo);
        return resultMessage;
    }

    @DeleteMapping("/good/{id}")
    public ResultMessage removeById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        try {
            goodInfoService.removeById(id);
        } catch (Exception e){
            resultMessage.setCode(ResultMessage.ERROR_CODE).setMessage(e.getMessage());
        }
        return resultMessage;
    }

}
