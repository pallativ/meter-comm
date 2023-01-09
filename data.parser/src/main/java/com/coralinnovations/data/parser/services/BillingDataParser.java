/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.coralinnovations.data.parser.services;

import com.coralinnovations.data.parser.models.MeterParameter;
import com.coralinnovations.data.parser.models.Parameter;
import com.coralinnovations.data.parser.utils.DlmsObjectConvert;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Veera
 */
public class BillingDataParser {

    private final String mrdFilePath;

    public BillingDataParser(String mrdFilePath) {
        this.mrdFilePath = mrdFilePath;
    }

    public HashMap<Integer, ArrayList<MeterParameter>> Parse() throws IOException, Exception {
        String data = ReadData(mrdFilePath);
        String obisCodesData = ReadCodes(mrdFilePath);
        var result = BaseParserService.GetParameters(data, false);
        var obisCodes = BaseParserService.GetObisCodes(obisCodesData);
        var mergeResult = MergeObisCodes(obisCodes, result);
        ConvertToReadable(mergeResult);
        return mergeResult;
    }

    private static String ReadData(String fileName) throws IOException {
        File dataFile = new File(fileName);
        String fileData = FileUtils.readFileToString(dataFile, Charset.defaultCharset());
        String obisData = StringUtils.substringBetween(fileData, "<OBISDATA>\n",
                "\n</OBISDATA>");
        return obisData;
    }

    private static String ReadCodes(String fileName) throws IOException {
        File dataFile = new File(fileName);
        String fileData = FileUtils.readFileToString(dataFile, Charset.defaultCharset());
        String data = StringUtils.substringBetween(fileData, "<OBISCODES>\n",
                "\n</OBISCODES>");
        var bytes = data.split(" ");
        return String.join(" ", Arrays.copyOfRange(bytes, 12, bytes.length - 2));
    }

    private static HashMap<Integer, ArrayList<MeterParameter>> MergeObisCodes(ArrayList<Parameter> obisCodes, HashMap<Integer, ArrayList<Parameter>> billingRecords) {
        Integer recordIndex = 0;
        var mergeResult = new HashMap<Integer, ArrayList<MeterParameter>>();
        for (Map.Entry<Integer, ArrayList<Parameter>> billingRecord : billingRecords.entrySet()) {
            var resultBilling = new ArrayList<MeterParameter>();
            int index = 0;
            for (var parameter : billingRecord.getValue()) {
                var meterParameter = new MeterParameter();
                meterParameter.setCode(obisCodes.get(index++).getHexValue());
                meterParameter.setHexValue(parameter.getHexValue());
                meterParameter.setDataType(parameter.getDataType());
                resultBilling.add(meterParameter);
            }
            mergeResult.put(recordIndex++, resultBilling);
        }
        return mergeResult;
    }

    private static void ConvertToReadable(HashMap<Integer, ArrayList<MeterParameter>> mergeResult) {
        for (Map.Entry<Integer, ArrayList<MeterParameter>> billingRecord : mergeResult.entrySet()) {
            for (MeterParameter meterParameter : billingRecord.getValue()) {
                switch (meterParameter.getDataType()) {
                    case 9 -> {
                        var dateTime = DlmsObjectConvert.toDateTime(meterParameter.getHexValue());
                        meterParameter.setValue(dateTime);
                    }
                    case 6, 5 -> {
                        var value = DlmsObjectConvert.ToLong(meterParameter.getHexValue().split(" "));
                        meterParameter.setValue(String.valueOf(value));
                    }
                    default ->
                        throw new AssertionError();
                }
            }
        }
    }
}
