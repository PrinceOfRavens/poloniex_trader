package com.nukedfence.services.polotrader.core.poloniex.api;

import com.nukedfence.services.polotrader.core.poloniex.data.MarketTicker;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PoloniexPublicAPI extends PoloniexAPI {

    public PoloniexPublicAPI(String url) {
        super(url);
    }

    /*public HashMap<String, HashMap<String, String>> returnTicker() {
        return new HashMap<>();
    }*/

    public HashMap<String, HashMap<String, String>> return24Volume() {
        return new HashMap<>();
    }

    public HashMap<String, Object> returnOrderBook() {
        return new HashMap<>();
    }

    public ArrayList<HashMap<String, String>> returnTradeHistory() {
        return new ArrayList<>();
    }

    public ArrayList<HashMap<String, String>> returnChartData() {
        return new ArrayList<>();
    }

    public HashMap<String, HashMap<String, String>> returnCurrencies() {
        return new HashMap<>();
    }

    public HashMap<String, HashMap<String, String>> returnLoanOrders() {
        return new HashMap<>();
    }

    public HashMap<String, MarketTicker> returnTicker() {
        //return getRequestedJSONObject("returnTicker");

        HashMap<String, MarketTicker> map = new HashMap<>();
        JSONObject result = getRequestedJSONObject("returnTicker");
        Iterator<?> keys = result.keys();
        while ( keys.hasNext() ) {
            String key = (String)keys.next();
            if ( result.get(key) instanceof JSONObject ) {
                JSONObject o = result.get(key);
                MarketTicker mt = new MarketTicker(key, result.getDouble("last"))
            }
        }
        return map;
    }

    public ArrayList<String> returnTradingPairs() {
        return new ArrayList<>(returnTicker().keySet());
    }
    
    
    private JSONObject getRequestedJSONObject(String command) {
        if (command != null && !command.isEmpty()) {
            String data = getRequestedContent(getUrl() + "?command=" + command);
            try {
                return JSONObject.fromObject(data);
            } catch (JSONException e) {
                return new JSONObject(true);
            }
        }
        return new JSONObject(true);
    }
    
    
    private String getRequestedContent(String url) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new IOException();
            }
            
            StringBuilder sb = new StringBuilder();
            try (InputStreamReader isr = new InputStreamReader((response.getEntity().getContent())); BufferedReader br = new BufferedReader(isr)) {
                String output;
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }
            }
            
            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
