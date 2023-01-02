package com.hes.lis.mltd.process;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.hes.lis.mltd.dao.MeterRuleEngineDAO;
import com.hes.lis.mltd.process.get.BillingRelatedGET;
import com.hes.lis.mltd.process.get.ConsumerDetailsGET;
import com.hes.lis.mltd.process.get.MeterGettings;
import com.hes.lis.mltd.process.set.BillingRelatedSET;
import com.hes.lis.mltd.process.set.ConsumerDetailsSET;
import com.hes.lis.mltd.process.set.EventsRelatedSET;
import com.hes.lis.mltd.process.set.LimiterControllerSET;
import com.hes.lis.mltd.process.set.MeterSettings;
import com.hes.lis.mltd.process.set.StatusWordResetSET;
import com.hes.lis.mltd.utils.LogUtils;
import com.hes.lis.mltd.utils.Utils;
import com.hes.lis.mltd.vo.Masterdata;
import com.hes.lis.mltd.vo.MeterReplica;
import com.hes.lis.mltd.vo.VeryFirstCommunication;


/**
 * 
 * @author psvr
 *
 */
public class MeterInitialCheckings {
	private static String className="MeterInitialCheckings";
	
	public void initialCheckings(Masterdata meterMaster,Date commStartDateTime,LogUtils logger,DataInputStream in,
			DataOutputStream toClient,Integer sleepSeconds){
		String methodName="initialCheckings";
		MeterRuleEngineDAO daoObj=new MeterRuleEngineDAO();
		MeterAuthentications meterAuths=new MeterAuthentications();
		MeterSettings meterSetObj=new MeterSettings();
		ConsumerDetailsSET consumerSET=new ConsumerDetailsSET();
		LimiterControllerSET limiterControlSET=new LimiterControllerSET();
		BillingRelatedSET billingSET=new BillingRelatedSET();
		Utils utilsObj=new Utils();

		boolean isUpdated=false;
		try{
			
			VeryFirstCommunication firstComm=daoObj.fetchMeterVeryFirstComm(meterMaster.getMeterNo());
			
			if(firstComm==null){
				LogUtils.appendLoggerDetails(logger,className,methodName, "Meter initiated VeryFirstCommunication");
				
				boolean isSecondLevelpasswordAuth=meterAuths.highLevelPasswordAuthentication(in,toClient, sleepSeconds, meterMaster.getMeterNo(), logger);
				LogUtils.appendLoggerDetails(logger,className,methodName, "Meter isSecondLevelpasswordAuth"+isSecondLevelpasswordAuth);
				
				if(isSecondLevelpasswordAuth) {
					Date serverDateTime=new Date();
//				    Date nepaliDate=utilsObj.convertEnglishToNepali(serverDateTime);
				    Calendar c = Calendar.getInstance();
				    c.setTime(serverDateTime);
				    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
				    			
				    LogUtils.appendLoggerDetails(logger,className,methodName, "Meter Staretd Initial Configurations");
					isUpdated=meterSetObj.setServerDateTimeToMeter(in, toClient, sleepSeconds, logger, meterMaster.getMeterNo(), serverDateTime, dayOfWeek);
					LogUtils.appendLoggerDetails(logger,className,methodName, "Meter Date Time settings : "+isUpdated);
					if(isUpdated) {
						isUpdated=consumerSET.setConsumerID(in, toClient, sleepSeconds, logger, meterMaster.getMeterNo(),meterMaster.getConsumerId());
						LogUtils.appendLoggerDetails(logger,className,methodName, "setConsumerID : "+isUpdated);
						if(isUpdated) {
							isUpdated=consumerSET.setConsumerName(in, toClient, sleepSeconds, logger, meterMaster.getMeterNo(),meterMaster.getConsumerName());
							LogUtils.appendLoggerDetails(logger,className,methodName, "setConsumerName : "+isUpdated);
							if(isUpdated) {
								isUpdated=consumerSET.setConsumerNumber(in, toClient, sleepSeconds, logger, meterMaster.getMeterNo(),meterMaster.getConsumerNo());
								LogUtils.appendLoggerDetails(logger,className,methodName, "setConsumerNumber : "+isUpdated);
								if(isUpdated) {
									isUpdated=limiterControlSET.setAllLimiterControllers(in, toClient, sleepSeconds, logger, meterMaster);
									LogUtils.appendLoggerDetails(logger,className,methodName, "setAllLimiterControllers : "+isUpdated);
									if(isUpdated) {
										isUpdated=billingSET.setBillingRelatedParams(in, toClient, sleepSeconds, logger, meterMaster);
										LogUtils.appendLoggerDetails(logger,className,methodName, "setBillingRelatedParams : "+isUpdated);
										if(isUpdated) {
											isUpdated=new StatusWordResetSET().setAllStatusWordReset(in, toClient, sleepSeconds, logger, meterMaster.getMeterNo());
											LogUtils.appendLoggerDetails(logger,className,methodName, "setAllStatusWordReset : "+isUpdated);
											if(isUpdated) {
												isUpdated=new EventsRelatedSET().setAllEventsReset(in, toClient, sleepSeconds, logger, meterMaster.getMeterNo());
												LogUtils.appendLoggerDetails(logger,className,methodName, "setAllEventsReset : "+isUpdated);
												if(isUpdated) {
													isUpdated=meterSetObj.relayDisconnect(in, toClient, sleepSeconds, meterMaster.getMeterNo(), logger);
													LogUtils.appendLoggerDetails(logger,className,methodName, "relayDisconnect : "+isUpdated);
													if(isUpdated) {
														isUpdated=meterSetObj.relayReConnect(in, toClient, sleepSeconds, meterMaster.getMeterNo(), logger);
														LogUtils.appendLoggerDetails(logger,className,methodName, "relayReConnect : "+isUpdated);
													}
												}
											}
										}
							    	}
								}
							}
						}
					}
				}
				
				
				if(isUpdated){
			    	VeryFirstCommunication firstCommunication=daoObj.fetchMeterVeryFirstComm(meterMaster.getMeterNo());
					
					if(firstCommunication==null){
						firstCommunication=new VeryFirstCommunication();
						firstCommunication.setCommDatetime(new Timestamp(new Date().getTime()));
						firstCommunication.setMeterSerialNumber(meterMaster.getMeterNo());
						isUpdated=daoObj.saveObject(firstCommunication);
					}
			    }
				
			}else {
				LogUtils.appendLoggerDetails(logger,className,methodName, "Not a VeryFirstCommunication");
			}	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateVeryfirstComm(String meterSerialNumber,Date commStartDateTime){
		String methodName="updateVeryfirstComm";
		MeterRuleEngineDAO daoObj=new MeterRuleEngineDAO();

		try{
			VeryFirstCommunication firstComm=new VeryFirstCommunication();
			firstComm.setCommDatetime(new Timestamp(commStartDateTime.getTime()));
			firstComm.setMeterSerialNumber(meterSerialNumber);
			boolean status=daoObj.saveObject(firstComm);
			if(!status) {//IF NOT SAVED then it should recheck in next communication
				firstComm=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public MeterReplica checkConfigParams(String meterSerialNumber,DataInputStream in,DataOutputStream toClient,Integer sleepSeconds,  String meterNumber,LogUtils logger){
		String methodName="checkConfigParams";
		MeterReplica meterReplica=null;
		MeterRuleEngineDAO dao=new MeterRuleEngineDAO();
		MeterGettings meterGetObj=new MeterGettings();
		ConsumerDetailsGET consumerGet=new ConsumerDetailsGET();
		BillingRelatedGET billingGet=new BillingRelatedGET();

		try{
			meterReplica=dao.fetchMeterReplica(meterSerialNumber);
			if(meterReplica==null){
				meterReplica=new MeterReplica();
				meterReplica.setMeterSerialNumber(meterSerialNumber);
			}
			
			if(meterReplica.getManufactureName()==null) {
				String manufactureName=meterGetObj.getManufactureName(in, toClient, sleepSeconds, meterNumber, logger);
				if(manufactureName!=null){
					meterReplica.setManufactureName(manufactureName);
				}
			}
			
			if(meterReplica.getMeterFirmwareVersion()==null) {
				String meterFirmwareVersion=meterGetObj.getMeterFirmWareVersion(in, toClient, sleepSeconds, meterNumber, logger);
				if(meterFirmwareVersion!=null){
					meterReplica.setMeterFirmwareVersion(meterFirmwareVersion);
				}
			}
			
			if(meterReplica.getMeterProgramFirmWareVersion()==null) {
				String meterProgramFirmwareVersion=meterGetObj.getMeterProgramFirmWareVersion(in, toClient, sleepSeconds, meterNumber, logger);
				if(meterProgramFirmwareVersion!=null){
					meterReplica.setMeterProgramFirmWareVersion(meterProgramFirmwareVersion);
				}
			}
			
			if(meterReplica.getMeterType()==null) {
				String meterType=meterGetObj.getMeterType(in, toClient, sleepSeconds, meterNumber, logger);
				if(meterType!=null){
					meterReplica.setMeterType(meterType);
				}
			}
			
			if(meterReplica.getModemFirmwareVersion()==null) {
				String modemFirmwareVersion=meterGetObj.getModemFirmWareVersion(in, toClient, sleepSeconds, meterNumber, logger);
				if(modemFirmwareVersion!=null){
					meterReplica.setModemFirmwareVersion(modemFirmwareVersion);
				}
			}
			
			if(meterReplica.getModemImeiNumber()==null) {
				String modemIMEINumber=meterGetObj.getModemImeiNumber(in, toClient, sleepSeconds, meterNumber, logger);
				if(modemIMEINumber!=null){
					meterReplica.setModemImeiNumber(modemIMEINumber);
				}
			}
			
			if(meterReplica.getSimDialNumber()==null) {
				String simDialNumber=meterGetObj.getSimDialNumber(in, toClient, sleepSeconds, meterNumber, logger);
				if(simDialNumber!=null){
					meterReplica.setSimDialNumber(simDialNumber);
				}
			}
			
			if(meterReplica.getSimSerialNumber()==null) {
				String simSerialNumber=meterGetObj.getSimSerialNumber(in, toClient, sleepSeconds, meterNumber, logger);
				if(simSerialNumber!=null){
					meterReplica.setSimSerialNumber(simSerialNumber);
				}
			}
			
			if(meterReplica.getRelayStatus()==null) {
				String relayStatus=meterGetObj.fetchRelayStatus(in, toClient, sleepSeconds, meterNumber, logger);
				if(relayStatus!=null){
					meterReplica.setRelayStatus(relayStatus);
				}
			}
			
			if(meterReplica.getConsumerId()==null) {
				String consumerId=consumerGet.getConsumerId(in, toClient, sleepSeconds, meterNumber, logger);
				if(consumerId!=null){
					meterReplica.setConsumerId(consumerId);
				}
			}
			
			if(meterReplica.getConsumerNo()==null) {
				String consumerNo=consumerGet.getConsumerNumber(in, toClient, sleepSeconds, meterNumber, logger);
				if(consumerNo!=null){
					meterReplica.setConsumerNo(consumerNo);
				}
			}
			
			if(meterReplica.getConsumerName()==null) {
				String consumerName=consumerGet.getConsumerName(in, toClient, sleepSeconds, meterNumber, logger);
				if(consumerName!=null){
					meterReplica.setConsumerName(consumerName);
				}
			}
			
			if(meterReplica.getForgivenessTime()==null) {
				String foregivenessTime=billingGet.getMeterForgivenessTime(in, toClient, sleepSeconds, logger, meterNumber);
				if(foregivenessTime!=null){
					meterReplica.setForgivenessTime(foregivenessTime);
				}
			}
			
			if(meterReplica.getMdResetDate()==null) {
				String mdResetdate=billingGet.fetchMdResetDate(in, toClient, sleepSeconds, meterNumber, logger);
				if(mdResetdate!=null){
					meterReplica.setMdResetDate(mdResetdate);
				}
			}
			
			if(meterReplica.getApprovedDemand()==null) {
				String approvedDemand=billingGet.fetchApprovedDemand(in, toClient, sleepSeconds, meterNumber, logger);
				if(approvedDemand!=null){
					meterReplica.setApprovedDemand(approvedDemand);
				}
			}
			
			if(meterReplica.getDemandIntegrationPeriod()==null) {
				String demandIntegrationPeriod=billingGet.fetchDemandIntegrationPeriod(in, toClient, sleepSeconds, meterNumber, logger);
				if(demandIntegrationPeriod!=null){
					meterReplica.setDemandIntegrationPeriod(demandIntegrationPeriod);
				}
			}
			
			if(meterReplica.getLoadProfileCapturePeriod()==null) {
				String loadProfileCapturePeriod=billingGet.fetchLoadProfileCapturePeriod(in, toClient, sleepSeconds, meterNumber, logger);
				if(loadProfileCapturePeriod!=null){
					meterReplica.setLoadProfileCapturePeriod(loadProfileCapturePeriod);
				}
			}
			
			if(meterReplica.getTreshHoldValue()==null) {
				String treshHoldval=billingGet.fetchTreshHoldValue(in, toClient, sleepSeconds, meterNumber, logger);
				if(treshHoldval!=null){
					meterReplica.setTreshHoldValue(treshHoldval);
				}
			}
			
			if((meterReplica.getIpAddress()==null)||(meterReplica.getPortNumber()==null)||(meterReplica.getApnName()==null)) {
				String[] ipAddressDetails=meterGetObj.fetchIpAddressDetails(in, toClient, sleepSeconds, meterNumber, logger);
				if(ipAddressDetails!=null){
					meterReplica.setIpAddress(ipAddressDetails[0]);
					meterReplica.setPortNumber(ipAddressDetails[1]);
					meterReplica.setApnName(ipAddressDetails[2]);
				}
			}
			
			if((meterReplica.getGprsIntervalTime()==null)||(meterReplica.getHeartBeat()==null)) {
				String[] gprsIntervalTime=meterGetObj.getGPRSIntervalTime(in, toClient, sleepSeconds, meterNumber, logger);
				if(gprsIntervalTime!=null){
					meterReplica.setGprsIntervalTime(gprsIntervalTime[0]);
					meterReplica.setHeartBeat(gprsIntervalTime[1]);
				}
			}
			
			Date meterDate=meterGetObj.fetchRTCFromMeter(in, toClient, sleepSeconds, meterNumber, logger);
			if(meterDate!=null){
				meterReplica.setLastMeterDateTime(new Timestamp(meterDate.getTime()));
			}
			
			String signalStrength=meterGetObj.getSignalStrength(in, toClient, sleepSeconds, meterNumber, logger);
			if(signalStrength!=null){
				meterReplica.setSignalStrength(signalStrength);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return meterReplica;
	}
	

}
