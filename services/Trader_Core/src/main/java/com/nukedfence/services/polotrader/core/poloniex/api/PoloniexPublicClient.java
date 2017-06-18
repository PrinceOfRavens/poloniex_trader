package com.nukedfence.services.polotrader.core.poloniex.api;

import com.nukedfence.services.polotrader.core.poloniex.data.MarketTicker;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

public class PoloniexPublicClient extends PoloniexAPIClient {

    private PoloniexPublicAPI publicAPI = new PoloniexPublicAPI("https://poloniex.com/public");


    public PoloniexPublicClient() {
        super();
    }

    public JSONObject returnTicker() {
        return (JSONObject) requestData(() -> publicAPI.returnTicker());
    }

    public Map<String, MarketTicker> getMarketTickers() {
        JSONObject tickers = returnTicker();
        Map<String, MarketTicker> marketTickers = new HashMap<>();
        Iterator<?> keys = tickers.keys();
        while (keys.hasNext()) {
            String pair = (String) keys.next();
            MarketTicker mt = MarketTicker.getInstance(pair, tickers.getJSONObject(pair));
            if (mt != null) marketTickers.put(pair, mt);
        }
        return marketTickers;
    }

    public List<String> getTradingPairs() {
        JSONObject data = returnTicker();
        List<String> tradingPairs = new ArrayList<>();
        Iterator<?> keys = data.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String[] keyparts = key.split("_");
            if (keyparts.length >= 2) {
                tradingPairs.add(keyparts[0] + "_" + keyparts[1]);
            }
        }
        return tradingPairs;
    }

    public JSONObject return24Volume() {
        return (JSONObject) requestData(() -> publicAPI.return24Volume());
    }

    public JSONObject returnOrderBook() {
        return (JSONObject) requestData(() -> publicAPI.returnOrderBook());
    }

    public JSONObject returnOrderBook(String currencyPair) {
        return (JSONObject) requestData(() -> publicAPI.returnOrderBook(currencyPair));
    }

    public JSONArray returnTradeHistory(String currencyPair) {
        return (JSONArray) requestData(() -> publicAPI.returnTradeHistory(currencyPair));
    }

    public JSONArray returnTradeHistory(String currencyPair, Long start) {
        return (JSONArray) requestData(() -> publicAPI.returnTradeHistory(currencyPair, start));
    }

    public JSONArray returnTradeHistory(String currencyPair, Long start, Long end) {
        return (JSONArray) requestData(() -> publicAPI.returnTradeHistory(currencyPair, start, end));
    }

    static class ChartDataPeriod {

        private long period;

        public static final ChartDataPeriod PERIOD_5_MIN = new ChartDataPeriod(300);
        public static final ChartDataPeriod PERIOD_15_MIN = new ChartDataPeriod(900);
        public static final ChartDataPeriod PERIOD_30_MIN = new ChartDataPeriod(1800);
        public static final ChartDataPeriod PERIOD_2_HOUR = new ChartDataPeriod(7200);
        public static final ChartDataPeriod PERIOD_4_HOUR = new ChartDataPeriod(14400);
        public static final ChartDataPeriod PERIOD_1_DAY = new ChartDataPeriod(86400);

        private ChartDataPeriod(long seconds) {
            period = seconds;
        }

        public long getPeriod() {
            return period;
        }
    }

    public JSONArray returnChartData(String currencyPair, ChartDataPeriod period) {
        return (JSONArray) requestData(() -> publicAPI.returnChartData(currencyPair, period.getPeriod()));
    }

    public JSONArray returnChartData(String currencyPair, ChartDataPeriod period, Long start) {
        return (JSONArray) requestData(() -> publicAPI.returnChartData(currencyPair, period.getPeriod(), start));
    }

    public JSONArray returnChartData(String currencyPair, ChartDataPeriod period, Long start, Long end) {
        return (JSONArray) requestData(() -> publicAPI.returnChartData(currencyPair, period.getPeriod(), start, end));
    }

    public JSONObject returnCurrencies() {
        return (JSONObject) requestData(() -> publicAPI.returnCurrencies());
    }

    public JSONObject returnLoanOrders(String currency) {
        return (JSONObject) requestData(() -> publicAPI.returnLoanOrders(currency));
    }
}
