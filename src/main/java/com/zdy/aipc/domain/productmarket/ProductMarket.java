package com.zdy.aipc.domain.productmarket;

import java.util.HashMap;

public abstract class ProductMarket {

    public abstract HashMap<String,Object> getLatestTradeInfo(String prodCode);

}
