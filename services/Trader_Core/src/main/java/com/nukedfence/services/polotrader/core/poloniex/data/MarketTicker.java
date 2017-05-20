package com.nukedfence.services.polotrader.core.poloniex.data;

import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MarketTicker {

    private String currencyPair;
    private double last;
    private double lowestAsk;
    private double highestBid;
    private double percentChange;
    private double baseVolume;
    private double quoteVolume;

    public MarketTicker(String pair) {
        this.currencyPair = pair;
    }

    public MarketTicker(String pair, double last, double lowestAsk, double highestBid, double percentChange, double baseVolume, double quoteVolume) {
        this(pair);
        this.last = last;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.percentChange = percentChange;
        this.baseVolume = baseVolume;
        this.quoteVolume = quoteVolume;
    }


    public static final String LAST = "last";
    public static final String LOWEST_ASK = "lowestAsk";
    public static final String HIGHEST_BID = "highestBid";
    public static final String PERCENT_CHANGE = "percentChange";
    public static final String BASE_VOLUME = "baseVolume";
    public static final String QUOTE_VOLUME = "quoteVolume";



    public String getCurrencyPair() {
        return currencyPair;
    }

    public double getLast() {
        return last;
    }

    public double getLowestAsk() {
        return lowestAsk;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public double getBaseVolume() {
        return baseVolume;
    }

    public double getQuoteVolume() {
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
