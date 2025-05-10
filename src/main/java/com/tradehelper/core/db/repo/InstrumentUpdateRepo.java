package com.tradehelper.core.db.repo;

import com.tradehelper.core.db.collection.InstrumentUpdateEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentUpdateRepo extends MongoRepository<InstrumentUpdateEntity, Long> {
}
