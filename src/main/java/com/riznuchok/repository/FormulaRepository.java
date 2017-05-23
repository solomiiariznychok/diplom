package com.riznuchok.repository;

import com.riznuchok.entity.Formula;
import com.riznuchok.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface FormulaRepository extends MongoRepository<Formula, Integer> {

    Formula findById(String id);
    List<Formula> findByUser(String userId);
    List<Formula> findByUser(String userId, Sort sort);
    List<Formula> findAll(Sort sort);
    List<Formula> findAll();



}
