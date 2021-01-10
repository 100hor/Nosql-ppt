package com.nosql.SQLRepository;

import com.nosql.SQLEntity.CompanySQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CompanySQLRepository extends JpaRepository<CompanySQL, Long> {
    Optional<CompanySQL> findFirstByFirm(String firm);

    @Transactional
    void deleteAllByFirm(String firm);
}
