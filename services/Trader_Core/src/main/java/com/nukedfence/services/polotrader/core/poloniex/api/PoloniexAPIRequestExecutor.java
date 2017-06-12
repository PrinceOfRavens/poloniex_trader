package com.nukedfence.services.polotrader.core.poloniex.api;

import java.util.concurrent.ArrayBlockingQueue;

public class PoloniexAPIRequestExecutor extends Thread{

    private ArrayBlockingQueue<PoloniexAPIRequest> requests;

    public PoloniexAPIRequestExecutor(ArrayBlockingQueue<PoloniexAPIRequest> requestQueue) {
        this("Request-Executor", requestQueue);
    }

    public PoloniexAPIRequestExecutor(String name, ArrayBlockingQueue<PoloniexAPIRequest> requestQueue) {
        super(name);
        requests = requestQueue;
    }

    @Override
    public void run() {
        while (!(Thread.currentThread().isInterrupted())) {
            try {
                PoloniexAPIRequest request = requests.take();
                if (request != null) {
                    long start = System.currentTimeMillis();
                    synchronized (request) {
                        request.processRequest();
                        request.notify();
                    }
                    long delta = System.currentTimeMillis() - start;
                    if (delta < 1000) {
                        Thread.sleep(1000 - delta);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread #" + Thread.currentThread().getId() + " is exiting run loop.");
    }

    void terminate() {
        if (super.isAlive()) {
            super.interrupt();
        }
    }
}
