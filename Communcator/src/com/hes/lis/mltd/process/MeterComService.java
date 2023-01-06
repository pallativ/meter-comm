package com.hes.lis.mltd.process;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.hes.lis.mltd.dao.MeterRuleEngineDAO;
import com.hes.lis.mltd.process.get.MeterGettings;
import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.DateUtils;
import com.hes.lis.mltd.utils.HexToStringUtils;
import com.hes.lis.mltd.utils.LogUtils;
import com.hes.lis.mltd.utils.Utils;
import com.hes.lis.mltd.vo.Masterdata;
import com.hes.lis.mltd.vo.MeterReplica;

/**
 *
 * @author psvr
 *
 */
public class MeterComService {

    private static String className = "OnDemandProcessAction";

    private static Integer instantInterval = 15;

    private static String workingDir;
    private static File instantDir, billingDir, loadDir, onDemandDir;

    static {

        try {
            workingDir = System.getProperty("user.dir");

            instantDir = new File(workingDir, "INSTANT");
            if (!instantDir.exists()) {
                instantDir.mkdirs();
            }

            instantDir = new File(instantDir, "TO_BE_PROCESSED");
            if (!instantDir.exists()) {
                instantDir.mkdirs();
            }

            billingDir = new File(workingDir, "BILLING");
            if (!billingDir.exists()) {
                billingDir.mkdirs();
            }

            billingDir = new File(billingDir, "TO_BE_PROCESSED");
            if (!billingDir.exists()) {
                billingDir.mkdirs();
            }

            loadDir = new File(workingDir, "LOAD PROFILE");
            if (!loadDir.exists()) {
                loadDir.mkdirs();
            }

            loadDir = new File(loadDir, "TO_BE_PROCESSED");
            if (!loadDir.exists()) {
                loadDir.mkdirs();
            }

            onDemandDir = new File(workingDir, "ON_DEMAND");
            if (!onDemandDir.exists()) {
                onDemandDir.mkdirs();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void process(Socket socketObj, Properties prop) {
        String methodName = "processCommunication";

        DataInputStream in = null;
        DataOutputStream toClient = null;
//		CommunicationUtils tcpComm=new CommunicationUtils();
        MeterAuthentications meterAuths = new MeterAuthentications();
        MeterDataReadAction readMeterObj = new MeterDataReadAction();
        MeterGettings meterGetObj = new MeterGettings();

        Utils utilsObj = new Utils();
        Integer sleepSeconds = 20;
        String meterNumber = null;
        LogUtils logger = null;
        MeterReplica meterReplica = null;
        MeterInitialCheckings initialCheckings = new MeterInitialCheckings();
        Date commStartDateTime = null;
        MeterRuleEngineDAO daoOjb = new MeterRuleEngineDAO();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            commStartDateTime = new Date();

            System.out.println(new Date() + "--->" + "(TCP COM STATUS : METER - HES SERVER COMMUNICATION ESTABLISHED)");

            socketObj.setSoTimeout(20000);
            toClient = new DataOutputStream(socketObj.getOutputStream());
            in = new DataInputStream(socketObj.getInputStream());

            System.out.println(new CommunicationUtils().readAllBytes(in, null, sleepSeconds));
//			processTampers(in, sleepSeconds, meterNumber, logger, tcpComm);

            boolean isLowlevelAuthenticated = meterAuths.lowLevelPasswordAuthentication(in, toClient, sleepSeconds, null, null);

            if (isLowlevelAuthenticated) {
                if (meterNumber == null) {
                    meterNumber = meterGetObj.readMeterNumber(in, toClient, sleepSeconds, logger);
                }

                logger = createFolders(workingDir, meterNumber, logger);//CREATE METER SPECIFIC FOLDERS

                if (meterNumber != null) {
                    System.out.println("(METER NUMBER) : " + meterNumber);

                    Masterdata meterMasterObj = daoOjb.fetchMeterMasterData(meterNumber);

                    if (meterMasterObj != null) {
                        Utils.updateLoggerDB(meterNumber, "START", "STARTED DATA COMM");
                        LogUtils.appendLoggerDetails(logger, className, methodName, "\n\n\nMeter is already configured in Masterdata and started communication");

//						new MeterInitialCheckings().initialCheckings(meterMasterObj, commStartDateTime, logger, in, toClient, sleepSeconds);
                        meterReplica = initialCheckings.checkConfigParams(meterNumber, in, toClient, sleepSeconds, meterNumber, logger);

                        Date toDate = new Date();
                        toDate = utilsObj.convertEnglishToNepali(toDate);
//						if(meterReplica.getLastMeterDateTime()!=null){
//							toDate=meterReplica.getLastMeterDateTime();
//						}
                        Date fromDate = new Date(toDate.getTime() - 20 * 60 * 1000);

                        LogUtils.appendLoggerDetails(logger, className, methodName, "(commStartDateTime) : " + commStartDateTime);
                        LogUtils.appendLoggerDetails(logger, className, methodName, "(meterReplica.getLastInstantDatetime()) : " + meterReplica.getLastInstantDatetime());
                        LogUtils.appendLoggerDetails(logger, className, methodName, "(instantInterval) : " + instantInterval);
                        LogUtils.appendLoggerDetails(logger, className, methodName, "(DIFF IN MIN) : " + DateUtils.diffInMinutes(meterReplica.getLastInstantDatetime(), commStartDateTime));

                        if ((meterReplica.getLastInstantDatetime() == null) || (DateUtils.diffInMinutes(meterReplica.getLastInstantDatetime(), commStartDateTime) > instantInterval)) {
                            boolean isinstantProcessed = readMeterObj.instantDataReading(in, toClient, sleepSeconds,
                                    meterNumber, logger, fromDate, toDate, "SCHEDULED", null, instantDir);
                            if (isinstantProcessed) {
                                meterReplica.setLastInstantDatetime(new Timestamp(new Date().getTime()));
                            }
                            LogUtils.appendLoggerDetails(logger, className, methodName, "(INSTANT DATA READING STATUS) : " + isinstantProcessed);
                        }

                        if ((meterReplica.getLastBillingDatetime() == null) || (DateUtils.diffInMinutes(meterReplica.getLastBillingDatetime(), commStartDateTime) > 1440)) {
                            boolean isBillingProcessed = readMeterObj.fetchBillingData(in, toClient, sleepSeconds, meterNumber, logger, "SCHEDULED", null, billingDir);
                            if (isBillingProcessed) {
                                meterReplica.setLastBillingDatetime(new Timestamp(new Date().getTime()));
                            }
                            LogUtils.appendLoggerDetails(logger, className, methodName, "(BILLING DATA READING STATUS) : " + isBillingProcessed);
                        }

                        if ((meterReplica.getLastLoadsurveyDatetime() == null) || (DateUtils.diffInMinutes(meterReplica.getLastLoadsurveyDatetime(), commStartDateTime) > 1440)) {
                            boolean isLoadDataProcessed = false;
                            Integer daysDiff = DateUtils.diffInDays(meterReplica.getLastLoadsurveyDatetime(), commStartDateTime);

                            if ((meterReplica.getLastLoadsurveyDatetime() == null) || (daysDiff >= 45)) {
                                isLoadDataProcessed = readMeterObj.readFullLoadData1(in, toClient, sleepSeconds, meterNumber, logger, "SCHEDULED", null, loadDir);
                                if (isLoadDataProcessed) {
                                    meterReplica.setLastLoadsurveyDatetime(new Timestamp(new Date().getTime()));
                                }
                            } else if (daysDiff < 45) {
                                Date toLoadDate = meterReplica.getLastLoadsurveyDatetime();
                                Date fromLoadDate = DateUtils.fetchDateBeforeDays(toLoadDate, daysDiff);

                                fromLoadDate = dateFormat.parse((dateTimeFormat.format(fromLoadDate)));
                                toLoadDate = dateFormat.parse((dateTimeFormat.format(toLoadDate)));

                                isLoadDataProcessed = readMeterObj.fetchLoadDataFromMeterFromTo1(in, toClient, sleepSeconds, meterNumber, fromLoadDate, toLoadDate, logger, "SCHEDULED", null, loadDir);
                                if (isLoadDataProcessed) {
                                    meterReplica.setLastLoadsurveyDatetime(new Timestamp(toLoadDate.getTime()));
                                }
                            }
                            LogUtils.appendLoggerDetails(logger, className, methodName, "(LOAD SURVEY DATA READING STATUS) : " + isLoadDataProcessed);
                        }

                        if ((meterReplica.getLastMidnightSnapDatetime() == null) || (DateUtils.diffInMinutes(meterReplica.getLastMidnightSnapDatetime(), commStartDateTime) > 1440)) {
                            boolean isLoadDataProcessed = false;
                            Integer daysDiff = DateUtils.diffInDays(meterReplica.getLastMidnightSnapDatetime(), commStartDateTime);

                            if ((meterReplica.getLastMidnightSnapDatetime() == null) || (daysDiff >= 45)) {
                                isLoadDataProcessed = readMeterObj.readFullLoadData9(in, toClient, sleepSeconds, meterNumber, logger, "SCHEDULED", null, loadDir);
                                if (isLoadDataProcessed) {
                                    meterReplica.setLastMidnightSnapDatetime(new Timestamp(new Date().getTime()));
                                }
                            } else if (daysDiff < 45) {
                                Date toLoadDate = meterReplica.getLastLoadsurveyDatetime();
                                Date fromLoadDate = DateUtils.fetchDateBeforeDays(toLoadDate, daysDiff);

                                fromLoadDate = dateFormat.parse((dateTimeFormat.format(fromLoadDate)));
                                toLoadDate = dateFormat.parse((dateTimeFormat.format(toLoadDate)));

                                isLoadDataProcessed = readMeterObj.fetchLoadDataFromMeterFromTo9(in, toClient, sleepSeconds, meterNumber, fromLoadDate, toLoadDate, logger, "SCHEDULED", null, loadDir);
                                if (isLoadDataProcessed) {
                                    meterReplica.setLastMidnightSnapDatetime(new Timestamp(toLoadDate.getTime()));
                                }
                            }
                            LogUtils.appendLoggerDetails(logger, className, methodName, "(LOAD SURVEY DATA READING STATUS) : " + isLoadDataProcessed);
                        }

                        // PROCESS ON DEMAND
                        meterReplica = new OnDemandProcessAction().processOnDemands(in, toClient, sleepSeconds, meterNumber, logger, meterReplica, meterMasterObj);

                    } else {
                        LogUtils.appendLoggerDetails(logger, className, methodName, "Meter is not configured in Masterdata");
                        Utils.updateLoggerDB(meterNumber, "", "METER IS NOT CONFIGURED");
                    }

                } else {
                    System.out.println("(METER NUMBER READING FAILED)");
                }

            } else {
                System.out.println("(INITIAL PASSWORD AUTHENTICATION FAILED)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (meterNumber != null) {
                Utils.updateLoggerDB(meterNumber, "END", "METER ENDS COMMUNICATION");
            }

            if (meterReplica != null) {
                meterReplica.setLastCommDatetime(new Timestamp(commStartDateTime.getTime()));
                new MeterRuleEngineDAO().saveOrUpdateObject(meterReplica);
            }

            try {
                meterGetObj.sendRLRQ(in, toClient, sleepSeconds, meterNumber, logger);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void processTampers(DataInputStream in, Integer sleepSeconds, String meterNumber, LogUtils logger, CommunicationUtils tcpComm) {
        String methodName = "processTampers";
        try {
            while (true) {
                try {
                    String tampersData = tcpComm.receiveBytesIgnoreTamperCheck(in, sleepSeconds, meterNumber, logger);
                    if (tampersData != null && tampersData.length() > 0) {
                        LogUtils.appendLoggerDetails(null, className, methodName, "On Push data ===>>> " + tampersData);
                        String[] responseArr = tampersData.split("\\ ");
                        //00 01 00 01 00 01 00 03 D8 01
                        if (tampersData.contains("00 01 00 01 00 00 00 03 01 11")) {
                            String signalStrength = Integer.parseInt(responseArr[responseArr.length - 1], 16) + "";
                        } else {
                            String obisCode = responseArr[26] + responseArr[27] + responseArr[28] + responseArr[29] + responseArr[30] + responseArr[31];
                            if (obisCode.equalsIgnoreCase("0000600100FF")) {
                                try {
                                    Integer dataSize = Integer.parseInt(responseArr[33], 16);
                                    String mtrNum = "";
                                    for (Integer i = 34; i < 34 + dataSize; i++) {
                                        mtrNum += responseArr[i];
                                    }
                                    meterNumber = HexToStringUtils.unHex(mtrNum);
//									LogUtils.appendLoggerDetails(null, "Meter Number From Push :: "+null);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                tcpComm.processTampersdata(tampersData, meterNumber);
                            }
                        }
                    } else {
                        break;
                    }
                } catch (Exception e) {
//					e.printStackTrace();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private LogUtils createFolders(String workingDir, String meterNumber, LogUtils logger) {
        String methodName = "createFolders";
        try {
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            File workingLogDir = new File(workingDir, "LOG_FILES");
            if (!workingLogDir.exists()) {
                workingLogDir.mkdirs();
            }

            File logDir = new File(workingLogDir, "METER_SPECIFIC_" + dateStr);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            File logFile = new File(logDir, "meter_specific_" + meterNumber + ".log");
            logger = new LogUtils(logFile);
            LogUtils.appendLoggerDetails(logger, className, methodName, meterNumber + " COMMUNICATION INITIATED BY METER");

//			File nonConfigDir=new File(workingDir,"NON_CONFIGURED_METERS");
//			if(!nonConfigDir.exists())
//				nonConfigDir.mkdirs();
//			File meterFile=new File(nonConfigDir,meterNumber+".log");
//			if(meterFile.exists()){
//				meterFile.delete();
//			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logger;
    }

}
