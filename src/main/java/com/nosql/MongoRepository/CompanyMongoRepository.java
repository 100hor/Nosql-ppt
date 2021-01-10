package com.nosql.MongoRepository;

import com.nosql.AggragionEntity.Count;
import com.nosql.MongoEntity.CompanyMongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CompanyMongoRepository extends MongoRepository<CompanyMongo, ObjectId> {
   @Transactional
    void deleteAllByFirm(String firm);


    Optional<CompanyMongo> findFirstByFirm(String firm);

    @Aggregation("{$match: { 'firm':'canon5' }}")
    List<CompanyMongo> matchFirm();

    List<CompanyMongo> findAllByFirm(String firm);

    @Aggregation("{ $group: {_id :'$country', count  : {$sum : 1}  } }")
    List<Count> countAllByCountry();
}
