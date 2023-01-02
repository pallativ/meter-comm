package com.hes.lis.mltd.process.set;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.LogUtils;


/**
 * 
 * @author achyuth kanth pamuru
 *
 */
public class EventsRelatedSET {
	
	private static String className="EventsRelatedSET";
	
	private static CommunicationUtils tcpCommObj=new CommunicationUtils();

	
	public boolean setOutsideFactoryClearTamper(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="setOutsideFactoryClearTamper";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C1 01 C1 00 01 01 00 A1 80 00 FF 02 00 11 01";
			LogUtils.appendLoggerDetails(logger,className,methodName,"setOutsideFactoryClearTamper command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {


					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"setOutsideFactoryClearTamper response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"setOutsideFactoryClearTamper ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"setOutsideFactoryClearTamper NCK");
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
	
	public boolean resetStandardEventLog(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetStandardEventLog";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 00 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetStandardEventLog command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetStandardEventLog response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetStandardEventLog ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetStandardEventLog NCK");
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
	
	public boolean resetFraudDetectionLog(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetFraudDetectionLog";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 01 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetFraudDetectionLog command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetFraudDetectionLog response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetFraudDetectionLog ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetFraudDetectionLog NCK");
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
	
	public boolean resetDisconnectorControlLog(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetDisconnectorControlLog";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 02 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetDisconnectorControlLog command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetDisconnectorControlLog response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetDisconnectorControlLog ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetDisconnectorControlLog NCK");
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
	
	public boolean resetPowerQualityLog(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetPowerQualityLog";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 04 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetPowerQualityLog command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetPowerQualityLog response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetPowerQualityLog ACK :: "+response+" ");
						break;
					}else{
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetPowerQualityLog NCK");
						trailCount++;
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
	
	public boolean resetCommunicationLog(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetCommunicationLog";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 05 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetCommunicationLog command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetCommunicationLog response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetCommunicationLog ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetCommunicationLog NCK");
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
	
	public boolean resetMainCoverRemovedEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetMainCoverRemovedEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 82 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetMainCoverRemovedEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetMainCoverRemovedEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetMainCoverRemovedEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetMainCoverRemovedEvent NCK");
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
	
	public boolean resetTerminalCoverRemovedEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetTerminalCoverRemovedEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 83 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetTerminalCoverRemovedEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetTerminalCoverRemovedEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetTerminalCoverRemovedEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetTerminalCoverRemovedEvent NCK");
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
	
	public boolean resetOverVoltageL1(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetOverVoltageL1";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 84 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverVoltageL1 command :: "+command+" ");
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverVoltageL1 response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverVoltageL1 ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverVoltageL1 NCK");
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
	
	public boolean resetOverVoltageL2(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetOverVoltageL2";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 85 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverVoltageL2 command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverVoltageL2 response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverVoltageL2 ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverVoltageL2 NCK");
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
	
	public boolean resetOverVoltageL3(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetOverVoltageL3";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 86 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverVoltageL1 command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverVoltageL3 response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverVoltageL3 ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverVoltageL3 NCK");
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
	
	public boolean resetUnderVoltageL1(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetUnderVoltageL1";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 87 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetUnderVoltageL1 command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetUnderVoltageL1 response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetUnderVoltageL1 ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetUnderVoltageL1 NCK");
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
	
	public boolean resetUnderVoltageL2(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetUnderVoltageL2";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 88 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetUnderVoltageL2 command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetUnderVoltageL2 response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetUnderVoltageL2 ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetUnderVoltageL2 NCK");
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
	
	public boolean resetUnderVoltageL3(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetUnderVoltageL3";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 89 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetUnderVoltageL3 command :: "+command+" ");
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetUnderVoltageL3 response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetUnderVoltageL3 ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetUnderVoltageL3 NCK");
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
	
	public boolean resetBypassEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetBypassEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 8D FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetBypassEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetBypassEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetBypassEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetBypassEvent NCK");
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
	
	public boolean resetStrongDCEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetStrongDCEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 8F FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetStrongDCEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetStrongDCEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetStrongDCEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetStrongDCEvent NCK");
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
	
	public boolean resetChargeEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetChargeEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 90 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetChargeEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetChargeEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetChargeEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetChargeEvent NCK");
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
	
	public boolean resetRelayDisconnectEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetRelayDisconnectEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 91 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetRelayDisconnectEvent command :: "+command+" ");
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetRelayDisconnectEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetRelayDisconnectEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetRelayDisconnectEvent NCK");
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
	
	public boolean resetRelayConnectEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetRelayConnectEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 92 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetRelayConnectEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetRelayConnectEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetRelayConnectEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetRelayConnectEvent NCK");
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
	
	public boolean resetOverloadEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetOverloadEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 94 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverloadEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverloadEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverloadEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetOverloadEvent NCK");
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
	
	public boolean resetShortPowerEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		
		String methodName="resetShortPowerEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 97 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetShortPowerEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetShortPowerEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetShortPowerEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetShortPowerEvent NCK");
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
	
