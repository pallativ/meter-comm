/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coralinnovations.data.parser.models;

/**
 *
 * @author Veera
 */
public class DataPointer {

    private int index;
    private int noOfObjects;

    public DataPointer() {
    }

    public int getIndex() {
        return index;
    }

    public int getNoOfObjects() {
        return noOfObjects;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setNoOfObjects(int noOfObjects) {
        this.noOfObjects = noOfObjects;
    }

    public int incrementAndGet() {
        return ++index;
    }

    public int getAndIncrement() {
        var val = index;
        index++;
        return val;
    }
}

