/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Veera
 */
public class ScalarConstant {
    private String obisCode;
    private float sclar;
    private String unit;
    private int dataIndex;

    public ScalarConstant(int dataIndex, String obisCode, float sclar, String unit) {
        this.obisCode = obisCode;
        this.sclar = sclar;
        this.unit = unit;
        this.dataIndex = dataIndex;
    }

    public int getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(int dataIndex) {
        this.dataIndex = dataIndex;
    }
        
    public String getObisCode() {
        return obisCode;
    }

    public void setObisCode(String obisCode) {
        this.obisCode = obisCode;
    }

    public float getSclar() {
        return sclar;
    }

    public void setSclar(float sclar) {
        this.sclar = sclar;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "ScalarConstant{" + "obisCode=" + obisCode + ", sclar=" + sclar + ", unit=" + unit + '}';
    }
    
}
