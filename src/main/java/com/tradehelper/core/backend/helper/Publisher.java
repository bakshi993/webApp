package com.tradehelper.core.backend.helper;

import com.tradehelper.core.config.cache.CacheStore;
import com.tradehelper.core.db.collection.InstrumentUpdateEntity;
import com.tradehelper.core.db.repo.InstrumentUpdateRepo;

import com.zerodhatech.models.Tick;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import lombok.Builder;

@Builder
public class Publisher implements Runnable {

    @Autowired
    private InstrumentUpdateRepo updateRepo;

    @Autowired
    private CacheStore<Long, InstrumentUpdateEntity> instrumentUpdateCacheStore;

    private Tick incomingTicks;

    private SimpMessagingTemplate template;

    @Override
    public void run() {
        template.convertAndSend("/topic/tickers", incomingTicks);
        InstrumentUpdateEntity instrumentUpdates = InstrumentUpdateEntity.builder()
                .instrumentToken(incomingTicks.getInstrumentToken())
                .lastTradeQuantity(incomingTicks.getLastTradedQuantity())
                .lastTradedPrice(incomingTicks.getLastTradedPrice())
                .change(incomingTicks.getChange())
                .build();
        instrumentUpdateCacheStore.put(instrumentUpdates.getInstrumentToken(), instrumentUpdates);
        updateRepo.save(instrumentUpdates);
    }
}
