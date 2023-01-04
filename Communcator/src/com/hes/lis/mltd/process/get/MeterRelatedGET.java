package com.hes.lis.mltd.process.get;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.FrameUtils;
import com.hes.lis.mltd.utils.LogUtils;

public class MeterRelatedGET {

    private static String className = "MeterRelatedGET";

    private static CommunicationUtils tcpCommObj = new CommunicationUtils();

    public Date fetchRTCFromMeter(DataInputStream in,
            DataOutputStream toClient,
            Integer sleepSeconds, String meterNumber, LogUtils logger) throws Exception {
        String methodName = "fetchRTCFromMeter";
        Date meterDate = null;
        try {
            String command = "00 01 00 01 00 01 00 0D C0 01 C1 00 08 00 00 01 00 00 FF 02 00";//00.00.01.00.00.255
            tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
            String responseData = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
            System.out.println("getchRTCFromMeter :: " + responseData);
            String[] responseArr = responseData.split("\\ ");
            if (responseArr[11].equalsIgnoreCase("00")) {
                //	09 0c 08 1c 05 03 02 0f 1e 38 00 fe a7 00			
                Integer year = Integer.parseInt(responseArr[14] + responseArr[15], 16);
                Integer month = Integer.parseInt(responseArr[16], 16);
                Integer day = Integer.parseInt(responseArr[17], 16);

                Integer hour = Integer.parseInt(responseArr[19], 16);
                Integer minutes = Integer.parseInt(responseArr[20], 16);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");

                meterDate = dateFormat.parse(StringUtils.leftPad(year + "", 4, "0")
                        + StringUtils.leftPad((month) + "", 2, "0")
                        + StringUtils.leftPad((day) + "", 2, "0")
                        + StringUtils.leftPad(hour + "", 2, "0")
                        + StringUtils.leftPad(minutes + "", 2, "0"));
                System.out.println(meterDate);
                LogUtils.appendLoggerDetails(logger, className, methodName, "getchRTCFromMeter :: " + meterDate);
            } else {
                LogUtils.appendLoggerDetails(logger, className, methodName, "getchRTCFromMeter Failed");
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception in Fecthing getchRTCFromMeter" + e.getMessage());
            throw e;
        }
        return meterDate;
    }

    public String getMeterType(DataInputStream in,
            DataOutputStream toClient,
            Integer sleepSeconds, String meterNumber, LogUtils logger
    ) throws Exception {
        String methodName = "getMeterType";
        String response = null;
        try {
            String command = "00 01 00 01 00 01 00 0D C0 01 C1 00 01 00 00 60 01 89 FF 02 00";
            tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
            String responseData = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
            String[] responseArr = responseData.split("\\ ");
            if (responseArr[11].equalsIgnoreCase("00")) {
                response = responseData;
                response = FrameUtils.fetchStringFromResponse(response);
                LogUtils.appendLoggerDetails(logger, className, methodName, "getMeterType :: " + response);
            } else {
                LogUtils.appendLoggerDetails(logger, className, methodName, "getMeterType Failed");
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception in Fecthing getMeterType" + e.getMessage());
            throw e;
        }
        return response;
    }

    public String getYearOfManufacture(DataInputStream in,
            DataOutputStream toClient,
            Integer sleepSeconds, String meterNumber, LogUtils logger) throws Exception {
        String methodName = "getYearOfManufacture";
        String response = null;
        try {

            String command = "00 01 00 01 00 01 00 0D C0 01 C1 00 01 00 00 60 01 04 FF 02 00";
            tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
            String responseData = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
            String[] responseArr = responseData.split("\\ ");
            if (responseArr[11].equalsIgnoreCase("00")) {
                response = responseData;
                LogUtils.appendLoggerDetails(logger, className, methodName, "getYearOfManufacture Success");
            } else {
                LogUtils.appendLoggerDetails(logger, className, methodName, "getYearOfManufacture Failed");
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception in Fecthing getYearOfManufacture" + e.getMessage());
            throw e;
        }
        return response;
    }

    public String getMeterFirmWareVersion(DataInputStream in,
            DataOutputStream toClient,
            Integer sleepSeconds, String meterNumber, LogUtils logger
    ) throws Exception {
        String methodName = "getMeterFirmWareVersion";
        String response = null;

        try {
            String command = "00 01 00 01 00 01 00 0D C0 01 C1 00 01 00 00 60 01 92 FF 02 00";
            tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
            String responseData = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
            String[] responseArr = responseData.split("\\ ");
            if (responseArr[11].equalsIgnoreCase("00")) {
                response = responseData;
                response = FrameUtils.fetchStringFromResponse(response);
                LogUtils.appendLoggerDetails(logger, className, methodName, "getMeterFirmWareVersion ::  " + response);
            } else {
                LogUtils.appendLoggerDetails(logger, className, methodName, "getMeterFirmWareVersion Failed");
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception in  getMeterFirmWareVersion" + e.getMessage());
            throw e;
        }
        return response;
    }

    public String getMeterProgramFirmWareVersion(DataInputStream in,
            DataOutputStream toClient,
            Integer sleepSeconds, String meterNumber, LogUtils logger
    ) throws Exception {
        String methodName = "getMeterProgramFirmWareVersion";
        String response = null;

        try {
            String command = "00 01 00 01 00 01 00 0D C0 01 C1 00 01 01 01 00 02 00 FF 02 00";
            tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
            String responseData = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
            String[] responseArr = responseData.split("\\ ");
            if (responseArr[11].equalsIgnoreCase("00")) {
                response = responseData;
                response = FrameUtils.fetchStringFromResponse(response);
                LogUtils.appendLoggerDetails(logger, className, methodName, "getMeterProgramFirmWareVersion ::  " + response);
            } else {
                LogUtils.appendLoggerDetails(logger, className, methodName, "getMeterProgramFirmWareVersion Failed");
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception in Fecthing getMeterProgramFirmWareVersion" + e.getMessage());
            throw e;
        }
        return response;
    }

    public String getModemFirmWareVersion(DataInputStream in,
            DataOutputStream toClient,
            Integer sleepSeconds, String meterNumber, LogUtils logger
    ) throws Exception {
        String methodName = "getModemFirmWareVersion";
        String response = null;

        try {
            String command = "00 01 00 01 00 01 00 0D C0 01 C1 00 01 00 00 60 01 8A FF 02 00";
            tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
            String responseData = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
            String[] responseArr = responseData.split("\\ ");
            if (responseArr[11].equalsIgnoreCase("00")) {
                response = responseData;
                response = FrameUtils.fetchStringFromResponse(response);
                LogUtils.appendLoggerDetails(logger, className, methodName, "getModemFirmWareVersion ::  " + response);
            } else {
                LogUtils.appendLoggerDetails(logger, className, methodName, "getModemFirmWareVersion Failed");
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception in getModemFirmWareVersion" + e.getMessage());
            throw e;
        }
        return response;
    }

    public String getModemImeiNumber(DataInputStream in,
            DataOutputStream toClient,
            Integer sleepSeconds, String meterNumber, LogUtils logger) throws Exception {
        String methodName = "getModemImeiNumber";
        String response = null;

        try {
            String command = "00 01 00 01 00 01 00 0D C0 01 C1 00 01 00 00 60 01 98 FF 02 00";//00.00.96.01.152.255
            tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
            String responseData = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
            String[] responseArr = responseData.split("\\ ");
            if (responseArr[11].equalsIgnoreCase("00")) {
                response = responseData;
                response = FrameUtils.fetchStringFromResponse(response);
                LogUtils.appendLoggerDetails(logger, className, methodName, "getModemImeiNumber :: " + response);
            } else {
                LogUtils.appendLoggerDetails(logger, className, methodName, "getModemImeiNumber Failed");
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception in Fecthing getModemImeiNumber" + e.getMessage());
            throw e;
        }
        return response;
    }

    public String getSignalStrength(DataInputStream in,
            DataOutputStream toClient,
            Integer sleepSeconds, String meterNumber, LogUtils logger) throws Exception {
        String methodName = "getSignalStrength";
        String response = null;

        try {
            String command = "00 01 00 01 00 01 00 0D C0 01 C1 00 01 00 81 19 00 00 FF 02 00";//00.129.25.00.00.255
            tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
            String responseData = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
            String[] responseArr = responseData.split("\\ ");
            if (responseArr[11].equalsIgnoreCase("00")) {
                response = responseArr[13];
                response = Integer.parseInt(response, 16) + "";
//				response=FrameUtils.fetchStringFromResponse(response);
                LogUtils.appendLoggerDetails(logger, className, methodName, "getSignalStrength :: " + response);
            } else {
                LogUtils.appendLoggerDetails(logger, className, methodName, "getSignalStrength NCK");
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception in Fecthing getSignalStrength" + e.getMessage());
            throw e;
        }
        return response;
    }

    public String fetchRelayStatus(DataInputStream in,
            DataOutputStream toClient,
            Integer sleepSeconds, String meterNumber, LogUtils logger) throws Exception {
        String methodName = "fetchRelayStatus";
        String response = null;

        try {
            String command = "00 01 00 01 00 01 00 0D C0 01 C1 00 46 00 00 60 03 0A FF 02 00";//00.00.96.03.10.255
            tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
            String responseData = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
            LogUtils.appendLoggerDetails(logger, className, methodName, "fetchRelayStatus Res  : " + responseData);
            String[] responseArr = responseData.split("\\ ");
            if (responseArr[11].equalsIgnoreCase("00")) {
                response = responseArr[13];
//				response=fetchStringFromResponse(response);
                LogUtils.appendLoggerDetails(logger, className, methodName, "fetchRelayStatus :: " + response);
            } else {
                LogUtils.appendLoggerDetails(logger, className, methodName, "fetchRelayStatus Failed");
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception in Fecthing fetchRelayStatus" + e.getMessage());
            throw e;
        }
        return response;
    }

    public String getSimSerialNumber(DataInputStream in,
            DataOutputStream toClient,
            Integer sleepSeconds, String meterNumber, LogUtils logger) throws Exception {
        String methodName = "getSimSerialNumber";
        String response = null;

        try {
            String command = "00 01 00 01 00 01 00 0D C0 01 C1 00 01 00 00 60 01 ED FF 02 00";//00.00.96.01.237.255
            tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
            String responseData = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
            String[] responseArr = responseData.split("\\ ");
            if (responseArr[11].equalsIgnoreCase("00")) {
                response = responseData;
                response = FrameUtils.fetchStringFromResponse(response);
                LogUtils.appendLoggerDetails(logger, className, methodName, "getSimSerialNumber :: " + response);
            } else {
                LogUtils.appendLoggerDetails(logger, className, methodName, "getSimSerialNumber Failed");
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception in Fecthing getSimSerialNumber" + e.getMessage());
            throw e;
        }
        return response;
    }

    public String getSimDialNumber(DataInputStream in,
            DataOutputStream toClient,
            Integer sleepSeconds, String meterNumber, LogUtils logger) throws Exception {
        String methodName = "getSimDialNumber";
        String response = null;

        try {
            String command = "00 01 00 01 00 01 00 0D C0 01 C1 00 01 00 00 60 01 F8 FF 02 00";//00.00.96.01.248.255
            tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
            String responseData = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
            String[] responseArr = responseData.split("\\ ");
            if (responseArr[11].equalsIgnoreCase("00")) {
                response = responseData;
                response = FrameUtils.fetchStringFromResponse(response);
                LogUtils.appendLoggerDetails(logger, className, methodName, "getSimDialNumber :: " + response);
            } else {
                LogUtils.appendLoggerDetails(logger, className, methodName, "getSimDialNumber Failed");
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception in Fecthing getSimDialNumber" + e.getMessage());
            throw e;
        }
        return response;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
