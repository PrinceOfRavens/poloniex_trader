package com.nukedfence.services.polotrader.core.poloniex.api;

public class Market {

    private PoloniexExchange poloniexExchange;

    private String currencyPair;

    private int poloId;
    private double last;
    private double lowestAsk;
    private double highestBid;
    private double percentChange;
    private double baseVolume;
    private double quoteVolume;

    private boolean isFrozen;

    private double _24hrHigh;
    private double _24hrLow;

    private Currency baseCurrency;
    private Currency quoteCurrency;

    private ChartData chartData;

    public Market(PoloniexExchange poloniexExchange, String currencyPair) {
        this.poloniexExchange = poloniexExchange;
        this.currencyPair = currencyPair;
        initializeMarket();
        chartData = new ChartData(this);
    }

    private void initializeMarket() {

    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public PoloniexExchange getPoloniexExchange() {
        return poloniexExchange;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public Currency getQuoteCurrency() {
        return quoteCurrency;
    }
}
