/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parser.app.parsers;

import com.parser.app.models.DataPointer;
import com.parser.app.models.Parameter;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Veera
 */
public class BaseDataParser {

    public static boolean EnableTraces = true;
    public static String frameSplitOperator = "\r\n";

    public static HashMap<Integer, ArrayList<Parameter>> getParameters(String framesAsString, boolean errorFlag) throws Exception {
        var completeData = getDataRecord(framesAsString, errorFlag);
        var result = parseArray(completeData);
        return result;
    }

    public static ArrayList<Parameter> getObisCodes(String data) throws Exception {
        var result = new ArrayList<Parameter>();
        var bytes = data.split(" ");
        var dataPointer = new DataPointer();
        dataPointer.setIndex(1);
        dataPointer.setNoOfObjects(0);
        var noOfRecords = Integer.parseInt(bytes[dataPointer.getAndIncrement()], 16);
        if (EnableTraces) {
            System.out.println("# Records:" + noOfRecords);
        }
        for (int i = 0; i < noOfRecords; i++) {
            var objectType = Integer.parseInt(bytes[dataPointer.getAndIncrement()], 16);
            var noOfObjectsInRecord = Integer.parseInt(bytes[dataPointer.getAndIncrement()], 16);
            dataPointer.setNoOfObjects(noOfObjectsInRecord);
            if (EnableTraces) {
                System.out.println("Record #:" + i);
                System.out.println("Object Type:" + objectType);
            }
            var items = parseStructure(bytes, dataPointer);
            result.addAll(items);
        }
        return result;
    }

    private static boolean isLastRecord(String[] bytes) {
        return bytes[11].equals("01") && (bytes[17].equals("70") || bytes[17].equals("81"));
    }

    private static String getDataRecord(String framesAsString, boolean errorFlag) {
        var dataRecords = framesAsString.split(frameSplitOperator);
        StringBuilder sb = new StringBuilder();
        for (String dataRecord : dataRecords) {
            String[] record = dataRecord.split(" ");
            var from = 20;
            // NOTE: This is based on traces provided by customer traces.
            if (isLastRecord(record)) {
                from = 18;
            }
            if(record[17].equals("81")){
                from = 19;
            }
            var temp = String.join(" ", Arrays.copyOfRange(record, from, record.length - 2));
            sb.append(temp).append(" ");
        }
        return sb.toString();
    }

    private static HashMap<Integer, ArrayList<Parameter>> parseArray(String data) throws Exception {
        var records = new HashMap<Integer, ArrayList<Parameter>>();
        var bytes = data.split(" ");
        var dataPointer = new DataPointer();
        dataPointer.setIndex(1);
        dataPointer.setNoOfObjects(0);
        var noOfRecords = Integer.parseInt(bytes[dataPointer.getAndIncrement()], 16);
        if (EnableTraces) {
            System.out.println("# Records:" + noOfRecords);
        }
        for (int i = 0; i < noOfRecords; i++) {
            var complexObjectType = Integer.parseInt(bytes[dataPointer.getAndIncrement()], 16);
            var noOfObjectsInRecord = Integer.parseInt(bytes[dataPointer.getAndIncrement()], 16);
            dataPointer.setNoOfObjects(noOfObjectsInRecord);
            if (EnableTraces) {
                System.out.println("Record #:" + i);
                System.out.println("Object Type:" + complexObjectType);
                System.out.println("# Objects in a record:" + noOfObjectsInRecord);
            }
            var parameters = parseStructure(bytes, dataPointer);
            records.put(i + 1, parameters);
        }
        return records;
    }

    private static ArrayList<Parameter> parseStructure(String[] bytes, DataPointer dataPointer) throws Exception {
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
                    if (EnableTraces) {
                        System.out.println(dataType + " " + String.join(" ", valueBytes));
                    }
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
                    if (EnableTraces) {
                        System.out.println("Data Type:" + dataType + " ,Length:" + noOfBytes + " ,HEX:" + String.join(" ", valueBytes));
                    }
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
                default -> {
                    //var temp = Arrays.copyOfRange(bytes, dataPointer.getIndex() - 10, dataPointer.getIndex() + 10);
                    //System.out.println(String.join(" ", temp));
                    throw new Exception("Invalid data type : " + dataType);
                }
            }
            dataPointer.setIndex(dataPointer.getIndex() + numberOfExtracted);
            currentObjectIndex++;
        }
        return result;
    }
}
