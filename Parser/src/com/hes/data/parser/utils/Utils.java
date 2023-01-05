package com.hes.data.parser.utils;

import com.hes.data.parser.models.ParseResult;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

/**
 *
 * @author psvr
 *
 */
public class Utils {

    public Map<String, String> readProperties(String fileName) {
        Map<String, String> map = new HashMap<>();
        try {
            String workingDir = System.getProperty("user.dir");
            File configFilePath = new File(workingDir, fileName);
            if (!configFilePath.exists()) {
                configFilePath = new File("c:\\smartmeter\\" + fileName);
            }

            List<String> dataList = FileUtils.readLines(configFilePath);

            for (String str : dataList) {
                try {
                    String[] strArr = str.split("=");
                    map.put(strArr[0], strArr[1].replace(" ", "").replace("\n", ""));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static ParseResult convert(String[] hexNumbers, int start, int length) {
        var parseResult = new ParseResult();
        var valueArray = Arrays.copyOfRange(hexNumbers, start, start + length);
        var hexRepresentation = String.join("", valueArray);

        var binaryRepresetation = getBinary(hexRepresentation);
        parseResult.HexData = String.join(" ", valueArray);
        parseResult.BinaryData = binaryRepresetation;
        System.out.println(String.format("HEX: %s -> BINARY: %s", parseResult.HexData, binaryRepresetation));

        if (binaryRepresetation.charAt(0) == '1') {
            parseResult.Value = convertFromNegativeHex(binaryRepresetation);
        } else {
            parseResult.Value = Long.parseLong(binaryRepresetation, 2);
        }
        parseResult.NextPointerLocation = start + length;
        return parseResult;
    }

    private static long convertFromNegativeHex(String binaryRepresenation) {
        StringBuilder newValue = new StringBuilder();
        for (int i = 0; i < binaryRepresenation.length(); i++) {
            newValue.append(binaryRepresenation.charAt(i) == '1' ? "0" : "1");
        }
        return (Long.parseLong(newValue.toString(), 2) * -1) + 1;
    }

    private static String getBinary(String hex) {
        var binary = Long.toBinaryString(Long.parseLong(hex, 16));
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 32 - binary.length()) {
            sb.append('0');
        }
        sb.append(binary);
        return sb.toString();
    }

    public static String GetDataRecord(String framesAsString) {
        var dataRecords = framesAsString.split("\n");
        StringBuilder sb = new StringBuilder();
        var lastRecordIndex = dataRecords.length;
        for (String dataRecord : dataRecords) {
            String[] record = dataRecord.split(" ");
            var from = 20;
            if(lastRecordIndex == 1)
                from = 18;
            var temp = String.join(" ", Arrays.copyOfRange(record, from, record.length - 2));
            sb.append(temp).append(" ");
            --lastRecordIndex;
        }
        return sb.toString();
    }

    public static void ParseComplexType(String data) {
        var bytes = data.split(" ");
        int index = 1;
        var noOfRecords = Integer.parseInt(bytes[index++], 16);
        System.out.println("# Records:" + noOfRecords);
        for (int i = 0; i < noOfRecords; i++) {
            var complexObjectType = Integer.parseInt(bytes[index++], 16);
            var noOfObjectsInRecord = Integer.parseInt(bytes[index++], 16);
            System.out.println("Record #:" + i);
            System.out.println("Complex Object Type:" + complexObjectType);
            System.out.println("# Objects in a record:" + noOfObjectsInRecord);
            index = ParseStructure(bytes, index, noOfObjectsInRecord);
        }
    }

    public static int ParseStructure(String[] bytes, int index, int noOfObjects) {
        var numberOfExtracted = 1;
        var currentObjectIndex = 0;
        while (currentObjectIndex < noOfObjects) {
            var dataType = Integer.parseInt(bytes[index], 16);
            switch (dataType) {
                case 6, 5 -> {
                    var noOfBytes = 4;
                    var start = index + 1;
                    var end = start + noOfBytes;
                    var valueBytes = Arrays.copyOfRange(bytes, start, end);
                    System.out.println(dataType + " " + String.join(" ", valueBytes));
                    numberOfExtracted = noOfBytes;
                }
                case 9 -> {
                    var noOfBytes = 13;
                    var start = index + 1;
                    var end = start + noOfBytes;
                    var valueBytes = Arrays.copyOfRange(bytes, start, end);
                    System.out.println("Attribute -> Data Type:" + dataType + " Data:" + String.join(" ", valueBytes));
                    numberOfExtracted = noOfBytes;
                }
                default ->
                    throw new AssertionError();
            }
            index += numberOfExtracted + 1;
            currentObjectIndex++;
        }
        return index;
    }
}
