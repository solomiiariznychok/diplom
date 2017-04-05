package com.riznuchok.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Formula {

    @Id
    String id;
    private String formula;
    private User user;

}
