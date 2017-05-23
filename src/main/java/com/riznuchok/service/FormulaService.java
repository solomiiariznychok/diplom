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
        if(formula.getAnnualRate() != null) {
            formula = this.calculateByThirdMethod(formula);
        }
        if(formula.getPercentWithVariableRate() != null) {
            formula = this.changedPercent(formula);
        }
      // if(formula.getJ() != null){

       // }
        formula = this.changedPercent2(formula);
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
        //TODO
        resultByThirdFormula.setYearSumList(yearSumList);
        resultByThirdFormula.setResult(result);
        formula.setResultByThirdFormula(resultByThirdFormula);
        return formula;
    }
    public  Formula changedPercent(Formula formula){
        Result resultByChangedPercent = new Result();

        List<Map<Integer, Double>> yearSumList = new ArrayList<>();
        Double sum = formula.getSum();
        Double result = 0.0;
        Double percent = formula.getPercent();
        Double percentSum = 0.0;
        percentSum = formula.getNormalPercentYears() * (percent/100);
        for(int i = 0; i < formula.getYearCount() - formula.getNormalPercentYears(); i++){
            percent += formula.getPercentWithVariableRate();
            percentSum += 1 * (percent/100);
        }
        result = formula.getSum() * (1 + percentSum);
        resultByChangedPercent.setResult(result);
        //TODO
        resultByChangedPercent.setYearSumList(yearSumList);
        formula.setResultByFourthFormula(resultByChangedPercent);
        return formula;
    }

    public  Formula changedPercent2(Formula formula){
        Result resultByChangedPercent2 = new Result();

        List<Map<Integer, Double>> yearSumList = new ArrayList<>();
        Double sum = formula.getSum();
        Double result = 0.0;
        Double percent = formula.getPercent();
        Double percentSum = 0.0;

        percentSum = Math.pow(1  + percent/100, formula.getNormalPercentYears());

        for(int i = 0; i < formula.getYearCount() - formula.getNormalPercentYears(); i++){

            percent += formula.getPercentWithVariableRate();
            percentSum *= 1 + (percent/100);
        }
        result = formula.getSum() *   percentSum;
        resultByChangedPercent2.setResult(result);
        //TODO
        resultByChangedPercent2.setYearSumList(yearSumList);
        formula.setResultFifthFormula(resultByChangedPercent2);
        return formula;
    }
}
