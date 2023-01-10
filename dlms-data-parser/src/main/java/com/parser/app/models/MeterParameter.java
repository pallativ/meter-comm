/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parser.app.models;

import java.util.ArrayList;

/**
 *
 * @author Veera
 */
public class MeterParameter {

    private int dataType;
    private String code;
    private String obisCode;
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

    public String getObisCode() {
        if (obisCode == null) {
            obisCode = ConvertObisCode(code);
        }
        return obisCode;
    }

    private String ConvertObisCode(String code) {
        var bytes = code.split(" ");
        var result = new ArrayList<String>();
        for (String aByte : bytes) {
            result.add(String.valueOf(Integer.parseInt(aByte, 16)));
        }
        return String.join(".", result);
    }

    @Override
    public String toString() {
        return "MeterParameter{" + "dataType=" + dataType + ", code=" + code + ", hexValue=" + hexValue + ", obisCode=" + obisCode + ", value=" + value + ", unit=" + unit + ", scalar=" + scalar + '}';
    }

}
