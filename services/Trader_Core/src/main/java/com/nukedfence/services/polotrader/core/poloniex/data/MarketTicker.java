package com.nukedfence.services.polotrader.core.poloniex.data;

import java.math.BigDecimal;

public class MarketTicker {

    private String currencyPair;
    private BigDecimal last;
    private BigDecimal lowestAsk;
    private BigDecimal highestBid;
    private BigDecimal percentChange;
    private BigDecimal baseVolume;
    private BigDecimal quoteVolume;

    public MarketTicker(String pair) {
        this.currencyPair = pair;
    }

    public MarketTicker(String pair, BigDecimal last, BigDecimal lowestAsk, BigDecimal highestBid, BigDecimal percentChange, BigDecimal baseVolume, BigDecimal quoteVolume) {
        this(pair);
        this.last = last;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.percentChange = percentChange;
        this.baseVolume = baseVolume;
        this.quoteVolume = quoteVolume;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public BigDecimal getLast() {
        return last;
    }

    public BigDecimal getLowestAsk() {
        return lowestAsk;
    }

    public BigDecimal getHighestBid() {
        return highestBid;
    }

    public BigDecimal getPercentChange() {
        return percentChange;
    }

    public BigDecimal getBaseVolume() {
        return baseVolume;
    }

    public BigDecimal getQuoteVolume() {
        return quoteVolume;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(currencyPair).append(":{\"last\":").append(last).append(",\"lowestAsk\":").append(lowestAsk)
                .append(",\"highestBid\":").append(highestBid).append(",\"percentChange\":").append(percentChange)
                .append(",\"baseVolume\":").append(baseVolume).append(",\"quoteVolume\":").append(quoteVolume)
                .append("}");
        return sb.toString();
    }
}
