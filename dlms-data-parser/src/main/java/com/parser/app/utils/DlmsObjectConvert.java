/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parser.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;


/**
 *
 * @author Veera
 */
public class DlmsObjectConvert {

    public static String toDateTime(String dateStr) {
        String str = "";
        try {

            String[] responseArr = dateStr.split(" ");
            Integer year = Integer.valueOf(responseArr[0] + responseArr[1], 16);
            Integer month = Integer.valueOf(responseArr[2], 16);
            Integer day = Integer.valueOf(responseArr[3], 16);

            Integer hour = Integer.valueOf(responseArr[5], 16);
            Integer minutes = Integer.valueOf(responseArr[6], 16);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");

            Date meterDate = dateFormat.parse(StringUtils.leftPad(year + "", 4, "0")
                    + StringUtils.leftPad((month) + "", 2, "0")
                    + StringUtils.leftPad((day) + "", 2, "0")
                    + StringUtils.leftPad(hour + "", 2, "0")
                    + StringUtils.leftPad(minutes + "", 2, "0"));

            str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(meterDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static long ToLong(String[] valueArray) throws NumberFormatException {
        var hexRepresentation = String.join("", valueArray);
        var binaryRepresetation = getBinary(hexRepresentation);

        if (binaryRepresetation.charAt(0) == '1') {
            return toLongNegative(binaryRepresetation);
        } else {
            return Long.parseLong(binaryRepresetation, 2);
        }
    }

    private static long toLongNegative(String binaryRepresenation) {
        StringBuilder newValue = new StringBuilder();
        for (int i = 0; i < binaryRepresenation.length(); i++) {
            newValue.append(binaryRepresenation.charAt(i) == '1' ? "0" : "1");
        }
        return (Long.parseLong(newValue.toString(), 2) * -1) + 1;
    }

    private static String getBinary(String hex) {
        var binary = Long.toBinaryString(Long.parseLong(hex, 16));
        return StringUtils.leftPad(binary, 32, '0');
    }
}
