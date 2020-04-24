package com.zdy.aipc.controllers;

import com.alibaba.fastjson.JSONObject;
import com.zdy.aipc.service.ProductRankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/productRank")
public class ProductRankController {

    @Autowired
    private ProductRankInfoService productRankInfoService;

    @RequestMapping(value = "/getTTData",method = RequestMethod.GET)
    public String getTTData(@RequestParam("rankType") int rankType, @RequestParam("sortType") int sortType, @RequestParam("rowNum") int rowNum){
        JSONObject jdata;
        try {
            jdata = productRankInfoService.getTTData(rankType,sortType,rowNum);
        }
        catch (Exception ex){
            return ex.getMessage();
        }
        return jdata.toJSONString();
    }

    @RequestMapping(value = "/downLoadTTData",method = RequestMethod.GET)
    public String downLoadTTData(@RequestParam("rankType") int rankType, @RequestParam("rowNum") int rowNum){

        try {
            productRankInfoService.downLoadTTData(rankType,rowNum);
        }
        catch (Exception ex){
            return ex.getMessage();
        }
        return "SUCCESSFULLY";
    }


}
