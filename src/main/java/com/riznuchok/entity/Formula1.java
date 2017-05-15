package com.riznuchok.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class Formula1 extends Formula {

    private Double sum;
    private Double percent;
    private Integer yearCount;
    List<Map<Integer, Double>> yearSumList;

    @Override
    public Formula calculate() {
        yearSumList = new ArrayList<>();
        Double result = 0.0;
        Double basicSum = sum;
        for(Integer i = 0; i < yearCount; i++){
            Double thisYearResult = (sum * percent)/100;
            result += thisYearResult;
            sum += result;
            Map<Integer, Double> yearSum = new HashMap<>();
            yearSum.put(i + 1, thisYearResult);
            yearSumList.add(yearSum);
        }
        sum = basicSum;
        this.setResult(result);
        return this;
    }
}
