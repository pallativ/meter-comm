package com.hes.lis.mltd.process.get;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.FrameUtils;
import com.hes.lis.mltd.utils.LogUtils;

public class BillingRelatedGET {
	
	private static String className="BillingReletedGET";
	
	private static CommunicationUtils tcpCommObj=new CommunicationUtils();

	public String getMeterForgivenessTime(DataInputStream in,DataOutputStream toClient,Integer sleepSeconds,LogUtils logger,String meterNumber){
		String methodName="getMeterForgivenessTime";
		String seconds=null;
		try{
			try{
				String command="00 01 00 01 00 01 00 0D C0 01 C3 00 03 01 00 85 82 00 FF 02 00";//0-0:96.1.137.255
				tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
				String response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
				String[] responseArr=response.split("\\ ");
				if(responseArr[11].equalsIgnoreCase("00")){
					seconds=responseArr[13]+responseArr[14];
					seconds=Integer.parseInt(seconds,16)+"";  
					LogUtils.appendLoggerDetails(logger,className,methodName,"getMeterForgivenessTime :: "+response+" ");
				}else{
					LogUtils.appendLoggerDetails(logger,className,methodName,"getMeterForgivenessTime NCK");
				}
			}catch (Exception e) {
				LogUtils.appendLoggerDetails(logger,className,methodName,"Exception in Fetching getMeterForgivenessTime  "+e.getMessage());
				seconds=null;
//				e.printStackTrace();
			}finally{
				LogUtils.appendLoggerDetails(logger,className,methodName,"getMeterForgivenessTime is  :: "+seconds);
			}

		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
		return seconds;
	}
	
	
	public String fetchMdResetDate(DataInputStream in,
			DataOutputStream toClient, 
			Integer sleepSeconds,  String meterNumber,LogUtils logger) throws Exception{
		String methodName="fetchMdResetDate";
		String response=null;
		try{   													   
			String command="00 01 00 01 00 01 00 0D C0 01 C1 00 16 00 00 0F 00 00 FF 04 00";
			tcpCommObj.sendBytes(toClient,sleepSeconds, command,logger);
			String responseData=tcpCommObj.receiveBytes(in,sleepSeconds,meterNumber,logger);
			LogUtils.appendLoggerDetails(logger,className,methodName, "fetchMdResetDate Res  : "+responseData);
			String[] responseArr=responseData.split("\\ ");
			if(responseArr[11].equalsIgnoreCase("00")){
//				response=responseArr[responseArr.length-1];
				Integer mdResetDate=Integer.parseInt(responseArr[responseArr.length-2],16);
				if(mdResetDate!=null){
					response=mdResetDate+"";
				}
//				response=fetchStringFromResponse(response);
				LogUtils.appendLoggerDetails(logger,className,methodName,"fetchMdResetDate :: "+response);
			}else{
				LogUtils.appendLoggerDetails(logger,className,methodName,"fetchMdResetDate Failed");
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception in Fecthing fetchMdResetDate "+e.getMessage());
			throw e;
		}
		return response;
	}
	
	
	public String fetchApprovedDemand(DataInputStream in,
			DataOutputStream toClient, 
			Integer sleepSeconds,  String meterNumber,LogUtils logger) throws Exception{
		String methodName="fetchApprovedDemand";
		String response=null;
		try{   													   
			String command="00 01 00 01 00 01 00 0D C0 01 C1 00 47 00 00 11 00 00 FF 04 00";
			tcpCommObj.sendBytes(toClient,sleepSeconds, command,logger);
			String responseData=tcpCommObj.receiveBytes(in,sleepSeconds,meterNumber,logger);
			LogUtils.appendLoggerDetails(logger,className,methodName, "fetchApprovedDemand RESPONSE  : "+responseData);
			String[] responseArr=responseData.split("\\ ");
			if(responseArr[11].equalsIgnoreCase("00")){
//				response=responseArr[13];
				Integer appDemand=FrameUtils.fetchIntFromResponse(responseData);
				if(appDemand!=null){
					appDemand=appDemand/1000;
					response=appDemand+"";
				}
				LogUtils.appendLoggerDetails(logger,className,methodName,"fetchApprovedDemand :: "+appDemand);
			}else{
				LogUtils.appendLoggerDetails(logger,className,methodName,"fetchApprovedDemand Failed");
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception in Fecthing fetchApprovedDemand "+e.getMessage());
			throw e;
		}
		return response;
	}
	
	public String fetchDemandIntegrationPeriod(DataInputStream in,
			DataOutputStream toClient, 
			Integer sleepSeconds,  String meterNumber,LogUtils logger) throws Exception{
		String methodName="fetchDemandIntegrationPeriod";
		String response=null;
		try{   													   
			String command="00 01 00 01 00 01 00 0D C0 01 C1 00 05 01 00 01 04 00 FF 08 00";
			tcpCommObj.sendBytes(toClient,sleepSeconds, command,logger);
			String responseData=tcpCommObj.receiveBytes(in,sleepSeconds,meterNumber,logger);
			LogUtils.appendLoggerDetails(logger,className,methodName, "fetchDemandIntegrationPeriod RESPONSE  : "+responseData);
			String[] responseArr=responseData.split("\\ ");
			if(responseArr[11].equalsIgnoreCase("00")){
//				response=responseArr[13];
				Integer demandIntPeriod=FrameUtils.fetchIntFromResponse(responseData);
				if(demandIntPeriod!=null){
					response=demandIntPeriod+"";
				}
//				response=fetchStringFromResponse(response);
				LogUtils.appendLoggerDetails(logger,className,methodName,"fetchDemandIntegrationPeriod :: "+demandIntPeriod);
			}else{
				LogUtils.appendLoggerDetails(logger,className,methodName,"fetchDemandIntegrationPeriod Failed");
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception in Fecthing fetchDemandIntegrationPeriod "+e.getMessage());
			throw e;
		}
		return response;
	}
	
	public String fetchLoadProfileCapturePeriod(DataInputStream in,
			DataOutputStream toClient, 
			Integer sleepSeconds,  String meterNumber,LogUtils logger) throws Exception{
		String methodName="fetchLoadProfileCapturePeriod";
		String response=null;
		try{   		
			String command="00 01 00 01 00 01 00 0D C0 01 C1 00 07 01 00 63 01 00 FF 04 00";
			tcpCommObj.sendBytes(toClient,sleepSeconds, command,logger);
			String responseData=tcpCommObj.receiveBytes(in,sleepSeconds,meterNumber,logger);
			LogUtils.appendLoggerDetails(logger,className,methodName, "fetchLoadProfileCapturePeriod1 Res  : "+responseData);
			String[] responseArr=responseData.split("\\ ");
			if(responseArr[11].equalsIgnoreCase("00")){
				Integer profileCapPeriod1=FrameUtils.fetchIntFromResponse(responseData);
				LogUtils.appendLoggerDetails(logger,className,methodName,"fetchLoadProfileCapturePeriod1 ACK :: "+profileCapPeriod1);
				
				command="00 01 00 01 00 01 00 0D C0 01 C1 00 07 01 00 63 88 00 FF 04 00";
				tcpCommObj.sendBytes(toClient,sleepSeconds, command,logger);
				responseData=tcpCommObj.receiveBytes(in,sleepSeconds,meterNumber,logger);
				LogUtils.appendLoggerDetails(logger,className,methodName, "fetchLoadProfileCapturePeriod7 Res  : "+responseData);
				responseArr=responseData.split("\\ ");
				if(responseArr[11].equalsIgnoreCase("00")){
					Integer profileCapPeriod7=FrameUtils.fetchIntFromResponse(responseData);
					LogUtils.appendLoggerDetails(logger,className,methodName,"fetchLoadProfileCapturePeriod7 :: "+profileCapPeriod7);
					
					if(profileCapPeriod7!=null){
						response=profileCapPeriod7+"";
					}
				}
				LogUtils.appendLoggerDetails(logger,className,methodName,"fetchLoadProfileCapturePeriod :: "+response);
			}else{
				LogUtils.appendLoggerDetails(logger,className,methodName,"fetchLoadProfileCapturePeriod Failed");
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception in Fecthing fetchLoadProfileCapturePeriod "+e.getMessage());
			throw e;
		}
		return response;
	}
	
	
	public String fetchTreshHoldValue(DataInputStream in,
			DataOutputStream toClient, 
			Integer sleepSeconds,  String meterNumber,LogUtils logger) throws Exception{
		String methodName="fetchTreshHoldValue";
		String response=null;
		try{   	
			
			String scalarCommand="00 01 00 01 00 01 00 0D C0 01 C1 00 03 01 00 01 23 85 FF 03 00";//SCALAR COMMAND
			LogUtils.appendLoggerDetails(logger,className,methodName,"fetchTreshHoldValue Scalar command :: "+scalarCommand+" ");
			
			tcpCommObj.sendBytes(toClient,  sleepSeconds, scalarCommand,logger);
			String responseData=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
			String[] responseArr=responseData.split("\\ ");
			LogUtils.appendLoggerDetails(logger,className,methodName,"fetchTreshHoldValue Scalar command response :: "+responseData+" ");
			
			if(responseArr[11].equalsIgnoreCase("00")){
				Double scalarValue=0.0;
				
				if(responseArr[15].equalsIgnoreCase("00")){
					scalarValue=1.0;
				}else if(responseArr[15].equalsIgnoreCase("FF")){
					scalarValue=0.1;
				}else if(responseArr[15].equalsIgnoreCase("FE")){
					scalarValue=0.01;
				}else if(responseArr[15].equalsIgnoreCase("FD")){
					scalarValue=0.001;
				}else if(responseArr[15].equalsIgnoreCase("FC")){
					scalarValue=0.0001;
				}else if(responseArr[15].equalsIgnoreCase("FB")){
					scalarValue=0.00001;
				}else if(responseArr[15].equalsIgnoreCase("FA")){
					scalarValue=0.000001;
				}else if(responseArr[15].equalsIgnoreCase("F9")){
					scalarValue=0.0000001;
				}else{
					scalarValue=Math.pow(10, Integer.parseInt(responseArr[0], 16));
				}
				
				String command="00 01 00 01 00 01 00 0D C0 01 C1 00 03 01 00 01 23 85 FF 02 00";
				tcpCommObj.sendBytes(toClient,sleepSeconds, command,logger);
				responseData=tcpCommObj.receiveBytes(in,sleepSeconds,meterNumber,logger);
				LogUtils.appendLoggerDetails(logger,className,methodName, "fetchTreshHoldValue command Res  : "+responseData);
				responseArr=responseData.split("\\ ");
				if(responseArr[11].equalsIgnoreCase("00")){
					Integer treshVal=FrameUtils.fetchIntFromResponse(responseData);
					response=(treshVal*scalarValue)+"";
					LogUtils.appendLoggerDetails(logger,className,methodName,"fetchTreshHoldValue command :: "+response);
				}
				
			}else{
				LogUtils.appendLoggerDetails(logger,className,methodName,"fetchTreshHoldValue Scalar command response Failed");
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception in Fecthing fetchLoadProfileCapturePeriod "+e.getMessage());
			throw e;
		}
		return response;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
