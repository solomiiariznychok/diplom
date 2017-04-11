package com.riznuchok.entity;

import lombok.Data;

@Data
public class Formula1 extends Formula {

    private Double x;
    private Double y;

    @Override
    public Formula calculate() {
        this.setResult(x + y);
        return this;
    }
}
