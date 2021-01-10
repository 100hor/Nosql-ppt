package com.nosql.MongoRepository;


import com.nosql.MongoEntity.ModelMongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModelMongoRepository extends MongoRepository<ModelMongo, ObjectId> {

}
