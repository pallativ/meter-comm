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
        var valueArray = Arrays.copyOfRange(hexNumbers, start, start + length);
        var result = convertPrivate(valueArray);
        result.NextPointerLocation = start + length;
        return result;
    }

    public static ParseResult convertPrivate(String[] valueArray) throws NumberFormatException {
        var parseResult = new ParseResult();
        var hexRepresentation = String.join("", valueArray);
        var binaryRepresetation = getBinary(hexRepresentation);
        parseResult.HexData = String.join(" ", valueArray);
        parseResult.BinaryData = binaryRepresetation;

        if (binaryRepresetation.charAt(0) == '1') {
            parseResult.Value = convertFromNegativeHex(binaryRepresetation);
        } else {
            parseResult.Value = Long.parseLong(binaryRepresetation, 2);
        }
        return parseResult;
    }

    public static long ToLongFromHex(String[] valueArray) throws NumberFormatException {
        var hexRepresentation = String.join("", valueArray);
        var binaryRepresetation = getBinary(hexRepresentation);

        if (binaryRepresetation.charAt(0) == '1') {
            return convertFromNegativeHex(binaryRepresetation);
        } else {
            return Long.parseLong(binaryRepresetation, 2);
        }
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

    
}
