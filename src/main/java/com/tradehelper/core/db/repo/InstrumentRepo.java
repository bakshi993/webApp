package com.tradehelper.core.db.repo;

import com.tradehelper.core.db.collection.InstrumentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepo extends MongoRepository<InstrumentEntity, Long> {

}
