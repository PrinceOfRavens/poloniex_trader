package com.nukedfence.services.polotrader.core.poloniex.api;

public abstract class PoloniexAPI {
    private String url;

    PoloniexAPI(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
