package com.tradehelper.core.backend.helper;

import com.tradehelper.core.config.cache.CacheStore;

import com.tradehelper.core.db.collection.BucketEntity;
import com.tradehelper.core.db.collection.InstrumentUpdateEntity;
import com.tradehelper.core.db.repo.BucketRepo;
import com.tradehelper.core.db.repo.InstrumentRepo;
import com.tradehelper.core.db.repo.InstrumentUpdateRepo;
import com.tradehelper.core.db.repo.WatchListRepo;

import com.zerodhatech.models.Instrument;
import jakarta.annotation.PostConstruct;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Log
public class init {

    @Autowired
    private CacheStore<Long, Instrument> instrumentCacheStore;

    @Autowired
    private CacheStore<Long, ArrayList<Long>> watchListCacheStore;

    @Autowired
    private CacheStore<String, BucketEntity> bucketEntityCacheStore;

    @Autowired
    public CacheStore<Long, InstrumentUpdateEntity> instrumentUpdateCacheStore;

    @Autowired
    private BucketRepo bucketRepo;

    @Autowired
    private InstrumentRepo instrumentRepo;

    @Autowired
    private WatchListRepo watchListRepo;

    @Autowired
    private InstrumentUpdateRepo instrumentUpdateRepo;


    private void loadInstrumentCacheStore() {
        instrumentRepo.findAll().forEach(instrument -> {
            instrumentCacheStore.put(instrument.getInstrumentToken(),
                    instrument.getInstrument());
        });
    }

    private void loadWatchListCacheStore() {
        watchListRepo.findAll().forEach(watchList -> {
            watchListCacheStore.put(watchList.getWatchListId(),
                    watchList.getWatchList());
        });
    }

    private void loadBucketEntityCacheStore() {
        bucketRepo.findAll().forEach(bucket -> {
            bucketEntityCacheStore.put(bucket.getBucketId(), bucket);
        });
    }

    private void loadInstrumentUpdateCacheStore() {
        instrumentUpdateRepo.findAll().forEach(updates -> {
            instrumentUpdateCacheStore.put(updates.getInstrumentToken(), updates);
        });
    }

    @PostConstruct
    private void loadAllCache() {
        loadBucketEntityCacheStore();
        loadInstrumentCacheStore();
        loadWatchListCacheStore();
        loadInstrumentUpdateCacheStore();
        log.info("Successfully loaded all cache");
    }
}
