package com.riznuchok.service;

import com.riznuchok.entity.Formula;
import com.riznuchok.repository.FormulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormulaService {

    @Autowired
    FormulaRepository formulaRepository;



}
