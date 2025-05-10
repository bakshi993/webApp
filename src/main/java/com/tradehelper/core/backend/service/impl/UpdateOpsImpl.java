package com.tradehelper.core.backend.service.impl;

import com.tradehelper.core.backend.client.KClient;
import com.tradehelper.core.backend.helper.Publisher;
import com.tradehelper.core.backend.service.UpdateOps;

import com.tradehelper.core.config.cache.CacheStore;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;

import com.zerodhatech.models.Order;
import com.zerodhatech.models.Tick;

import com.zerodhatech.ticker.OnError;
import com.zerodhatech.ticker.OnTicks;
import com.zerodhatech.ticker.OnConnect;
import com.zerodhatech.ticker.KiteTicker;
import com.zerodhatech.ticker.OnDisconnect;
import com.zerodhatech.ticker.OnOrderUpdate;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log
@Service
public class UpdateOpsImpl implements UpdateOps {

    private KiteTicker ticker;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private CacheStore<Long, ArrayList<Long>> watchListCacheStore;

    final private ExecutorService service = Executors.newFixedThreadPool(10);

    public boolean connectKiteListener() throws KiteException {

        ticker = new KiteTicker(KClient.getKiteConnect().getAccessToken(), KClient.getKiteConnect().getApiKey());
        ticker.setOnConnectedListener(new OnConnect() {
            @Override
            public void onConnected() {
                log.info("Successfully connected to Kite Tickers");
                watchListCacheStore.getAllKeyValues().forEach((k,v) -> {
                    ticker.subscribe(v);
                    ticker.setMode(v, KiteTicker.modeFull);
                });
            }
        });
        ticker.setOnOrderUpdateListener(new OnOrderUpdate() {
            @Override
            public void onOrderUpdate(Order order) {
                log.info("Order :: " + order.orderId + " -->" + order.status);
                log.info("Order details  :: " + order.toString());
                template.convertAndSend("/topic/orders", order);
            }
        });
        ticker.setOnTickerArrivalListener(new OnTicks() {
            @Override
            public void onTicks(ArrayList<Tick> arrayList) {
                for (Tick tick : arrayList) {
                    service.submit(Publisher.builder().incomingTicks(tick).template(template).build());
                }
            }
        });

        ticker.setOnErrorListener(new OnError() {
            @Override
            public void onError(Exception e) {
                log.warning("Kite Ticker Error :: " + e.getLocalizedMessage());
            }

            @Override
            public void onError(KiteException e) {
                log.warning("Kite Ticker Error :: " + e.getLocalizedMessage());
            }

            @Override
            public void onError(String s) {
                log.warning("Kite Ticker Error :: " + s);
            }
        });
        ticker.setOnDisconnectedListener(new OnDisconnect() {
            @Override
            public void onDisconnected() {
                log.info("Successfully disconnected from Kite Tickers");
            }
        });
        ticker.setTryReconnection(true);
        ticker.setMaximumRetries(10);
        ticker.setMaximumRetryInterval(30);
        ticker.connect();
        if (ticker.isConnectionOpen()) {
            return true;
        }
        return false;
    }

    public void subscribeTokens(ArrayList<Long> tokens) {
        ticker.subscribe(tokens);
        ticker.setMode(tokens, KiteTicker.modeFull);
        log.info("Successfully subscribed to :: " + tokens);
    }

    public void disconnectKiteListener() {
        ticker.disconnect();
        log.info("Disconnect Kite Ticker ....");
    }
}
