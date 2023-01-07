/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hes.data.parser.models;

/**
 *
 * @author Veera
 */
public class MeterParameter {
    private int DataType;
    private String Code;
    private String HexValue;
    private String Value;
    private String Unit;
    private String Scalar;

    public int getDataType() {
        return DataType;
    }

    public void setDataType(int DataType) {
        this.DataType = DataType;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getHexValue() {
        return HexValue;
    }

    public void setHexValue(String HexValue) {
        this.HexValue = HexValue;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public String getScalar() {
        return Scalar;
    }

    public void setScalar(String Scalar) {
        this.Scalar = Scalar;
    }

    @Override
    public String toString() {
        return "MeterParameter{" + "DataType=" + DataType + ", Code=" + Code + ", HexValue=" + HexValue + ", Value=" + Value + ", Unit=" + Unit + ", Scalar=" + Scalar + '}';
    }
    
    
}
