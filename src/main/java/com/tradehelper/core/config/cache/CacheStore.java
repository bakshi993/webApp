package com.tradehelper.core.config.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import lombok.extern.java.Log;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Log
public class CacheStore<K, V> {

    private Cache<K, V> cache;

    public CacheStore(int expiryDuration, TimeUnit timeUnit) {
        cache = CacheBuilder
                    .newBuilder()
                    .expireAfterAccess(expiryDuration, timeUnit)
                    .build(new CacheLoader<K, V>() {
                        @Override
                        public V load(K key) throws Exception {
                            return null;
                        }
                    });
    }

    public V get(K key) {
        return cache.getUnchecked(key);
    }

    public void put(K key, V value) {
        cache.asMap().put(key, value);
        log.info("Successfully added key = {" + key + "} & val = {" + value + "} to cache");
    }

    public ConcurrentMap<K,V> getAllKeyValues() {
        return cache.asMap();
    }
}
