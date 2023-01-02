package com.hes.lis.mltd.process.set;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.LogUtils;

public class StatusWordResetSET {
	
	private static String className="StatusWordResetSET";
	
	private static CommunicationUtils tcpCommObj=new CommunicationUtils();
	
	public boolean resetEventStatusWordReset(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetEventStatusWordReset";
		boolean setStatus=false;

		try{
			String command="00 01 00 01 00 01 00 12 C1 01 C1 00 01 00 00 61 81 00 FF 02 00 06 00 00 00 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetEventStatusWordReset command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetEventStatusWordReset response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetEventStatusWordReset ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetEventStatusWordReset NCK");
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
	
	
	public boolean resetErrorObject(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetErrorObject";
		boolean setStatus=false;

		try{
			String command="00 01 00 01 00 01 00 12 C1 01 C2 00 01 00 00 61 61 00 FF 02 00 06 00 00 00 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetErrorObject command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetErrorObject response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetErrorObject ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetErrorObject NCK");
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
	
	public boolean resetAlarmRegistor1(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetAlarmRegistor1";
		boolean setStatus=false;

		try{
			String command="00 01 00 01 00 01 00 12 C1 01 C3 00 01 00 00 61 62 00 FF 02 00 06 00 00 00 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmRegistor1 command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmRegistor1 response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmRegistor1 ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmRegistor1 NCK");
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
	
	public boolean resetAlarmDescriptor1(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetAlarmDescriptor1";
		boolean setStatus=false;

		try{
			String command="00 01 00 01 00 01 00 12 C1 01 C4 00 01 00 00 61 62 14 FF 02 00 06 00 00 00 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmDescriptor1 command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmDescriptor1 response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmDescriptor1 ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmDescriptor1 NCK");
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

	public boolean resetAlarmRegistor2(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetAlarmRegistor2";
		boolean setStatus=false;

		try{
			String command="00 01 00 01 00 01 00 12 C1 01 C5 00 01 00 00 61 62 01 FF 02 00 06 00 00 00 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmRegistor2 command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmRegistor2 response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmRegistor2 ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmRegistor2 NCK");
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
	
	public boolean resetAlarmDescriptor2(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
		String methodName="resetAlarmDescriptor2";
		boolean setStatus=false;

		try{
			String command="00 01 00 01 00 01 00 12 C1 01 C6 00 01 00 00 61 62 15 FF 02 00 06 00 00 00 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmDescriptor2 command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmDescriptor2 response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmDescriptor2 ACK :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"resetAlarmDescriptor2 NCK");
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
	
	
	public boolean setAllStatusWordReset(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			String meterNumber) {
//		String methodName="setAllStatusWordReset";
		boolean status=false;
		
		try{
			
			if(resetEventStatusWordReset(in, toClient, sleepSeconds, logger, meterNumber)){
			if(resetErrorObject(in, toClient, sleepSeconds, logger, meterNumber)){
			if(resetAlarmRegistor1(in, toClient, sleepSeconds, logger, meterNumber)){
			if(resetAlarmDescriptor1(in, toClient, sleepSeconds, logger, meterNumber)){
			if(resetAlarmRegistor2(in, toClient, sleepSeconds, logger, meterNumber)){
			if(resetAlarmDescriptor2(in, toClient, sleepSeconds, logger, meterNumber)){
				status=true;
			}}}}}}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
