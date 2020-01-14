package com.example.demo.model.dao.repository;

import com.example.demo.model.dvo.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // Spring provided DAO
public interface DataJpaRepository extends JpaRepository<Data, Long> {

    //JPA Query customization & configure
    @Query(value="SELECT * FROM data LIMIT :limit",  nativeQuery = true)
    List<Data> findAllByLimit(@Param("limit") Integer limit);
    List<Data> findByClientId(Long clientId);
    List<Data> findByClientIdOrderByStepCount(Long clientId);
    List<Data> findByClientIdOrderByStepCountDesc(Long clientId);
    List<Data> findByClientIdAndStepCountIsBetween(Long clientId, Integer start, Integer end);
}
