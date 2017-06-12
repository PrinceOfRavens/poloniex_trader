package com.nukedfence.services.polotrader.core.poloniex.api;

public class ChartData {

    private Market market;

    public ChartData(Market market) {
        this.market = market;
        initializeChartData();
    }

    private void initializeChartData() {
        market.getPoloniexExchange();
    }
}
