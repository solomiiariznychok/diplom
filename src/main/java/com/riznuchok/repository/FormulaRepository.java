package com.riznuchok.repository;

import com.riznuchok.entity.Formula;
import com.riznuchok.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulaRepository extends MongoRepository<Formula, Integer> {

    Formula findById(String id);
    List<Formula> findByUserOrderByCreatedDate(String userId);
    List<Formula> findAllOrOrderByCreatedDate();



}
