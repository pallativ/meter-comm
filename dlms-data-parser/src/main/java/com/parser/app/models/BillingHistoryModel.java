/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parser.app.models;

import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Veera
 */
public class BillingHistoryModel {

    private int index;
    private String meterNumber;
    private ArrayList<MeterParameter> parameters;

    public BillingHistoryModel(int index, ArrayList<MeterParameter> parameters) {
        this.index = index;
        this.parameters = parameters;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<MeterParameter> getParameters() {
        return parameters;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public void setParameters(ArrayList<MeterParameter> parameters) {
        this.parameters = parameters;
    }

    public Optional<MeterParameter> getValue(String obisCode) {
        return this.parameters.stream()
                .filter(model -> model.getObisCode().equals(obisCode))
                .findFirst();
    }

    public Long getLongValue(String obisHexCode) {
        var parameter = this.parameters.stream()
                .filter(model -> model.getCode().equals(obisHexCode.toUpperCase()))
                .findFirst();
        return Long.valueOf(parameter.get().getValue());
    }
}
