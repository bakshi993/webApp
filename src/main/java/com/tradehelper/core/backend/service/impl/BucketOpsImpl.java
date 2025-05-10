package com.tradehelper.core.backend.service.impl;

import com.tradehelper.core.backend.service.BucketOps;
import com.tradehelper.core.db.collection.BucketEntity;
import com.tradehelper.core.config.cache.CacheStore;
import com.tradehelper.core.pojo.InstrumentDetails;

import com.tradehelper.core.db.repo.BucketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

@Service
public class BucketOpsImpl implements BucketOps {

    @Autowired
    private CacheStore<String, BucketEntity> bucketEntityCacheStore;

    @Autowired
    private BucketRepo bucketRepo;

    @Override
    public BucketEntity createNewBucket(String bucketName, String description, int stopLoss, int target) {
        final String newBucketId = UUID.randomUUID().toString();
        final BucketEntity newBucket = BucketEntity.builder().bucketId(newBucketId)
                .bucketName(bucketName)
                .bucketDescription(description)
                .stopLoss(stopLoss)
                .target(target)
                .createdOn(new Date())
                .modifiedOn(new Date())
                .isActive(true)
                .isDeleted(false)
                .instrumentDetailsList(new ArrayList<>())
                .build();
        bucketRepo.save(newBucket);
        bucketEntityCacheStore.put(newBucketId, newBucket);
        return newBucket;
    }

    @Override
    public ConcurrentMap<String, BucketEntity> getAllBuckets() {
        return bucketEntityCacheStore.getAllKeyValues();
    }

    @Override
    public boolean deleteBucket(String bucketId) {
        final BucketEntity bucketEntity = bucketRepo.findById(bucketId).orElse(null);
        if (Objects.nonNull(bucketEntity)) {
            bucketEntity.setDeleted(true);
            bucketEntity.setDeletedOn(new Date());
            bucketRepo.save(bucketEntity);
            bucketEntityCacheStore.put(bucketId, bucketEntity);
        }
        return false;
    }

    @Override
    public BucketEntity addPositions(String bucketId, Long instrumentToken, String positionType) {
        final BucketEntity bucketEntity = bucketRepo.findById(bucketId).orElse(null);
        if (Objects.nonNull(bucketEntity)) {
            bucketEntity.getInstrumentDetailsList().add(
                    InstrumentDetails.builder()
                            .instrumentToken(instrumentToken)
                            .positionType(positionType)
                            .build());
            bucketRepo.save(bucketEntity);
            bucketEntityCacheStore.put(bucketId, bucketEntity);
            return bucketEntity;
        }
        return null;
    }

    @Override
    public BucketEntity removePositions(String bucketId, Long instrumentToken,
                                        String positionType) {
        /*InstrumentDetails instrumentDetails = BucketDetails.bucketList.get(bucketId).getInstrumentDetailsList()
                .stream()
                .filter(ins -> ins.getTradingSymbol().equals(tradingSymbol) && ins.getExchange().equals(exchange))
                .findFirst()
                .get();
        BucketDetails.bucketList.get(bucketId).getInstrumentDetailsList().remove(instrumentDetails);
        BucketMap.bucketSymbolMap.get(bucketId).*/
        // TODO: 19-02-2024
        return BucketEntity.builder().build();
    }

    @Override
    public BucketEntity setStopLoss(String bucketId, Integer stopLoss) {
        final BucketEntity bucketEntity = bucketRepo.findById(bucketId).orElse(null);
        if (Objects.nonNull(bucketEntity)) {
            bucketEntity.setStopLoss(stopLoss);
            bucketRepo.save(bucketEntity);
            bucketEntityCacheStore.put(bucketId, bucketEntity);
            return bucketEntity;
        }
        return null;
    }

    @Override
    public BucketEntity setProfitTarget(String bucketId, Integer target) {
        final BucketEntity bucketEntity = bucketRepo.findById(bucketId).orElse(null);
        if (Objects.nonNull(bucketEntity)) {
            bucketEntity.setTarget(target);
            bucketRepo.save(bucketEntity);
            bucketEntityCacheStore.put(bucketId, bucketEntity);
            return bucketEntity;
        }
        return null;
    }
}
