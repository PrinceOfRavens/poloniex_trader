package com.nukedfence.services.polotrader.core.poloniex.api;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Startup {
    public static void main(String args[]) {
        PoloniexPublicAPI api = new PoloniexPublicAPI("https://poloniex.com/public");
        //JSON result = api.returnTicker();
        //JSON result = api.return24Volume();
        //JSON result = api.returnOrderBook("BTC_ETH");
        //JSON result = api.returnTradeHistory("BTC_ETH");
        GregorianCalendar g1 = new GregorianCalendar();
        g1.add(Calendar.HOUR, -4);
        GregorianCalendar g2 = new GregorianCalendar();
        g2.add(Calendar.HOUR, -3);
        //JSON result = api.returnTradeHistory("BTC_ETH", g1.getTimeInMillis()/1000, g2.getTimeInMillis()/1000);
        //JSON result = api.returnTradeHistory("BTC_ETH", g2.getTimeInMillis()/1000);
        //JSON result = api.returnChartData("BTC_ETH", 1800L, g1.getTimeInMillis()/1000, g2.getTimeInMillis()/1000);
        //JSON result = api.returnChartData("BTC_ETH", 1800L, g1.getTimeInMillis()/1000);
        //JSON result = api.returnChartData("BTC_ETH", 1800L);
        //JSON result = api.returnCurrencies();
        //JSON result = api.returnLoanOrders("BTC");

        PoloniexTradingAPI api2 = new PoloniexTradingAPI(
                "https://poloniex.com/tradingApi",
                "RHOBGDCP-JAP0ROY4-EZBD4JWZ-F915HE2Y",
                "bc1d4341a4e77c9e494416e0243fd5f0d399ee2583a7e4277507d9bed5eb9e8d9da4223f188acc0665def9cc1c1705496deb07efcb2cb20cb83ea79eb0a03755");

        //JSON result = api2.returnBalances();
        JSON result = api2.returnCompleteBalances();

        System.out.println("Output from call:");
        System.out.println(result.toString(2));
    }
}
