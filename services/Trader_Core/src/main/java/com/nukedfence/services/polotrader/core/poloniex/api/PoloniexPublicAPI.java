package com.nukedfence.services.polotrader.core.poloniex.api;

import java.util.ArrayList;
import java.util.HashMap;

public class PoloniexPublicAPI {
    public HashMap<String, HashMap<String, String>> returnTicker() {
        return new HashMap<>();
    }

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
}
