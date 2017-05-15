package com.riznuchok.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
public abstract class Formula implements Serializable {

    @Id
    String id;
    private Double resultSimplePecent;
    private User user;
    @CreatedDate
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private Date createdDate;


    public abstract Formula calculate();

}
