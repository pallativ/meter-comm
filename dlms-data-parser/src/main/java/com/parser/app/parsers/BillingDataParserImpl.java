/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.parser.app.parsers;

import com.parser.app.models.BillingHistoryModel;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author Veera
 */
@Service
public class BillingDataParserImpl implements BillingDataParser {

    @Autowired
    private Environment env;

    @Override
    public ArrayList<BillingHistoryModel> Parse(String mrdFilePath) throws IOException, Exception {

        // TODO : Fix this issue
        if (env != null) {
            var temp = env.getProperty("dlms.data.parser.enableTraces", "false");
            BaseDataParser.EnableTraces = Boolean.parseBoolean(temp);
        }

        String obisCodes = ReadData(mrdFilePath, "OBISCODES");
        var fileName = new File(mrdFilePath).getName();
        var meterNumber = fileName.split("_")[0];
        var bytes = obisCodes.split(" ");
        bytes = Arrays.copyOfRange(bytes, 12, bytes.length - 2);
        obisCodes = String.join(" ", bytes);
        var obisCodesCollection = BaseDataParser.GetObisCodes(obisCodes);

        String obisCodesData = ReadData(mrdFilePath, "OBISDATA");
        var dataCollection = BaseDataParser.GetParameters(obisCodesData, false);

        // This commented for now, to make it functional.
//        String scalerCodes = ReadData(mrdFilePath, "SCALAROBISCODES");
//        bytes = scalerCodes.split(" ");
//        bytes = Arrays.copyOfRange(bytes, 20, bytes.length - 2);
//        scalerCodes = String.join(" ", bytes);
//        var scalarCodeCollection = BaseDataParser.GetObisCodes(scalerCodes);
//
//        String scalerCodesData = ReadData(mrdFilePath, "SCALAROBISDATA");
//        bytes = scalerCodesData.split(" ");
//        bytes = Arrays.copyOfRange(bytes, 20, bytes.length - 2);
//        scalerCodesData = String.join(" ", bytes);
//        var scalarCodeDataCollection = BaseDataParser.GetObisCodes(scalerCodesData);
        var mergeResult = MergeObisCodes(obisCodesCollection, dataCollection);
        ConvertToReadable(mergeResult);
        mergeResult.forEach(model -> model.setMeterNumber(meterNumber));
        return mergeResult;
    }

    private static String ReadData(String fileName, String xmlTagName) throws IOException {
        File dataFile = new File(fileName);
        String fileData = FileUtils.readFileToString(dataFile, Charset.defaultCharset());
        var openTag = "<" + xmlTagName + ">\r\n";
        var closeTag = "\r\n</" + xmlTagName + ">";
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

    private static ArrayList<BillingHistoryModel> MergeObisCodes(ArrayList<Parameter> obisCodes, HashMap<Integer, ArrayList<Parameter>> billingRecords) {
        Integer recordIndex = 0;
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

    private static void ConvertToReadable(ArrayList<BillingHistoryModel> mergeResult) {
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
                    default ->
                        throw new AssertionError();
                }
            }
        }
    }
}
