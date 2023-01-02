package com.hes.lis.mltd.process.set;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.LogUtils;
import com.hes.lis.mltd.vo.Masterdata;

public class LimiterControllerSET {
	
	private static CommunicationUtils tcpCommObj=new CommunicationUtils();
	
	private static String className="LimiterControllerSET";
	
	
	
	
	public boolean setClassIdLogicalNameAttributeIndex(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			Masterdata masterDataObj) {
		String methodName="setClassIdLogicalNameAttributeIndex";
		boolean setStatus=false;

		try{
//			   Value---->structure[Class_Id:3; Logical_Name:010081E200FF; Attribute_Index:2; ] 
			String command="00 01 00 01 00 01 00 1C C1 01 C1 00 47 00 00 11 00 00 FF 02 00 02 03 12 00 03 09 06 01 00 81 E2 00 FF 0F 02";
			LogUtils.appendLoggerDetails(logger,className,methodName,"command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,masterDataObj.getMeterNo(),logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName," Success :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"Failed");
					}
					
					if (trailCount > 4) {
						break;
					}
				} catch (Exception e) {
					trailCount++;
				}
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return setStatus;
	}
	
	
	public boolean setThreashHoldNormal(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			Masterdata masterDataObj) {
		String methodName="setThreashHoldNormal";
		boolean setStatus=false;

		try{
//			      Value---->Threshold_Normal:15841;  
			String command="00 01 00 01 00 01 00 12 C1 01 C2 00 47 00 00 11 00 00 FF 04 00 06 00 00 3D E1";
			LogUtils.appendLoggerDetails(logger,className,methodName,"command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,masterDataObj.getMeterNo(),logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName," Success :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"Failed");
					}
					
					if (trailCount > 4) {
						break;
					}
				} catch (Exception e) {
					trailCount++;
				}
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return setStatus;
	}
	
	public boolean setThreashHoldEmergency(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			Masterdata masterDataObj) {
		String methodName="setThreashHoldEmergency";
		boolean setStatus=false;

		try{
//			     Value---->Threshold_Emergency:0;  
			String command="00 01 00 01 00 01 00 12 C1 01 C3 00 47 00 00 11 00 00 FF 05 00 06 00 00 00 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,masterDataObj.getMeterNo(),logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName," Success :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"Failed");
					}
					
					if (trailCount > 4) {
						break;
					}
				} catch (Exception e) {
					trailCount++;
				}
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return setStatus;
	}
	
	
	public boolean setMinOverThresholdDuration(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			Masterdata masterDataObj) {
		String methodName="setMinOverThresholdDuration";
		boolean setStatus=false;

		try{
//			     Value---->Min_Over_Threshold_Duration:30;  
			String command="00 01 00 01 00 01 00 12 C1 01 C4 00 47 00 00 11 00 00 FF 06 00 06 00 00 00 1E";
			LogUtils.appendLoggerDetails(logger,className,methodName,"command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,masterDataObj.getMeterNo(),logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName," Success :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"Failed");
					}
					
					if (trailCount > 4) {
						break;
					}
				} catch (Exception e) {
					trailCount++;
				}
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return setStatus;
	}
	
	public boolean setMinUnderThresholdDuration(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			Masterdata masterDataObj) {
		String methodName="setMinUnderThresholdDuration";
		boolean setStatus=false;

		try{
//			     Value---->Min_Under_Threshold_Duration:180; 
			String command="00 01 00 01 00 01 00 12 C1 01 C5 00 47 00 00 11 00 00 FF 07 00 06 00 00 00 B4";
			LogUtils.appendLoggerDetails(logger,className,methodName,"command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,masterDataObj.getMeterNo(),logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName," Success :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"Failed");
					}
					
					if (trailCount > 4) {
						break;
					}
				} catch (Exception e) {
					trailCount++;
				}
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return setStatus;
	}
	
	
	public boolean setEmergencyProfileActivationTimeANdDuration(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			Masterdata masterDataObj) {
		String methodName="setEmergencyProfileActivationTimeANdDuration";
		boolean setStatus=false;

		try{
//			     Value---->structure[Emergency_Profile_Id:1; Emergency_Activation_Time:081F04060514232BFFFF0000; Emergency_Duration:0; ] 
			String command="00 01 00 01 00 01 00 25 C1 01 C6 00 47 00 00 11 00 00 FF 08 00 02 03 12 00 01 09 0C 08 1F 04 06 05 14 23 2B FF FF 00 00 06 00 00 00 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,masterDataObj.getMeterNo(),logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName," Success :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"Failed");
					}
					
					if (trailCount > 4) {
						break;
					}
				} catch (Exception e) {
					trailCount++;
				}
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return setStatus;
	}
	
	
	public boolean setEmergencyProfileGroupIdList(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			Masterdata masterDataObj) {
		String methodName="setEmergencyProfileGroupIdList";
		boolean setStatus=false;

		try{
//			     Value---->array{Emergency_Profile_Group_Id_List:1; }
			String command="00 01 00 01 00 01 00 12 C1 01 C7 00 47 00 00 11 00 00 FF 09 00 01 01 12 00 01";
			LogUtils.appendLoggerDetails(logger,className,methodName,"command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,masterDataObj.getMeterNo(),logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName," Success :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"Failed");
					}
					
					if (trailCount > 4) {
						break;
					}
				} catch (Exception e) {
					trailCount++;
				}
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return setStatus;
	}
	
	public boolean setScriptLogicalName1And2Selector1And2(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			Masterdata masterDataObj) {
		String methodName="setScriptLogicalName1And2Selector1And2";
		boolean setStatus=false;

		try{
//			        Value---->structure[structure[Script_Logical_Name:00000A006AFF; Script_Selector:1; ]structure[Script_Logical_Name:00000A006AFF; Script_Selector:2; ]] 
			String command="00 01 00 01 00 01 00 29 C1 01 C8 00 47 00 00 11 00 00 FF 0B 00 02 02 02 02 09 06 00 00 0A 00 6A FF 12 00 01 02 02 09 06 00 00 0A 00 6A FF 12 00 02";
			LogUtils.appendLoggerDetails(logger,className,methodName,"command :: "+command+" ");
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					String response=tcpCommObj.receiveBytes(in, sleepSeconds,masterDataObj.getMeterNo(),logger);
					String[] responseArr=response.split("\\ ");
					LogUtils.appendLoggerDetails(logger,className,methodName,"response :: "+response+" ");

					if(responseArr[11].equalsIgnoreCase("00")){
						setStatus=true;
						LogUtils.appendLoggerDetails(logger,className,methodName," Success :: "+response+" ");
						break;
					}else{
						trailCount++;
						LogUtils.appendLoggerDetails(logger,className,methodName,"Failed");
					}
					
					if (trailCount > 4) {
						break;
					}
				} catch (Exception e) {
					trailCount++;
				}
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return setStatus;
	}
	
	
	
	public boolean setAllLimiterControllers(DataInputStream in,
			DataOutputStream toClient, Integer sleepSeconds, LogUtils logger,
			Masterdata masterDataObj) {
//		String methodName="setAllStatusWordReset";
		boolean status=false;
		
		try{
			
			if(setClassIdLogicalNameAttributeIndex(in, toClient, sleepSeconds, logger, masterDataObj)){
			if(setThreashHoldNormal(in, toClient, sleepSeconds, logger, masterDataObj)){
			if(setThreashHoldEmergency(in, toClient, sleepSeconds, logger, masterDataObj)){
			if(setMinOverThresholdDuration(in, toClient, sleepSeconds, logger, masterDataObj)){
			if(setMinUnderThresholdDuration(in, toClient, sleepSeconds, logger, masterDataObj)){
			if(setEmergencyProfileActivationTimeANdDuration(in, toClient, sleepSeconds, logger, masterDataObj)){
			if(setEmergencyProfileGroupIdList(in, toClient, sleepSeconds, logger, masterDataObj)){
			if(setScriptLogicalName1And2Selector1And2(in, toClient, sleepSeconds, logger, masterDataObj)){
				status=true;
			}}}}}}}}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
