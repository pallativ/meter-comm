/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coralinnovations.data.parser.models;

/**
 *
 * @author Veera
 */
public class MeterParameter {
    private int dataType;
    private String code;
    private String hexValue;
    private String value;
    private String unit;
    private String scalar;

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int DataType) {
        this.dataType = DataType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String Code) {
        this.code = Code;
    }

    public String getHexValue() {
        return hexValue;
    }

    public void setHexValue(String HexValue) {
        this.hexValue = HexValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String Value) {
        this.value = Value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String Unit) {
        this.unit = Unit;
    }

    public String getScalar() {
        return scalar;
    }

    public void setScalar(String Scalar) {
        this.scalar = Scalar;
    }

    @Override
    public String toString() {
        return "MeterParameter{" + "DataType=" + dataType + ", Code=" + code + ", HexValue=" + hexValue + ", Value=" + value + ", Unit=" + unit + ", Scalar=" + scalar + '}';
    }
    
    
}
