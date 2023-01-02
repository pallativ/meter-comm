package com.hes.data.parser.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.hes.data.parser.utils.HexToStringUtils;

/**
 * 
 * @author psvr
 *
 */
public class DataProcessorAction {

	public List<String> processObisHeaderData(String obisHeader) {
		List<String> list = new ArrayList<String>();
		try {

			String[] strArr = obisHeader.split(" ");

			String wrapperVersion = strArr[0] + strArr[1];// Wrapper Version
			String destAddress = strArr[2] + strArr[3];// destination address
			String srcAddress = strArr[4] + strArr[5];// Source address
			String lengthOfFrame = strArr[6] + strArr[7];// length of frame
			String readActionResponse = strArr[8] + strArr[9];// Read action response
			String invokeIdAndPriority = strArr[10];// Invoke ID and priority
			String timeOutFlag = strArr[11];// Invoke ID and priority
			// String noOfObjects=strArr[12];// CCCCCC?????????
			String noOfDataObjects = strArr[13];// No Of data objects

			Integer noOfDataObj = Integer.decode("0x" + noOfDataObjects);
			boolean isDataObjectStarted = false;
			for (Integer i = 14; i < strArr.length; i++) {
				if (strArr[i].equalsIgnoreCase("02")) {
					isDataObjectStarted = true;
					i++;
				}

				if (isDataObjectStarted) {
					Integer noOfdataElements = Integer.decode("0x" + strArr[i]);
					i++;

					try {
						for (Integer k = 0; k < noOfdataElements; k++) {
							String dataEleStr = "";
							if (strArr[i].equalsIgnoreCase("12")) {
								Integer noOfDigits = 2;
								for (Integer j = 0; j < noOfDigits; j++) {
									dataEleStr += strArr[++i] + " ";
								}
								i++;
							} else if (strArr[i].equalsIgnoreCase("09")) {
								Integer noOfDigits = Integer.decode("0x" + strArr[++i]);
								for (Integer j = 0; j < noOfDigits; j++) {
									dataEleStr += strArr[++i];
									if (j < noOfDigits - 1) {
										dataEleStr += " ";
									}
								}
								i++;
								list.add(dataEleStr);
							} else if (strArr[i].equalsIgnoreCase("0F")) {
								Integer noOfDigits = 1;
								for (Integer j = 0; j < noOfDigits; j++) {
									dataEleStr += strArr[++i] + " ";
								}
								i++;
							}
						}
					} catch (Exception e) {
						// e.printStackTrace();
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<String> processBillObisData(String obisData) {
		List<String> list = new ArrayList<String>();
		try {

			String[] strArr = obisData.split(" ");

			// String wrapperVersion=strArr[0]+strArr[1];// Wrapper Version
			// String destAddress=strArr[2]+strArr[3];// destination address
			// String srcAddress=strArr[4]+strArr[5];// Source address
			// String lengthOfFrame=strArr[6]+strArr[7];// length of frame
			// String readActionResponse=strArr[8]+strArr[9];// Read action response
			// String invokeIdAndPriority=strArr[10];//Invoke ID and priority
			// String timeOutFlag=strArr[11];//Invoke ID and priority
			// String noOfObjects=strArr[12];// CCCCCC?????????
			String noOfDataObjects = strArr[13];// No Of data objects

			Integer noOfDataObj = Integer.decode("0x" + noOfDataObjects);
			boolean isDataObjectStarted = false;
			for (Integer i = 14; i < strArr.length; i++) {
				if (strArr[i].equalsIgnoreCase("02")) {
					isDataObjectStarted = true;
					i++;
				}

				if (isDataObjectStarted) {
					Integer noOfdataElements = Integer.decode("0x" + strArr[i]);
					i++;

					try {
						for (Integer k = 0; k < noOfdataElements; k++) {
							String dataEleStr = "";

							if ((strArr[i].equalsIgnoreCase("05"))) {
								Integer noOfDigits = 4;
								for (Integer j = 0; j < noOfDigits; j++) {
									dataEleStr += strArr[++i] + " ";
								}
								i++;
								dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
							} else if ((strArr[i].equalsIgnoreCase("06"))) {
								Integer noOfDigits = 4;
								for (Integer j = 0; j < noOfDigits; j++) {
									dataEleStr += strArr[++i] + " ";
								}
								i++;
								dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
							} else if (strArr[i].equalsIgnoreCase("09")) {
								Integer noOfDigits = Integer.decode("0x" + strArr[++i]);
								for (Integer j = 0; j < noOfDigits; j++) {
									dataEleStr += strArr[++i] + " ";
								}
								i++;
								if (noOfDigits == 8) {
									dataEleStr = new HexToStringUtils().unHex(dataEleStr);
								} else {
									dataEleStr = new HexToStringUtils().convertDateTime(dataEleStr);
								}

							} else if (strArr[i].equalsIgnoreCase("10")) {
								Integer noOfDigits = 2;
								for (Integer j = 0; j < noOfDigits; j++) {
									dataEleStr += strArr[++i] + " ";
								}
								i++;
								dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
							} else if (strArr[i].equalsIgnoreCase("11")) {
								Integer noOfDigits = 1;
								for (Integer j = 0; j < noOfDigits; j++) {
									dataEleStr += strArr[++i] + " ";
								}
								i++;
								dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
							} else if (strArr[i].equalsIgnoreCase("12")) {
								Integer noOfDigits = 2;
								for (Integer j = 0; j < noOfDigits; j++) {
									dataEleStr += strArr[++i] + " ";
								}
								i++;
								dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
							} else if (strArr[i].equalsIgnoreCase("0F")) {
								Integer noOfDigits = 1;
								for (Integer j = 0; j < noOfDigits; j++) {
									dataEleStr += strArr[++i] + " ";
								}
								i++;
								dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
							}

							list.add(dataEleStr);
						}
					} catch (Exception e) {
						// e.printStackTrace();
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void processXMLFiles(File watchFolder, String watchFolderPath) {
		InstantDataParser instantParser = new InstantDataParser();
		BillingDataParser billingParser = new BillingDataParser();
		LoadProfile1DataParser loadProfile1Parser = new LoadProfile1DataParser();
		try {

			File backupFolder = new File(watchFolderPath, "BACKUP_FILES");
			if (!backupFolder.exists())
				backupFolder.mkdirs();

			File successFolder = new File(backupFolder, "COMPLETED");
			if (!successFolder.exists())
				successFolder.mkdirs();

			File failureFolder = new File(backupFolder, "NOT COMPLETED");
			if (!failureFolder.exists())
				failureFolder.mkdirs();

			File[] files = watchFolder.listFiles();
			if (files != null && files.length > 0) {
				System.out.println("Files Ready for processing");
				for (File file : files) {
					String status = "false";
					try {
						if ((file.getName().endsWith("MDE"))) {
							File dataFile = new File(file.getAbsolutePath().replace(".MDE", ""));
							if (dataFile.exists()) {
								String meterSerialNumber = file.getName().split("_")[0];
								String fileData = FileUtils.readFileToString(dataFile);

								String obisHeader = StringUtils.substringBetween(fileData, "<OBISCODES>\n",
										"\n</OBISCODES>");
								String obisData = StringUtils.substringBetween(fileData, "<OBISDATA>\n",
										"\n</OBISDATA>");

								List<String> obisHeaderList = new DataProcessorAction()
										.processObisHeaderData(obisHeader);
								List<String> obisDataList = null;
								// List<String> obisDataList=instantParser.processInstantObisData(obisData);

								if (file.getName().contains("INSTANT")) {
									obisDataList = instantParser.processInstantObisData(obisData);
									List<String> dataList = new DataProcessorAction()
											.processObisDataList(obisHeaderList, obisDataList);
									status = instantParser.processInstantData(obisHeaderList, dataList,
											watchFolderPath);
								} else if (file.getName().contains("BILL")) {
									try {
										obisDataList = billingParser.processBillingObisData(obisData);
										billingParser.processBillingData(obisHeaderList, obisDataList, watchFolderPath,
												meterSerialNumber);
									} catch (Exception e) {
										e.printStackTrace();
									}

								} else if (file.getName().contains("LOAD1")) {
									try {
										obisDataList = loadProfile1Parser.processLoadProfileObisData(obisData);
										// loadProfile1Parser.processLoadProfileObisData(obisHeaderList, obisDataList,
										// watchFolderPath,meterSerialNumber);
									} catch (Exception e) {
										e.printStackTrace();
									}

								}

								try {
									String dateSTr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
									if (status.equalsIgnoreCase("true")) {
										File successDir = new File(successFolder, dateSTr);
										if (!successDir.exists())
											successDir.mkdirs();

										FileUtils.moveFile(dataFile, new File(successDir, dataFile.getName()));

									} else {
										File failureDir = new File(failureFolder, dateSTr);
										if (!failureDir.exists())
											failureDir.mkdirs();

										FileUtils.moveFile(dataFile, new File(failureDir, dataFile.getName()));

									}

									try {
										file.delete();
									} catch (Exception e) {
										e.printStackTrace();
									}

								} catch (Exception e) {
									e.printStackTrace();
								}

								/**
								 * 
								 * FILES TO BE MOVED TO SUCCESS / FAILURE FOLDERS
								 * 
								 */

							}

						} else {
							System.out.println("file is not an MDE");
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] arg) {
		DataProcessorAction dataObj = new DataProcessorAction();
		try {
			String watchFolderPath = null;

			try {
				watchFolderPath = arg[0];
			} catch (Exception e) {
				// e.printStackTrace();
			}

			if (watchFolderPath == null) {
				// watchFolderPath="C:\\files\\LOAD_PROFILE\\";
				watchFolderPath = "C:\\files\\INSTANT\\";
			}

			File watchFolder = new File(watchFolderPath, "TO_BE_PROCESSED");
			System.out.println("watchFolder :: " + watchFolder.getAbsolutePath());

			while (true) {
				try {
					System.out.println("Started and finding files...!!!" + new Date());
					dataObj.processXMLFiles(watchFolder, watchFolderPath);
					Thread.sleep(5 * 1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<String> processObisDataList(List<String> obisHeaderList, List<String> obisDataList) {
		List<String> dataList = new ArrayList<String>();

		try {
			StringBuilder strbd = new StringBuilder();

			Integer counter = 1;
			for (String str : obisDataList) {

				if (counter / obisHeaderList.size() == 1) {
					strbd.append(str);
					counter = 0;
					dataList.add(strbd.toString());
					strbd = new StringBuilder();
				} else {
					strbd.append(str + ",");
				}
				counter++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

}
