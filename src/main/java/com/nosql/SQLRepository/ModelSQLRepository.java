package com.nosql.SQLRepository;

import com.nosql.SQLEntity.ModelSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModelSQLRepository extends JpaRepository<ModelSQL, Long> {
}
