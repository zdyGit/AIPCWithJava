package com.zdy.aipc.domain;

import com.zdy.aipc.domain.Product.TradeableProduct;
import com.zdy.aipc.domain.productmarket.CYBProductMarket;
import com.zdy.aipc.domain.tradestragegy.DefaultTradeStrategy;
import org.junit.Test;

public class TradeableProductTest {

    @Test
    public void prodInfoTest() throws Exception{
        TradeableProduct p = new TradeableProduct("a1");
        p.setProductMarket(new CYBProductMarket(p.getProdCode()));
        try {

            p.setTradeStrategy(new DefaultTradeStrategy(p));
            double amount =  p.getLatestTradeAmount();
            System.out.println(amount);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println(p.getLatestPrice());
        p.setTradeInfo();
    }
}
