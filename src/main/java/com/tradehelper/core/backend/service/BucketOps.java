package com.tradehelper.core.backend.service;

import com.tradehelper.core.db.collection.BucketEntity;

import java.util.concurrent.ConcurrentMap;

public interface BucketOps {
    BucketEntity createNewBucket(String bucketName, String description, int stopLoss, int target);

    ConcurrentMap<String, BucketEntity> getAllBuckets();

    boolean deleteBucket(String bucketId);

    BucketEntity addPositions(String bucketId, Long instrumentToken, String positionType);

    BucketEntity setStopLoss(String bucketId, Integer stopLoss);

    BucketEntity removePositions(String bucketId, Long instrumentToken, String positionType);

    BucketEntity setProfitTarget(String bucketId, Integer target);
}
