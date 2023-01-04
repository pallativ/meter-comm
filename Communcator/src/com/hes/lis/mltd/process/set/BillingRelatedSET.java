package com.hes.lis.mltd.process.set;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.apache.commons.lang.StringUtils;

import com.hes.lis.mltd.process.get.BillingRelatedGET;
import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.LogUtils;
import com.hes.lis.mltd.vo.Masterdata;

/**
 *
 * @author achyuth
 *
 */
public class BillingRelatedSET {

    private static String className = "BillingRelatedSET";

    private static CommunicationUtils tcpCommObj = new CommunicationUtils();

    public boolean setApprovedDemand(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            String meterNumber, String approvedDemandStr) {
        String methodName = "setApprovedDemand";
        boolean setStatus = false;

        try {
            Double approvedDemandVal = Double.parseDouble(approvedDemandStr);
            approvedDemandVal = approvedDemandVal * 1000;
            Integer approvedDemand = approvedDemandVal.intValue();
            String command = "00 01 00 01 00 01 00 12 C1 01 C2 00 47 00 00 11 00 00 FF 04 00 06 ";
            command += StringUtils.leftPad(String.format("%x", approvedDemand), 8, "0");
            LogUtils.appendLoggerDetails(logger, className, methodName, "setApprovedDemand command :: " + command + " ");

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {

                    tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
                    String response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                    String[] responseArr = response.split("\\ ");
                    LogUtils.appendLoggerDetails(logger, className, methodName, "setApprovedDemand response :: " + response + " ");

                    if (responseArr[11].equalsIgnoreCase("00")) {
                        setStatus = true;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setApprovedDemand :: " + response + " ");
                        break;
                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setApprovedDemand Failed");
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

    public boolean setMDResetDate(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            String meterNumber, String mdResetDay) {
        String methodName = "setMDResetDate";
        boolean setStatus = false;

        try {
            Integer mdResetDayInt = Integer.parseInt(mdResetDay);
            String command = "00 01 00 01 00 01 00 1E C1 01 C4 00 16 00 00 0F 00 00 FF 04 00 01 01 02 02 09 04 00 00 00 00 09 05 FF FF FF ";
            command += StringUtils.leftPad(String.format("%x", mdResetDayInt), 2, "0") + " FF";
            LogUtils.appendLoggerDetails(logger, className, methodName, "setMDIResetDate Req :: " + command + " ");

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {
                    tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
                    String response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                    String[] responseArr = response.split("\\ ");
                    LogUtils.appendLoggerDetails(logger, className, methodName, "setMDIResetDate Res :: " + response + " ");

                    if (responseArr[11].equalsIgnoreCase("00")) {
                        setStatus = true;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setMDIResetDate :: " + response + " ");
                        break;
                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setMDIResetDate Failed");
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

    /*public boolean setMonthlyBillingDate(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber, Integer mdResetDay) {
		boolean setStatus=false;


		try{
			String command="00 01 00 01 00 01 00 1E C1 01 C1 00 03 00 00 00 01 02 FF 04 00 01 01 02 02 09 04 00 00 00 00 09 05 FF FF FF ";
			command+=StringUtils.leftPad(String.format("%x", mdResetDay), 2,"0")+" FF";
			LogUtils.appendLoggerDetails(logger,className,methodName,"setMDIResetDate command :: "+command+" ");

			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"setMDIResetDate response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"setMDIResetDate ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"setMDIResetDate NCK");
					}


					if (trailCount > 4) {
						break;
					}
				} catch (Exception e) {
					trailCount++;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return setStatus;
	}*/
    public boolean setMDReset(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            String meterNumber) {
        String methodName = "setMDReset";
        boolean setStatus = false;

        try {
            String command = "00 01 00 01 00 01 00 10 C3 01 C5 00 09 00 00 0A 00 01 FF 01 01 12 00 01";
            LogUtils.appendLoggerDetails(logger, className, methodName, "setMDIReset command :: " + command + " ");

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {

                    tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
                    String response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                    String[] responseArr = response.split("\\ ");
                    LogUtils.appendLoggerDetails(logger, className, methodName, "setMDIReset response :: " + response + " ");

                    if (responseArr[11].equalsIgnoreCase("00")) {
                        setStatus = true;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setMDIReset ACK :: " + response + " ");
                        break;
                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setMDIReset NCK");
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

    public boolean setForgivenessTimeForManualBilling(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            String meterNumber, String forgiveTimeSeconds) {
        String methodName = "setForgivenessTimeForManualBilling";
        boolean setStatus = false;

        try {
            Integer forgiveSec = Integer.parseInt(forgiveTimeSeconds);
            String command = "00 01 00 01 00 01 00 10 C1 01 C3 00 03 01 00 85 82 00 FF 02 00 12 ";
            command += StringUtils.leftPad(String.format("%x", forgiveSec), 4, "0");
            LogUtils.appendLoggerDetails(logger, className, methodName, "setForgivenessTimeForManualBilling Req :: " + command + " ");

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {
                    tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
                    String response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                    String[] responseArr = response.split("\\ ");
                    LogUtils.appendLoggerDetails(logger, className, methodName, "setForgivenessTimeForManualBilling Res :: " + response + " ");
                    if (responseArr[11].equalsIgnoreCase("00")) {
                        setStatus = true;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setForgivenessTimeForManualBilling :: " + response + " ");
                        break;
                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setForgivenessTimeForManualBilling Failed");
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

    public boolean setDemandIntegrationPeriod(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            String meterNumber, String period, String noOfPeriods) {
        String methodName = "setDemandIntegrationPeriod";
        boolean setStatus = false;

        try {
            String command1 = "00 01 00 01 00 01 00 12 C1 01 C1 00 05 01 00 01 04 00 FF 08 00 06";
            Integer periodInt = Integer.parseInt(period);
            command1 += StringUtils.leftPad(String.format("%x", periodInt), 8, "0");
            LogUtils.appendLoggerDetails(logger, className, methodName, "setDemandIntegrationPeriod command :: " + command1 + " ");

            String command2 = "00 01 00 01 00 01 00 10 C1 01 C1 00 05 01 00 01 04 00 FF 09 00 12";
            Integer periodsCount = Integer.parseInt(noOfPeriods);
            command2 += StringUtils.leftPad(String.format("%x", periodsCount), 4, "0");
            LogUtils.appendLoggerDetails(logger, className, methodName, "setDemandIntegrationPeriodCount command :: " + command2 + " ");

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {

                    tcpCommObj.sendBytes(toClient, sleepSeconds, command1, logger);
                    String response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                    String[] responseArr = response.split("\\ ");
                    LogUtils.appendLoggerDetails(logger, className, methodName, "setDemandIntegrationPeriod response :: " + response + " ");

                    if (responseArr[11].equalsIgnoreCase("00")) {

                        tcpCommObj.sendBytes(toClient, sleepSeconds, command2, logger);
                        response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                        responseArr = response.split("\\ ");
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setDemandIntegrationPeriodCount response :: " + response + " ");

                        if (responseArr[11].equalsIgnoreCase("00")) {
                            setStatus = true;
                            LogUtils.appendLoggerDetails(logger, className, methodName, "setDemandIntegrationPeriodCount ACK :: " + response + " ");
                            break;
                        } else {
                            trailCount++;
                            LogUtils.appendLoggerDetails(logger, className, methodName, "setDemandIntegrationPeriodCount NCK");
                        }
                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setDemandIntegrationPeriod NCK");
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

    public boolean setLoadProfileCapturePeriod(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            String meterNumber, String period) {
        String methodName = "setLoadProfileCapturePeriod";
        boolean setStatus = false;

        try {
            String command1 = "00 01 00 01 00 01 00 12 C1 01 C1 00 07 01 00 63 01 00 FF 04 00 06";
            Integer periodInt = Integer.parseInt(period);
            command1 += StringUtils.leftPad(String.format("%x", periodInt), 8, "0");
            LogUtils.appendLoggerDetails(logger, className, methodName, "setLoadProfileCapturePeriod1 command :: " + command1 + " ");

            String command2 = "00 01 00 01 00 01 00 12 C1 01 C1 00 07 01 00 63 88 00 FF 04 00 06";
            command2 += StringUtils.leftPad(String.format("%x", periodInt), 8, "0");
            LogUtils.appendLoggerDetails(logger, className, methodName, "setLoadProfileCapturePeriod7 command :: " + command2 + " ");

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {

                    tcpCommObj.sendBytes(toClient, sleepSeconds, command1, logger);
                    String response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                    String[] responseArr = response.split("\\ ");
                    LogUtils.appendLoggerDetails(logger, className, methodName, "setLoadProfileCapturePeriod1 response :: " + response + " ");

                    if (responseArr[11].equalsIgnoreCase("00")) {

                        tcpCommObj.sendBytes(toClient, sleepSeconds, command2, logger);
                        response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                        responseArr = response.split("\\ ");
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setLoadProfileCapturePeriod7 response :: " + response + " ");

                        if (responseArr[11].equalsIgnoreCase("00")) {
                            setStatus = true;
                            LogUtils.appendLoggerDetails(logger, className, methodName, "setLoadProfileCapturePeriod7 ACK :: " + response + " ");
                            break;
                        } else {
                            trailCount++;
                            LogUtils.appendLoggerDetails(logger, className, methodName, "setLoadProfileCapturePeriod7 NCK");
                        }
                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setLoadProfileCapturePeriod1 NCK");
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

    public boolean setTreshHoldData(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            String meterNumber, String treshHoldVal) {
        String methodName = "setTreshHoldData";
        boolean setStatus = false;

        try {
            String scalarCommand = "00 01 00 01 00 01 00 0D C0 01 C1 00 03 01 00 01 23 85 FF 03 00";//SCALAR COMMAND
            LogUtils.appendLoggerDetails(logger, className, methodName, "setTreshHoldData Scalar command :: " + scalarCommand + " ");

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {

                    tcpCommObj.sendBytes(toClient, sleepSeconds, scalarCommand, logger);
                    String response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                    String[] responseArr = response.split("\\ ");
                    LogUtils.appendLoggerDetails(logger, className, methodName, "setTreshHoldData Scalar command response :: " + response + " ");

                    if (responseArr[11].equalsIgnoreCase("00")) {

                        Double scalarValue = 0.0;

                        if (responseArr[15].equalsIgnoreCase("00")) {
                            scalarValue = 1.0;
                        } else if (responseArr[15].equalsIgnoreCase("FF")) {
                            scalarValue = 0.1;
                        } else if (responseArr[15].equalsIgnoreCase("FE")) {
                            scalarValue = 0.01;
                        } else if (responseArr[15].equalsIgnoreCase("FD")) {
                            scalarValue = 0.001;
                        } else if (responseArr[15].equalsIgnoreCase("FC")) {
                            scalarValue = 0.0001;
                        } else if (responseArr[15].equalsIgnoreCase("FB")) {
                            scalarValue = 0.00001;
                        } else if (responseArr[15].equalsIgnoreCase("FA")) {
                            scalarValue = 0.000001;
                        } else if (responseArr[15].equalsIgnoreCase("F9")) {
                            scalarValue = 0.0000001;
                        } else {
                            scalarValue = Math.pow(10, Integer.parseInt(responseArr[0], 16));
                        }

                        Integer treshHoldValInt = (int) (Integer.parseInt(treshHoldVal) / scalarValue);

                        String command = "00 01 00 01 00 01 00 10 C1 01 C1 00 03 01 00 01 23 85 FF 02 00 12 "
                                + StringUtils.leftPad(String.format("%x", treshHoldValInt), 4, "0");

                        tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
                        response = tcpCommObj.receiveBytes(in, sleepSeconds, meterNumber, logger);
                        responseArr = response.split("\\ ");
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setTreshHoldData value command response :: " + response + " ");

                        if (responseArr[11].equalsIgnoreCase("00")) {
                            setStatus = true;
                            LogUtils.appendLoggerDetails(logger, className, methodName, "setTreshHoldData value command ACK :: " + response + " ");
                            break;
                        } else {
                            trailCount++;
                            LogUtils.appendLoggerDetails(logger, className, methodName, "setTreshHoldData value command NCK");
                        }

                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "setTreshHoldData Scalar command response");
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

    public boolean disableOrEnablemanualBilling(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            Masterdata masterDataObj) {
        String methodName = "disableOrEnablemanualBilling";
        boolean setStatus = false;

        try {
//			        Value---->Value:0;
            String command = "00 01 00 01 00 01 00 0F C1 01 C1 00 01 01 00 9D 80 00 FF 02 00 11 00";
            LogUtils.appendLoggerDetails(logger, className, methodName, "command :: " + command + " ");

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {
                    tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
                    String response = tcpCommObj.receiveBytes(in, sleepSeconds, masterDataObj.getMeterNo(), logger);
                    String[] responseArr = response.split("\\ ");
                    LogUtils.appendLoggerDetails(logger, className, methodName, "response :: " + response + " ");

                    if (responseArr[11].equalsIgnoreCase("00")) {
                        setStatus = true;
                        LogUtils.appendLoggerDetails(logger, className, methodName, " Success :: " + response + " ");
                        break;
                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "Failed");
                    }

                    if (trailCount > 4) {
                        break;
                    }
                } catch (Exception e) {
                    trailCount++;
                }
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception : " + e.getMessage());
            e.printStackTrace();
        }

        return setStatus;
    }

    public boolean enableForgivenessTimeForManualBilling(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            Masterdata masterDataObj) {
        String methodName = "enableForgivenessTimeForManualBilling";
        boolean setStatus = false;

        try {
//			        Value---->Value:0;
            String command = "00 01 00 01 00 01 00 0D C0 01 C2 00 03 01 00 85 82 00 FF 03 00";
            LogUtils.appendLoggerDetails(logger, className, methodName, "command :: " + command + " ");

            Integer trailCount = 0;
            for (Integer i = 0; i < 5; i++) {
                try {
                    tcpCommObj.sendBytes(toClient, sleepSeconds, command, logger);
                    String response = tcpCommObj.receiveBytes(in, sleepSeconds, masterDataObj.getMeterNo(), logger);
                    String[] responseArr = response.split("\\ ");
                    LogUtils.appendLoggerDetails(logger, className, methodName, "response :: " + response + " ");

                    if (responseArr[11].equalsIgnoreCase("00")) {
                        setStatus = true;
                        LogUtils.appendLoggerDetails(logger, className, methodName, " Success :: " + response + " ");
                        break;
                    } else {
                        trailCount++;
                        LogUtils.appendLoggerDetails(logger, className, methodName, "Failed");
                    }

                    if (trailCount > 4) {
                        break;
                    }
                } catch (Exception e) {
                    trailCount++;
                }
            }
        } catch (Exception e) {
            LogUtils.appendLoggerDetails(logger, className, methodName, "Exception : " + e.getMessage());
            e.printStackTrace();
        }

        return setStatus;
    }

    public boolean setBillingRelatedParams(DataInputStream in,
            DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
            Masterdata masterDataObj) {
        String methodName = "setBillingRelatedParams";
        boolean status = false;

        try {

            if (masterDataObj.getForgivenessTime() == null) {
                LogUtils.appendLoggerDetails(logger, className, methodName, "masterDataObj.getForgivenessTime : null");
                return status;
            } else if (masterDataObj.getMdresetdate() == null) {
                LogUtils.appendLoggerDetails(logger, className, methodName, "masterDataObj.getMdresetdate : null");
                return status;
            } else {
                if (disableOrEnablemanualBilling(in, toClient, sleepSeconds, logger, masterDataObj)) {
                    if (enableForgivenessTimeForManualBilling(in, toClient, sleepSeconds, logger, masterDataObj)) {
                        if (setForgivenessTimeForManualBilling(in, toClient, sleepSeconds, logger, masterDataObj.getMeterNo(), masterDataObj.getForgivenessTime())) {
                            if (setMDResetDate(in, toClient, sleepSeconds, logger, masterDataObj.getMeterNo(), masterDataObj.getMdresetdate())) {
                                if (setMDReset(in, toClient, sleepSeconds, logger, masterDataObj.getMeterNo())) {
                                    status = true;
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] args) {
        Double approvedDemandVal = 23.5;
        approvedDemandVal = approvedDemandVal * 1000;
        Integer approvedDemand = approvedDemandVal.intValue();
        System.out.println(approvedDemand);
        // TODO Auto-generated method stub
    }

}
