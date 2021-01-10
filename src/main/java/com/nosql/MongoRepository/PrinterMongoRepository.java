package com.nosql.MongoRepository;

import com.nosql.AggragionEntity.Count;
import com.nosql.MongoEntity.PrinterMongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PrinterMongoRepository extends MongoRepository<PrinterMongo, ObjectId> {

    Optional<PrinterMongo> findFirstByName(String name);

    @Aggregation("{ $group: {_id :'$company.firm', count  : {$sum : 1}  } }")
    List<Count> countAllByFirm();

    @Aggregation("{ $group: {_id : '$company.country', count  : {$sum : 1}  } }")
    List<Count> countAllByCountry();

    @Aggregation("{ $group: { id :$id , price  : {$min : $price}  } }")
    List<PrinterMongo> findAllByPriceMin();
}
