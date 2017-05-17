package com.riznuchok.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode
public class Result {

    private double result;
    private List<Map<Integer, Double>> yearSumList;
}
