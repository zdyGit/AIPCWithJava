package com.zdy.aipc.service.tradestragegy;


import com.alibaba.fastjson.JSONObject;
import com.zdy.aipc.domain.ProductInfo;
import org.springframework.stereotype.Service;


@Service
public interface ITradeStrategy {
    JSONObject getTradeInfo(ProductInfo product) throws Exception;

}
