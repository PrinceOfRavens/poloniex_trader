package com.nukedfence.services.polotrader.core.poloniex.api;

import net.sf.json.JSON;

public class PoloniexAPIRequest {
    private JSON returnData;
    private RequestHandler requestHandler;

    public JSON getReturnData() {
        return returnData;
    }

    public void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void processRequest() {
        returnData = requestHandler.handleRequest();
    }

    public interface RequestHandler {
        JSON handleRequest();
    }
}