	public boolean resetLongPowerFailureEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetLongPowerFailureEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 98 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetLongPowerFailureEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetLongPowerFailureEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetLongPowerFailureEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetLongPowerFailureEvent NCK");
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
	
	public boolean resetChangeMeterModeEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetChangeMeterModeEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 A7 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetChangeMeterModeEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetChangeMeterModeEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetChangeMeterModeEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetChangeMeterModeEvent NCK");
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
	
	public boolean resetEnergyReversalL1Event(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetEnergyReversalL1Event";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 BF FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetEnergyReversalL1Event command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetEnergyReversalL1Event response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetEnergyReversalL1Event ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetEnergyReversalL1Event NCK");
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
	
	public boolean resetEnergyReversalL2Event(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetEnergyReversalL2Event";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 C0 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetEnergyReversalL2Event command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetEnergyReversalL2Event response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetEnergyReversalL2Event ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetEnergyReversalL2Event NCK");
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
	
	public boolean resetEnergyReversalL3Event(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetEnergyReversalL3Event";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 C1 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetEnergyReversalL3Event command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetEnergyReversalL3Event response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetEnergyReversalL3Event ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetEnergyReversalL3Event NCK");
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
	
	public boolean resetNeutralLineInterfaceEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetNeutralLineInterfaceEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 C4 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetNeutralLineInterfaceEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetNeutralLineInterfaceEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetNeutralLineInterfaceEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetNeutralLineInterfaceEvent NCK");
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
	
	public boolean resetPhaseVoltageNeutralL1Event(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetPhaseVoltageNeutralL1Event";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 C5 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetPhaseVoltageNeutralL1Event command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetPhaseVoltageNeutralL1Event response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetPhaseVoltageNeutralL1Event ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetPhaseVoltageNeutralL1Event NCK");
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
	
	public boolean resetPhaseVoltageNeutralL2Event(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetPhaseVoltageNeutralL2Event";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 C6 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetPhaseVoltageNeutralL2Event command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetPhaseVoltageNeutralL2Event response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetPhaseVoltageNeutralL2Event ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetPhaseVoltageNeutralL2Event NCK");
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
	
	public boolean resetPhaseVoltageNeutralL3Event(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetPhaseVoltageNeutralL3Event";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 C7 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetPhaseVoltageNeutralL3Event command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetPhaseVoltageNeutralL3Event response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetPhaseVoltageNeutralL3Event ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetPhaseVoltageNeutralL3Event NCK");
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
	
	public boolean resetManagementTokenEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetManagementTokenEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 95 FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetManagementTokenEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetManagementTokenEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetManagementTokenEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetManagementTokenEvent NCK");
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
	
	public boolean resetAccountChangeEvent(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetAccountChangeEvent";
		boolean setStatus=false;
		

		try{
			
			String command="00 01 00 01 00 01 00 0F C3 01 C1 00 07 00 00 63 62 BD FF 01 01 0F 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetAccountChangeEvent command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {

					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetAccountChangeEvent response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetAccountChangeEvent ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetAccountChangeEvent NCK");
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
	
	
	
	public boolean setAllEventsReset(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
//		String methodName="setAllEventsReset";
		boolean status=false;
		
		try{
			
			if(setOutsideFactoryClearTamper(in, toClient, sleepSeconds, logger, meterNumber)){
			if(resetCommunicationLog(in, toClient, sleepSeconds, logger, meterNumber)){
			if(resetFraudDetectionLog(in, toClient, sleepSeconds, logger, meterNumber)){
			if(resetStandardEventLog(in, toClient, sleepSeconds, logger, meterNumber)){
			if(resetPowerQualityLog(in, toClient, sleepSeconds, logger, meterNumber)){
			if(resetDisconnectorControlLog(in, toClient, sleepSeconds, logger, meterNumber)){
				status=true;
			}}}}}}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	

}

