package com.hes.lis.mltd.process;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hes.lis.mltd.dao.MeterRuleEngineDAO;
import com.hes.lis.mltd.process.get.BillingRelatedGET;
import com.hes.lis.mltd.process.get.ConsumerDetailsGET;
import com.hes.lis.mltd.process.get.MeterGettings;
import com.hes.lis.mltd.process.set.BillingRelatedSET;
import com.hes.lis.mltd.process.set.ConsumerDetailsSET;
import com.hes.lis.mltd.process.set.MeterSettings;
import com.hes.lis.mltd.utils.LogUtils;
import com.hes.lis.mltd.utils.Utils;
import com.hes.lis.mltd.vo.HesDemandRequestDetails;
import com.hes.lis.mltd.vo.Masterdata;
import com.hes.lis.mltd.vo.MeterReplica;

public class OnDemandProcessAction {
	private static String className="OnDemandProcessAction";
	
	
	public MeterReplica processOnDemands(DataInputStream in,DataOutputStream toClient,Integer sleepSeconds,String meterNumber,LogUtils logger,MeterReplica meterReplica,Masterdata masterdataObj) {
		String methodName="processOnDemands";
		MeterGettings meterGetObj=new MeterGettings();
		MeterSettings meterSetObj=new MeterSettings();

		ConsumerDetailsGET consumerGet=new ConsumerDetailsGET();
		ConsumerDetailsSET consumerSet=new ConsumerDetailsSET();
		
		BillingRelatedGET billingGetobj=new BillingRelatedGET();
		BillingRelatedSET billingSetobj=new BillingRelatedSET();

		MeterRuleEngineDAO ruleEngObj=new MeterRuleEngineDAO();
		try {
			LogUtils.appendLoggerDetails(logger,className,methodName, "Checking for On Demand requests..");
			List<HesDemandRequestDetails> onDemandList=new MeterRuleEngineDAO().fetchHesDemandRequestDetails(meterNumber) ;
			
			if(onDemandList!=null) {
				
				if(onDemandList.size()>0) {
					
					for(Integer i=0;i<onDemandList.size();i++) {
						try {
							boolean isOnDemandProcessed=false;
							HesDemandRequestDetails onDemandObj=onDemandList.get(i);
							LogUtils.appendLoggerDetails(logger,className,methodName, "ON DEMAND PROCESSING IS : "+onDemandObj.getCheckCommand());
							if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_DATETIME")) {
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								Date meterDateTime=meterGetObj.fetchRTCFromMeter(in, toClient, sleepSeconds, meterNumber, logger);
								String meterDateTimeStr=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(meterDateTime);
								onDemandObj.setGetValue(meterDateTimeStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setLastMeterDateTime(new Timestamp(meterDateTime.getTime()));
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_SIGNAL_STRENGTH")) {
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String sigNalStr=meterGetObj.getSignalStrength(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(sigNalStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setSignalStrength(sigNalStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_METER_TYPE")) {
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String meterType=meterGetObj.getMeterType(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(meterType);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setMeterType(meterType);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_METER_VERSION")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=meterGetObj.getMeterFirmWareVersion(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");	
								isOnDemandProcessed=true;
								meterReplica.setMeterFirmwareVersion(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_METER_PROGRAM_VERSION")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=meterGetObj.getMeterProgramFirmWareVersion(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setMeterProgramFirmWareVersion(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_MODEM_VERSION")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=meterGetObj.getModemFirmWareVersion(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setModemFirmwareVersion(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_MANUFACTURE_NAME")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=meterGetObj.getManufactureName(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setManufactureName(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_SIM_SERIAL_NUMBER")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=meterGetObj.getSimSerialNumber(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setSimSerialNumber(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_SIM_DIAL_NUMBER")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=meterGetObj.getSimDialNumber(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setSimDialNumber(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_MODEM_IMEI")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=meterGetObj.getModemImeiNumber(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setModemImeiNumber(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_RELAY_STATUS")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=meterGetObj.fetchRelayStatus(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setRelayStatus(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_CONSUMER_NAME")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=consumerGet.getConsumerName(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setConsumerName(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_CONSUMER_ID")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=consumerGet.getConsumerId(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setConsumerId(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_CONSUMER_NO")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=consumerGet.getConsumerNumber(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setConsumerNo(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_METER_FORGIVENESS_TIME")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=billingGetobj.getMeterForgivenessTime(in, toClient, sleepSeconds, logger, meterNumber);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setForgivenessTime(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_MD_RESET_DATE")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=billingGetobj.fetchMdResetDate(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setMdResetDate(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_APPROVED_DEMAND")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=billingGetobj.fetchApprovedDemand(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setApprovedDemand(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_DEMAND_INTEGRATION_PERIOD")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=billingGetobj.fetchDemandIntegrationPeriod(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setDemandIntegrationPeriod(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_LOAD_PROFILE_CAPTURE_PERIOD")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=billingGetobj.fetchLoadProfileCapturePeriod(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setLoadProfileCapturePeriod(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_TRESHHOLD_VALUE")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String getValStr=billingGetobj.fetchTreshHoldValue(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setTreshHoldValue(getValStr);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_IP_PORT_APN")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String[] getValStr=meterGetObj.fetchIpAddressDetails(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr[0]+"|"+getValStr[1]+"|"+getValStr[2]);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setIpAddress(getValStr[0]);
								meterReplica.setPortNumber(getValStr[1]);
								meterReplica.setApnName(getValStr[2]);
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("GET_GPRS_HEART_BEAT")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								String[] getValStr=meterGetObj.getGPRSIntervalTime(in, toClient, sleepSeconds, meterNumber, logger);
								onDemandObj.setGetValue(getValStr[0]+"|"+getValStr[1]);
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("COMPLETED");
								isOnDemandProcessed=true;
								meterReplica.setGprsIntervalTime(getValStr[0]);
								meterReplica.setGprsIntervalTime(getValStr[1]);
								
							}
							
							
							
							
							
							// GET SETTINGS COMPLETED.. NOW SET SETTINGS STARTS
							
							else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_DATETIME")){
								Date serverDateTime=new Date();
							    Date nepaliDate=new Utils().convertEnglishToNepali(serverDateTime);
							    Calendar c = Calendar.getInstance();
							    c.setTime(serverDateTime);
							    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
							    
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								boolean isDataSet=meterSetObj.setServerDateTimeToMeter(in, toClient, sleepSeconds, logger, meterNumber, nepaliDate, dayOfWeek);
								if(isDataSet) {
									onDemandObj.setSetValue(serverDateTime+"");
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("COMPLETED");
								}								
//							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_METER_TYPE")){
//							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_MANUFACTURE_NAME")){
//							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_SIM_SERIAL_NUMBER")){
//							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_SIM_DIAL_NUMBER")){
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_CONSUMER_NAME")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								if(masterdataObj.getConsumerName()!=null) {
									boolean isDataSet=consumerSet.setConsumerName(in, toClient, sleepSeconds, logger, meterNumber, masterdataObj.getConsumerName());
									if(isDataSet) {
										onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
										onDemandObj.setRequestStatus("COMPLETED");
										meterReplica.setConsumerName(masterdataObj.getConsumerName());
									}
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
									
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_CONSUMER_ID")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								if(masterdataObj.getConsumerId()!=null) {
									boolean isDataSet=consumerSet.setConsumerID(in, toClient, sleepSeconds, logger, meterNumber, masterdataObj.getConsumerId());
									if(isDataSet) {
										onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
										onDemandObj.setRequestStatus("COMPLETED");
										meterReplica.setConsumerId(masterdataObj.getConsumerId());
									}
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
									
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_CONSUMER_NO")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								if(masterdataObj.getConsumerNo()!=null) {
									boolean isDataSet=consumerSet.setConsumerNumber(in, toClient, sleepSeconds, logger, meterNumber, masterdataObj.getConsumerNo());
									if(isDataSet) {
										onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
										onDemandObj.setRequestStatus("COMPLETED");
										meterReplica.setConsumerNo(masterdataObj.getConsumerNo());
									}	
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
								
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_METER_FORGIVENESS_TIME")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								if(masterdataObj.getForgivenessTime()!=null) {
									boolean isDataSet=billingSetobj.setForgivenessTimeForManualBilling(in, toClient, sleepSeconds, logger, meterNumber,masterdataObj.getForgivenessTime());
									if(isDataSet) {
										onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
										onDemandObj.setRequestStatus("COMPLETED");
										meterReplica.setForgivenessTime(masterdataObj.getForgivenessTime());
									}	
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
								
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_MD_RESET_DATE")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								if(masterdataObj.getMdresetdate()!=null) {
									boolean isDataSet=billingSetobj.setMDResetDate(in, toClient, sleepSeconds, logger, meterNumber, masterdataObj.getMdresetdate());
									if(isDataSet) {
										onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
										onDemandObj.setRequestStatus("COMPLETED");
										meterReplica.setMdResetDate(masterdataObj.getMdresetdate());
									}
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
								
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_APPROVED_DEMAND")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								if(masterdataObj.getApprovedDemandVal()!=null) {
									boolean isDataSet=billingSetobj.setApprovedDemand(in, toClient, sleepSeconds, logger, meterNumber, masterdataObj.getApprovedDemandVal()+"");
									if(isDataSet) {
										onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
										onDemandObj.setRequestStatus("COMPLETED");
										meterReplica.setApprovedDemand(masterdataObj.getApprovedDemandVal()+"");
									}
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
								
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_DEMAND_INTEGRATION_PERIOD")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								if((masterdataObj.getDemandIntegrationPeriod()!=null)&&(masterdataObj.getDemandIntegrationPeriodCount()!=null)) {
									boolean isDataSet=billingSetobj.setDemandIntegrationPeriod(in, toClient, sleepSeconds, logger, meterNumber, masterdataObj.getDemandIntegrationPeriod(), masterdataObj.getDemandIntegrationPeriodCount());
									if(isDataSet) {
										onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
										onDemandObj.setRequestStatus("COMPLETED");
										meterReplica.setDemandIntegrationPeriod(masterdataObj.getDemandIntegrationPeriod());
										meterReplica.setDemandIntegrationPeriodCount(masterdataObj.getDemandIntegrationPeriodCount());
									}
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
								
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_LOAD_PROFILE_CAPTURE_PERIOD")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								if(masterdataObj.getLoadProfileCapturePeriod()!=null) {
									boolean isDataSet=billingSetobj.setLoadProfileCapturePeriod(in, toClient, sleepSeconds, logger, meterNumber, masterdataObj.getLoadProfileCapturePeriod());
									if(isDataSet) {
										onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
										onDemandObj.setRequestStatus("COMPLETED");
										meterReplica.setLoadProfileCapturePeriod(masterdataObj.getLoadProfileCapturePeriod());
									}
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
								
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_TRESHHOLD_VALUE")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								if(masterdataObj.getTreshHoldValue()!=null) {
									boolean isDataSet=billingSetobj.setTreshHoldData(in, toClient, sleepSeconds, logger, meterNumber, masterdataObj.getTreshHoldValue());
									if(isDataSet) {
										onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
										onDemandObj.setRequestStatus("COMPLETED");
										meterReplica.setTreshHoldValue(masterdataObj.getTreshHoldValue());
									}
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
								
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_RELAY_CONNECT")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								boolean isDataSet=meterSetObj.relayReConnect(in, toClient, sleepSeconds, meterNumber, logger);
								if(isDataSet) {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("COMPLETED");
									meterReplica.setRelayStatus("ON");
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_RELAY_DISCONNECT")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								boolean isDataSet=meterSetObj.relayDisconnect(in, toClient, sleepSeconds, meterNumber, logger);
								if(isDataSet) {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("COMPLETED");
									meterReplica.setRelayStatus("OFF");
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_IP_PORT_APN")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								
								if((masterdataObj.getIpAddress()!=null)&&(masterdataObj.getPortNumber()!=null)&&(masterdataObj.getApnName()!=null)) {
									boolean isDataSet=meterSetObj.setMeterIpAddressPortAndApnName(in, toClient, sleepSeconds, logger, meterNumber, masterdataObj.getIpAddress(), masterdataObj.getPortNumber(), masterdataObj.getApnName());
									if(isDataSet) {
										onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
										onDemandObj.setRequestStatus("COMPLETED");
										meterReplica.setIpAddress(masterdataObj.getIpAddress());
										meterReplica.setPortNumber(masterdataObj.getPortNumber());
										meterReplica.setApnName(masterdataObj.getApnName());
									}
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
								
							}else if(onDemandObj.getCheckCommand().equalsIgnoreCase("SET_GPRS_HEART_BEAT")){
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								
								if((masterdataObj.getGprsIntervalTime()!=null)&&(masterdataObj.getHeartBeat()!=null)) {
									boolean isDataSet=meterSetObj.setGPRSIntervalTime(in, toClient, sleepSeconds, meterNumber, logger, masterdataObj.getGprsIntervalTime(), masterdataObj.getHeartBeat());
									if(isDataSet) {
										onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
										onDemandObj.setRequestStatus("COMPLETED");
										meterReplica.setGprsIntervalTime(masterdataObj.getGprsIntervalTime());
										meterReplica.setHeartBeat(masterdataObj.getHeartBeat());
									}
								}else {
									onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
									onDemandObj.setRequestStatus("INVALID REQ DATA");
								}
							}
							
							
							else {
								onDemandObj.setReqStartTime(new Timestamp(new Date().getTime()));
								onDemandObj.setReqCompTime(new Timestamp(new Date().getTime()));
								onDemandObj.setRequestStatus("INVALID REQUEST");
							}
							
							
								
							ruleEngObj.saveOrUpdateObject(onDemandObj);
							
							
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}else {
					LogUtils.appendLoggerDetails(logger,className,methodName, "No Ondemand Requests found..:  0");
				}
			}else {
				LogUtils.appendLoggerDetails(logger,className,methodName, "No Ondemand Requests found.");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return meterReplica;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
