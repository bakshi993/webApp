package com.tradehelper.core.db.repo;

import com.tradehelper.core.db.collection.BucketEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BucketRepo extends MongoRepository<BucketEntity, String> {
}
