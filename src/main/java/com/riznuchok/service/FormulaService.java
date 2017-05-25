package com.riznuchok.service;

import com.riznuchok.entity.Formula;
import com.riznuchok.entity.Result;
import com.riznuchok.repository.FormulaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FormulaService {

    @Autowired
    FormulaRepository formulaRepository;

    public Formula createFormula(Formula formula) {
        return this.calculate(formula);
    }

    private Formula calculate(Formula formula) {
        try {
            formula = this.calculateByFirstMethod(formula);
            formula = this.calculateBySecondMethod(formula);
        } catch (NullPointerException e) {
            log.warn("some value missed");
        }


        if (formula.getChangeablePercentRate() != null) {
            try {
                formula = this.changedPercent(formula);
                formula = this.changedPercent2(formula);
            } catch (NullPointerException e) {
                log.warn("some value missed");
            }
        }
        if (formula.getAnnualRate() != null) {
            try {
                formula = this.calculateByThirdMethod(formula);
            } catch (NullPointerException e) {
                log.warn("some value missed");
            }
        }
        // if(formula.getJ() != null){

        // }

        return formulaRepository.save(formula);
    }

    public Formula calculateByFirstMethod(Formula formula) {

        Result resultByFirstFormula = new Result();
        List<Map<Integer, Double>> yearSumList = new ArrayList<>();
        Double result = 0.0;
        Double sum = formula.getSum();

        Map<Integer, Double> yearS = new HashMap<>();
//        yearS.put(0, formula.getSum());
//        yearSumList.add(yearS);

        for (Integer i = 0; i < formula.getYearCount(); i++) {
            Double thisYearResult = sum * (1 + i * formula.getPercent() / 100);
            Map<Integer, Double> yearSum = new HashMap<>();
            yearSum.put(i, thisYearResult);
            yearSumList.add(yearSum);
        }
        result = sum * (1 + formula.getYearCount() * formula.getPercent() / 100);
        resultByFirstFormula.setResult(result);


        yearS = new HashMap<>();
        yearS.put(formula.getYearCount(), result);
        yearSumList.add(yearS);

        resultByFirstFormula.setYearSumList(yearSumList);
        formula.setResultBySimplePercent(resultByFirstFormula);
        return formula;
    }

    public Formula calculateBySecondMethod(Formula formula) {
        Result resultBySecondFormula = new Result();
        List<Map<Integer, Double>> yearSumList = new ArrayList<>();
        Double result = 0.0;
        Double sum = formula.getSum();

        Map<Integer, Double> yearS = new HashMap<>();
//        yearS.put(0, formula.getSum());
//        yearSumList.add(yearS);

        for (Integer i = 0; i < formula.getYearCount(); i++) {
            Double thisYearResult = formula.getSum() * Math.pow((1 + formula.getPercent() / 100), i);
            Map<Integer, Double> yearSum = new HashMap<>();
            yearSum.put(i, thisYearResult);
            yearSumList.add(yearSum);
        }
        result = formula.getSum() * Math.pow((1 + formula.getPercent() / 100), formula.getYearCount());


        yearS = new HashMap<>();
        yearS.put(formula.getYearCount(), result);
        yearSumList.add(yearS);

        resultBySecondFormula.setYearSumList(yearSumList);
        resultBySecondFormula.setResult(result);
        formula.setResultByComplexPercent(resultBySecondFormula);
        return formula;
    }

    public Formula calculateByThirdMethod(Formula formula) {
        Result resultByThirdFormula = new Result();
        List<Map<Integer, Double>> yearSumList = new ArrayList<>();
        Double result = 0.0;
        result = formula.getSum() * Math.pow(1 + formula.getAnnualRate() / formula.getIncreasingPercentValueForYear(),
                formula.getCountOfCalculatingPeriod() * formula.getIncreasingPercentValueForYear());
        //TODO
        resultByThirdFormula.setYearSumList(yearSumList);
        resultByThirdFormula.setResult(result);
        formula.setResultByFormulaWithAnnualRate(resultByThirdFormula);
        return formula;
    }

    public Formula changedPercent(Formula formula) {
        Result resultByChangedPercent = new Result();

        List<Map<Integer, Double>> yearSumList = new ArrayList<>();
        Double sum = formula.getSum();
        Double result = 0.0;
        Double percent = formula.getPercent();
        Double percentSum = 0.0;
        percentSum = formula.getConstantPercentRate() * (percent / 100);

        Map<Integer, Double> yearS = new HashMap<>();
//        yearS.put(0, formula.getSum());
//        yearSumList.add(yearS);

        for (int i = 0; i < formula.getYearCount() - formula.getConstantPercentRate(); i++) {
            percent += formula.getChangeablePercentRate();
            percentSum += 1 * (percent / 100);
        }
        result = formula.getSum() * (1 + percentSum);
        resultByChangedPercent.setResult(result);
        //TODO

        percent = formula.getPercent();
        for(int i = 0; i < formula.getYearCount(); i++){
            Double thisYearResult;
            if(i <= formula.getConstantPercentRate()){
                thisYearResult = formula.getSum() *  (1 + i * formula.getPercent() / 100);
            } else {
                percent = formula.getPercent();
                percentSum = formula.getConstantPercentRate() * (percent / 100);
                for(int j = 0; j < i - formula.getConstantPercentRate(); j++){
                    percent += formula.getChangeablePercentRate();
                    percentSum += 1 * (percent / 100);
                }
                thisYearResult = formula.getSum() * (1 + percentSum);
            }
            Map<Integer, Double> yearSum = new HashMap<>();
            yearSum.put(i, thisYearResult);
            yearSumList.add(yearSum);
        }


        yearS = new HashMap<>();
        yearS.put(formula.getYearCount(), result);
        yearSumList.add(yearS);

        resultByChangedPercent.setYearSumList(yearSumList);
        formula.setResultByFormulaWithChangRateSimplePercent(resultByChangedPercent);
        return formula;
    }

    public Formula changedPercent2(Formula formula) {
        Result resultByChangedPercent2 = new Result();

        List<Map<Integer, Double>> yearSumList = new ArrayList<>();
        Double sum = formula.getSum();
        Double result = 0.0;
        Double percent = formula.getPercent();
        Double percentSum = Math.pow(1 + percent / 100, formula.getConstantPercentRate());

        Map<Integer, Double> yearS = new HashMap<>();
//        yearS.put(0, formula.getSum());
//        yearSumList.add(yearS);

        for (int i = 0; i < formula.getYearCount() - formula.getConstantPercentRate(); i++) {

            percent += formula.getChangeablePercentRate();
            percentSum *= 1 + (percent / 100);
        }
        result = formula.getSum() * percentSum;
        resultByChangedPercent2.setResult(result);
        //TODO

        percent = formula.getPercent();
        percentSum = Math.pow(1 + percent / 100, formula.getConstantPercentRate());
        for(int i = 0; i < formula.getYearCount(); i++){
            Double thisYearResult;
            if(i <= formula.getConstantPercentRate()){
                thisYearResult = formula.getSum() * Math.pow((1 + formula.getPercent() / 100), i);
            } else {
                percent = formula.getPercent();
                percentSum = Math.pow(1 + percent / 100, formula.getConstantPercentRate());
                for(int j = 0; j < i - formula.getConstantPercentRate(); j++){
                    percent += formula.getChangeablePercentRate();
                    percentSum *= 1 + (percent / 100);
                }
                thisYearResult = formula.getSum() * percentSum;
            }
            Map<Integer, Double> yearSum = new HashMap<>();
            yearSum.put(i, thisYearResult);
            yearSumList.add(yearSum);
        }


        yearS = new HashMap<>();
        yearS.put(formula.getYearCount(), result);
        yearSumList.add(yearS);

        resultByChangedPercent2.setYearSumList(yearSumList);
        formula.setResultByFormulaWithChangRateComplexPercent(resultByChangedPercent2);
        return formula;
    }

    public Formula updateFormula(String id, Formula formula) {
        if (formulaRepository.findById(id) == null) {
            throw new RuntimeException("can't find formula with id= " + id);
        }
        formula.setId(id);
        return this.calculate(formula);

    }
}
