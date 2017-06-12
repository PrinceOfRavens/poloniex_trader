package com.nukedfence.services.polotrader.core.poloniex.api;

import net.sf.json.JSON;

import java.io.Closeable;
import java.util.concurrent.ArrayBlockingQueue;

public class PoloniexAPIClient implements Closeable {

    private ArrayBlockingQueue<PoloniexAPIRequest> requests = new ArrayBlockingQueue<>(256, true);
    private PoloniexAPIRequestExecutor[] executorThreads = new PoloniexAPIRequestExecutor[6];

    public PoloniexAPIClient() {
        initializeExecutorThreads();
    }

    private void initializeExecutorThreads() {
        for (int i = 0; i < executorThreads.length; i++) {
            executorThreads[i] = new PoloniexAPIRequestExecutor(requests);
            executorThreads[i].start();
        }
    }

    @Override
    public void close() {
        for (PoloniexAPIRequestExecutor requestExecutor : executorThreads) {
            requestExecutor.terminate();
        }
    }

    protected JSON requestData(PoloniexAPIRequest.RequestHandler requestHandler) {
        PoloniexAPIRequest request = new PoloniexAPIRequest();
        request.setRequestHandler(requestHandler);
        requests.add(request);
        try {
            synchronized (request) {
                request.wait();
            }
            return request.getReturnData();
        } catch (InterruptedException e) {
            return null;
        }
    }
}
