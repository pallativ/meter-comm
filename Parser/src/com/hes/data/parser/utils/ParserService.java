/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hes.data.parser.utils;

import com.hes.data.parser.models.DataPointer;
import com.hes.data.parser.models.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;

/**
 *
 * @author Veera
 */
public class ParserService {

    public static HashMap<Integer, ArrayList<Parameter>> GetParameters(String framesAsString, boolean errorFlag) {
        var records = GetDataRecord(framesAsString, errorFlag);
        var result = ParseComplexType(records);
        return result;
    }

    public static ArrayList<Parameter> GetObisCodes(String data) {
        var result = new ArrayList<Parameter>();
        var bytes = data.split(" ");
        var dataPointer = new DataPointer();
        dataPointer.setIndex(1);
        dataPointer.setNoOfObjects(0);
        var noOfRecords = Integer.parseInt(bytes[dataPointer.getAndIncrement()], 16);
        System.out.println("# Records:" + noOfRecords);
        for (int i = 0; i < noOfRecords; i++) {
            var objectType = Integer.parseInt(bytes[dataPointer.getAndIncrement()], 16);
            var noOfObjectsInRecord = Integer.parseInt(bytes[dataPointer.getAndIncrement()], 16);
            dataPointer.setNoOfObjects(noOfObjectsInRecord);
            System.out.println("Record #:" + i);
            System.out.println("Object Type:" + objectType);
            var items = ParseStructure(bytes, dataPointer);
            result.addAll(items);
        }
        return result;
    }

    private static String GetDataRecord(String framesAsString, boolean errorFlag) {
        var dataRecords = framesAsString.split("\n");
        StringBuilder sb = new StringBuilder();
        var lastRecordIndex = dataRecords.length;
        for (String dataRecord : dataRecords) {
            String[] record = dataRecord.split(" ");
            var from = 20;
            if (lastRecordIndex == 1 && errorFlag == true) {
                from = 18;
            }
            var temp = String.join(" ", Arrays.copyOfRange(record, from, record.length - 2));
            sb.append(temp).append(" ");
            --lastRecordIndex;
        }
        return sb.toString();
    }

    private static HashMap<Integer, ArrayList<Parameter>> ParseComplexType(String data) {
        var records = new HashMap<Integer, ArrayList<Parameter>>();
        var bytes = data.split(" ");
        var dataPointer = new DataPointer();
        dataPointer.setIndex(1);
        dataPointer.setNoOfObjects(0);
        var noOfRecords = Integer.parseInt(bytes[dataPointer.getAndIncrement()], 16);
        System.out.println("# Records:" + noOfRecords);
        for (int i = 0; i < noOfRecords; i++) {
            var complexObjectType = Integer.parseInt(bytes[dataPointer.getAndIncrement()], 16);
            var noOfObjectsInRecord = Integer.parseInt(bytes[dataPointer.getAndIncrement()], 16);
            dataPointer.setNoOfObjects(noOfObjectsInRecord);
            System.out.println("Record #:" + i);
            System.out.println("Object Type:" + complexObjectType);
            System.out.println("# Objects in a record:" + noOfObjectsInRecord);
            var parameters = ParseStructure(bytes, dataPointer);
            records.put(i + 1, parameters);
        }
        return records;
    }

    public static ArrayList<Parameter> ParseStructure(String[] bytes, DataPointer dataPointer) {
        ArrayList<Parameter> result = new ArrayList<>();
        var numberOfExtracted = 1;
        var currentObjectIndex = 0;
        while (currentObjectIndex < dataPointer.getNoOfObjects()) {
            var dataType = Integer.parseInt(bytes[dataPointer.getIndex()], 16);
            switch (dataType) {
                case 0x06, 0x05 -> { // 5 - Int32  6 - Uint32
                    var noOfBytes = 4;
                    var start = dataPointer.incrementAndGet();
                    var end = start + noOfBytes;
                    var valueBytes = Arrays.copyOfRange(bytes, start, end);
                    var parameter = new Parameter();
                    parameter.setDataType(dataType);
                    parameter.setHexValue(String.join(" ", valueBytes));
                    result.add(parameter);
                    System.out.println(dataType + " " + String.join(" ", valueBytes));
                    numberOfExtracted = noOfBytes;
                }
                case 0x09 -> { // OctetString.
                    var noOfBytes = Integer.parseInt(bytes[dataPointer.incrementAndGet()], 16);
                    var start = dataPointer.incrementAndGet();
                    var end = start + noOfBytes;
                    var valueBytes = Arrays.copyOfRange(bytes, start, end);
                    var parameter = new Parameter();
                    parameter.setDataType(dataType);
                    parameter.setHexValue(String.join(" ", valueBytes));
                    System.out.println("Data Type:" + dataType + " ,Length:" + noOfBytes + " ,HEX:" + String.join(" ", valueBytes));
                    numberOfExtracted = noOfBytes;
                    result.add(parameter);
                }
                case 0x12 -> {
                    // String UTF8 // It's Hack need to understand better.
                    // This is as per old. I am not sure about this - Veera
                    dataPointer.getAndIncrement();
                    dataPointer.getAndIncrement();
                    numberOfExtracted = 1;
                }
                case 0x02 -> { // Strucutre
                    throw new NotImplementedException();
                }
                case 0x0F -> {
                    // Don't know what this is. I don't find any documenation for this type.
                    // Reading just one byte as per old code. - Veera
                    dataPointer.getAndIncrement();
                    numberOfExtracted = 1;
                }
                default ->
                    throw new AssertionError();
            }
            dataPointer.setIndex(dataPointer.getIndex() + numberOfExtracted);
            currentObjectIndex++;
        }

        return result;
    }

}
