package com.hes.lis.mltd.process.set;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.HexConvertionClassUtils;
import com.hes.lis.mltd.utils.LogUtils;
import com.hes.lis.mltd.utils.Utils;


/**
 * 
 * @author psvr
 * 
 */
public class MeterSettings {
	private static String className="MeterSettings";
	
	private static CommunicationUtils tcpCommObj=new CommunicationUtils();
	
	public boolean relayDisconnect(DataInputStream in,
			DataOutputStream toClient, 
			Integer sleepSeconds,  String meterNumber,LogUtils logger) throws Exception{
		String methodName="relayDisconnect";
		boolean isRelayDisconnected=false;
		
		try{   													   
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 46 00 00 60 03 0A FF 01 01 0F 00";//00.00.96.03.10.255

			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,sleepSeconds, command,logger);
					String responseData=tcpCommObj.receiveBytes(in,sleepSeconds,meterNumber,logger);
					LogUtils.appendLoggerDetails(logger,className,methodName, "RELAY DISCONNECT RESPONSE  : "+responseData);
					String[] responseArr=responseData.split("\\ ");
					if(responseArr[11].equalsIgnoreCase("00")){
						isRelayDisconnected=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"RELAY DISCONNECT SUCCESS");
						Utils.updateLoggerDB(meterNumber, "RELAY DISCONNECT", "SUCCESS");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"RELAY DISCONNECT FAILED");
						Utils.updateLoggerDB(meterNumber, "RELAY DISCONNECT", "FAILED");
					}
					
					if (trailCount > 4) {
						break;
					}
				} catch (Exception e) {
					trailCount++;
				}
			}
			
			
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"EXCEPTION CAUGHT IN RELAY DISCONNECT"+e.getMessage());
			throw e;
		}
		return isRelayDisconnected;
	}
	
	public boolean relayReConnect(DataInputStream in,
			DataOutputStream toClient, 
			Integer sleepSeconds,  String meterNumber,LogUtils logger) throws Exception{
		String methodName="relayReConnect";
		boolean isRelayConnected=false;;
		
		try{   													   
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 46 00 00 60 03 0A FF 02 01 0F 00";//00.00.96.03.10.255
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,sleepSeconds, command,logger);
					String responseData=tcpCommObj.receiveBytes(in,sleepSeconds,meterNumber,logger);
					LogUtils.appendLoggerDetails(logger,className,methodName, "RELAY CONNECT RESPONSE  : "+responseData);
					String[] responseArr=responseData.split("\\ ");
					if(responseArr[11].equalsIgnoreCase("00")){
						isRelayConnected=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"RELAY CONNECT SUCCESS");
						Utils.updateLoggerDB(meterNumber, "RELAY CONNECT", "SUCCESS");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"RELAY CONNECT FAILED");
						Utils.updateLoggerDB(meterNumber, "RELAY CONNECT", "FAILED");
					}
					
					if (trailCount > 4) {
						break;
					}
				} catch (Exception e) {
					trailCount++;
				}
			}
			
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"EXCEPTION CAUGHT IN RELAY CONNECT"+e.getMessage());
			throw e;
		}
		return isRelayConnected;
	}
	
	
	public boolean setServerDateTimeToMeter(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber, Date nepaliDate,Integer dayOfweek) {
		String methodName="setServerDateTimeToMeter";
		boolean setStatus=false;

		try{
			
			String dateCmdStr=HexConvertionClassUtils.dateTimeHexBytesByDate(nepaliDate,dayOfweek);
			String command="00 01 00 01 00 01 00 1B C1 01 C1 00 08 00 00 01 00 00 FF 02 00 "+dateCmdStr;
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					LogUtils.appendLoggerDetails(logger,className,methodName,"setServerDateTimeToMeter :: "+response+" ");
					String[] responseArr=response.split("\\ ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"setServerDateTimeToMeter :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"setServerDateTimeToMeter NCK");
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
	}
	
	public boolean setMeterIpAddressPortAndApnName(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber, String ipAddress,String port,String apnName) {
		boolean setStatus=false;
		String methodName="setMeterIpAddressPortAndApnName";

		try{
			String[] ipArray=ipAddress.split("\\.");
			String ipAddressHex=StringUtils.leftPad(String.format("%x", Integer.parseInt(ipArray[0]))+"", 2, "0")+" "+
					  StringUtils.leftPad(String.format("%x", Integer.parseInt(ipArray[1]))+"", 2, "0")+" "+
					  StringUtils.leftPad(String.format("%x", Integer.parseInt(ipArray[2]))+"", 2, "0")+" "+
					  StringUtils.leftPad(String.format("%x", Integer.parseInt(ipArray[3]))+"", 2, "0")+" ";
			
			String portHex=StringUtils.leftPad(String.format("%x", Integer.parseInt(port)), 4,"0");
//			String portHex=StringUtils.leftPad(Integer.toHexString(0xFF & Integer.parseInt(port))+"", 4, "0");
			String apnNameHex=HexConvertionClassUtils.toHexString(apnName);
			
			String command="C1 01 C1 00 01 00 81 19 00 89 FF 02 00 02 03 06";
			command=command+" "+ipAddressHex+" 12 "+portHex+" 09";
			command=command+" "+StringUtils.leftPad(Integer.toHexString(0xFF & apnName.length())+"", 2, "0");
			command=command+" "+apnNameHex;
			
			Integer frameSize=((command.replace(" ", "")).length()/2);
			
			command="00 01 00 01 00 01 "+StringUtils.leftPad(Integer.toHexString(0xFF & frameSize)+"", 4, "0")+" "+command;
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"setMeterIpAddressPortAndApnName command :: "+command+" ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"setMeterIpAddressPortAndApnName res :: "+response+" ");
					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"setMeterIpAddressPortAndApnName :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"setMeterIpAddressPortAndApnName NCK");
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
	}
	
	public boolean setGPRSIntervalTime(DataInputStream in,DataOutputStream toClient, 
			Integer sleepSeconds,  String meterNumber,LogUtils logger,String commTime,String heartBeat) throws Exception{
		String methodName="setGPRSIntervalTime";
		boolean isSet=false;;
		
		try{   		
			Integer commSeconds=Integer.parseInt(commTime);
			Integer heartBeatSeconds=Integer.parseInt(heartBeat);
			String command="00 01 00 01 00 01 00 17 C1 01 C1 00 01 00 00 60 02 CF FF 02 00 02 03 03";
			command+=" 01 12 "+StringUtils.leftPad(String.format("%x", commSeconds), 4,"0");
			command+=" 12 "+StringUtils.leftPad(String.format("%x", heartBeatSeconds), 4,"0");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,sleepSeconds, command,logger);
					String responseData=tcpCommObj.receiveBytes(in,sleepSeconds,meterNumber,logger);
					LogUtils.appendLoggerDetails(logger,className,methodName, "setGPRSIntervalTime RESPONSE  : "+responseData);
					String[] responseArr=responseData.split("\\ ");
					
					if(responseArr[11].equalsIgnoreCase("00")){
						isSet=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"setGPRSIntervalTime ACK :: "+isSet);
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"setGPRSIntervalTime NCK");
					}
					
					if (trailCount > 4) {
						break;
					}
				} catch (Exception e) {
					trailCount++;
				}
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"ERROR in setGPRSIntervalTime"+e.getMessage());
			throw e;
		}
		return isSet;
	}
	
	

}
