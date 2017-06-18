package com.nukedfence.services.polotrader.core.poloniex.api;

import com.nukedfence.services.polotrader.core.poloniex.data.MarketTicker;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.Closeable;
import java.util.*;

public class PoloniexExchange implements Closeable {
    private PoloniexSubscriber poloniexSubscriber;
    private PoloniexPublicClient poloniexPublicClient = new PoloniexPublicClient();
    Map<String, Market> markets;
    Map<String, Currency> currencies;

    public PoloniexExchange() {
        initializeCurrencies();
        initializeMarkets();
    }

    public Map<String, Currency> getCurrencies() {
        if (currencies == null) currencies = new HashMap<>();
        return currencies;
    }

    public Currency getCurrency(String currencyId) {
        Currency c = null;
        if (currencyId != null) {
            c = getCurrencies().get(currencyId);
        }
        return c;
    }

    private void initializeCurrencies() {
        JSONObject data = poloniexPublicClient.returnCurrencies();
        Iterator<?> keys = data.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            try {
                Currency c = new Currency(key);
                c.setCurrencyInfo(data.getJSONObject(key));
                getCurrencies().put(key, c);
            } catch (JSONException e) {
                System.out.println("Error initializing Currency: " + key + ".");
                e.printStackTrace();
            }
        }
    }

    public Map<String, Market> getMarkets() {
        if (markets == null) markets = new HashMap<>();
        return markets;
    }

    public Market getMarket(String currencyPair) {
        return getMarkets().get(currencyPair);
    }

    public List<Market> getMarkets(Currency currency) {
        List<Market> marketList = new ArrayList<>();
        for (Market m : getMarkets().values()) {
            if (m.getBaseCurrency().equals(currency) || m.getQuoteCurrency().equals(currency)) {
                marketList.add(m);
            }
        }
        return marketList;
    }

    public Market getMarket(Currency baseCurrency, Currency quoteCurrency) {
        if (baseCurrency != null && quoteCurrency != null) {
            return getMarkets().get(baseCurrency.getIdentifier() + "_" + quoteCurrency.getIdentifier());
        }
        return null;
    }

    private void initializeMarkets() {
        List<String> tradingPairs = poloniexPublicClient.getTradingPairs();
        //Map<String, MarketTicker> tickers = poloniexPublicClient.getMarketTickers();
        for (String tradingPair : tradingPairs) {
            Market m = new Market(this, tradingPair);
            getMarkets().put(tradingPair, m);
        }
    }

    @Override
    public void close() {
        poloniexPublicClient.close();
    }
}
