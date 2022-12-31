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
import com.hes.data.parser.vo.InstantData;
import com.hes.data.parser.vo.InstantDataId;

/**
 * 
 * @author psvr
 *
 */
public class InstantDataParser {
	
	public List<String> processInstantObisData(String obisData){
		List<String> list=new ArrayList<String>();
		try{
			
			String[] strArr=obisData.split(" ");
			
//			String wrapperVersion=strArr[0]+strArr[1];// Wrapper Version
//			String destAddress=strArr[2]+strArr[3];// destination address
//			String srcAddress=strArr[4]+strArr[5];// Source address
//			String lengthOfFrame=strArr[6]+strArr[7];// length of frame
//			String readActionResponse=strArr[8]+strArr[9];// Read action response
//			String invokeIdAndPriority=strArr[10];//Invoke ID and priority
//			String timeOutFlag=strArr[11];//Invoke ID and priority
//			String noOfObjects=strArr[12];// CCCCCC?????????
			String noOfDataObjects=strArr[13];// No Of data objects
			
			
			Integer noOfDataObj=Integer.decode("0x"+noOfDataObjects);
			boolean isDataObjectStarted=false;
			for(Integer i=14;i<strArr.length;i++){
				if(strArr[i].equalsIgnoreCase("02")){
					isDataObjectStarted=true;
					i++;
				}
				
				if(isDataObjectStarted){
					Integer noOfdataElements=Integer.decode("0x"+strArr[i]);
					i++;
					
					try{
						for(Integer k=0;k<noOfdataElements;k++){
							String dataEleStr="";
							
							if((strArr[i].equalsIgnoreCase("05"))){
								Integer noOfDigits=4;
								for(Integer j=0;j<noOfDigits;j++){
									dataEleStr+=strArr[++i]+" ";
								}
								i++;
								dataEleStr=Integer.parseInt(dataEleStr.replace(" ", ""), 16)+"";
							}else if((strArr[i].equalsIgnoreCase("06"))){
								Integer noOfDigits=4;
								for(Integer j=0;j<noOfDigits;j++){
									dataEleStr+=strArr[++i]+" ";
								}
								i++;
								dataEleStr=Integer.parseInt(dataEleStr.replace(" ", ""), 16)+"";
							}else if(strArr[i].equalsIgnoreCase("09")){
								Integer noOfDigits=Integer.decode("0x"+strArr[++i]);
								for(Integer j=0;j<noOfDigits;j++){
									dataEleStr+=strArr[++i]+" ";
								}
								i++;
								if(noOfDigits==8){
									dataEleStr=new HexToStringUtils().unHex(dataEleStr);
								}else{
									dataEleStr=new HexToStringUtils().convertDateTime(dataEleStr);
								}
								
								
							}else if(strArr[i].equalsIgnoreCase("10")){
								Integer noOfDigits=2;
								for(Integer j=0;j<noOfDigits;j++){
									dataEleStr+=strArr[++i]+" ";
								}
								i++;
								dataEleStr=Integer.parseInt(dataEleStr.replace(" ", ""), 16)+"";
							}else if(strArr[i].equalsIgnoreCase("11")){
								Integer noOfDigits=1;
								for(Integer j=0;j<noOfDigits;j++){
									dataEleStr+=strArr[++i]+" ";
								}
								i++;
								dataEleStr=Integer.parseInt(dataEleStr.replace(" ", ""), 16)+"";
							}else if(strArr[i].equalsIgnoreCase("12")){
								Integer noOfDigits=2;
								for(Integer j=0;j<noOfDigits;j++){
									dataEleStr+=strArr[++i]+" ";
								}
								i++;
								dataEleStr=Integer.parseInt(dataEleStr.replace(" ", ""), 16)+"";
							}else if(strArr[i].equalsIgnoreCase("0F")){
								Integer noOfDigits=1;
								for(Integer j=0;j<noOfDigits;j++){
									dataEleStr+=strArr[++i]+" ";
								}
								i++;
								dataEleStr=Integer.parseInt(dataEleStr.replace(" ", ""), 16)+"";
							}
							
							list.add(dataEleStr);
						}
					}catch (Exception e) {
//						e.printStackTrace();
					}
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public String processInstantData(List<String> obisHeaderList,List<String> dataList,String watchFolderPath){
		String status="false";
		try{
			Map<String,String> scalarprop=new Utils().readProperties("scalar.MDV");
	        DecimalFormat df = new DecimalFormat("#####.##");

			
			for(String str:dataList){
				
				try{
					InstantData instantData=new InstantData();
					InstantDataId id=new InstantDataId();
//					id.setInstantDatetime(instantDatetime);
//					id.setMeterSerialNumber(meterSerialNumber);
					
					String[] strArr=str.split("\\,");
					
					for(Integer i=0;i<strArr.length;i++){
						String dataVal=strArr[i];
						String obisCode=obisHeaderList.get(i);
						
						String scalarVal=scalarprop.get(obisCode.toUpperCase());
						if(scalarVal!=null){
							Double sclVal=Double.parseDouble(scalarVal);
							dataVal=df.format(Double.parseDouble(dataVal)*sclVal)+"";
//							dataVal=(Double.parseDouble(dataVal)*sclVal)+"";
						}
						
						if(obisCode.equalsIgnoreCase("00 00 01 00 00 FF")){//CLOCK
							try{
								Date instantDatetime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataVal);
								id.setInstantDatetime(new Timestamp(instantDatetime.getTime()));
//								instantData.setInstantDatetimeIst(instantDatetimeIst);
							}catch (Exception e) {
								e.printStackTrace();
							}
							
						}else if(obisCode.equalsIgnoreCase("00 00 60 01 00 FF")){//METER SERIAL NUMBER
							id.setMeterSerialNumber(dataVal);
						}else if(obisCode.equalsIgnoreCase("01 00 01 08 00 FF")){//Sum import active energy
							instantData.setSumImportActiveEnergy(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 02 08 00 FF")){//Sum export active energy
							instantData.setSumExportActiveEnergy(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 0F 08 00 FF")){//Active energy (|+A|+|-A|) Combined total
							instantData.setActiveEnergyCombinedTotal(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 10 08 00 FF")){//Active energy (|+A|-|-A|) Net total
							instantData.setActiveEnergyNetTotal(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 09 08 00 FF")){//Apparent energy import (+VA) (QI+QIV)
							instantData.setApparentEnergyImport(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 0A 08 00 FF")){//Apparent energy export (-VA) (QII+QIII)
							instantData.setApparentEnergyExport(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 20 07 00 FF")){//Instantaneous voltage L1
							instantData.setVoltageL1(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 1F 07 00 FF")){//Instantaneous current L1
							instantData.setCurrentL1(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 34 07 00 ff")){//Instantaneous voltage L2
							instantData.setVoltageL2(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 33 07 00 ff")){//Instantaneous current L2
							instantData.setCurrentL2(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 48 07 00 ff")){//Instantaneous voltage L3
							instantData.setVoltageL3(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 47 07 00 ff")){//Instantaneous current L3
							instantData.setCurrentL3(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 0E 07 00 FF")){//Instantaneous net frequency
							instantData.setNetFrequency(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 01 07 00 FF")){//Instantaneous active import power (+A)
							instantData.setActiveImportPower(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 02 07 00 FF")){//Instantaneous active export power (-A)
							instantData.setActiveExportPower(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 03 07 00 FF")){//Instantaneous reactive import power (+R))
							instantData.setReactiveImportPower(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 04 07 00 FF")){//Instantaneous reactive export power (+R))
							instantData.setReactiveExportPower(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 09 07 00 FF")){//Instantaneous apparent import power (+VA)
							instantData.setApparentImportPower(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 09 07 00 FF")){//Instantaneous apparent Export power (+VA)
							instantData.setApparentExportPower(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 0A 07 00 FF")){//Instantaneous apparent Export power (+VA)
							instantData.setApparentExportPower(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 0D 07 00 FF")){//Power factor
							instantData.setPowerFactorL1(Double.parseDouble(dataVal));
						}else if(obisCode.equalsIgnoreCase("01 00 51 07 04 FF")){//Phase Angle from I (L1) to U (L1)
							instantData.setPhaseAngleL1(Double.parseDouble(dataVal));
						}
					}
					
					instantData.setId(id);
					
					status=new MeterRuleEngineDAO().saveObject(instantData);
					
					logData(instantData, status, watchFolderPath);
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	public void logData(InstantData instantData,String status,String watchFolderPath) {
			
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
					FileUtils.writeStringToFile(successFile, instantData.toString()+"\n",true);
				}else if(status.equalsIgnoreCase("false")) {
					FileUtils.writeStringToFile(failedFile, instantData.toString()+"\n",true);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
