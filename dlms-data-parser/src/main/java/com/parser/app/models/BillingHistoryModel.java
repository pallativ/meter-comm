/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parser.app.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

/**
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

    public void setParameters(ArrayList<MeterParameter> parameters) {
        this.parameters = parameters;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public Optional<MeterParameter> getValue(String obisCode) {
        return this.parameters.stream()
                .filter(model -> model.getObisCode().equals(obisCode))
                .findFirst();
    }

    public Float getFloat(String obisHexCode, float scalar) throws Exception {
        var parameter = this.parameters.stream()
                .filter(model -> model.getCode().equals(obisHexCode.toUpperCase()))
                .findFirst();
        if (parameter.isEmpty()) {
            throw new Exception("OBIS code not present: " + obisHexCode);
        }
        return Long.valueOf(parameter.get().getValue()) * scalar;
    }

    public Float getFloat(String obisHexCode, Map<String, String> scalarValues) throws Exception {
        Float scalar = 1F;
        if (scalarValues.containsKey(obisHexCode)) {
            scalar = Float.valueOf(scalarValues.get(obisHexCode));
        }
        return getFloat(obisHexCode, scalar);
    }


    public LocalDateTime getDateTime(String obisHexCode) throws Exception {
        var parameter = this.parameters.stream()
                .filter(model -> model.getCode().equals(obisHexCode.toUpperCase()) && model.getDataType() == 9)
                .findFirst();
        if (parameter.isEmpty()) {
            throw new Exception("OBIS code not present: " + obisHexCode);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(parameter.get().getValue(), formatter);
        return dateTime;
    }
}
