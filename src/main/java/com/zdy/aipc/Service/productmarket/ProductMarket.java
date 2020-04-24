package com.zdy.aipc.service.productmarket;

import com.zdy.aipc.domain.ProductInfo;

import java.util.HashMap;

public abstract class ProductMarket {

    public abstract HashMap<String,Object> getLatestTradeInfo(ProductInfo productInfo);

}
