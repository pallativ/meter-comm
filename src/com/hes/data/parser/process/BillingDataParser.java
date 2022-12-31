package com.hes.data.parser.process;

import java.io.File;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.hes.data.parser.dao.MeterRuleEngineDAO;
import com.hes.data.parser.utils.HexToStringUtils;
import com.hes.data.parser.utils.Utils;
import com.hes.data.parser.vo.BillingData;
import com.hes.data.parser.vo.BillingDataId;

import javassist.bytecode.stackmap.BasicBlock.Catch;

public class BillingDataParser {
	
	
	
	public List<String> processBillingObisData(String obisData){
		List<String> list=new ArrayList<String>();
		try{
			
			String[] framesArr=obisData.split("\n");
			String[] strArr=framesArr[0].split(" ");
			
			Integer noOfIntervals=0;// No Of data objects
			Integer noOfDataObjects=0;//noOfDataObjects
			String dataEleStr="";
			for(Integer cnt=0;cnt<framesArr.length;cnt++) {
				Integer pointer=0;
				strArr=framesArr[cnt].split(" ");
				try {
					if(cnt==0) {
//						String wrapperVersion=strArr[0]+strArr[1];// Wrapper Version
//						String destAddress=strArr[2]+strArr[3];// destination address
//						String srcAddress=strArr[4]+strArr[5];// Source address
//						String lengthOfFrame=strArr[6]+strArr[7];// length of frame
//						String readActionResponse=strArr[8]+strArr[9];// Read action response
//						String invokeIdAndPriority=strArr[10];//Invoke ID and priority
//						String timeOutFlag=strArr[11];//Invoke ID and priority
//						String noOfObjects=strArr[12];// CCCCCC?????????
						
						if(strArr[20].equalsIgnoreCase("01")&&strArr[22].equalsIgnoreCase("02")) {
							noOfIntervals=Integer.decode("0x"+strArr[21]);// No Of data objects
							noOfDataObjects=Integer.decode("0x"+strArr[23]);//noOfDataObjects
						}
						pointer=24;
					}else {
//						pointer=22;
					}
					
					for(Integer y=0;y<noOfIntervals;y++) {//No of intervals parsing
						try {
							
							StringBuilder strb=new StringBuilder();
							
							if(y>0) {
								pointer=pointer+2;
							}
							for(Integer z=0;z<noOfDataObjects;z++) {//No of noOfDataObjects parsing
								try {
									String strArrPointerVal=strArr[pointer];
									dataEleStr="";
									try {
										
										if(strArr[pointer].equalsIgnoreCase("FF")&&strArr[pointer+1].equalsIgnoreCase("FF")&&((strArr.length==pointer+2))) {//LAST
											strArr=framesArr[++cnt].split(" ");
											pointer=20;
											if(!((strArr[0]+strArr[1]+strArr[2]+strArr[3]).equalsIgnoreCase("00010001"))) {
												strArr=framesArr[++cnt].split(" ");
												
//												continue;
											}
											
											strArrPointerVal=strArr[pointer];
										}else if (strArr[pointer+1].equalsIgnoreCase("FF")&&strArr[pointer+2].equalsIgnoreCase("FF")&&((strArr.length==pointer+3))) {
											strArrPointerVal=strArr[pointer];
											strArr=framesArr[++cnt].split(" ");
											pointer=19;
											if(!((strArr[0]+strArr[1]+strArr[2]+strArr[3]).equalsIgnoreCase("00010001"))) {
												strArr=framesArr[++cnt].split(" ");
//												pointer=20;
//												continue;
											}
											
											
										}
									}catch (Exception e) {
										e.printStackTrace();
									}
									
									if((strArrPointerVal.equalsIgnoreCase("05"))){
										Integer noOfDigits=4;
										if(pointer+noOfDigits>strArr.length-1-2) {
											Integer k=strArr.length-1-2-pointer;
											for(Integer j=0;j<k;j++){
												dataEleStr+=strArr[++pointer]+" ";
												noOfDigits--;
											}
											strArr=framesArr[++cnt].split(" ");
											if(noOfDigits==1) {//if last 3 digits
												pointer=17;
											}else {//if last 2 digits
												pointer=19;
											}
										}
										
										for(Integer j=0;j<noOfDigits;j++){
											dataEleStr+=strArr[++pointer]+" ";
										}
										pointer++;
										dataEleStr=Integer.parseInt(dataEleStr.replace(" ", ""), 16)+"";
									}else if((strArrPointerVal.equalsIgnoreCase("06"))){
										Integer noOfDigits=4;
										if(pointer+noOfDigits>strArr.length-1-2) {
											Integer k=strArr.length-1-2-pointer;
											for(Integer j=0;j<k;j++){
												dataEleStr+=strArr[++pointer]+" ";
												noOfDigits--;
											}
											strArr=framesArr[++cnt].split(" ");
											if(noOfDigits==1) {//if last 3 digits
												pointer=17;
											}else {//if last 2 digits
												pointer=19;
											}
											
										}
										for(Integer j=0;j<noOfDigits;j++){
											dataEleStr+=strArr[++pointer]+" ";
										}
										pointer++;
										dataEleStr=Integer.parseInt(dataEleStr.replace(" ", ""), 16)+"";
									}else if(strArrPointerVal.equalsIgnoreCase("09")){
										Integer noOfDigits=Integer.decode("0x"+strArr[++pointer]);
										if(pointer+noOfDigits>strArr.length-1-2) {
											Integer k=strArr.length-1-2-pointer;
											for(Integer j=0;j<k;j++){
												dataEleStr+=strArr[++pointer]+" ";
												noOfDigits--;
											}
											strArr=framesArr[++cnt].split(" ");
											if(noOfDigits==1) {//if last 3 digits
												pointer=17;
											}else {//if last 2 digits
												pointer=19;
											}
										}
										for(Integer j=0;j<noOfDigits;j++){
											dataEleStr+=strArr[++pointer]+" ";
										}
										pointer++;
										if(noOfDigits==8){
											dataEleStr=HexToStringUtils.unHex(dataEleStr);
										}else{
											dataEleStr=HexToStringUtils.convertDateTime(dataEleStr);
										}
										
										
									}else if(strArrPointerVal.equalsIgnoreCase("10")){
										Integer noOfDigits=2;
										if(pointer+noOfDigits>strArr.length-1-2) {
											Integer k=strArr.length-1-2-pointer;
											for(Integer j=0;j<k;j++){
												dataEleStr+=strArr[++pointer]+" ";
												noOfDigits--;
											}
											strArr=framesArr[++cnt].split(" ");
											if(noOfDigits==1) {//if last 3 digits
												pointer=17;
											}else {//if last 2 digits
												pointer=19;
											}
										}
										for(Integer j=0;j<noOfDigits;j++){
											dataEleStr+=strArr[++pointer]+" ";
										}
										pointer++;
										dataEleStr=Integer.parseInt(dataEleStr.replace(" ", ""), 16)+"";
									}else if(strArrPointerVal.equalsIgnoreCase("11")){
										Integer noOfDigits=1;
										if(pointer+noOfDigits>strArr.length-1-2) {
											Integer k=strArr.length-1-2-pointer;
											for(Integer j=0;j<k;j++){
												dataEleStr+=strArr[++pointer]+" ";
												noOfDigits--;
											}
											strArr=framesArr[++cnt].split(" ");
											if(noOfDigits==1) {//if last 3 digits
												pointer=17;
											}else {//if last 2 digits
												pointer=19;
											}
										}
										for(Integer j=0;j<noOfDigits;j++){
											dataEleStr+=strArr[++pointer]+" ";
										}
										pointer++;
										dataEleStr=Integer.parseInt(dataEleStr.replace(" ", ""), 16)+"";
									}else if(strArrPointerVal.equalsIgnoreCase("12")){
										Integer noOfDigits=2;
										if(pointer+noOfDigits>strArr.length-1-2) {
											Integer k=strArr.length-1-2-pointer;
											for(Integer j=0;j<k;j++){
												dataEleStr+=strArr[++pointer]+" ";
												noOfDigits--;
											}
											strArr=framesArr[++cnt].split(" ");
											if(noOfDigits==1) {//if last 3 digits
												pointer=17;
											}else {//if last 2 digits
												pointer=19;
											}
										}
										for(Integer j=0;j<noOfDigits;j++){
											dataEleStr+=strArr[++pointer]+" ";
										}
										pointer++;
										dataEleStr=Integer.parseInt(dataEleStr.replace(" ", ""), 16)+"";
									}else if(strArrPointerVal.equalsIgnoreCase("0F")){
										Integer noOfDigits=1;
										if(pointer+noOfDigits>strArr.length-1-2) {
											Integer k=strArr.length-1-2-pointer;
											for(Integer j=0;j<k;j++){
												dataEleStr+=strArr[++pointer]+" ";
												noOfDigits--;
											}
											strArr=framesArr[++cnt].split(" ");
											if(noOfDigits==1) {//if last 3 digits
												pointer=17;
											}else {//if last 2 digits
												pointer=19;
											}
										}
										for(Integer j=0;j<noOfDigits;j++){
											dataEleStr+=strArr[++pointer]+" ";
										}
										pointer++;
										dataEleStr=Integer.parseInt(dataEleStr.replace(" ", ""), 16)+"";
									}
									
									
									System.out.println(dataEleStr+",");
									System.out.println(" ==> pointer : "+pointer);
									strb.append(dataEleStr+",");
									
								}catch (Exception e) {
									e.printStackTrace();
								}
							}
							list.add(strb.toString());
							System.out.println(y+"==>"+strb.toString());
							
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public String processBillingData(List<String> obisHeaderList,List<String> dataList,String watchFolderPath,String meterSerialNumber){
		String status="false";
		try{
			Map<String,String> scalarprop=new Utils().readProperties("scalar.MDV");
	        DecimalFormat df = new DecimalFormat("#####.##");

			
			for(String str:dataList){
				
				try{
					BillingData billingData=new BillingData();
					BillingDataId id=new BillingDataId();
//					id.setInstantDatetime(instantDatetime);
//					id.setMeterSerialNumber(meterSerialNumber);
					
					String[] strArr=str.split("\\,");
					
					for(Integer i=0;i<strArr.length;i++){
						String dataVal=strArr[i];
						String obisCode=obisHeaderList.get(i);
						
						
						String scalarVal=scalarprop.get(obisCode.toUpperCase());
						
						try {
							if(scalarVal!=null){
								Double sclVal=Double.parseDouble(scalarVal);
								dataVal=df.format(Double.parseDouble(dataVal)*sclVal)+"";
//								dataVal=(Double.parseDouble(dataVal)*sclVal)+"";
							}
						}catch (Exception e) {
							// TODO: handle exception
						}
						
						System.out.println("obisCode :: "+obisCode +" ==> dataVal :: "+dataVal);
						
						
						if(obisCode.equalsIgnoreCase("00 00 01 00 00 FF")){//clock
							try{
								Date instantDatetime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataVal);
								id.setBillingDatetime(new Timestamp(instantDatetime.getTime()));
							}catch (Exception e) {
								e.printStackTrace();
							}
						}else if(obisCode.equalsIgnoreCase("01 00 01 08 00 FF")){//Active energy import (+A)
							billingData.setActEgyImp(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 02 08 00 FF")){//Active energy export (-A)
							billingData.setActEgyExp(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 0F 08 00 FF")){//Active energy (|+A|+|-A|) Combined total
							billingData.setActEgyCombinedTotal(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 10 08 00 FF")){//Active energy (|+A|-|-A|) Net total
							billingData.setActEgyNetTotal(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 09 08 00 FF")){//Apparent energy import (+VA) (QI+QIV)
							billingData.setAppEgyImp(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 0A 08 00 FF")){//Apparent energy export (-VA) (QII+QIII)
							billingData.setAppEgyExp(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 01 08 01 FF")){//Active energy import (+A) rate 1
							billingData.setActEgyImpRt1(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 01 08 02 FF")){//Active energy import (+A) rate 2
							billingData.setActEgyImpRt2(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 01 08 03 FF")){//Active energy import (+A) rate 3
							billingData.setActEgyImpRt3(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 01 08 04 FF")){//Active energy import (+A) rate 4
							billingData.setActEgyImpRt4(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 02 08 01 FF")){//Active energy export (-A) rate 1
							billingData.setActEgyExpRt1(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 02 08 02 FF")){//Active energy export (-A) rate 2
							billingData.setActEgyExpRt2(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 02 08 03 FF")){//Active energy export (-A) rate 3
							billingData.setActEgyExpRt3(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 02 08 04 FF")){//Active energy export (-A) rate 4
							billingData.setActEgyExpRt4(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 09 08 01 FF")){//Apparent energy import (+VA) rate 1
							billingData.setAppEgyImpRt1(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 09 08 02 FF")){//Apparent energy import (+VA) rate 2
							billingData.setAppEgyImpRt2(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 09 08 03 FF")){//Apparent energy import (+VA) rate 3
							billingData.setAppEgyImpRt3(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 09 08 04 FF")){//Apparent energy import (+VA) rate 4
							billingData.setAppEgyImpRt4(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 0A 08 01 FF")){//Apparent energy export (-VA) rate 1
							billingData.setAppEgyExpRt1(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 0A 08 02 FF")){//Apparent energy export (-VA) rate 2
							billingData.setAppEgyExpRt2(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 0A 08 03 FF")){//Apparent energy export (-VA) rate 3
							billingData.setAppEgyExpRt3(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 0A 08 04 FF")){//Apparent energy export (-VA) rate 4
							billingData.setAppEgyExpRt4(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 0F 08 01 FF")){//Active energy Combined total (|+A|+|-A|) rate 1
							billingData.setActEgyCombTotRt1(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 0F 08 02 FF")){//Active energy Combined total (|+A|+|-A|) rate 2
							billingData.setActEgyCombTotRt2(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 0F 08 03 FF")){//Active energy Combined total (|+A|+|-A|) rate 3
							billingData.setActEgyCombTotRt3(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 0F 08 04 FF")){//Active energy Combined total (|+A|+|-A|) rate 4
							billingData.setActEgyCombTotRt4(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 10 08 01 FF")){//Active energy net total (|+A|-|-A|) rate 1
							billingData.setActEgyNetTotRt1(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 10 08 02 FF")){//Active energy net total (|+A|-|-A|) rate 2
							billingData.setActEgyNetTotRt2(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 10 08 03 FF")){//Active energy net total (|+A|-|-A|) rate 3
							billingData.setActEgyNetTotRt3(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 10 08 04 FF")){//Active energy net total (|+A|-|-A|) rate 4
							billingData.setActEgyNetTotRt4(Double.parseDouble(dataVal));
						}
						else if(obisCode.equalsIgnoreCase("01 00 01 06 00 FF")){//Maximum Demand Register 1 -Active energy import (+A)
							if(billingData.getMaxDmdReg1ActEgyImp()!=null) {
								try{
									Date dateTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataVal);
									billingData.setMaxDmdReg1ActEgyImpTime(new Timestamp(dateTime.getTime()));
								}catch (Exception e) {
									e.printStackTrace();
								}
							}else {
								billingData.setMaxDmdReg1ActEgyImp(Double.parseDouble(dataVal));
							}
							
						}
						else if(obisCode.equalsIgnoreCase("01 00 02 06 00 FF")){//Maximum Demand Register 6 -Active energy export (-A)
							if(billingData.getMaxDmdReg6ActEgyExp()!=null) {
								try{
									Date dateTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataVal);
									billingData.setMaxDmdReg6ActEgyExpTime(new Timestamp(dateTime.getTime()));
								}catch (Exception e) {
									e.printStackTrace();
								}
							}else {
								billingData.setMaxDmdReg6ActEgyExp(Double.parseDouble(dataVal));
							}
						}
						else if(obisCode.equalsIgnoreCase("01 00 09 06 00 FF")){//Apparent MD (+) 
							if(billingData.getAppMdImp()!=null) {
								try{
									Date dateTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataVal);
									billingData.setAppMdImpOccurringTime(new Timestamp(dateTime.getTime()));
								}catch (Exception e) {
									e.printStackTrace();
								}
							}else {
								billingData.setAppMdImp(Double.parseDouble(dataVal));
							}
						}
						else if(obisCode.equalsIgnoreCase("01 00 0A 06 00 FF")){//Apparent MD (-) 
							if(billingData.getAppMdExp()!=null) {
								try{
									Date dateTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataVal);
									billingData.setAppMdExpOccurringTime(new Timestamp(dateTime.getTime()));
								}catch (Exception e) {
									e.printStackTrace();
								}
							}else {
								billingData.setAppMdExp(Double.parseDouble(dataVal));
							}
						}
					}
					
					id.setMeterSerialNumber(meterSerialNumber);
					billingData.setId(id);
					
					status=new MeterRuleEngineDAO().saveObject(billingData);
					
					logData(billingData, status, watchFolderPath);
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	public void logData(BillingData billingData,String status,String watchFolderPath) {
		
		try {
			
			String todayDate=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			
			File successFile=new File(watchFolderPath,"PROCESSED");
			if(!successFile.exists())
				successFile.mkdirs();
			successFile=new File(successFile,todayDate+".MDV");
			
			File failedFile=new File(watchFolderPath,"FAILED");
			if(!failedFile.exists())
				failedFile.mkdirs();
			failedFile=new File(failedFile,todayDate+".MDV");
			
			
			if(status.equalsIgnoreCase("true")) {
				FileUtils.writeStringToFile(successFile, billingData.toString()+"\n",true);
			}else if(status.equalsIgnoreCase("false")) {
				FileUtils.writeStringToFile(failedFile, billingData.toString()+"\n",true);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {

		try{
//			String obisHeader="00 01 00 01 00 01 02 c6 c4 01 c1 00 01 27 02 04 12 00 08 09 06 00 00 01 00 00 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 00 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 02 08 00 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 0f 08 00 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 10 08 00 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 09 08 00 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 0a 08 00 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 01 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 02 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 03 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 04 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 02 08 01 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 02 08 02 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 02 08 03 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 02 08 04 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 09 08 01 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 09 08 02 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 09 08 03 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 09 08 04 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 0a 08 01 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 0a 08 02 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 0a 08 03 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 0a 08 04 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 0f 08 01 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 0f 08 02 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 0f 08 03 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 0f 08 04 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 10 08 01 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 10 08 02 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 10 08 03 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 10 08 04 ff 0f 02 12 00 00 02 04 12 00 04 09 06 01 00 01 06 00 ff 0f 02 12 00 00 02 04 12 00 04 09 06 01 00 01 06 00 ff 0f 05 12 00 00 02 04 12 00 04 09 06 01 00 02 06 00 ff 0f 02 12 00 00 02 04 12 00 04 09 06 01 00 02 06 00 ff 0f 05 12 00 00 02 04 12 00 04 09 06 01 00 09 06 00 ff 0f 02 12 00 00 02 04 12 00 04 09 06 01 00 09 06 00 ff 0f 05 12 00 00 02 04 12 00 04 09 06 01 00 0a 06 00 ff 0f 02 12 00 00 02 04 12 00 04 09 06 01 00 0a 06 00 ff 0f 05 12 00 00 ff ff";
//			List<String> obisHeaderList=new DataProcessorAction().processObisHeaderData(obisHeader);
//			System.out.println(obisHeaderList.size());
			
//			String obisData="00 01 00 01 00 01 04 02 c4 02 c9 00 00 00 00 01 00 82 03 f4 01 0b 02 2d 09 0c 08 1d 05 01 01 00 00 00 00 fe a7 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 05 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 05 00 00 00 00 05 00 00 00 00 05 00 00 00 00 05 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 2d 09 0c 08 1d 0c 01 07 00 00 00 00 fe a7 00 06 00 00 02 f4 06 00 00 00 00 06 00 00 02 f4 05 00 00 02 f4 06 00 00 02 fb 06 00 00 00 00 06 00 00 01 30 06 00 00 01 97 06 00 00 00 2c 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 01 35 06 00 00 01 99 06 00 00 00 2d 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 01 30 06 00 00 01 97 06 00 00 00 2c 06 00 00 00 00 05 00 00 01 30 05 00 00 01 97 05 00 00 00 2c 05 00 00 00 00 06 00 00 01 f7 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 fd 06 00 00 00 00 06 00 00 07 05 09 0c 08 1d 0b 19 02 05 35 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 07 06 09 0c 08 1d 0b 19 02 05 36 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 2d 09 0c 08 1e 01 01 03 00 00 00 00 fe a7 00 06 00 00 25 f1 06 00 00 00 00 06 00 00 25 f1 05 00 00 25 f1 06 00 00 28 0f 06 00 00 00 00 06 00 00 0d 42 06 00 00 18 63 06 00 00 00 4c 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0e 18 06 00 00 19 9f 06 00 00 00 56 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0d 42 06 00 00 18 63 06 00 00 00 4c 06 00 00 00 00 05 00 00 0d 42 05 00 00 18 63 05 00 00 00 4c 05 00 00 00 00 06 00 00 07 a1 06 00 00 00 00 06 00 00 00 0d 06 00 00 00 00 06 00 00 1e 42 06 00 00 00 00 06 00 00 06 57 09 0c 08 1d 0c 0c 04 06 01 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 09 0a 09 0c 08 1d 0c 04 03 0f 20 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 2d 09 0c 08 1e 02 01 06 00 00 00 00 fe a7 00 06 00 00 3b bd 06 00 00 00 00 06 00 00 3b bd 05 00 00 3b bd 06 00 00 3e 3f 06 00 00 00 00 06 00 00 14 d2 06 00 00 25 ff 06 00 00 00 eb 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 15 ca 06 00 00 27 70 06 00 00 01 04 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 14 d2 06 00 00 25 ff 06 00 00 00 eb 06 00 00 00 00 05 00 00 14 d2 05 00 00 25 ff 05 00 00 00 eb 05 00 00 00 00 06 00 00 09 ee 06 00 00 00 00 06 00 00 00 2b 06 00 00 00 00 06 00 00 31 a3 06 00 00 ff ff\n" + 
//					"00 01 00 01 00 01 04 02 c4 02 c1 00 00 00 00 02 00 82 03 f4 00 00 06 00 00 07 ec 09 0c 08 1e 01 13 07 11 19 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 07 ec 09 0c 08 1e 01 13 07 11 19 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 2d 09 0c 08 1e 03 01 02 00 00 00 00 fe a7 00 06 00 00 40 00 06 00 00 00 00 06 00 00 40 00 05 00 00 40 00 06 00 00 42 94 06 00 00 00 00 06 00 00 15 0a 06 00 00 29 d2 06 00 00 01 24 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 16 05 06 00 00 2b 50 06 00 00 01 3f 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 15 0a 06 00 00 29 d2 06 00 00 01 24 06 00 00 00 00 05 00 00 15 0a 05 00 00 29 d2 05 00 00 01 24 05 00 00 00 00 06 00 00 0a 2a 06 00 00 00 00 06 00 00 00 4e 06 00 00 00 00 06 00 00 35 88 06 00 00 00 00 06 00 00 04 b1 09 0c 08 1e 02 0f 06 06 0b 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 04 b1 09 0c 08 1e 02 0f 06 06 0b 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 2d 09 0c 08 1e 04 01 05 00 00 00 00 fe a7 00 06 00 00 4c ab 06 00 00 00 00 06 00 00 4c ab 05 00 00 4c ab 06 00 00 4f 93 06 00 00 00 00 06 00 00 18 c2 06 00 00 32 99 06 00 00 01 4f 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 19 cc 06 00 00 34 56 06 00 00 01 70 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 18 c2 06 00 00 32 99 06 00 00 01 4f 06 00 00 00 00 05 00 00 18 c1 05 00 00 32 99 05 00 00 01 4f 05 00 00 00 00 06 00 00 12 d6 06 00 00 00 00 06 00 00 01 47 06 00 00 00 00 06 00 00 38 8d 06 00 00 00 00 06 00 00 07 5a 09 0c 08 1e 03 1b 07 08 3b 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 07 5b 09 0c 08 1e 03 1b 07 08 3b 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 2d 09 0c 08 1e 05 01 02 00 00 00 00 fe a7 00 06 00 00 61 89 06 00 00 00 00 06 00 00 61 89 05 00 00 61 89 06 00 00 65 f8 06 00 00 00 00 06 00 00 1f ce 06 00 00 40 1d 06 00 00 01 9c 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 21 0a 06 00 00 43 10 06 00 00 01 dd 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 1f cf 06 00 00 40 1d 06 00 00 01 9c 06 00 00 00 00 05 00 00 1f ce 05 00 00 40 1d 05 00 00 01 9c 05 00 00 00 00 06 00 00 22 2d 06 00 00 00 00 06 00 00 02 21 06 00 00 00 00 06 00 00 3d 39 06 00 00 00 00 06 00 00 05 f5 09 0c 08 1e 04 0a 07 08 1b 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 07 82 09 0c 08 1e 04 1d 05 06 12 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 2d 09 0c 08 1e 06 01 05 00 00 00 00 fe a7 00 06 00 00 70 59 06 00 00 00 00 06 00 00 70 59 05 00 00 70 58 06 00 00 75 76 06 00 00 00 00 06 00 00 26 a0 06 00 00 47 4c 06 00 00 02 6c 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 28 07 06 00 00 4a a7 06 00 00 02 c8 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 ff ff\n" + 
//					"00 01 00 01 00 01 03 d8 c4 02 c1 01 00 00 00 03 00 82 03 ca 00 00 00 06 00 00 00 00 06 00 00 26 a0 06 00 00 47 4c 06 00 00 02 6c 06 00 00 00 00 05 00 00 26 a0 05 00 00 47 4c 05 00 00 02 6c 05 00 00 00 00 06 00 00 2b 43 06 00 00 00 00 06 00 00 02 57 06 00 00 00 00 06 00 00 42 be 06 00 00 00 00 06 00 00 08 a3 09 0c 08 1e 05 17 03 07 02 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 08 a6 09 0c 08 1e 05 17 03 07 02 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 2d 09 0c 08 1e 07 01 01 00 00 00 00 fe a7 00 06 00 00 7e b8 06 00 00 00 00 06 00 00 7e b8 05 00 00 7e b8 06 00 00 84 01 06 00 00 00 00 06 00 00 2e 1b 06 00 00 4d 81 06 00 00 03 5c 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 2f a5 06 00 00 50 e5 06 00 00 03 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 2e 1c 06 00 00 4d 81 06 00 00 03 5c 06 00 00 00 00 05 00 00 2d d9 05 00 00 4d 81 05 00 00 03 5c 05 00 00 00 00 06 00 00 33 d1 06 00 00 00 00 06 00 00 02 87 06 00 00 00 00 06 00 00 48 5e 06 00 00 00 00 06 00 00 05 73 09 0c 08 1e 06 16 05 08 16 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 05 73 09 0c 08 1e 06 16 05 08 16 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 2d 09 0c 08 1e 08 01 03 00 00 00 00 fe a7 00 06 00 00 93 56 06 00 00 00 00 06 00 00 93 56 05 00 00 93 56 06 00 00 98 f7 06 00 00 00 00 06 00 00 37 c2 06 00 00 57 71 06 00 00 04 63 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 39 73 06 00 00 5a fb 06 00 00 04 ca 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 37 c2 06 00 00 57 71 06 00 00 04 63 06 00 00 00 00 05 00 00 37 80 05 00 00 57 71 05 00 00 04 63 05 00 00 00 00 06 00 00 48 05 06 00 00 00 00 06 00 00 02 ab 06 00 00 00 00 06 00 00 48 a5 06 00 00 00 00 06 00 00 06 1b 09 0c 08 1e 07 1a 05 12 15 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 06 1e 09 0c 08 1e 07 1a 05 12 15 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 2d 09 0c 08 1e 09 01 04 00 00 00 00 fe a7 00 06 00 00 ab a3 06 00 00 00 00 06 00 00 ab a3 05 00 00 ab a2 06 00 00 b1 9f 06 00 00 00 00 06 00 00 42 d8 06 00 00 63 df 06 00 00 05 55 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 44 a8 06 00 00 67 96 06 00 00 05 cb 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 42 d8 06 00 00 63 df 06 00 00 05 55 06 00 00 00 00 05 00 00 42 96 05 00 00 63 df 05 00 00 05 2d 05 00 00 00 00 06 00 00 5f c6 06 00 00 00 00 06 00 00 03 28 06 00 00 00 00 06 00 00 48 b3 06 00 00 00 00 06 00 00 04 dc 09 0c 08 1e 08 03 05 07 0f 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 04 dd 09 0c 08 1e 08 03 05 07 0f 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 ff ff";
			
			String obisData="00 01 00 01 00 01 00 6f c4 01 c1 00 01 01 02 14 09 0c 08 1e 06 13 ff 0c 00 00 00 fe a7 00 11 00 09 08 32 30 34 31 37 38 39 35 06 00 00 00 01 06 00 00 00 00 06 00 00 00 01 05 00 00 00 01 06 00 00 00 01 06 00 00 00 00 12 07 d7 12 00 00 12 13 7e 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 12 03 e8 10 00 00 ff ff";
			
			List<String> obisDataList=new BillingDataParser().processBillingObisData(obisData);
			System.out.println(obisDataList.size());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
