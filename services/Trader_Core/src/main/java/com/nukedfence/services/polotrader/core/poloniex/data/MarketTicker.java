package com.nukedfence.services.polotrader.core.poloniex.data;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class MarketTicker {

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

    private long timestamp;

    private MarketTicker(String pair, int id, double last, double lowestAsk, double highestBid, double percentChange,
                         double baseVolume, double quoteVolume, boolean isFrozen, double _24hrHigh, double _24hrLow) {
        this.currencyPair = pair;
        this.poloId = id;
        this.last = last;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.percentChange = percentChange;
        this.baseVolume = baseVolume;
        this.quoteVolume = quoteVolume;
        this.isFrozen = isFrozen;
        this._24hrHigh = _24hrHigh;
        this._24hrLow = _24hrLow;

        this.timestamp = System.currentTimeMillis();
    }

    public static final String KEY_POLO_ID = "id";
    public static final String KEY_LAST = "last";
    public static final String KEY_LOWEST_ASK = "lowestAsk";
    public static final String KEY_HIGHEST_BID = "highestBid";
    public static final String KEY_PERCENT_CHANGE = "percentChange";
    public static final String KEY_BASE_VOLUME = "baseVolume";
    public static final String KEY_QUOTE_VOLUME = "quoteVolume";
    public static final String KEY_IS_FROZEN = "isFrozen";
    public static final String KEY_24HR_HIGH = "high24hr";
    public static final String KEY_24HR_LOW = "low24hr";

    public static MarketTicker getInstance(String currencyPair, JSONObject tickerInfo) {
        try {
            int id = getIntFromJSONObject(tickerInfo, KEY_POLO_ID);
            double last = getDoubleFromJSONObject(tickerInfo, KEY_LAST);
            double lowestAsk = getDoubleFromJSONObject(tickerInfo, KEY_LOWEST_ASK);
            double highestBid = getDoubleFromJSONObject(tickerInfo, KEY_HIGHEST_BID);
            double percentChange = getDoubleFromJSONObject(tickerInfo, KEY_PERCENT_CHANGE);
            double baseVolume = getDoubleFromJSONObject(tickerInfo, KEY_BASE_VOLUME);
            double quoteVolume = getDoubleFromJSONObject(tickerInfo, KEY_QUOTE_VOLUME);
            boolean isFrozen = getBooleanFromJSONObject(tickerInfo, KEY_IS_FROZEN);
            double high24hr = getDoubleFromJSONObject(tickerInfo, KEY_24HR_HIGH);
            double low24hr = getDoubleFromJSONObject(tickerInfo, KEY_24HR_LOW);
            return new MarketTicker(currencyPair, id, last, lowestAsk, highestBid, percentChange,
                    baseVolume, quoteVolume, isFrozen, high24hr, low24hr);
        } catch (Exception e) {
            return null;
        }
    }

    private static int getIntFromJSONObject(JSONObject jObj, String key) throws Exception {
        try {
            return Integer.parseInt(jObj.getString(key));
        } catch (JSONException e) {
            System.out.println("MarketTicker Error obtaining value from JSONObject using key '" + key + "'.");
        } catch (NumberFormatException e) {
            System.out.println("MarketTicker Error parsing int from value for key '" + key + "'.");
        }
        throw new Exception();
    }

    private static double getDoubleFromJSONObject(JSONObject jObj, String key) throws Exception {
        try {
            return Double.parseDouble(jObj.getString(key));
        } catch (JSONException e) {
            System.out.println("MarketTicker Error obtaining value from JSONObject using key '" + key + "'.");
        } catch (NumberFormatException e) {
            System.out.println("MarketTicker Error parsing double from value for key '" + key + "'.");
        }
        throw new Exception();
    }

    private static boolean getBooleanFromJSONObject(JSONObject jObj, String key) throws Exception {
        try {
            return Boolean.parseBoolean(jObj.getString(key));
        } catch (JSONException e) {
            System.out.println("MarketTicker Error obtaining value from JSONObject using key '" + key + "'.");
        } catch (NumberFormatException e) {
            System.out.println("MarketTicker Error parsing boolean from value for key '" + key + "'.");
        }
        throw new Exception();
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public int getId() {
        return poloId;
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

    public boolean isFrozen() {
        return isFrozen;
    }

    public double get24hrHigh() {
        return _24hrHigh;
    }

    public double get24hrLow() {
        return _24hrLow;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(currencyPair).append(":{").append("\"id\":").append(poloId).append(",\"last\":").append(last)
                .append(",\"lowestAsk\":").append(lowestAsk).append(",\"highestBid\":").append(highestBid)
                .append(",\"percentChange\":").append(percentChange).append(",\"baseVolume\":").append(baseVolume)
                .append(",\"quoteVolume\":").append(quoteVolume).append(",\"isFrozen\":").append(isFrozen)
                .append(",\"24hrHigh:\":").append(_24hrHigh).append(",\"24hrLow\":").append(_24hrLow)
                .append("}");
        return sb.toString();
    }
}
