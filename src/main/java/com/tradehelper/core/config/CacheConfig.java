package com.tradehelper.core.config;

import com.tradehelper.core.db.collection.BucketEntity;
import com.tradehelper.core.config.cache.CacheStore;

import com.tradehelper.core.db.collection.InstrumentUpdateEntity;
import com.zerodhatech.models.Instrument;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public CacheStore<Long, Instrument> instrumentCacheStore() {
        return new CacheStore<>(24, TimeUnit.HOURS);
    }

    @Bean
    public CacheStore<Long, ArrayList<Long>> watchListCacheStore() {
        return new CacheStore<>(365, TimeUnit.DAYS);
    }

    @Bean
    public CacheStore<String, BucketEntity> bucketEntityCacheStore() {
        return new CacheStore<>(24, TimeUnit.DAYS);
    }

    @Bean
    public CacheStore<Long, InstrumentUpdateEntity> instrumentUpdateEntityCacheStore() {
        return new CacheStore<>(24, TimeUnit.DAYS);
    }
}
