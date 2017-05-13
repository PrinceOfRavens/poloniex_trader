package com.nukedfence.services.polotrader.core.poloniex.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class PoloniexTradingAPI extends PoloniexAPI {

    private String apiKey;
    private String apiSecret;

    public PoloniexTradingAPI(String url, String apiKey, String apiSecret) {
        super(url);
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public HashMap<String, BigDecimal> returnBalances() {
        return new HashMap<>();
    }

    public HashMap<String, HashMap<String, BigDecimal>> returnCompleteBalances() {
        return new HashMap<>();
    }

    public HashMap<String, String> returnDepositAddresses() {
        return new HashMap<>();
    }

    public HashMap<String, String> generateNewAddress() {
        return new HashMap<>();
    }

    public HashMap<String, ArrayList<HashMap<String, String>>> returnDepositsWithdrawals() {
        return new HashMap<>();
    }

    public ArrayList<HashMap<String, String>> returnOpenOrders() {
        return new ArrayList<>();
    }

    public ArrayList<HashMap<String, String>> returnTradeHistory() {
        return new ArrayList<>();
    }

    public ArrayList<HashMap<String, String>> returnOrderTrades() {
        return new ArrayList<>();
    }

    public HashMap<String, Object> buy() {
        return new HashMap<>();
    }

    public HashMap<String, Object> sell() {
        return new HashMap<>();
    }

    public boolean cancelOrder() {
        return true;
    }

    public HashMap<String, String> moveOrder() {
        return new HashMap<>();
    }

    public HashMap<String, String> withdraw() {
        return new HashMap<>();
    }

    public HashMap<String, String> returnFeeInfo() {
        return new HashMap<>();
    }

    public HashMap<String, HashMap<String, String>> returnAvailableAccountBalances() {
        return new HashMap<>();
    }

    public HashMap<String, HashMap<String, String>> returnTradableBalances() {
        return new HashMap<>();
    }

    public HashMap<String, String> transferBalance() {
        return new HashMap<>();
    }

    public HashMap<String, String> returnMarginAccountSummary() {
        return new HashMap<>();
    }

    public HashMap<String, Object> marginBuy() {
        return new HashMap<>();
    }

    public HashMap<String, Object> marginSell() {
        return new HashMap<>();
    }

    public HashMap<String, String> getMarginPosition() {
        return new HashMap<>();
    }

    public HashMap<String, Object> closeMarginPosition() {
        return new HashMap<>();
    }

    public HashMap<String, String> createLoanOffer() {
        return new HashMap<>();
    }

    public HashMap<String, String> cancelLoanOffer() {
        return new HashMap<>();
    }

    public HashMap<String, Object> returnOpenLoanOffers() {
        return new HashMap<>();
    }

    public HashMap<String, Object> returnActiveLoans() {
        return new HashMap<>();
    }

    public ArrayList<HashMap<String, String>> returnLendingHistory() {
        return new ArrayList<>();
    }

    public HashMap<String, String> toggleAutoRenew() {
        return new HashMap<>();
    }
}
