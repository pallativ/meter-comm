/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parser.app.models;

/**
 *
 * @author Veera
 */
public class Parameter {

    private int DataType;
    private String HexValue;

    public void setHexValue(String HexValue) {
        this.HexValue = HexValue;
    }

    public int getDataType() {
        return DataType;
    }

    public void setDataType(int DataType) {
        this.DataType = DataType;
    }

    public String getHexValue() {
        return HexValue;
    }

    @Override
    public String toString() {
        return "Parameter{" + "DataType=" + DataType + ", HexValue=" + HexValue + '}';
    }
    
}

