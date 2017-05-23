package com.riznuchok.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
public  class Formula extends BaseFormula implements Serializable {

    private Double sum;
    private Double percent;
    private Integer yearCount;

    private Double annualRate;
    private Integer countYearPeriodm;
    private Double countCalculatingn;

    private Result resultByFirstFormula;
    private Result resultBySecondFormula;
    protected Result resultByThirdFormula;
}
