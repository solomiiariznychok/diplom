package com.riznuchok.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public abstract class BaseFormula {

    @Id
    private String id;
    private User user;
    @CreatedDate
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private Date createdDate;
}
