package com.hes.lis.mltd.process.get;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.FrameUtils;
import com.hes.lis.mltd.utils.LogUtils;

public class ConsumerDetailsGET {
	
	private static String className="ConsumerDetailsGET";
	
	private static CommunicationUtils tcpCommObj=new CommunicationUtils();

	public String getConsumerName(DataInputStream in,
			DataOutputStream toClient, 
			Integer sleepSeconds,  String meterNumber,LogUtils logger) throws Exception{
		String methodName="getConsumerName";
		String response=null;
		
		try{					
			String command="00 01 00 01 00 01 00 0D C0 01 C1 00 01 00 00 60 01 02 FF 02 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"getConsumerName REQ :: "+command);
			tcpCommObj.sendBytes(toClient,sleepSeconds, command,logger);
			String responseData=tcpCommObj.receiveBytes(in,sleepSeconds,meterNumber,logger);
			LogUtils.appendLoggerDetails(logger,className,methodName,"getConsumerName RES :: "+responseData);
			String[] responseArr=responseData.split("\\ ");
			if(responseArr[11].equalsIgnoreCase("00")){
				response=responseData;
				response=FrameUtils.fetchStringFromResponse(response);
				LogUtils.appendLoggerDetails(logger,className,methodName,"getConsumerName ACK :: "+response);
			}else{
				LogUtils.appendLoggerDetails(logger,className,methodName,"getConsumerName NCK");
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception in Fecthing getConsumerName"+e.getMessage());
			throw e;
		}
		return response;
	}
	
	public String getConsumerNumber(DataInputStream in,
			DataOutputStream toClient, 
			Integer sleepSeconds,  String meterNumber,LogUtils logger) throws Exception{
		String methodName="getConsumerNumber";
		String response=null;
		
		try{		
			
			String command="00 01 00 01 00 01 00 0D C0 01 C1 00 01 00 00 60 01 01 FF 02 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"getConsumerNumber REQ :: "+command);
			tcpCommObj.sendBytes(toClient,sleepSeconds, command,logger);
			String responseData=tcpCommObj.receiveBytes(in,sleepSeconds,meterNumber,logger);
			LogUtils.appendLoggerDetails(logger,className,methodName,"getConsumerNumber RES :: "+responseData);
			String[] responseArr=responseData.split("\\ ");
			if(responseArr[11].equalsIgnoreCase("00")){
				response=responseData;
				response=FrameUtils.fetchStringFromResponse(response);
				LogUtils.appendLoggerDetails(logger,className,methodName,"getConsumerNumber ACK :: "+response);
			}else{
				LogUtils.appendLoggerDetails(logger,className,methodName,"getConsumerNumber NCK");
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception in Fecthing getConsumerNumber"+e.getMessage());
			throw e;
		}
		return response;
	}
	
	public String getConsumerId(DataInputStream in, DataOutputStream toClient, 
			Integer sleepSeconds,  String meterNumber,LogUtils logger) throws Exception{
		String methodName="getConsumerId";
		String response=null;
		
		try{		
			String command="00 01 00 01 00 01 00 0D C0 01 C1 00 01 00 00 60 01 02 FF 02 00";
			LogUtils.appendLoggerDetails(logger,className,methodName,"getConsumerId REQ :: "+command);
			tcpCommObj.sendBytes(toClient,sleepSeconds, command,logger);
			String responseData=tcpCommObj.receiveBytes(in,sleepSeconds,meterNumber,logger);
			LogUtils.appendLoggerDetails(logger,className,methodName,"getConsumerId RES :: "+responseData);
			String[] responseArr=responseData.split("\\ ");
			if(responseArr[11].equalsIgnoreCase("00")){
				response=responseData;
				response=FrameUtils.fetchStringFromResponse(response);
				LogUtils.appendLoggerDetails(logger,className,methodName,"getConsumerId ACK :: "+response);
			}else{
				LogUtils.appendLoggerDetails(logger,className,methodName,"getConsumerId NCK "+responseData);
			}
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName,"Exception in Fecthing getConsumerId"+e.getMessage());
			throw e;
		}
		return response;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
