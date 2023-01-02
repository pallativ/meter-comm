package com.hes.lis.mltd.process;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.hes.lis.mltd.utils.CommunicationUtils;
import com.hes.lis.mltd.utils.LogUtils;


/**
 * 
 * @author psvr
 *
 */
public class MeterAuthentications {
	
	private static String className="MeterAuthentications";
	
	private static CommunicationUtils tcpCommObj =new CommunicationUtils();
	
	public boolean lowLevelPasswordAuthentication(DataInputStream in,DataOutputStream toClient,Integer sleepSeconds,String meterNumber,LogUtils logger){
		String methodName="lowLevelPasswordAuthentication";
		boolean isAuthenticated=false;
		String response=null;
		try{
			String command="00 01 00 01 00 01 00 48 60 46 80 02 07 80 A1 09 06 07 60 85 74 05 08 01 01 A6 0A 04 08 " +
					  	   "48 45 43 00 05 00 00 01 8A 02 07 80 8B 07 60 85 74 05 08 02 01 AC 0A 80 08 30 30 30 30 " +
					  	   "30 30 30 30 BE 10 04 0E 01 00 00 00 06 5F 1F 04 FF FF FF FF FF FF";
			
			LogUtils.appendLoggerDetails(logger,className,methodName,"INITIAL PASSWORD COMMAND :: "+command+" ");
			tcpCommObj.sendBytes(toClient, sleepSeconds, command,logger);
			response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
			LogUtils.appendLoggerDetails(logger,className,methodName,"INITIAL PASSWORD RESPONSE :: "+response+" ");
			String[] responseArr=response.split("\\ ");
			if(responseArr[25].equalsIgnoreCase("00")){
				isAuthenticated=true;
			}
			
		}catch (Exception e) {
			LogUtils.appendLoggerDetails(logger,className,methodName, "INITIAL PASSWORD AUTHENTICATION CAUGHT EXCEPTION :: "+e.getMessage());
		}finally{
			LogUtils.appendLoggerDetails(logger,className,methodName, "INITIAL PASSWORD AUTHENTICATION STATUS :: "+isAuthenticated);
		}
		
		return isAuthenticated;
	}
	
	public boolean highLevelPasswordAuthentication(DataInputStream in,DataOutputStream toClient,Integer sleepSeconds,String meterNumber,LogUtils logger){
		String methodName="highLevelPasswordAuthentication";
		boolean isAuthenticated=false;
		String response=null;
		try{
			String command="00 01 00 01 00 01 00 3C 60 3A 80 02 07 80 A1 09 06 07 60 85 74 05 08 01 01 8A 02 07 80 "+ 
					"8B 07 60 85 74 05 08 02 01 AC 0A 80 08 30 30 30 30 30 30 30 30 BE 10 04 0E 01 00 00 00 "+ 
					"06 5F 1F 04 FF FF FF FF FF FF";
			
			Integer trailCount = 0;
			for(Integer i=0;i<5;i++){
				try {
					tcpCommObj.sendBytes(toClient,  sleepSeconds, command,logger);
					response=tcpCommObj.receiveBytes(in, sleepSeconds,meterNumber,logger);
					LogUtils.appendLoggerDetails(logger,className,methodName,"setServerDateTimeToMeter :: "+response+" ");
					String[] responseArr=response.split("\\ ");

					if(responseArr[25].equalsIgnoreCase("00")){
						isAuthenticated=true;
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
			LogUtils.appendLoggerDetails(logger,className,methodName, "HIGH LEVEL PASSWORD CAUGHT EXCEPTION :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			LogUtils.appendLoggerDetails(logger,className,methodName, "HIGH LEVEL PASSWORD AUTHENTICATION STATUS :: "+isAuthenticated);
		}
		return isAuthenticated;
	}
	
}
