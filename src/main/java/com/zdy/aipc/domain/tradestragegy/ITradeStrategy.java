package com.zdy.aipc.domain.tradestragegy;

public interface ITradeStrategy {
    double getTradeAmount() throws Exception;

    double getChangeRate() throws Exception;

    int getDaysDiff();
}
