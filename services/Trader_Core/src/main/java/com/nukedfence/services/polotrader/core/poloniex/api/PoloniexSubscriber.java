package com.nukedfence.services.polotrader.core.poloniex.api;

import rx.Observable;
import ws.wamp.jawampa.*;
import ws.wamp.jawampa.connection.IWampConnectorProvider;
import ws.wamp.jawampa.transport.netty.NettyWampClientConnectorProvider;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PoloniexSubscriber {

    private WampClient client = null;

    private IWampConnectorProvider connectorProvider = new NettyWampClientConnectorProvider();

    PoloniexSubscriber() {
        try {
            WampClientBuilder builder = new WampClientBuilder();
            builder.withConnectorProvider(connectorProvider)
                    .withUri("wss://api.poloniex.com")
                    .withRealm("realm1")
                    .withInfiniteReconnects()
                    .withReconnectInterval(5, TimeUnit.SECONDS);
            client = builder.build();

            client.statusChanged()
                    .subscribe((WampClient.State newState) -> {
                        if (newState instanceof WampClient.ConnectedState) {
                            System.out.println("PoloniexSubscriber has connected to the remote router");
                            subscribeToTicker();
                        } else if (newState instanceof WampClient.DisconnectedState) {
                            System.out.println("PoloniexSubscriber is disconnected from the remote router");
                        } else if (newState instanceof WampClient.ConnectingState) {
                            System.out.println("PoloniexSubscriber has started connecting to the remote router");
                        }
                    });

            client.open();
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }

    private void subscribeToTicker() {
        if (tickerEventHandlers.size() > 0) {
            client.statusChanged().subscribe((WampClient.State newState) -> {
                tickerEventSubscription = client.makeSubscription("ticker");
                tickerEventSubscription.subscribe(
                        (s) -> {
                            System.out.println("Recieved ticker update for " + s.arguments().get(0) + ".");
                            for (TickerEventHandler t : getTickerEventHandlers()) {
                                t.handle(s.arguments());
                            }
                        },
                        (e) -> {
                            System.out.println("Error [" + e + "] has occurred.");
                        });
            });
        }
    }

    private Observable<PubSubData> tickerEventSubscription;
    private ArrayList<TickerEventHandler> tickerEventHandlers;

    public void subscribeTicker(TickerEventHandler tickerEventHandler) {
        addTickerEventHandler(tickerEventHandler);
    }

    public void unsubscribeTicker(TickerEventHandler tickerEventHandler) {
        removeTickerEventHandler(tickerEventHandler);
    }

    private ArrayList<TickerEventHandler> getTickerEventHandlers() {
        if (tickerEventHandlers == null) tickerEventHandlers = new ArrayList<>();
        return tickerEventHandlers;
    }

    private void addTickerEventHandler(TickerEventHandler tickerEventHandler) {
        if (!getTickerEventHandlers().contains(tickerEventHandler)) {
            getTickerEventHandlers().add(tickerEventHandler);
        }
    }

    private void removeTickerEventHandler(TickerEventHandler tickerEventHandler) {
        if (getTickerEventHandlers().contains(tickerEventHandler)) {
            getTickerEventHandlers().remove(tickerEventHandler);
        }
    }
}
