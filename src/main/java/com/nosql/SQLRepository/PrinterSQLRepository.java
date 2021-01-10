package com.nosql.SQLRepository;


import com.nosql.SQLEntity.PrinterSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrinterSQLRepository extends JpaRepository<PrinterSQL, Long> {
    Optional<PrinterSQL> findFirstByName(String name);
}
