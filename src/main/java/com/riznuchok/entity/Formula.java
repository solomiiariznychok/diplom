package com.riznuchok.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public abstract class Formula {

    @Id
    String id;
    private Double result;
    private User user;


    public abstract Formula calculate();

}
