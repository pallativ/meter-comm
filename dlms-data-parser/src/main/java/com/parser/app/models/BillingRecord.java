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
public class BillingRecord {

    private int index;
    private ArrayList<MeterParameter> parameters;

    public BillingRecord(int index, ArrayList<MeterParameter> parameters) {
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

}
