package com.hes.lis.mltd.process.set;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.apache.commons.lang.StringUtils;

import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.HexConvertionClassUtils;
import com.hes.lis.mltd.utils.LogUtils;

public class ConsumerDetailsSET {

    private static String className = "ConsumerDetailsSET";

    private static CommunicationUtils tcpCommObj = new CommunicationUtils();

    public boolean setConsumerID(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            String meterNumber, String consumerId) {
        String methodName = "setConsumerID";
        boolean setStatus = false;

        try {
            String consumerID = consumerId;
            StringBuilder strb = new StringBuilder();
            String command = "C1 01 C1 00 01 00 00 60 01 03 FF 02 00 09";
            String dataHex = HexConvertionClassUtils.toHexString(consumerID);
            command = command + " " + (StringUtils.leftPad(String.format("%x", (dataHex.length() / 3)), 2, "0")) + " " + dataHex;
            strb.append("00 01 00 01 00 01 ");
            strb.append(StringUtils.leftPad(String.format("%x", (command.length() / 3)), 4, "0"));
            strb.append(command);
            command = strb.toString();
            LogUtils.appendLoggerDetails(logger, className, methodName, "Setting Consumer Id Req  :: " + command + " ");

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {
                    tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
                    String response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                    LogUtils.appendLoggerDetails(logger, className, methodName, "Setting Consumer Id Command Res :: " + response + " ");
                    String[] responseArr = response.split("\\ ");

                    if (responseArr[11].equalsIgnoreCase("00")) {
                        setStatus = true;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "Setting Consumer Id Command Response :: " + response + " ");
                        break;
                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "Setting Consumer Id Command Not success.Retrying");
                    }

                    if (trailCount > 4) {
                        break;
                    }
                } catch (Exception e) {
                    trailCount++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return setStatus;
    }

    public boolean setConsumerName(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            String meterNumber, String consumername) {
        String methodName = "setConsumerName";
        boolean setStatus = false;

        try {
            String consumerID = consumername;
            StringBuilder strb = new StringBuilder();
            String command = "C1 01 C1 00 01 00 00 60 01 02 FF 02 00 09";
            String dataHex = HexConvertionClassUtils.toHexString(consumerID);
            command = command + " " + (StringUtils.leftPad(String.format("%x", (dataHex.length() / 3)), 2, "0")) + " " + dataHex;
            strb.append("00 01 00 01 00 01 ");
            strb.append(StringUtils.leftPad(String.format("%x", (command.length() / 3)), 4, "0"));
            strb.append(command);
            command = strb.toString();
            LogUtils.appendLoggerDetails(logger, className, methodName, "setConsumerName Req  :: " + command + " ");

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {
                    tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
                    String response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                    LogUtils.appendLoggerDetails(logger, className, methodName, "setConsumerName Command Res :: " + response + " ");
                    String[] responseArr = response.split("\\ ");

                    if (responseArr[11].equalsIgnoreCase("00")) {
                        setStatus = true;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setConsumerName Command Response :: " + response + " ");
                        break;
                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setConsumerName Command Not success.Retrying");
                    }

                    if (trailCount > 4) {
                        break;
                    }
                } catch (Exception e) {
                    trailCount++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return setStatus;
    }

    public boolean setConsumerNumber(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            String meterNumber, String consumerNumber) {
        String methodName = "setConsumerNumber";
        boolean setStatus = false;

        try {
            String consumerID = consumerNumber;
            StringBuilder strb = new StringBuilder();
            String command = "C1 01 C1 00 01 00 00 60 01 01 FF 02 00 09";
            String dataHex = HexConvertionClassUtils.toHexString(consumerID);
            command = command + " " + (StringUtils.leftPad(String.format("%x", (dataHex.length() / 3)), 2, "0")) + " " + dataHex;
            strb.append("00 01 00 01 00 01 ");
            strb.append(StringUtils.leftPad(String.format("%x", (command.length() / 3)), 4, "0"));
            strb.append(command);
            command = strb.toString();
            LogUtils.appendLoggerDetails(logger, className, methodName, "setConsumerNumber Req  :: " + command + " ");

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {
                    tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
                    String response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                    LogUtils.appendLoggerDetails(logger, className, methodName, "setConsumerNumber Command Res :: " + response + " ");
                    String[] responseArr = response.split("\\ ");

                    if (responseArr[11].equalsIgnoreCase("00")) {
                        setStatus = true;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setConsumerNumber Command Response :: " + response + " ");
                        break;
                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setConsumerNumber Command Not success.Retrying");
                    }

                    if (trailCount > 4) {
                        break;
                    }
                } catch (Exception e) {
                    trailCount++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return setStatus;
    }

    public static void main(String[] args) {

        try {

            StringBuilder strb = new StringBuilder();
            String command = "C1 01 C1 00 01 00 00 60 01 AD FF 02 00 0A";
            String dataHex = HexConvertionClassUtils.toHexString("001.003.0003");
            command = command + " " + (StringUtils.leftPad(String.format("%x", (dataHex.length() / 3)), 2, "0")) + " " + dataHex;
            strb.append("00 01 00 01 00 01 ");
            strb.append(StringUtils.leftPad(String.format("%x", (command.length() / 3)), 4, "0"));
            strb.append(command);
            command = strb.toString();
            System.out.println("setConsumerId command :: " + command + " ");

        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO Auto-generated method stub

    }

}
