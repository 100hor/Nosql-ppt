package com.nosql.MongoRepository;

import com.nosql.MongoEntity.TypeMongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TypeMongoRepository extends MongoRepository<TypeMongo, ObjectId> {

    List<TypeMongo> findAllByName(String name);

    Optional<TypeMongo> findFirstByName(String Name);

    void deleteAllByName(String name);
}
