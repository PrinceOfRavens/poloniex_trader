package com.nukedfence.services.polotrader.core.poloniex.api;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

public class Startup {
    public static void main(String args[]) {
        PoloniexPublicAPI api = new PoloniexPublicAPI("https://poloniex.com/public");
        List<String> result = api.returnTradingPairs();
        //JSONObject result = api.returnTradingPairs();
        System.out.println("Output from call:");
        //System.out.println(result.toString(2));
        System.out.println(result.toString());
    }
}
