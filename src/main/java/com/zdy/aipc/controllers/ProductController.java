package com.zdy.aipc.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zdy.aipc.domain.ProductInfo;
import com.zdy.aipc.domain.ProductTradeParam;
import com.zdy.aipc.domain.ProductTradeRecord;
import com.zdy.aipc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/getProductInfo/{prodCode}",method= RequestMethod.GET)
    public String getProductInfo(@PathVariable(name = "prodCode") String prodCode) throws Exception{
        return productService.getLatestProductInfo(prodCode).toJSONString();
    }

    @RequestMapping(value = "/getProductInfo/all",method= RequestMethod.GET)
    public String getAllProductInfo() throws Exception{
        return productService.getLatestAllProductInfo().toJSONString();
    }

    @RequestMapping(value = "/getLatestTradeAmount/{prodCode}",method= RequestMethod.GET)
    public String getLatestTradeAmount(@PathVariable(name = "prodCode") String prodCode) throws Exception{
        return productService.getLatestTradeAmount(prodCode).toJSONString();
    }

    @RequestMapping(value = "/getLatestTradeAmount/all",method= RequestMethod.GET)
    public String getLatestTradeAmount() throws Exception{
        return productService.getLatestAllTradeAmount().toJSONString();
    }

    @RequestMapping(value = "/getProductTradeParam/{prodCode}",method= RequestMethod.GET)
    public String getProductTradeParam(@PathVariable(name = "prodCode") String prodCode) throws Exception{

        ProductTradeParam productTradeParam = productService.getProductTradeParam(prodCode);
        return JSON.toJSONString(productTradeParam);
    }

    @RequestMapping(value = "/getLatestTradeRecord/{prodCode}",method= RequestMethod.GET)
    public String getLatestTradeRecord(@PathVariable(name = "prodCode") String prodCode) throws Exception{
        ProductTradeRecord productTradeRecord = productService.getLastTradeRecord(prodCode);
        return JSON.toJSONString(productTradeRecord);
    }

    @RequestMapping(value = "/saveTradeInfo/{prodCode}",method= RequestMethod.GET)
    public String saveTradeInfo(@PathVariable(name = "prodCode") String prodCode) throws Exception{
        try{
            productService.addTradeRecord(prodCode);
        }
        catch (Exception ex){
            return String.format("%s,%s","ERROR",ex.getMessage());
        }
        return "SUCCESSFULLY";
    }

    @RequestMapping(value = "/autoUpdateBaseindex/{prodCode}",method = RequestMethod.GET)
    public String autoUpdateBaseindex(@PathVariable(value = "prodCode") String prodCode){
        try
        {
            productService.autoUpdateBaseindex(prodCode);
            return "SUCCESSFULLY";
        }
        catch (Exception ex){
            return "FAILED,"+ex.getMessage();
        }
    }
}
