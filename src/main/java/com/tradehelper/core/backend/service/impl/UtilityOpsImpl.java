package com.tradehelper.core.backend.service.impl;

import com.tradehelper.core.backend.client.KClient;
import com.tradehelper.core.backend.service.UtilityOps;
import com.tradehelper.core.db.collection.InstrumentEntity;
import com.tradehelper.core.config.cache.CacheStore;
import com.tradehelper.core.db.repo.InstrumentRepo;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Instrument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.List;

@Service
public class UtilityOpsImpl implements UtilityOps {

    @Autowired
    private InstrumentRepo instrumentRepo;

    @Autowired
    private CacheStore<Long, Instrument> instrumentCacheStore;

    @Override
    public boolean refreshInstrumentLists() throws IOException, KiteException {
        final List<Instrument> instruments = KClient.getKiteConnect().getInstruments();
        for (Instrument instrument : instruments) {
            instrumentRepo.save(InstrumentEntity
                    .builder()
                    .instrumentToken(instrument.getInstrument_token())
                    .instrument(instrument)
                    .build()
            );
            instrumentCacheStore.put(instrument.getInstrument_token(), instrument);
        }
        return true;
    }

    public void loadAllCache() {

    }
}
