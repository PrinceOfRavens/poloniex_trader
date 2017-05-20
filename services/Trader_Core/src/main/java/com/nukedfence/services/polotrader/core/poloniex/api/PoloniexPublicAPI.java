package com.nukedfence.services.polotrader.core.poloniex.api;

import net.sf.json.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class PoloniexPublicAPI extends PoloniexAPI {

    public PoloniexPublicAPI(String url) {
        super(url);
    }

    /*public HashMap<String, MarketTicker> returnTicker() {
        HashMap<String, MarketTicker> map = new HashMap<>();
        JSONObject result = getRequestedJSONObject("returnTicker");
        Iterator<?> keys = result.keys();
        while ( keys.hasNext() ) {
            String key = (String)keys.next();
            if ( result.get(key) instanceof JSONObject ) {
                JSONObject o = (JSONObject)result.get(key);
                try {
                    map.put(key, new MarketTicker(
                            key,
                            o.getDouble(MarketTicker.LAST),
                            o.getDouble(MarketTicker.LOWEST_ASK),
                            o.getDouble(MarketTicker.HIGHEST_BID),
                            o.getDouble(MarketTicker.PERCENT_CHANGE),
                            o.getDouble(MarketTicker.BASE_VOLUME),
                            o.getDouble(MarketTicker.QUOTE_VOLUME)
                    ));
                } catch (JSONException e) {
                    System.out.println("Invalid Market Ticker received.  [ Pair : " + key + " ]");
                }
            }
        }
        return map;
    }*/

    /*public ArrayList<String> returnTradingPairs() {
        ArrayList<String> pairs = new ArrayList<>();
        JSONObject result = getRequestedJSONObject("returnTicker");
        Iterator<?> keys = result.keys();
        while ( keys.hasNext() ) {
            String key = (String)keys.next();
            pairs.add(key);
        }
        return pairs;
    }*/

    public JSONObject returnTicker() {
        return getRequestedJSONObject("returnTicker");
    }

    public JSONObject return24Volume() {
        return getRequestedJSONObject("return24hVolume");
    }

    public JSONObject returnOrderBook() {
        return returnOrderBook("all");
    }

    public JSONObject returnOrderBook(String currencyPair) {
        return getRequestedJSONObject("returnOrderBook", new HashMap<String, String>(){{put("currencyPair", currencyPair);}});
    }

    public JSONArray returnTradeHistory(String currencyPair) {
        return returnTradeHistory(currencyPair, null, null);
    }

    public JSONArray returnTradeHistory(String currencyPair, Long start) {
        return returnTradeHistory(currencyPair, start, null);
    }

    public JSONArray returnTradeHistory(String currencyPair, Long start, Long end) {
        return getRequestedJSONArray("returnTradeHistory", new HashMap<String, String>(){{
            put("currencyPair", currencyPair);
            put("start", (start != null) ? start.toString() : null);
            put("end", (end != null) ? end.toString() : null);
        }});
    }

    public JSONArray returnChartData(String currencyPair, long period) {
        return returnChartData(currencyPair, period, null, null);
    }

    public JSONArray returnChartData(String currencyPair, long period, Long start) {
        return returnChartData(currencyPair, period, start, null);
    }

    public JSONArray returnChartData(String currencyPair, long period, Long start, Long end) {
        return getRequestedJSONArray("returnChartData", new HashMap<String, String>(){{
            put("currencyPair", currencyPair);
            put("period", String.valueOf(period));
            put("start", (start != null) ? start.toString() : null);
            put("end", (end != null) ? end.toString() : null);
        }});
    }

    public JSONObject returnCurrencies() {
        return getRequestedJSONObject("returnCurrencies");
    }

    public JSONObject returnLoanOrders(String currency) {
        return getRequestedJSONObject("returnLoanOrders", new HashMap<String, String>(){{put("currency", currency);}});
    }



    @Override
    protected String getRequestedContent(String url, Map<String, String> params) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            System.out.println("Requesting content for: " + poloniexPublicUrlBuilder(url, params));
            HttpGet request = new HttpGet(poloniexPublicUrlBuilder(url, params));

            try (CloseableHttpResponse response = client.execute(request)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new IOException();
                }
                HttpEntity responseEntity = response.getEntity();
                return EntityUtils.toString(responseEntity);
            }
        } catch (IOException e) {
            return null;
        }
    }

    private String poloniexPublicUrlBuilder(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(url);
        if ( params != null ) {
            sb.append("?").append(getQueryParamString(params));
        }
        return sb.toString();
    }
}
