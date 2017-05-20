package com.nukedfence.services.polotrader.core.poloniex.api;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class PoloniexTradingAPI extends PoloniexAPI {

    private String apiKey;
    private String apiSecret;

    public PoloniexTradingAPI(String url, String apiKey, String apiSecret) {
        super(url);
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public JSONObject returnBalances() {
        return getRequestedJSONObject("returnBalances");
    }

    public JSONObject returnCompleteBalances() {
        return getRequestedJSONObject("returnCompleteBalances");
    }

    public JSONObject returnDepositAddresses() {
        return getRequestedJSONObject("returnDepositAddresses");
    }

    public JSONObject generateNewAddress(String currency) {
        return getRequestedJSONObject("generateNewAddress", new HashMap<String, String>(){{put("currency", currency);}});
    }

    public JSONObject returnDepositsWithdrawals(long start, long end) {
        return getRequestedJSONObject("returnDepositWithdrawals", new HashMap<String, String>(){{
            put("start", String.valueOf(start));
            put("end", String.valueOf(end));
        }});
    }

    public JSONObject returnOpenOrders() {
        return getRequestedJSONObject("returnOpenOrders", new HashMap<String, String>(){{
            put("currencyPair", "all");
        }});
    }

    public JSON returnOpenOrders(String currencyPair) {
        return getRequestedJSON("returnOpenOrders", new HashMap<String, String>(){{
            put("currencyPair", currencyPair);
        }});
    }

    public JSONObject returnTradeHistory() {
        return returnTradeHistory((Long)null);
    }

    public JSONObject returnTradeHistory(Long start) {
        return returnTradeHistory(start, null);
    }

    public JSONObject returnTradeHistory(Long start, Long end) {
        return getRequestedJSONObject("returnTradeHistory", new HashMap<String, String>(){{
            put("currencyPair", "all");
            put("start", (start != null) ? start.toString() : null);
            put("end", (end != null) ? end.toString() : null);
        }});
    }

    public JSONArray returnTradeHistory(String currencyPair) {
        return returnTradeHistory(currencyPair, null);
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

    public JSONArray returnOrderTrades(long orderNumber) {
        return getRequestedJSONArray("returnOrderTrades", new HashMap<String, String>(){{
            put("orderNumber", String.valueOf(orderNumber));
        }});
    }

    public enum OrderType {
        FILL_OR_KILL,
        IMMEDIATE_OR_CANCEL,
        POST_ONLY
    }

    public JSONObject buy(String currencyPair, double rate, long amount) {
        return buy(currencyPair, rate, amount, null);
    }

    public JSONObject buy(String currencyPair, double rate, long amount, OrderType order) {
        return getRequestedJSONObject("buy", new HashMap<String, String>(){{
            put("currencyPair", currencyPair);
            put("rate", String.valueOf(rate));
            put("amount", String.valueOf(amount));
            switch (order) {
                case FILL_OR_KILL:
                    put("fillOrKill", "1");
                    break;
                case IMMEDIATE_OR_CANCEL:
                    put("immediateOrCancel", "1");
                    break;
                case POST_ONLY:
                    put("postOnly", "1");
                    break;
            }
        }});
    }

    public JSONObject sell(String currencyPair, double rate, long amount) {
        return sell(currencyPair, rate, amount, null);
    }

    public JSONObject sell(String currencyPair, double rate, long amount, OrderType orderType) {
        return getRequestedJSONObject("buy", new HashMap<String, String>(){{
            put("currencyPair", currencyPair);
            put("rate", String.valueOf(rate));
            put("amount", String.valueOf(amount));
            switch (orderType) {
                case FILL_OR_KILL:
                    put("fillOrKill", "1");
                    break;
                case IMMEDIATE_OR_CANCEL:
                    put("immediateOrCancel", "1");
                    break;
                case POST_ONLY:
                    put("postOnly", "1");
                    break;
            }
        }});
    }

    public JSONObject cancelOrder(long orderNumber) {
        return getRequestedJSONObject("cancelOrder", new HashMap<String, String>(){{
            put("orderNumber", String.valueOf(orderNumber));
        }});
    }

    public JSONObject moveOrder(long orderNumber, double rate) {
        return moveOrder(orderNumber, rate, null, null);
    }

    public JSONObject moveOrder(long orderNumber, double rate, Long amount) {
        return moveOrder(orderNumber, rate, amount, null);
    }

    public JSONObject moveOrder(long orderNumber, double rate, OrderType orderType) {
        return moveOrder(orderNumber, rate, null, orderType);
    }

    public JSONObject moveOrder(long orderNumber, double rate, Long amount, OrderType orderType) {
        return getRequestedJSONObject("moveOrder", new HashMap<String, String>(){{
            put("orderNumber", String.valueOf(orderNumber));
            put("rate", String.valueOf(rate));
            put("amount", (amount != null) ? amount.toString() : null);
            switch (orderType) {
                case IMMEDIATE_OR_CANCEL:
                    put("immediateOrCancel", "1");
                    break;
                case POST_ONLY:
                    put("postOnly", "1");
                    break;
            }
        }});
    }

    public JSONObject withdraw(String currency, double amount, String address) {
        return withdraw(currency, amount, address, null);
    }

    public JSONObject withdraw(String currency, double amount, String address, String paymentId) {
        return getRequestedJSONObject("withdraw", new HashMap<String, String>(){{
            put("currency", currency);
            put("amount", String.valueOf(amount));
            put("address", address);
            put("paymentId", paymentId);
        }});
    }

    public JSONObject returnFeeInfo() {
        return getRequestedJSONObject("returnFeeInfo");
    }

    public enum PoloniexAccountType {
        EXCHANGE,
        MARGIN,
        LENDING
    }

    public JSONObject returnAvailableAccountBalances() {
        return returnAvailableAccountBalances(null);
    }

    public JSONObject returnAvailableAccountBalances(PoloniexAccountType account) {
        return getRequestedJSONObject("returnAvailableAccountBalances", new HashMap<String, String>(){{
            switch (account) {
                case EXCHANGE:
                    put("account", "exchange");
                    break;
                case MARGIN:
                    put("account", "margin");
                    break;
                case LENDING:
                    put("account", "lending");
                    break;
            }
        }});
    }

    public JSONObject returnTradableBalances() {
        return getRequestedJSONObject("returnTradableBalances");
    }

    public JSONObject transferBalance(String currency, double amount, PoloniexAccountType fromAccount, PoloniexAccountType toAccount) {
        return getRequestedJSONObject("transferBalance", new HashMap<String, String>(){{
            put("currency", currency);
            put("amount", String.valueOf(amount));
            switch (fromAccount) {
                case EXCHANGE:
                    put("fromAccount", "exchange");
                    break;
                case MARGIN:
                    put("fromAccount", "margin");
                    break;
                case LENDING:
                    put("fromAccount", "lending");
                    break;
            }
            switch (toAccount) {
                case EXCHANGE:
                    put("toAccount", "exchange");
                    break;
                case MARGIN:
                    put("toAccount", "margin");
                    break;
                case LENDING:
                    put("toAccount", "lending");
                    break;
            }
        }});
    }

    public JSONObject returnMarginAccountSummary() {
        return getRequestedJSONObject("returnMarginAccountSummary");
    }

    public JSONObject marginBuy(String currencyPair, double rate, double amount) {
        return marginBuy(currencyPair, rate, amount, null);
    }

    public JSONObject marginBuy(String currencyPair, double rate, double amount, Double lendingRate) {
        return getRequestedJSONObject("marginBuy", new HashMap<String, String>(){{
            put("currencyPair", currencyPair);
            put("rate", String.valueOf(rate));
            put("amount", String.valueOf(amount));
            put("lendingRate", (lendingRate != null) ? lendingRate.toString() : null);
        }});
    }

    public JSONObject marginSell(String currencyPair, double rate, double amount) {
        return marginSell(currencyPair, rate, amount, null);
    }

    public JSONObject marginSell(String currencyPair, double rate, double amount, Double lendingRate) {
        return getRequestedJSONObject("marginBuy", new HashMap<String, String>(){{
            put("currencyPair", currencyPair);
            put("rate", String.valueOf(rate));
            put("amount", String.valueOf(amount));
            put("lendingRate", (lendingRate != null) ? lendingRate.toString() : null);
        }});
    }

    public JSONObject getMarginPosition() {
        return getMarginPosition("all");
    }

    public JSONObject getMarginPosition(String currencyPair) {
        return getRequestedJSONObject("getMarginPosition", new HashMap<String, String>(){{
            put("currencyPair", currencyPair);
        }});
    }

    public JSONObject closeMarginPosition(String currencyPair) {
        return getRequestedJSONObject("closeMarginPosition", new HashMap<String, String>(){{
            put("currencyPair", currencyPair);
        }});
    }

    public JSONObject createLoanOffer(String currency, double amount, long duration, boolean autoRenew, double lendingRate) {
        return getRequestedJSONObject("createLoanOffer", new HashMap<String, String>(){{
            put("currency", currency);
            put("amount", String.valueOf(amount));
            put("duration", String.valueOf(duration));
            put("autoRenew", (autoRenew) ? "1" : "0");
            put("lendingRate", String.valueOf(lendingRate));
        }});
    }

    public JSONObject cancelLoanOffer(long orderNumber) {
        return getRequestedJSONObject("cancelLoanOffer", new HashMap<String, String>(){{
            put("orderNumber", String.valueOf(orderNumber));
        }});
    }

    public JSONObject returnOpenLoanOffers() {
        return getRequestedJSONObject("returnOpenLoanOffers");
    }

    public JSONObject returnActiveLoans() {
        return getRequestedJSONObject("returnActiveLoans");
    }

    public JSONArray returnLendingHistory(long start, long end) {
        return returnLendingHistory(start, end, null);
    }

    public JSONArray returnLendingHistory(long start, long end, Long limit) {
        return getRequestedJSONArray("returnLendingHistory", new HashMap<String, String>(){{
            put("start", String.valueOf(start));
            put("end", String.valueOf(end));
            put("limit", (limit != null) ? limit.toString() : null);
        }});
    }

    public JSONObject toggleAutoRenew(long orderNumber) {
        return getRequestedJSONObject("toggleAutoRenew", new HashMap<String, String>(){{
            put("orderNumber", String.valueOf(orderNumber));
        }});
    }




    @Override
    protected String getRequestedContent(String url, Map<String, String> params) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            final String nonce = String.valueOf(System.currentTimeMillis());
            params.put("nonce", nonce);
            String queryArgs = getQueryParamString(params);
            String sign = hmacSHA512Digest(queryArgs);

            HttpPost post = new HttpPost(url);
            post.addHeader("Key", this.apiKey);
            post.addHeader("Sign", sign);

            post.setEntity(new UrlEncodedFormEntity(getPostParams(params)));

            try (CloseableHttpResponse response = client.execute(post)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new IOException();
                }
                HttpEntity responseEntity = response.getEntity();
                return EntityUtils.toString(responseEntity);
            }
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            return null;
        }
    }

    private String hmacSHA512Digest(String queryArgs) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac shaMac = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(this.apiSecret.getBytes(), "HmacSHA512");
        shaMac.init(keySpec);
        final byte[] macData = shaMac.doFinal(queryArgs.getBytes());
        return Hex.encodeHexString(macData);
    }

    private List<NameValuePair> getPostParams(Map<String, String> params) {
        List<NameValuePair> postParams = new ArrayList<>();
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (param.getKey() != null && param.getValue() != null) {
                    postParams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
            }
        }
        return postParams;
    }
}
