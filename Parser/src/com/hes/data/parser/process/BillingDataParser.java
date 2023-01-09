package com.hes.data.parser.process;

import java.io.File;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.hes.data.parser.dao.MeterRuleEngineDAO;
import com.hes.data.parser.models.MeterParameter;
import com.hes.data.parser.models.Parameter;
import com.hes.data.parser.utils.HexToStringUtils;
import com.hes.data.parser.utils.ParserService;
import com.hes.data.parser.utils.Utils;
import com.hes.data.parser.vo.BillingData;
import com.hes.data.parser.vo.BillingDataId;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;

public class BillingDataParser {

    public List<String> processBillingObisData(String obisData) {
        List<String> list = new ArrayList<>();
        try {

            String[] framesArr = obisData.split("\n");
            String[] strArr = framesArr[0].split(" ");

            Integer noOfIntervals = 0;// No Of data objects
            Integer noOfDataObjects = 0;//noOfDataObjects
            String dataEleStr = "";
            for (Integer cnt = 0; cnt < framesArr.length; cnt++) {
                Integer pointer = 0;
                strArr = framesArr[cnt].split(" ");
                try {
                    if (cnt == 0) {
//						String wrapperVersion=strArr[0]+strArr[1];// Wrapper Version
//						String destAddress=strArr[2]+strArr[3];// destination address
//						String srcAddress=strArr[4]+strArr[5];// Source address
//						String lengthOfFrame=strArr[6]+strArr[7];// length of frame
//						String readActionResponse=strArr[8]+strArr[9];// Read action response
//						String invokeIdAndPriority=strArr[10];//Invoke ID and priority
//						String timeOutFlag=strArr[11];//Invoke ID and priority
//						String noOfObjects=strArr[12];// CCCCCC?????????

                        if (strArr[20].equalsIgnoreCase("01") && strArr[22].equalsIgnoreCase("02")) {
                            noOfIntervals = Integer.decode("0x" + strArr[21]);// No Of data objects
                            noOfDataObjects = Integer.decode("0x" + strArr[23]);//noOfDataObjects
                        }
                        pointer = 24;
                    } else {
//						pointer=22;
                    }

                    for (Integer y = 0; y < noOfIntervals; y++) {//No of intervals parsing
                        try {

                            StringBuilder strb = new StringBuilder();

                            // TODO : What is this condition.
                            if (y > 0) {
                                pointer = pointer + 2;
                            }
                            for (Integer z = 0; z < noOfDataObjects; z++) {//No of noOfDataObjects parsing
                                try {
                                    String strArrPointerVal = strArr[pointer];
                                    dataEleStr = "";
                                    try {

                                        if (strArr[pointer].equalsIgnoreCase("FF") && strArr[pointer + 1].equalsIgnoreCase("FF") && ((strArr.length == pointer + 2))) {//LAST
                                            strArr = framesArr[++cnt].split(" ");
                                            pointer = 20;
                                            if (!((strArr[0] + strArr[1] + strArr[2] + strArr[3]).equalsIgnoreCase("00010001"))) {
                                                strArr = framesArr[++cnt].split(" ");

//												continue;
                                            }

                                            strArrPointerVal = strArr[pointer];
                                        } else if (strArr[pointer + 1].equalsIgnoreCase("FF") && strArr[pointer + 2].equalsIgnoreCase("FF") && ((strArr.length == pointer + 3))) {
                                            strArrPointerVal = strArr[pointer];
                                            strArr = framesArr[++cnt].split(" ");
                                            pointer = 19;
                                            if (!((strArr[0] + strArr[1] + strArr[2] + strArr[3]).equalsIgnoreCase("00010001"))) {
                                                strArr = framesArr[++cnt].split(" ");
//												pointer=20;
//												continue;
                                            }

                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    if ((strArrPointerVal.equalsIgnoreCase("05"))) {
                                        var result = Utils.convert(strArr, ++pointer, 4);
                                        dataEleStr = String.valueOf(result.Value);
                                        pointer = result.NextPointerLocation;
                                    } else if ((strArrPointerVal.equalsIgnoreCase("06"))) {
                                        var result = Utils.convert(strArr, ++pointer, 4);
                                        dataEleStr = String.valueOf(result.Value);
                                        pointer = result.NextPointerLocation;
                                    } else if (strArrPointerVal.equalsIgnoreCase("09")) {
                                        Integer noOfDigits = Integer.decode("0x" + strArr[++pointer]);
                                        if (pointer + noOfDigits > strArr.length - 1 - 2) {
                                            Integer k = strArr.length - 1 - 2 - pointer;
                                            for (Integer j = 0; j < k; j++) {
                                                dataEleStr += strArr[++pointer] + " ";
                                                noOfDigits--;
                                            }
                                            strArr = framesArr[++cnt].split(" ");
                                            if (noOfDigits == 1) {//if last 3 digits
                                                pointer = 17;
                                            } else {//if last 2 digits
                                                pointer = 19;
                                            }
                                        }
                                        for (Integer j = 0; j < noOfDigits; j++) {
                                            dataEleStr += strArr[++pointer] + " ";
                                        }
                                        pointer++;
                                        if (noOfDigits == 8) {
                                            dataEleStr = HexToStringUtils.unHex(dataEleStr);
                                        } else {
                                            dataEleStr = HexToStringUtils.convertDateTime(dataEleStr);
                                        }

                                    } else if (strArrPointerVal.equalsIgnoreCase("10")) {
                                        Integer noOfDigits = 2;
                                        if (pointer + noOfDigits > strArr.length - 1 - 2) {
                                            Integer k = strArr.length - 1 - 2 - pointer;
                                            for (Integer j = 0; j < k; j++) {
                                                dataEleStr += strArr[++pointer] + " ";
                                                noOfDigits--;
                                            }
                                            strArr = framesArr[++cnt].split(" ");
                                            if (noOfDigits == 1) {//if last 3 digits
                                                pointer = 17;
                                            } else {//if last 2 digits
                                                pointer = 19;
                                            }
                                        }
                                        for (Integer j = 0; j < noOfDigits; j++) {
                                            dataEleStr += strArr[++pointer] + " ";
                                        }
                                        pointer++;
                                        dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
                                    } else if (strArrPointerVal.equalsIgnoreCase("11")) {
                                        Integer noOfDigits = 1;
                                        if (pointer + noOfDigits > strArr.length - 1 - 2) {
                                            Integer k = strArr.length - 1 - 2 - pointer;
                                            for (Integer j = 0; j < k; j++) {
                                                dataEleStr += strArr[++pointer] + " ";
                                                noOfDigits--;
                                            }
                                            strArr = framesArr[++cnt].split(" ");
                                            if (noOfDigits == 1) {//if last 3 digits
                                                pointer = 17;
                                            } else {//if last 2 digits
                                                pointer = 19;
                                            }
                                        }
                                        for (Integer j = 0; j < noOfDigits; j++) {
                                            dataEleStr += strArr[++pointer] + " ";
                                        }
                                        pointer++;
                                        dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
                                    } else if (strArrPointerVal.equalsIgnoreCase("12")) {
                                        Integer noOfDigits = 2;
                                        if (pointer + noOfDigits > strArr.length - 1 - 2) {
                                            Integer k = strArr.length - 1 - 2 - pointer;
                                            for (Integer j = 0; j < k; j++) {
                                                dataEleStr += strArr[++pointer] + " ";
                                                noOfDigits--;
                                            }
                                            strArr = framesArr[++cnt].split(" ");
                                            if (noOfDigits == 1) {//if last 3 digits
                                                pointer = 17;
                                            } else {//if last 2 digits
                                                pointer = 19;
                                            }
                                        }
                                        for (Integer j = 0; j < noOfDigits; j++) {
                                            dataEleStr += strArr[++pointer] + " ";
                                        }
                                        pointer++;
                                        dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
                                    } else if (strArrPointerVal.equalsIgnoreCase("0F")) {
                                        Integer noOfDigits = 1;
                                        if (pointer + noOfDigits > strArr.length - 1 - 2) {
                                            Integer k = strArr.length - 1 - 2 - pointer;
                                            for (Integer j = 0; j < k; j++) {
                                                dataEleStr += strArr[++pointer] + " ";
                                                noOfDigits--;
                                            }
                                            strArr = framesArr[++cnt].split(" ");
                                            if (noOfDigits == 1) {//if last 3 digits
                                                pointer = 17;
                                            } else {//if last 2 digits
                                                pointer = 19;
                                            }
                                        }
                                        for (Integer j = 0; j < noOfDigits; j++) {
                                            dataEleStr += strArr[++pointer] + " ";
                                        }
                                        pointer++;
                                        dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
                                    }

                                    System.out.println(dataEleStr + ",");
                                    System.out.println(" ==> pointer : " + pointer);
                                    strb.append(dataEleStr + ",");

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            list.add(strb.toString());
                            System.out.println(y + "==>" + strb.toString());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String processBillingData(List<String> obisHeaderList, List<String> dataList, String watchFolderPath, String meterSerialNumber) {
        String status = "false";
        try {
            Map<String, String> scalarprop = new Utils().readProperties("scalar.MDV");
            DecimalFormat df = new DecimalFormat("#####.##");

            for (String str : dataList) {

                try {
                    BillingData billingData = new BillingData();
                    BillingDataId id = new BillingDataId();
//					id.setInstantDatetime(instantDatetime);
//					id.setMeterSerialNumber(meterSerialNumber);

                    String[] strArr = str.split("\\,");

                    for (Integer i = 0; i < strArr.length; i++) {
                        String dataVal = strArr[i];
                        String obisCode = obisHeaderList.get(i);

                        String scalarVal = scalarprop.get(obisCode.toUpperCase());

                        try {
                            if (scalarVal != null) {
                                Double sclVal = Double.parseDouble(scalarVal);
                                dataVal = df.format(Double.parseDouble(dataVal) * sclVal) + "";
//								dataVal=(Double.parseDouble(dataVal)*sclVal)+"";
                            }
                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                        System.out.println("obisCode :: " + obisCode + " ==> dataVal :: " + dataVal);

                        if (obisCode.equalsIgnoreCase("00 00 01 00 00 FF")) {//clock
                            try {
                                Date instantDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataVal);
                                id.setBillingDatetime(new Timestamp(instantDatetime.getTime()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (obisCode.equalsIgnoreCase("01 00 01 08 00 FF")) {//Active energy import (+A)
                            billingData.setActEgyImp(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 02 08 00 FF")) {//Active energy export (-A)
                            billingData.setActEgyExp(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 0F 08 00 FF")) {//Active energy (|+A|+|-A|) Combined total
                            billingData.setActEgyCombinedTotal(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 10 08 00 FF")) {//Active energy (|+A|-|-A|) Net total
                            billingData.setActEgyNetTotal(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 09 08 00 FF")) {//Apparent energy import (+VA) (QI+QIV)
                            billingData.setAppEgyImp(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 0A 08 00 FF")) {//Apparent energy export (-VA) (QII+QIII)
                            billingData.setAppEgyExp(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 01 08 01 FF")) {//Active energy import (+A) rate 1
                            billingData.setActEgyImpRt1(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 01 08 02 FF")) {//Active energy import (+A) rate 2
                            billingData.setActEgyImpRt2(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 01 08 03 FF")) {//Active energy import (+A) rate 3
                            billingData.setActEgyImpRt3(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 01 08 04 FF")) {//Active energy import (+A) rate 4
                            billingData.setActEgyImpRt4(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 02 08 01 FF")) {//Active energy export (-A) rate 1
                            billingData.setActEgyExpRt1(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 02 08 02 FF")) {//Active energy export (-A) rate 2
                            billingData.setActEgyExpRt2(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 02 08 03 FF")) {//Active energy export (-A) rate 3
                            billingData.setActEgyExpRt3(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 02 08 04 FF")) {//Active energy export (-A) rate 4
                            billingData.setActEgyExpRt4(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 09 08 01 FF")) {//Apparent energy import (+VA) rate 1
                            billingData.setAppEgyImpRt1(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 09 08 02 FF")) {//Apparent energy import (+VA) rate 2
                            billingData.setAppEgyImpRt2(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 09 08 03 FF")) {//Apparent energy import (+VA) rate 3
                            billingData.setAppEgyImpRt3(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 09 08 04 FF")) {//Apparent energy import (+VA) rate 4
                            billingData.setAppEgyImpRt4(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 0A 08 01 FF")) {//Apparent energy export (-VA) rate 1
                            billingData.setAppEgyExpRt1(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 0A 08 02 FF")) {//Apparent energy export (-VA) rate 2
                            billingData.setAppEgyExpRt2(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 0A 08 03 FF")) {//Apparent energy export (-VA) rate 3
                            billingData.setAppEgyExpRt3(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 0A 08 04 FF")) {//Apparent energy export (-VA) rate 4
                            billingData.setAppEgyExpRt4(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 0F 08 01 FF")) {//Active energy Combined total (|+A|+|-A|) rate 1
                            billingData.setActEgyCombTotRt1(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 0F 08 02 FF")) {//Active energy Combined total (|+A|+|-A|) rate 2
                            billingData.setActEgyCombTotRt2(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 0F 08 03 FF")) {//Active energy Combined total (|+A|+|-A|) rate 3
                            billingData.setActEgyCombTotRt3(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 0F 08 04 FF")) {//Active energy Combined total (|+A|+|-A|) rate 4
                            billingData.setActEgyCombTotRt4(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 10 08 01 FF")) {//Active energy net total (|+A|-|-A|) rate 1
                            billingData.setActEgyNetTotRt1(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 10 08 02 FF")) {//Active energy net total (|+A|-|-A|) rate 2
                            billingData.setActEgyNetTotRt2(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 10 08 03 FF")) {//Active energy net total (|+A|-|-A|) rate 3
                            billingData.setActEgyNetTotRt3(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 10 08 04 FF")) {//Active energy net total (|+A|-|-A|) rate 4
                            billingData.setActEgyNetTotRt4(Double.parseDouble(dataVal));
                        } else if (obisCode.equalsIgnoreCase("01 00 01 06 00 FF")) {//Maximum Demand Register 1 -Active energy import (+A)
                            if (billingData.getMaxDmdReg1ActEgyImp() != null) {
                                try {
                                    Date dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataVal);
                                    billingData.setMaxDmdReg1ActEgyImpTime(new Timestamp(dateTime.getTime()));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                billingData.setMaxDmdReg1ActEgyImp(Double.parseDouble(dataVal));
                            }

                        } else if (obisCode.equalsIgnoreCase("01 00 02 06 00 FF")) {//Maximum Demand Register 6 -Active energy export (-A)
                            if (billingData.getMaxDmdReg6ActEgyExp() != null) {
                                try {
                                    Date dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataVal);
                                    billingData.setMaxDmdReg6ActEgyExpTime(new Timestamp(dateTime.getTime()));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                billingData.setMaxDmdReg6ActEgyExp(Double.parseDouble(dataVal));
                            }
                        } else if (obisCode.equalsIgnoreCase("01 00 09 06 00 FF")) {//Apparent MD (+) 
                            if (billingData.getAppMdImp() != null) {
                                try {
                                    Date dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataVal);
                                    billingData.setAppMdImpOccurringTime(new Timestamp(dateTime.getTime()));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                billingData.setAppMdImp(Double.parseDouble(dataVal));
                            }
                        } else if (obisCode.equalsIgnoreCase("01 00 0A 06 00 FF")) {//Apparent MD (-) 
                            if (billingData.getAppMdExp() != null) {
                                try {
                                    Date dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataVal);
                                    billingData.setAppMdExpOccurringTime(new Timestamp(dateTime.getTime()));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                billingData.setAppMdExp(Double.parseDouble(dataVal));
                            }
                        }
                    }

                    id.setMeterSerialNumber(meterSerialNumber);
                    billingData.setId(id);

                    status = new MeterRuleEngineDAO().saveObject(billingData);

                    logData(billingData, status, watchFolderPath);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public void logData(BillingData billingData, String status, String watchFolderPath) {

        try {

            String todayDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

            File successFile = new File(watchFolderPath, "PROCESSED");
            if (!successFile.exists()) {
                successFile.mkdirs();
            }
            successFile = new File(successFile, todayDate + ".MDV");

            File failedFile = new File(watchFolderPath, "FAILED");
            if (!failedFile.exists()) {
                failedFile.mkdirs();
            }
            failedFile = new File(failedFile, todayDate + ".MDV");

            if (status.equalsIgnoreCase("true")) {
                FileUtils.writeStringToFile(successFile, billingData.toString() + "\n", true);
            } else if (status.equalsIgnoreCase("false")) {
                FileUtils.writeStringToFile(failedFile, billingData.toString() + "\n", true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            var fileName = "C:\\Users\\Veera\\source\\repos\\MeterComm\\MRD\\20417406_BILL_2041740603012023225648963_S.MD";
            String obisData = ReadData(fileName);
            var result = ParserService.GetParameters(obisData, false);
            var obisCodes = ParserService.GetObisCodes(ReadOBISCodesData(fileName));
            var mergeResult = MergeObisCodes(obisCodes, result);
            ConvertToReadable(mergeResult);
            System.out.println(mergeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String ReadData(String fileName) throws IOException {
        File dataFile = new File(fileName);
        String fileData = FileUtils.readFileToString(dataFile);
        String obisData = StringUtils.substringBetween(fileData, "<OBISDATA>\n",
                "\n</OBISDATA>");
        return obisData;
    }

    private static String ReadOBISCodesData(String fileName) throws IOException {
        File dataFile = new File(fileName);
        String fileData = FileUtils.readFileToString(dataFile);
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
                        var dateTime = HexToStringUtils.convertDateTime(meterParameter.getHexValue());
                        meterParameter.setValue(dateTime);
                    }
                    case 6, 5 -> {
                        var value = Utils.ToLongFromHex(meterParameter.getHexValue().split(" "));
                        meterParameter.setValue(String.valueOf(value));
                    }
                    default -> throw new AssertionError();
                }
            }
        }
    }

}
