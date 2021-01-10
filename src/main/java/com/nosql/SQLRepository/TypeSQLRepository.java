package com.nosql.SQLRepository;


import com.nosql.SQLEntity.TypeSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeSQLRepository extends JpaRepository<TypeSQL, Long> {
    List<TypeSQL> findAllByType(String type);

    Optional<TypeSQL> findFirstByType(String type);
}
