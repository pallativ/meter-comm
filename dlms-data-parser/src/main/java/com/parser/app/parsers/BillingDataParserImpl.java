/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.parser.app.parsers;

import com.parser.app.models.BillingHistoryModel;
import com.parser.app.models.MeterParameter;
import com.parser.app.models.Parameter;
import com.parser.app.utils.DlmsObjectConvert;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Veera
 */
@Service
public class BillingDataParserImpl implements BillingDataParser {

    private static String frameSplitOperator = "\r\n";
    @Autowired
    private Environment env;

    private static String readData(String fileName, String xmlTagName) throws IOException {
        File dataFile = new File(fileName);
        String fileData = FileUtils.readFileToString(dataFile, Charset.defaultCharset());
        var openTag = "<" + xmlTagName + ">" + frameSplitOperator;
        var closeTag = frameSplitOperator + "</" + xmlTagName + ">";
        return StringUtils.substringBetween(fileData, openTag, closeTag);
    }

    private static ArrayList<BillingHistoryModel> mergeObisCodes(ArrayList<Parameter> obisCodes, HashMap<Integer, ArrayList<Parameter>> billingRecords) {
        var mergeResult = new ArrayList<BillingHistoryModel>();
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
            mergeResult.add(new BillingHistoryModel(billingRecordIndex++, billingParameters));
        }
        return mergeResult;
    }

    private static void updateValues(ArrayList<BillingHistoryModel> mergeResult) {
        for (BillingHistoryModel billingRecord : mergeResult) {
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
                    default -> throw new AssertionError();
                }
            }
        }
    }

    @Override
    public ArrayList<BillingHistoryModel> parse(String mrdFilePath) throws Exception {

        // TODO : Fix this issue
        if (env != null) {
            var temp = env.getProperty("dlms.data.parser.enableTraces", "false");
            BaseDataParser.EnableTraces = Boolean.parseBoolean(temp);
            frameSplitOperator = env.getProperty("dlms.data.parser.splitOperator");
            BaseDataParser.frameSplitOperator = frameSplitOperator;
        }

        String obisCodes = readData(mrdFilePath, "OBISCODES");
        var fileName = new File(mrdFilePath).getName();
        var meterNumber = fileName.split("_")[0];
        var bytes = obisCodes.split(" ");
        bytes = Arrays.copyOfRange(bytes, 12, bytes.length - 2);
        obisCodes = String.join(" ", bytes);
        var obisCodesCollection = BaseDataParser.getObisCodes(obisCodes);

        String obisCodesData = readData(mrdFilePath, "OBISDATA");
        var dataCollection = BaseDataParser.getParameters(obisCodesData, false);

        /*
 This commented for now, to make it functional.
        String scalarCodes = ReadData(mrdFilePath, "SCALAROBISCODES");
        bytes = scalarCodes.split(" ");
        bytes = Arrays.copyOfRange(bytes, 20, bytes.length - 2);
        scalarCodes = String.join(" ", bytes);
        var scalarCodeCollection = BaseDataParser.getObisCodes(scalerCodes);

        String scalarCodesData = ReadData(mrdFilePath, "SCALAROBISDATA");
        bytes = scalarCodesData.split(" ");
        bytes = Arrays.copyOfRange(bytes, 20, bytes.length - 2);
        scalarCodesData = String.join(" ", bytes);
        var scalarCodeDataCollection = BaseDataParser.getObisCodes(scalerCodesData);
*/
        var mergeResult = mergeObisCodes(obisCodesCollection, dataCollection);
        updateValues(mergeResult);
        mergeResult.forEach(model -> model.setMeterNumber(meterNumber));
        return mergeResult;
    }
}
