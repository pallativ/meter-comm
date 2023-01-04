package com.hes.lis.mltd.process;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.HexConvertionClassUtils;
import com.hes.lis.mltd.utils.LogUtils;
import com.hes.lis.mltd.utils.Utils;

/**
 *
 * @author achyuth kanth pamuru.
 *
 */
public class EventsAction {

    private static String className = "EventsAction";

    public static void main(String[] arg) {
        try {
            String obisHeader = "00 01 00 01 00 01 03 80 C4 02 C1 01 00 00 00 01 00 82 01 e8 01 31 02 04 12 00 08 09 06 01 00 01 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 02 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 03 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 04 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 09 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 01 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 02 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 03 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 04 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 02 08 01 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 02 08 02 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 02 08 03 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 02 08 04 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 03 08 01 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 03 08 02 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 03 08 03 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 03 08 04 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 04 08 01 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 04 08 02 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 04 08 03 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 04 08 04 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 09 08 01 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 09 08 02 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 09 08 03 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 09 08 04 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 0A 08 01 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 0A 08 02 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 0A 08 03 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 0A 08 04 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 01 06 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 01 06 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 02 06 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 02 06 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 03 06 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 03 06 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 04 06 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 04 06 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 15 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 16 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 17 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 18 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 29 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 2A 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 2B 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 2C 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 3D 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 3E 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 3F 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 40 08 00 FF 0F 02 12 00 00 FF FF";
            String[] obisArr = obisHeader.split("02 04 12 00");

            for (Integer i = 1; i < obisArr.length; i++) {
                String[] obiCodesArr = obisArr[i].split(" ");
                String obisCode = "";
                for (Integer j = 4; j < 10; j++) {
                    obisCode += obiCodesArr[j] + " ";
                }
                System.out.println(obisCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String fetchScalarData(File scalarFolder, String meterNumber, String eventType, DataInputStream in, DataOutputStream toClient, List<String> obisheaderList, Integer sleepSeconds, LogUtils logger) {
        String response = null;
        String command = null;

        List<String> scalarHeaderFinal = new ArrayList<>();
        List<String> scalarDataFinal = new ArrayList<String>();
        try {
            List<String> scalarHeaderList = new ArrayList<String>();
            File eventScalarFile = new File(scalarFolder, meterNumber + "_" + eventType + ".dat");
            if (!eventScalarFile.exists()) {

                for (String obisHeader : obisheaderList) {
                    try {
                        String[] obisArr = obisHeader.split("02 04 12 00");

                        for (Integer i = 1; i < obisArr.length; i++) {
                            String[] obiCodesArr = obisArr[i].split(" ");
                            String obisCode = "";
                            for (Integer j = 4; j < 10; j++) {
                                obisCode += obiCodesArr[j] + " ";
                            }
                            if (!obisCode.contains("00 00 01")) {//IGNORE DATE OBIS CODE
                                scalarHeaderList.add(obisCode);
                            }
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }

                for (String scalarObisCode : scalarHeaderList) {
                    try {
                        if (scalarObisCode.split("\\ ")[3].equalsIgnoreCase("06")) {//06 Demand
                            command = "00 01 00 01 00 01 00 0D C0 01 C0 00 04 " + scalarObisCode + "03 00";
                        } else {
                            command = "00 01 00 01 00 01 00 0D C0 01 C0 00 03 " + scalarObisCode + "03 00";
                        }

                        List<String> responseList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, command, meterNumber, logger);
                        if (responseList != null) {
                            response = responseList.get(0);

                            String[] responseArr = response.split("\\ ");
                            if (responseArr[11].equalsIgnoreCase("00")) {
                                if (!scalarHeaderFinal.contains(scalarObisCode)) {
                                    String scalarData = "";
                                    for (Integer j = 12; j < 18; j++) {
                                        scalarData += responseArr[j] + " ";
                                    }
                                    scalarHeaderFinal.add(scalarObisCode);
                                    scalarDataFinal.add(scalarData);
                                }

                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (scalarHeaderFinal.size() == scalarDataFinal.size()) {
                    String scalarHeader = "";
                    scalarHeader += "00 01 00 01 00 01 00 78 C4 01 C1 00 00 00 00 01 00 82 01";
                    scalarHeader += StringUtils.leftPad(String.format("%x", scalarHeaderFinal.size()), 2, "0");

                    for (String obisCode : scalarHeaderFinal) {
                        scalarHeader += " 02 04 12 00 03 09 06 " + obisCode + "0F 02 12 00 00";
                    }
                    scalarHeader += " FF FF";

                    String scalarData = "";
                    scalarData += "00 01 00 01 00 01 00 30 C4 01 C1 00 00 00 00 01 00 82 01 ";
                    scalarData += StringUtils.leftPad(String.format("%x", scalarHeaderFinal.size()), 2, "0");
                    for (String obisData : scalarDataFinal) {
                        scalarData += obisData;
                    }
                    scalarData += "FF FF";

                    StringBuilder headerStrb = new StringBuilder();
                    headerStrb.append("00 01 00 01 00 01 ");
                    headerStrb.append(StringUtils.leftPad(String.format("%x", (scalarHeader.length() / 3) - 7), 4, "0"));
                    headerStrb.append(" " + scalarHeader.substring(24));

                    System.out.println(" scalarHeader :: " + headerStrb.toString());

                    StringBuilder dataStrb = new StringBuilder();
                    dataStrb.append("00 01 00 01 00 01 ");
                    dataStrb.append(StringUtils.leftPad(String.format("%x", (scalarData.length() / 3) - 7), 4, "0"));
                    dataStrb.append(" " + scalarHeader.substring(24));

                    System.out.println(" scalarData :: " + scalarData);

                    response = frameDataStringInTags("scalarheader", headerStrb.toString());
                    response += frameDataStringInTags("scalardata", dataStrb.toString());

                    FileUtils.writeStringToFile(eventScalarFile, response);

                }
            } else {
                response = FileUtils.readFileToString(eventScalarFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static CommunicationUtils tcpCommObj = new CommunicationUtils();

    public boolean fetchRelayConnectEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchRelayConnectEvents started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 92 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 92 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchRelayConnectEvents", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_RELAY_CONNECT_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/RELAY_CONNECT/");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchRelayConnectEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchRelayConnectEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchRelayConnectEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchRelayDisConnectEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchRelayDisConnectEvents started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 91 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 91 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchRelayDisConnectEvents", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_RELAY_DISCONNECT_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/RELAY_DISCONNECT/");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchRelayDisConnectEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchRelayDisConnectEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchRelayDisConnectEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchByPassEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchByPassEvents started reading.");

            String scalarHeader = "";
            String scalarData = "";

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 8D FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C6 00 07 00 00 63 62 8D FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_BYPASS_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchByPassEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchByPassEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchByPassEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchCommunicationLogEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchCommunicationLogEvents started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 05 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 05 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchCommunicationLogEvents", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_COMMUNICATIONLOG_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchCommunicationLogEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchCommunicationLogEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchCommunicationLogEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchDisconnectorControlEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchDisconnectorControlEvents started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 02 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 02 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchDisconnectorControlEvents", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_DISCONNECTORCONTROL_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchDisconnectorControlEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchDisconnectorControlEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchDisconnectorControlEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchFraudDetectionEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchFraudDetectionEvents started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 01 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 01 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchFraudDetectionEvents", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_FRAUDDETECTION_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchFraudDetectionEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchFraudDetectionEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchFraudDetectionEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchLongPowerFailureEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchLongPowerFailureEvents started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 98 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 98 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchLongPowerFailureEvents", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_LONGPOWERFAILURE_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/SHORT_POWER_FAILURE/");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchLongPowerFailureEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchLongPowerFailureEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchLongPowerFailureEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchNeutralLineEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchNeutralLineEvents started reading.");

            String scalarHeader = "";
            String scalarData = "";

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 C4 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C6 00 07 00 00 63 62 C4 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_NEUTRALLINE_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchNeutralLineEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchNeutralLineEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchNeutralLineEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchOverVoltage_L1_Events(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchOverVoltage_L1_Events started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 84 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 84 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchOverVoltage_L1_Events", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_OVERVOLTAGE_L1_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/OVER_VOLTAGE_L1/");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchOverVoltage_L1_Events File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchOverVoltage_L1_Events Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchOverVoltage_L1_Events :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchOverVoltage_L2_Events(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchOverVoltage_L2_Events started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 85 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 85 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchOverVoltage_L2_Events", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_OVERVOLTAGE_L2_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/OVER_VOLTAGE_L2/");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchOverVoltage_L2_Events File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchOverVoltage_L2_Events Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchOverVoltage_L2_Events :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchOverVoltage_L3_Events(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchOverVoltage_L3_Events started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 86 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 86 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchOverVoltage_L3_Events", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_OVERVOLTAGE_L3_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/OVER_VOLTAGE_L3");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchOverVoltage_L3_Events File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchOverVoltage_L3_Events Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchOverVoltage_L3_Events :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchPhaseVoltageInNeutral_L1_Events(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchPhaseVoltageInNeutral_L1_Events started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 C5 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C6 00 07 00 00 63 62 C5 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchPhaseVoltageInNeutral_L1_Events", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_PHASEVOLTAGE_IN_NEUTRAL__L1_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/PHASE_VOLTAGE_L1");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchPhaseVoltageInNeutral_L1_Events File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchPhaseVoltageInNeutral_L1_Events Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchPhaseVoltageInNeutral_L1_Events :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchPhaseVoltageInNeutral_L2_Events(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchPhaseVoltageInNeutral_L2_Events started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 C6 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C6 00 07 00 00 63 62 C6 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchPhaseVoltageInNeutral_L2_Events", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_PHASEVOLTAGE_IN_NEUTRAL__L2_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/PHASE_VOLTAGE_L2");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchPhaseVoltageInNeutral_L2_Events File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchPhaseVoltageInNeutral_L2_Events Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchPhaseVoltageInNeutral_L2_Events :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchPhaseVoltageInNeutral_L3_Events(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchPhaseVoltageInNeutral_L3_Events started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 C7 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C6 00 07 00 00 63 62 C7 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchPhaseVoltageInNeutral_L3_Events", in, toClient, obisheaderList, sleepSeconds, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_PHASEVOLTAGE_IN_NEUTRAL__L3_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/PHASE_VOLTAGE_L3");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchPhaseVoltageInNeutral_L3_Events File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchPhaseVoltageInNeutral_L3_Events Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchPhaseVoltageInNeutral_L3_Events :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchPowerQualityEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchPowerQualityEvents started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 04 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 04 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchPowerQualityEvents", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_POWER_QUALITY_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchPowerQualityEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchPowerQualityEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchPowerQualityEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchShortPowerFailureEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;
        try {
            LogUtils.appendLogger(logger, "fetchShortPowerFailureEvents started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 97 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 97 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchShortPowerFailureEvents", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_SHORT_POWER_FAILURE_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/LONG_POWER_FAILURE/");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchShortPowerFailureEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchShortPowerFailureEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchShortPowerFailureEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchStandardEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger,
             String requestType, String requestId, File eventsDir) {
        String methodName = "fetchStandardEvents";
        boolean isDataCompleted = false;
        String scalarHeader = "";
        String scalarData = "";
        try {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Started Reading : ");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 00 FF 03 00";
            LogUtils.appendLoggerDetails(logger, className, methodName, "OBIS HEADER COMMAND : " + obisheader);

            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 00 FF 02 00";
            LogUtils.appendLoggerDetails(logger, className, methodName, "OBIS DATA COMMAND : " + obisDataCommand);
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            
            String scalarDataStr = fetchScalarData(null, meterNumber, "fetchPowerQualityEvents", in, toClient, obisheaderList, sleepSeconds, logger);

            LogUtils.appendLoggerDetails(logger, className, methodName, "requestType : " + requestType);

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_P";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_S";
            }

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<METERDATA>\n");
            fileStrb.append("<OBISCODES>\n");
            fileStrb.append(Utils.frameDataInRAWFile(obisheaderList) + "\n");
            fileStrb.append("</OBISCODES>\n");
            fileStrb.append("<OBISDATA>\n");
            fileStrb.append(Utils.frameDataInRAWFile(obisDataList) + "\n");
            fileStrb.append("</OBISDATA>\n");
            fileStrb.append("<SCALAROBISCODES>\n");
            fileStrb.append(scalarHeader + "\n");
            fileStrb.append("</SCALAROBISCODES>\n");
            fileStrb.append("<SCALAROBISDATA>\n");
            fileStrb.append(scalarData + "\n");
            fileStrb.append("</SCALAROBISDATA>\n");
            fileStrb.append("</METERDATA>");

            String fileName = meterNumber + "_STANDARDEVENTS_" + requestId + ".MD";

            File inputFile = new File(eventsDir, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".MDE");
                eofFile.createNewFile();
                LogUtils.appendLoggerDetails(logger, className, methodName, "_STANDARDEVENTS_ FILE IS : " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchStandardEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchStandardEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchUnderVoltage_L1_Events(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchUnderVoltage_L1_Events started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 87 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 87 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchUnderVoltage_L1_Events", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_UNDER_VOLTAGE_L1_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/UNDER_VOLTAGE_L1");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchUnderVoltage_L1_Events File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchUnderVoltage_L1_Events Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchUnderVoltage_L1_Events :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchUnderVoltage_L2_Events(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchUnderVoltage_L2_Events started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 88 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 88 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchUnderVoltage_L2_Events", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_UNDER_VOLTAGE_L2_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/UNDER_VOLTAGE_L2");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchUnderVoltage_L2_Events File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchUnderVoltage_L2_Events Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchUnderVoltage_L2_Events :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchUnderVoltage_L3_Events(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchUnderVoltage_L3_Events started reading.");

            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 89 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 89 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            String scalarDataStr = fetchScalarData(scalarFolder, meterNumber, "fetchUnderVoltage_L3_Events", in, toClient, obisheaderList, sleepSeconds, logger);
            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(scalarDataStr);
            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_UNDER_VOLTAGE_L3_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/events/UNDER_VOLTAGE_L3");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchUnderVoltage_L3_Events File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchUnderVoltage_L3_Events Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchUnderVoltage_L3_Events :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchEnergyReversal_L1_Events(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchEnergyReversal_L1_Events started reading.");

            String scalarHeader = "";
            String scalarData = "";
            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 BF FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 BF FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_ENERGY_REVERSAL_L1_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchEnergyReversal_L1_Events File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchEnergyReversal_L1_Events Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchEnergyReversal_L1_Events :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchEnergyReversal_L2_Events(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchEnergyReversal_L2_Events started reading.");

            String scalarHeader = "";
            String scalarData = "";
            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 C0 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 C0 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_ENERGY_REVERSAL_L2_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchEnergyReversal_L2_Events File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchEnergyReversal_L2_Events Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchEnergyReversal_L2_Events :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchEnergyReversal_L3_Events(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchEnergyReversal_L3_Events started reading.");

            String scalarHeader = "";
            String scalarData = "";
            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 C1 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 C1 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_ENERGY_REVERSAL_L3_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchEnergyReversal_L3_Events File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchEnergyReversal_L3_Events Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchEnergyReversal_L3_Events :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchAccountChangeEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchAccountChangeEvents started reading.");

            String scalarHeader = "";
            String scalarData = "";
            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 BD FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C4 00 07 00 00 63 62 BD FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_ACCOUNT_CHANGE_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchAccountChangeEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchAccountChangeEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchAccountChangeEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchChangeMeterModeEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchChangeMeterModeEvents started reading.");

            String scalarHeader = "";
            String scalarData = "";
            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 A7 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C4 00 07 00 00 63 62 A7 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_CHANGE_METER_MODE_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchChangeMeterModeEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchChangeMeterModeEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchChangeMeterModeEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchLocalDisconnectEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchLocalDisconnectEvents started reading.");

            String scalarHeader = "";
            String scalarData = "";
            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 94 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 CB 00 07 00 00 63 62 94 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_LOCAL_DISCONNECT_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchLocalDisconnectEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchLocalDisconnectEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchLocalDisconnectEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchLongPowerfailureEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchLongPowerfailureEvents started reading.");

            String scalarHeader = "";
            String scalarData = "";
            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 98 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 98 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_LONG_POWER_FAILURE_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchLongPowerfailureEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchLongPowerfailureEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchLongPowerfailureEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchMainCoverRemovedEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchMainCoverRemovedEvents started reading.");

            String scalarHeader = "";
            String scalarData = "";
            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 82 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C6 00 07 00 00 63 62 82 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_MAIN_COVER_REMOVED_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchMainCoverRemovedEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchMainCoverRemovedEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchMainCoverRemovedEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchManagementTokenEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchManagementTokenEvents started reading.");

            String scalarHeader = "";
            String scalarData = "";
            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 95 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C3 00 07 00 00 63 62 95 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_MANAGEMENT_TOKEN_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchManagementTokenEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchManagementTokenEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchManagementTokenEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchNeutralLineInterferenceEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchNeutralLineInterferenceEvents started reading.");

            String scalarHeader = "";
            String scalarData = "";
            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 C4 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C6 00 07 00 00 63 62 C4 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_NEUTRAL_LINE_INTERFERENCE_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchNeutralLineInterferenceEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchNeutralLineInterferenceEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchNeutralLineInterferenceEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchStrongDCFieldEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchStrongDCFieldEvents started reading.");

            String scalarHeader = "";
            String scalarData = "";
            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 8F FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C6 00 07 00 00 63 62 8F FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_STRONG_DC_FIELD_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchStrongDCFieldEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchStrongDCFieldEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchStrongDCFieldEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public boolean fetchTerminalCoverEvents(DataInputStream in, DataOutputStream toClient, Integer sleepSeconds, String meterNumber, LogUtils logger, String requestType, String requestId, File scalarFolder) {
        boolean isDataCompleted = false;

        try {
            LogUtils.appendLogger(logger, "fetchTerminalCoverEvents started reading.");

            String scalarHeader = "";
            String scalarData = "";
            String obisheader = "00 01 00 01 00 01 00 0D C0 01 C1 00 07 00 00 63 62 83 FF 03 00";
            List<String> obisheaderList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisheader, meterNumber, logger);
            String obisDataCommand = "00 01 00 01 00 01 00 0D C0 01 C6 00 07 00 00 63 62 83 FF 02 00";
            List<String> obisDataList = tcpCommObj.receiveCompleteData(in, toClient, sleepSeconds, obisDataCommand, meterNumber, logger);

            StringBuilder fileStrb = new StringBuilder();
            fileStrb.append("<data>");
            fileStrb.append(frameDataInTags("obisheader", obisheaderList));
            fileStrb.append(frameDataInTags("obisdata", obisDataList));
            fileStrb.append(frameDataStringInTags("scalarheader", scalarHeader));
            fileStrb.append(frameDataStringInTags("scalardata", scalarData));

            fileStrb.append("</data>");

            if (requestType.equalsIgnoreCase("ON DEMAND")) {
                requestId = requestId + "_ON";
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssSS");
                requestId = meterNumber + format.format(date) + "_SC";
            }

            String fileName = "EVENT_TERMINAL_COVER_" + meterNumber + "_" + requestId + ".xml";

            File watchFolder = new File("/data/hexing/raw/watch/tampers_watch");
            if (!watchFolder.exists()) {
                watchFolder.mkdirs();
            }

            File inputFile = new File(watchFolder, fileName);
            FileUtils.writeStringToFile(inputFile, fileStrb.toString(), true);

            isDataCompleted = true;
            if (isDataCompleted) {
                File eofFile = new File(inputFile.getAbsolutePath() + ".EOF");
                eofFile.createNewFile();
                LogUtils.appendLogger(logger, "fetchTerminalCoverEvents File :: " + inputFile.getAbsolutePath() + " ");
            }

        } catch (Exception e) {
            LogUtils.appendLogger(logger, "fetchTerminalCoverEvents Exception :: " + e.getMessage() + " ");
            e.printStackTrace();
        } finally {
            LogUtils.appendLogger(logger, "fetchTerminalCoverEvents :: " + isDataCompleted + " ");
        }

        return isDataCompleted;
    }

    public static String frameDataInTags(String tagName, List<String> dataList) {
        StringBuilder strb = null;
        try {
            if (dataList.size() > 0) {
                strb = new StringBuilder();
                strb.append("<" + tagName + ">");
                for (int i = 0; i < dataList.size(); i++) {
                    String strElement = dataList.get(i);
//					strElement=strElement.replace(" ", "").toLowerCase();

                    String dataString = strElement.toLowerCase() + "ff ff ";
                    String[] strArr = dataString.split(" ");
                    Integer sizeOfFrame = strArr.length - 8;
                    String[] sizeArr = HexConvertionClassUtils.fetchSixeInHex(sizeOfFrame);
                    strArr[6] = sizeArr[0];
                    strArr[7] = sizeArr[1];

                    for (String str : strArr) {
                        strb.append(str);
                    }

                    if (i != dataList.size() - 1) {
                        strb.append("\n");
                    }

                    /*if(i!=dataList.size()-1){
						strb.append(strElement);
					}else{
						strb.append(strElement);
					}*/
                }
                strb.append("</" + tagName + ">");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strb.toString();
    }

    public static String frameDataStringInTags(String tagName, String dataStr) {
        StringBuilder strb = new StringBuilder();
        try {
            if (dataStr != null && dataStr.length() > 0) {
                strb = new StringBuilder();
                strb.append("<" + tagName + ">");
                strb.append(dataStr.toLowerCase().replace(" ", ""));
                strb.append("</" + tagName + ">");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strb.toString();
    }

}
