package com.zdy.aipc.domain;

public class DefaultTradeStrategy implements ITradeStrategy {

    private AbstractProduct product;

    @Override
    public double getTradeAmount() {
        return 0;
    }
}
