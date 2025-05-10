package com.tradehelper.core.db.repo;

import com.tradehelper.core.db.collection.WatchListEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface WatchListRepo extends MongoRepository<WatchListEntity, Long> {
}
