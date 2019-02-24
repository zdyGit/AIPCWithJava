package com.zdy.aipc.controllers;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zdy.aipc.Service.ProductService;
import com.zdy.aipc.domain.Product;
import com.zdy.aipc.domain.TradeRecord;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class ProductController {

    @RequestMapping("/v1/product")
    @ResponseBody
    public String productManage(@RequestBody JSONObject jParam) throws Exception {

        String prodCode = jParam.getString("prodCode");
        String action = jParam.getString("action");
        if(action == null){
            action = "";
        }
        action = action.trim().toLowerCase();

        String response ;
        switch (action){
            case "getlatesttradeamount":
                response =ProductService.getLatestTradeAmount(prodCode)+"";
                break;
            case "getlatestchangerate":
                response =ProductService.getLatestChangeRate(prodCode)+"";
                break;
            case "getlatestdaysdiff":
                response =ProductService.getLatestDaysDiff(prodCode)+"";
                break;
            case "getlasttraderecord":
                TradeRecord  tradeRecord =ProductService.getLastTradeRecord(prodCode);
                response = JSONObject.toJSONString(tradeRecord, SerializerFeature.WriteNullStringAsEmpty);
                break;
            default:
                response ="getlatesttradeamount/getlatestchangerate/getlatestdaysdiff/getlasttraderecord";
                break;

        }
        return response;
    }
}
