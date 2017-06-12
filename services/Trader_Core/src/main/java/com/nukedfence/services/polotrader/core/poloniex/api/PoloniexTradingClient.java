package com.nukedfence.services.polotrader.core.poloniex.api;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PoloniexTradingClient extends PoloniexAPIClient {

    private PoloniexTradingAPI tradingAPI;


    public PoloniexTradingClient(String apiKey, String apiSecret) {
        super();
        tradingAPI = new PoloniexTradingAPI("https://poloniex.com/tradingApi", apiKey, apiSecret);
    }

    public JSONObject returnBalances() {
        return (JSONObject) requestData(() -> tradingAPI.returnBalances());
    }

    public JSONObject returnCompleteBalances() {
        return (JSONObject) requestData(() -> tradingAPI.returnCompleteBalances());
    }

    public JSONObject returnDepositAddresses() {
        return (JSONObject) requestData(() -> tradingAPI.returnDepositAddresses());
    }

    public JSONObject generateNewAddress(String currency) {
        return (JSONObject) requestData(() -> tradingAPI.generateNewAddress(currency));
    }

    public JSONObject returnDepositsWithdrawals(long start, long end) {
        return (JSONObject) requestData(() -> tradingAPI.returnDepositsWithdrawals(start, end));
    }

    public JSONObject returnOpenOrders() {
        return (JSONObject) requestData(() -> tradingAPI.returnOpenOrders());
    }

    public JSON returnOpenOrders(String currencyPair) {
        return requestData(() -> tradingAPI.returnOpenOrders(currencyPair));
    }

    public JSONArray returnTradeHistory() {
        return (JSONArray) requestData(() -> tradingAPI.returnTradeHistory());
    }

    public JSONArray returnTradeHistory(long start) {
        return (JSONArray) requestData(() -> tradingAPI.returnTradeHistory(start));
    }

    public JSONArray returnTradeHistory(long start, long end) {
        return (JSONArray) requestData(() -> tradingAPI.returnTradeHistory(start, end));
    }

    public JSONArray returnTradeHistory(String currencyPair) {
        return (JSONArray) requestData(() -> tradingAPI.returnTradeHistory(currencyPair));
    }

    public JSONArray returnTradeHistory(String currencyPair, long start) {
        return (JSONArray) requestData(() -> tradingAPI.returnTradeHistory(currencyPair, start));
    }

    public JSONArray returnTradeHistory(String currencyPair, long start, long end) {
        return (JSONArray) requestData(() -> tradingAPI.returnTradeHistory(currencyPair, start, end));
    }

    public JSONArray returnOrderTrades(long orderNumber) {
        return (JSONArray) requestData(() -> tradingAPI.returnOrderTrades(orderNumber));
    }

    public JSONObject buy(String currencyPair, double rate, double amount) {
        return (JSONObject) requestData(() -> tradingAPI.buy(currencyPair, rate, amount));
    }

    public JSONObject buy(String currencyPair, double rate, double amount, PoloniexTradingAPI.OrderType orderType) {
        return (JSONObject) requestData(() -> tradingAPI.buy(currencyPair, rate, amount, orderType));
    }

    public JSONObject sell(String currencyPair, double rate, double amount) {
        return (JSONObject) requestData(() -> tradingAPI.sell(currencyPair, rate, amount));
    }

    public JSONObject sell(String currencyPair, double rate, double amount, PoloniexTradingAPI.OrderType orderType) {
        return (JSONObject) requestData(() -> tradingAPI.sell(currencyPair, rate, amount, orderType));
    }

    public JSONObject cancelOrder(long orderNumber) {
        return (JSONObject) requestData(() -> tradingAPI.cancelOrder(orderNumber));
    }

    public JSONObject moveOrder(long orderNumber, double rate) {
        return (JSONObject) requestData(() -> tradingAPI.moveOrder(orderNumber, rate));
    }

    public JSONObject moveOrder(long orderNumber, double rate, double amount) {
        return (JSONObject) requestData(() -> tradingAPI.moveOrder(orderNumber, rate, amount));
    }

    public JSONObject moveOrder(long orderNumber, double rate, PoloniexTradingAPI.OrderType orderType) {
        return (JSONObject) requestData(() -> tradingAPI.moveOrder(orderNumber, rate, orderType));
    }

    public JSONObject moveOrder(long orderNumber, double rate, double amount, PoloniexTradingAPI.OrderType orderType) {
        return (JSONObject) requestData(() -> tradingAPI.moveOrder(orderNumber, rate, amount, orderType));
    }

    public JSONObject withdraw(String currency, double amount, String address) {
        return (JSONObject) requestData(() -> tradingAPI.withdraw(currency, amount, address));
    }

    public JSONObject withdraw(String currency, double amount, String address, String paymentId) {
        return (JSONObject) requestData(() -> tradingAPI.withdraw(currency, amount, address, paymentId));
    }

    public JSONObject returnFeeInfo() {
        return (JSONObject) requestData(() -> tradingAPI.returnFeeInfo());
    }

    public JSONObject returnAvailableAccountBalances() {
        return (JSONObject) requestData(() -> tradingAPI.returnAvailableAccountBalances());
    }

    public JSONObject returnAvailableAccountBalances(PoloniexTradingAPI.PoloniexAccountType accountType) {
        return (JSONObject) requestData(() -> tradingAPI.returnAvailableAccountBalances(accountType));
    }

    public JSONObject returnTradableBalances() {
        return (JSONObject) requestData(() -> tradingAPI.returnTradableBalances());
    }

    public JSONObject transferBalance(String currency, double amount, PoloniexTradingAPI.PoloniexAccountType fromAccount, PoloniexTradingAPI.PoloniexAccountType toAccount) {
        return (JSONObject) requestData(() -> tradingAPI.transferBalance(currency, amount, fromAccount, toAccount));
    }

    public JSONObject returnMarginAccountSummary() {
        return (JSONObject) requestData(() -> tradingAPI.returnMarginAccountSummary());
    }

    public JSONObject marginBuy(String currencyPair, double rate, double amount) {
        return (JSONObject) requestData(() -> tradingAPI.marginBuy(currencyPair, rate, amount));
    }

    public JSONObject marginBuy(String currencyPair, double rate, double amount, double lendingRate) {
        return (JSONObject) requestData(() -> tradingAPI.marginBuy(currencyPair, rate, amount, lendingRate));
    }

    public JSONObject marginSell(String currencyPair, double rate, double amount) {
        return (JSONObject) requestData(() -> tradingAPI.marginSell(currencyPair, rate, amount));
    }

    public JSONObject marginSell(String currencyPair, double rate, double amount, double lendingRate) {
        return (JSONObject) requestData(() -> tradingAPI.marginSell(currencyPair, rate, amount, lendingRate));
    }

    public JSONObject getMarginPosition() {
        return (JSONObject) requestData(() -> tradingAPI.getMarginPosition());
    }

    public JSONObject getMarginPosition(String currencyPair) {
        return (JSONObject) requestData(() -> tradingAPI.getMarginPosition(currencyPair));
    }

    public JSONObject closeMarginPosition(String currencyPair) {
        return (JSONObject) requestData(() -> tradingAPI.closeMarginPosition(currencyPair));
    }

    public JSONObject createLoanOffer(String currency, double amount, long duration, boolean autoRenew, double lendingRate) {
        return (JSONObject) requestData(() -> tradingAPI.createLoanOffer(currency, amount, duration, autoRenew, lendingRate));
    }

    public JSONObject cancelLoanOffer(long orderNumber) {
        return (JSONObject) requestData(() -> tradingAPI.cancelLoanOffer(orderNumber));
    }

    public JSONObject returnOpenLoanOffers() {
        return (JSONObject) requestData(() -> tradingAPI.returnOpenLoanOffers());
    }

    public JSONObject returnActiveLoans() {
        return (JSONObject) requestData(() -> tradingAPI.returnActiveLoans());
    }

    public JSONArray returnLendingHistory(long start, long end) {
        return (JSONArray) requestData(() -> tradingAPI.returnLendingHistory(start, end));
    }

    public JSONArray returnLendingHistory(long start, long end, long limit) {
        return (JSONArray) requestData(() -> tradingAPI.returnLendingHistory(start, end, limit));
    }

    public JSONObject toggleAutoRenew(long orderNumber) {
        return (JSONObject) requestData(() -> tradingAPI.toggleAutoRenew(orderNumber));
    }
}
