package com.nukedfence.services.polotrader.core.poloniex.api;

import net.sf.json.JSONArray;
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
import java.util.ArrayList;
import java.util.HashMap;

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

    public JSONObject returnTicker() {
        return getRequestedJSONObject("returnTicker");
    }

    public JSONObject returnTradingPairs() {
        return returnTicker();
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
