package com.hes.lis.mltd.process.set;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.LogUtils;
import com.hes.lis.mltd.utils.Utils;

public class MeterLimiterControlSET {

    private static String className = "MeterLimiterControlSET";

    private static CommunicationUtils tcpCommObj = new CommunicationUtils();

    public boolean structureLogicalNameAttributeIndex(DataInputStream in,
            DataOutputStream toClient,
            Integer sleepSeconds, String meterNumber, LogUtils logger) throws Exception {
        String methodName = "structureLogicalNameAttributeIndex";
        boolean isRelayDisconnected = false;

        try {
            String command = "00 01 00 01 00 01 00 1C C1 01 C1 00 47 00 00 11 00 00 FF 02 00 02 03 12 "
                    + "00 03 " //STRUCTURE
                    + "09 06 01 00 81 E2 00 FF 0F " //LogicalName
                    + "02";//00.00.96.03.10.255		//Attribute Index

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {
                    tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
                    String responseData = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                    LogUtils.appendLoggerDetails(logger, className, methodName, "structureLogicalNameAttributeIndex  : " + responseData);
                    String[] responseArr = responseData.split("\\ ");
                    if (responseArr[11].equalsIgnoreCase("00")) {
                        isRelayDisconnected = true;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "structureLogicalNameAttributeIndex SUCCESS");
                        Utils.updateLoggerDB(meterNumber, "structureLogicalNameAttributeIndex", "SUCCESS");
                        break;
                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "structureLogicalNameAttributeIndex FAILED");
                        Utils.updateLoggerDB(meterNumber, "structureLogicalNameAttributeIndex", "FAILED");
                    }

                    if (trailCount > 4) {
                        break;
                    }
                } catch (Exception e) {
                    trailCount++;
                }
            }

        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "EXCEPTION CAUGHT IN structureLogicalNameAttributeIndex : " + e.getMessage());
            throw e;
        }
        return isRelayDisconnected;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
