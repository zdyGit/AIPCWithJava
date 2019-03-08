package com.zdy.aipc.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zdy.aipc.Service.ProductService;
import com.zdy.aipc.domain.TradeRecord;
import com.zdy.aipc.utils.MathUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@EnableAutoConfiguration
public class ProductController {

    private static HashMap<String,String> actionMenu = new HashMap<String,String>();

    static {
        actionMenu.put("actionMode1","getLatestTradeAmount");
        actionMenu.put("actionMode1.1","getLatestChangeRate");
        actionMenu.put("actionMode1.2","getLatestDaysDiff");
        actionMenu.put("actionMode2","getLastTradeRecord");
        actionMenu.put("actionMode3","saveTradeRecord");
        actionMenu.put("actionMode0","getProdInfo");
    }

    @RequestMapping("/v1/product")
    @ResponseBody
    public String productMain(@RequestBody JSONObject jParam) throws Exception {

        String prodCode = jParam.getString("prodCode");
        if(prodCode == null){
            prodCode = "";
        }
        prodCode = prodCode.trim().toLowerCase();

        String action = jParam.getString("action");
        if(action == null){
            action = "";
        }
        action = action.trim().toLowerCase();

        JSONObject jres = new JSONObject();
        switch (action){
            case "actionmode1":
                jres.put("tradeAmount",ProductService.getLatestTradeAmount(prodCode)+"");
                jres.put("action tips",actionMenu);
                break;
            case "actionmode1.1":
                jres.put("changeRate",ProductService.getLatestChangeRate(prodCode)+"");
                jres.put("action tips",actionMenu);
                break;
            case "actionmode1.2":
                jres.put("daysDiff",ProductService.getLatestDaysDiff(prodCode)+"");
                jres.put("action tips",actionMenu);
                break;
            case "actionmode2":
                TradeRecord  tradeRecord =ProductService.getLastTradeRecord(prodCode);
                jres.put("tradeRecord",JSONObject.toJSONString(tradeRecord, SerializerFeature.WriteNullStringAsEmpty));
                jres.put("action tips",actionMenu);
                break;
            case "actionmode3":
                ProductService.saveTradeRecord(prodCode);
                jres.put("result","success");
                jres.put("action tips",actionMenu);
                break;
            case "actionmode0":
                jres.put("prodCode",prodCode);
                jres.put("latestPrice",ProductService.getLatestPrice(prodCode));
                jres.put("latestDropRate", MathUtils.LimitPoint(ProductService.getLatestDropRate(prodCode)*100,2));
                jres.put("maxDropRate",MathUtils.LimitPoint(ProductService.getLatestChangeRate(prodCode)*100,2));
                jres.put("maxDaysDiff",ProductService.getLatestDaysDiff(prodCode));
                jres.put("tradeAmount",ProductService.getLatestTradeAmount(prodCode));
                jres.put("lastTradeRecord",JSONObject.toJSONString(ProductService.getLastTradeRecord(prodCode), SerializerFeature.WriteNullStringAsEmpty));
                jres.put("action tips",actionMenu);
                break;
            default:
                jres.put("action tips",actionMenu);
                break;

        }
        return jres.toJSONString();
    }
}
