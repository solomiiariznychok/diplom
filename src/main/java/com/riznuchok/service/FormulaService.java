package com.riznuchok.service;

import com.riznuchok.entity.Formula;
import com.riznuchok.entity.Result;
import com.riznuchok.repository.FormulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FormulaService {

    @Autowired
    FormulaRepository formulaRepository;

    public Formula calculate(Formula formula){
        formula = this.calculateByFirstMethod(formula);
        formula = this.calculateBySecondMethod(formula);
        formula = this.calculateByThirdMethod(formula);
      // if(formula.getJ() != null){

       // }
        return formulaRepository.save(formula);
    }

    public Formula calculateByFirstMethod(Formula formula){

        Result resultByFirstFormula = new Result();
        List<Map<Integer, Double>> yearSumList = new ArrayList<>();
        Double result = 0.0;
        Double sum = formula.getSum();
        for(Integer i = 0; i < formula.getYearCount(); i++){
            Double thisYearResult = sum * (1 + i * formula.getPercent()/100);
            Map<Integer, Double> yearSum = new HashMap<>();
            yearSum.put(i + 1, thisYearResult);
            yearSumList.add(yearSum);
        }
        result = sum * (1 + formula.getYearCount() * formula.getPercent()/100);
        resultByFirstFormula.setResult(result);
        resultByFirstFormula.setYearSumList(yearSumList);
        formula.setResultByFirstFormula(resultByFirstFormula);
        return formula;
    }

    public Formula calculateBySecondMethod(Formula formula){
        Result resultBySecondFormula = new Result();
        List<Map<Integer, Double>> yearSumList = new ArrayList<>();
        Double result = 0.0;
        Double sum = formula.getSum();
        for(Integer i = 0; i < formula.getYearCount(); i++){
            Double thisYearResult = formula.getSum() * Math.pow((1 + formula.getPercent()/100), i);
            Map<Integer, Double> yearSum = new HashMap<>();
            yearSum.put(i + 1, thisYearResult);
            yearSumList.add(yearSum);
        }
        result = formula.getSum() * Math.pow((1 + formula.getPercent()/100), formula.getYearCount());
        resultBySecondFormula.setYearSumList(yearSumList);
        resultBySecondFormula.setResult(result);
        formula.setResultBySecondFormula(resultBySecondFormula);
        return formula;
    }

    public Formula calculateByThirdMethod(Formula formula){
        Result resultByThirdFormula = new Result();
        List<Map<Integer, Double>> yearSumList = new ArrayList<>();
        Double result = 0.0;
        result = formula.getSum() * Math.pow(1 + formula.getAnnualRate()/formula.getCountYearPeriodm(),
                formula.getCountCalculatingn() * formula.getCountYearPeriodm());
        resultByThirdFormula.setYearSumList(yearSumList);
        resultByThirdFormula.setResult(result);
        formula.setResultByThirdFormula(resultByThirdFormula);
        return formula;
    }














}
