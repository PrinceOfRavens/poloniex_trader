package com.nukedfence.services.polotrader.core.poloniex.api;

import com.fasterxml.jackson.databind.node.ArrayNode;

public interface TickerEventHandler {
    public void handleTickerEvent(ArrayNode a);
}
