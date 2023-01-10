/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.parser.app.services;

import com.parser.app.models.BillingRecord;
import com.parser.app.models.MeterParameter;
import com.parser.app.models.Parameter;
import com.parser.app.utils.DlmsObjectConvert;
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

    public ArrayList<BillingRecord> Parse() throws IOException, Exception {
        String obisCodes = ReadData(mrdFilePath, "OBISCODES");
        var bytes = obisCodes.split(" ");
        bytes = Arrays.copyOfRange(bytes, 12, bytes.length - 2);
        obisCodes = String.join(" ", bytes);
        var obisCodesCollection = BaseParserService.GetObisCodes(obisCodes);

        String obisCodesData = ReadData(mrdFilePath, "OBISDATA");
        var dataCollection = BaseParserService.GetParameters(obisCodesData, false);

        // This commented for now, to make it functional.
//        String scalerCodes = ReadData(mrdFilePath, "SCALAROBISCODES");
//        bytes = scalerCodes.split(" ");
//        bytes = Arrays.copyOfRange(bytes, 20, bytes.length - 2);
//        scalerCodes = String.join(" ", bytes);
//        var scalarCodeCollection = BaseParserService.GetObisCodes(scalerCodes);
//
//        String scalerCodesData = ReadData(mrdFilePath, "SCALAROBISDATA");
//        bytes = scalerCodesData.split(" ");
//        bytes = Arrays.copyOfRange(bytes, 20, bytes.length - 2);
//        scalerCodesData = String.join(" ", bytes);
//        var scalarCodeDataCollection = BaseParserService.GetObisCodes(scalerCodesData);
        var mergeResult = MergeObisCodes(obisCodesCollection, dataCollection);
        ConvertToReadable(mergeResult);
        return mergeResult;
    }

    private static String ReadData(String fileName, String xmlTagName) throws IOException {
        File dataFile = new File(fileName);
        String fileData = FileUtils.readFileToString(dataFile, Charset.defaultCharset());
        var openTag = "<" + xmlTagName + ">\n";
        var closeTag = "\n</" + xmlTagName + ">";
        String obisData = StringUtils.substringBetween(fileData, openTag, closeTag);
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

    private static ArrayList<BillingRecord> MergeObisCodes(ArrayList<Parameter> obisCodes, HashMap<Integer, ArrayList<Parameter>> billingRecords) {
        Integer recordIndex = 0;
        var mergeResult = new ArrayList<BillingRecord>();
        int billingRecordIndex = 1;
        for (Map.Entry<Integer, ArrayList<Parameter>> billingRecord : billingRecords.entrySet()) {
            var billingParameters = new ArrayList<MeterParameter>();
            int index = 0;
            for (var parameter : billingRecord.getValue()) {
                var meterParameter = new MeterParameter();
                meterParameter.setCode(obisCodes.get(index++).getHexValue());
                meterParameter.setHexValue(parameter.getHexValue());
                meterParameter.setDataType(parameter.getDataType());
                billingParameters.add(meterParameter);
            }
            mergeResult.add(new BillingRecord(billingRecordIndex++, billingParameters));
        }
        return mergeResult;
    }

    private static void ConvertToReadable(ArrayList<BillingRecord> mergeResult) {
        for (BillingRecord billingRecord : mergeResult) {
            for (MeterParameter meterParameter : billingRecord.getParameters()) {
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
