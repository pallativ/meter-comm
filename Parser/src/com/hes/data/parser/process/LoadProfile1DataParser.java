package com.hes.data.parser.process;

import java.util.ArrayList;
import java.util.List;

import com.hes.data.parser.utils.HexToStringUtils;

public class LoadProfile1DataParser {

	public List<String> processLoadProfileObisData(String obisData) {
		List<String> list = new ArrayList<String>();
		try {

			String[] framesArr = obisData.split("\n");
			String[] strArr = framesArr[0].split(" ");

			Integer noOfIntervals = 0;// No Of data objects
			Integer noOfDataObjects = 0;// noOfDataObjects
			String dataEleStr = "";
			for (Integer cnt = 0; cnt < framesArr.length; cnt++) {
				Integer pointer = 0;
				Integer intervalLen = 0;
				strArr = framesArr[cnt].split(" ");
				try {
					if (cnt == 0) {
						// String wrapperVersion=strArr[0]+strArr[1];// Wrapper Version
						// String destAddress=strArr[2]+strArr[3];// destination address
						// String srcAddress=strArr[4]+strArr[5];// Source address
						// String lengthOfFrame=strArr[6]+strArr[7];// length of frame
						// String readActionResponse=strArr[8]+strArr[9];// Read action response
						// String invokeIdAndPriority=strArr[10];//Invoke ID and priority
						// String timeOutFlag=strArr[11];//Invoke ID and priority
						// String noOfObjects=strArr[12];// CCCCCC?????????

						if ((strArr[20].equalsIgnoreCase("01")) && (strArr[21].equalsIgnoreCase("81"))
								&& strArr[23].equalsIgnoreCase("02")) {
							noOfIntervals = Integer.decode("0x" + strArr[22]);// No Of data objects
							noOfDataObjects = Integer.decode("0x" + strArr[24]);// noOfDataObjects
							intervalLen = 1;
							pointer = 25;
						} else if ((strArr[20].equalsIgnoreCase("01")) && (strArr[21].equalsIgnoreCase("82"))
								&& strArr[24].equalsIgnoreCase("02")) {
							noOfIntervals = Integer.decode("0x" + strArr[22] + strArr[23]);// No Of data objects
							noOfDataObjects = Integer.decode("0x" + strArr[25]);// noOfDataObjects
							intervalLen = 2;
							pointer = 26;
						}

					} else {
						// pointer=22;
					}

					for (Integer y = 0; y < noOfIntervals; y++) {// No of intervals parsing
						try {
							if (pointer == 1022) {
								System.out.println();
							}

							StringBuilder strb = new StringBuilder();

							if (y > 0) {
								pointer = pointer + 2;
							}
							for (Integer z = 0; z < noOfDataObjects; z++) {// No of noOfDataObjects parsing
								try {
									if (strArr.length == pointer) {// IF THE LAST BYTE ENDS WITH DATA OBJECT
										strArr = framesArr[++cnt].split(" ");
										pointer = 20 + intervalLen;
									}

									String strArrPointerVal = strArr[pointer];
									dataEleStr = "";
									try {

										if (strArr[pointer].equalsIgnoreCase("FF")
												&& strArr[pointer + 1].equalsIgnoreCase("FF")
												&& ((strArr.length == pointer + 2 + intervalLen))) {// LAST DIGITS AFTER
																									// OBJECT ELEMENT
																									// STARTS WITH DATA
											strArr = framesArr[++cnt].split(" ");
											pointer = 20;
											if (!((strArr[0] + strArr[1] + strArr[2] + strArr[3])
													.equalsIgnoreCase("00010001"))) {
												strArr = framesArr[++cnt].split(" ");

												// continue;
											}

											strArrPointerVal = strArr[pointer];
										} else if (strArr[pointer + 1].equalsIgnoreCase("FF")
												&& strArr[pointer + 2].equalsIgnoreCase("FF")
												&& ((strArr.length == pointer + 3 + intervalLen))) {// LAST DIGITS AFTER
																									// OBJECT ELEMENT
																									// BUT ONE STARTS
																									// WITH DATA
											strArrPointerVal = strArr[pointer];
											strArr = framesArr[++cnt].split(" ");
											pointer = 19;
											if (!((strArr[0] + strArr[1] + strArr[2] + strArr[3])
													.equalsIgnoreCase("00010001"))) {
												strArr = framesArr[++cnt].split(" ");
												// pointer=20;
												// continue;
											}

										}
									} catch (Exception e) {
										e.printStackTrace();
									}

									if ((strArrPointerVal.equalsIgnoreCase("05"))) {
										Integer noOfDigits = 4;
										if (pointer + noOfDigits > strArr.length - 1 - 2) {
											Integer k = strArr.length - 1 - 2 - pointer;
											for (Integer j = 0; j < k; j++) {
												dataEleStr += strArr[++pointer] + " ";
												noOfDigits--;
											}
											strArr = framesArr[++cnt].split(" ");
											if (noOfDigits == 1) {// if last 3 digits
												pointer = 17 + intervalLen;
											} else {// if last 2 digits
												pointer = 19 + intervalLen;
											}
										}

										for (Integer j = 0; j < noOfDigits; j++) {
											dataEleStr += strArr[++pointer] + " ";
										}
										pointer++;
										dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
									} else if ((strArrPointerVal.equalsIgnoreCase("06"))) {
										Integer noOfDigits = 4;
										if (pointer + noOfDigits > strArr.length - 1 - 2) {
											Integer k = strArr.length - 1 - 2 - pointer;
											for (Integer j = 0; j < k; j++) {
												dataEleStr += strArr[++pointer] + " ";
												noOfDigits--;
											}
											strArr = framesArr[++cnt].split(" ");
											if (noOfDigits == 1) {// if last 3 digits
												pointer = 17 + intervalLen;
											} else {// if last 2 digits
												pointer = 19 + intervalLen;
											}

										}
										for (Integer j = 0; j < noOfDigits; j++) {
											dataEleStr += strArr[++pointer] + " ";
										}
										pointer++;
										dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
									} else if (strArrPointerVal.equalsIgnoreCase("09")) {
										Integer noOfDigits = Integer.decode("0x" + strArr[++pointer]);
										if (pointer + noOfDigits > strArr.length - 1 - 2) {
											Integer k = strArr.length - 1 - 2 - pointer;
											for (Integer j = 0; j < k; j++) {
												dataEleStr += strArr[++pointer] + " ";
												noOfDigits--;
											}
											strArr = framesArr[++cnt].split(" ");
											if (noOfDigits == 1) {// if last 3 digits
												pointer = 17 + intervalLen;
											} else {// if last 2 digits
												pointer = 19 + intervalLen;
											}
										}
										for (Integer j = 0; j < noOfDigits; j++) {
											dataEleStr += strArr[++pointer] + " ";
										}
										pointer++;
										if (noOfDigits == 8) {
											dataEleStr = HexToStringUtils.unHex(dataEleStr);
										} else {
											dataEleStr = HexToStringUtils.convertDateTime(dataEleStr);
										}

									} else if (strArrPointerVal.equalsIgnoreCase("10")) {
										Integer noOfDigits = 2;
										if (pointer + noOfDigits > strArr.length - 1 - 2) {
											Integer k = strArr.length - 1 - 2 - pointer;
											for (Integer j = 0; j < k; j++) {
												dataEleStr += strArr[++pointer] + " ";
												noOfDigits--;
											}
											strArr = framesArr[++cnt].split(" ");
											if (noOfDigits == 1) {// if last 3 digits
												pointer = 17 + intervalLen;
											} else {// if last 2 digits
												pointer = 19 + intervalLen;
											}
										}
										for (Integer j = 0; j < noOfDigits; j++) {
											dataEleStr += strArr[++pointer] + " ";
										}
										pointer++;
										dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
									} else if (strArrPointerVal.equalsIgnoreCase("11")) {
										Integer noOfDigits = 1;
										if (pointer + noOfDigits > strArr.length - 1 - 2) {
											Integer k = strArr.length - 1 - 2 - pointer;
											for (Integer j = 0; j < k; j++) {
												dataEleStr += strArr[++pointer] + " ";
												noOfDigits--;
											}
											strArr = framesArr[++cnt].split(" ");
											if (noOfDigits == 1) {// if last 3 digits
												pointer = 17 + intervalLen;
											} else {// if last 2 digits
												pointer = 19 + intervalLen;
											}
										}
										for (Integer j = 0; j < noOfDigits; j++) {
											dataEleStr += strArr[++pointer] + " ";
										}
										pointer++;
										dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
									} else if (strArrPointerVal.equalsIgnoreCase("12")) {
										Integer noOfDigits = 2;
										if (pointer + noOfDigits > strArr.length - 1 - 2) {
											Integer k = strArr.length - 1 - 2 - pointer;
											for (Integer j = 0; j < k; j++) {
												dataEleStr += strArr[++pointer] + " ";
												noOfDigits--;
											}
											strArr = framesArr[++cnt].split(" ");
											if (noOfDigits == 1) {// if last 3 digits
												pointer = 17 + intervalLen;
											} else {// if last 2 digits
												pointer = 19 + intervalLen;
											}
										}
										for (Integer j = 0; j < noOfDigits; j++) {
											dataEleStr += strArr[++pointer] + " ";
										}
										pointer++;
										dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
									} else if (strArrPointerVal.equalsIgnoreCase("02")) {
										Integer noOfDigits = 2;
										if (pointer + noOfDigits > strArr.length - 1 - 2) {
											Integer k = strArr.length - 1 - 2 - pointer;
											for (Integer j = 0; j < k; j++) {
												dataEleStr += strArr[++pointer] + " ";
												noOfDigits--;
											}
											strArr = framesArr[++cnt].split(" ");
											if (noOfDigits == 1) {// if last 3 digits
												pointer = 17 + intervalLen;
											} else {// if last 2 digits
												pointer = 19 + intervalLen;
											}
										}
										for (Integer j = 0; j < noOfDigits; j++) {
											dataEleStr += strArr[++pointer] + " ";
										}
										pointer++;
										dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
									} else if (strArrPointerVal.equalsIgnoreCase("0F")) {
										Integer noOfDigits = 1;
										if (pointer + noOfDigits > strArr.length - 1 - 2) {
											Integer k = strArr.length - 1 - 2 - pointer;
											for (Integer j = 0; j < k; j++) {
												dataEleStr += strArr[++pointer] + " ";
												noOfDigits--;
											}
											strArr = framesArr[++cnt].split(" ");
											if (noOfDigits == 1) {// if last 3 digits
												pointer = 17 + intervalLen;
											} else {// if last 2 digits
												pointer = 19 + intervalLen;
											}
										}
										for (Integer j = 0; j < noOfDigits; j++) {
											dataEleStr += strArr[++pointer] + " ";
										}
										pointer++;
										dataEleStr = Integer.parseInt(dataEleStr.replace(" ", ""), 16) + "";
									}

									if (dataEleStr.equalsIgnoreCase("")) {
										System.out.println();
									}

									System.out.println(dataEleStr + ",");
									System.out.println(" ==> pointer : " + pointer + " ==> cnt : " + cnt);
									strb.append(dataEleStr + ",");

								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							list.add(strb.toString());
							System.out.println(y + "==>" + strb.toString());

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {

		try {
			// String obisData="00 01 00 01 00 01 04 02 c4 02 c1 00 00 00 00 01 00 82 03 f4
			// 01 82 07 0b 02 16 09 0c 08 1e 0a 0b ff 07 1e 00 00 fe a7 00 11 00 06 00 02 42
			// 0c 06 00 00 00 00 06 00 02 42 0c 05 00 02 42 0c 06 00 00 0d 9f 06 00 00 37 89
			// 06 00 02 50 9d 06 00 00 00 00 06 00 00 81 53 06 00 01 98 45 06 00 00 29 b9 06
			// 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00
			// 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff
			// ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 08 00 00 00 fe a7 00
			// 11 00 06 00 02 42 14 06 00 00 00 00 06 00 02 42 14 05 00 02 42 14 06 00 00 0d
			// 9f 06 00 00 37 8b 06 00 02 50 a5 06 00 00 00 00 06 00 00 81 53 06 00 01 98 4d
			// 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06
			// 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00
			// 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 08
			// 1e 00 00 fe a7 00 11 00 06 00 02 42 1c 06 00 00 00 00 06 00 02 42 1c 05 00 02
			// 42 1c 06 00 00 0d 9f 06 00 00 37 8c 06 00 02 50 ad 06 00 00 00 00 06 00 00 81
			// 53 06 00 01 98 55 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00
			// 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00
			// fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c
			// 08 1e 0a 0b ff 09 00 00 00 fe a7 00 11 00 06 00 02 42 27 06 00 00 00 00 06 00
			// 02 42 27 05 00 02 42 27 06 00 00 0d 9f 06 00 00 37 8d 06 00 02 50 b9 06 00 00
			// 00 00 06 00 00 81 53 06 00 01 98 60 06 00 00 29 b9 06 00 00 00 00 06 00 00 00
			// 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a
			// 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff
			// ff 09 02 16 09 0c 08 1e 0a 0b ff 09 1e 00 00 fe a7 00 11 00 06 00 02 42 30 06
			// 00 00 00 00 06 00 02 42 30 05 00 02 42 30 06 00 00 0d 9f 06 00 00 37 8f 06 00
			// 02 50 c1 06 00 00 00 00 06 00 00 81 53 06 00 01 98 69 06 00 00 29 b9 06 00 00
			// 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a
			// bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff
			// ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 0a 00 00 00 fe a7 00 11 00
			// 06 00 02 42 3e 06 00 00 00 00 06 00 02 42 3e 05 00 02 42 3e 06 00 00 0d a0 06
			// 00 00 37 90 06 00 02 50 d0 06 00 00 00 00 06 00 00 81 53 06 00 01 98 77 06 00
			// 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00
			// 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00
			// 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 0a 1e 00
			// 00 fe a7 00 11 00 06 00 02 42 45 06 00 00 00 00 06 00 02 42 45 05 00 02 42 45
			// 06 00 00 0d a0 06 00 00 37 91 06 00 02 50 d7 06 00 00 00 00 06 00 00 81 53 06
			// 00 01 98 7e 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00
			// 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7
			// 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e
			// 0a 0b ff 0b 00 00 00 fe a7 00 11 00 06 00 02 42 58 06 00 00 00 00 06 00 02 42
			// 58 05 00 02 42 58 06 00 00 0d a1 06 00 00 37 91 06 00 02 50 ea 06 00 00 ff ff
			// \n" +
			// "00 01 00 01 00 01 04 02 c4 02 c1 00 00 00 00 02 00 82 03 f4 00 00 06 00 00
			// 81 53 06 00 01 98 91 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00
			// 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00
			// 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09
			// 0c 08 1e 0a 0b ff 0b 1e 00 00 fe a7 00 11 00 06 00 02 42 64 06 00 00 00 00 06
			// 00 02 42 64 05 00 02 42 64 06 00 00 0d a2 06 00 00 37 92 06 00 02 50 f6 06 00
			// 00 00 00 06 00 00 81 53 06 00 01 98 9d 06 00 00 29 b9 06 00 00 00 00 06 00 00
			// 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e
			// 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00
			// ff ff 09 02 16 09 0c 08 1e 0a 0b ff 0c 00 00 00 fe a7 00 11 00 06 00 02 42 6a
			// 06 00 00 00 00 06 00 02 42 6a 05 00 02 42 6a 06 00 00 0d a2 06 00 00 37 95 06
			// 00 02 50 fe 06 00 00 00 00 06 00 00 81 53 06 00 01 98 a4 06 00 00 29 b9 06 00
			// 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00
			// 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff
			// ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 0c 1e 00 00 fe a7 00 11
			// 00 06 00 02 42 70 06 00 00 00 00 06 00 02 42 70 05 00 02 42 70 06 00 00 0d a2
			// 06 00 00 37 97 06 00 02 51 04 06 00 00 00 00 06 00 00 81 53 06 00 01 98 a9 06
			// 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00
			// 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00
			// 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 0d 00
			// 00 00 fe a7 00 11 00 06 00 02 42 7a 06 00 00 00 00 06 00 02 42 7a 05 00 02 42
			// 7a 06 00 00 0d a2 06 00 00 37 9b 06 00 02 51 0e 06 00 00 00 00 06 00 00 81 53
			// 06 00 01 98 b3 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06
			// 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe
			// a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08
			// 1e 0a 0b ff 0d 1e 00 00 fe a7 00 11 00 06 00 02 42 86 06 00 00 00 00 06 00 02
			// 42 86 05 00 02 42 86 06 00 00 0d a2 06 00 00 37 9f 06 00 02 51 1c 06 00 00 00
			// 00 06 00 00 81 53 06 00 01 98 c0 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00
			// 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07
			// 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff
			// 09 02 16 09 0c 08 1e 0a 0b ff 0e 00 00 00 fe a7 00 11 00 06 00 02 42 92 06 00
			// 00 00 00 06 00 02 42 92 05 00 02 42 92 06 00 00 0d a2 06 00 00 37 a3 06 00 02
			// 51 28 06 00 00 00 00 06 00 00 81 53 06 00 01 98 cb 06 00 00 29 b9 06 00 00 00
			// 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd
			// 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff
			// ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 0e 1e 00 00 fe a7 00 11 00 06
			// 00 02 42 a4 06 00 00 00 00 06 00 02 42 a4 05 00 02 42 a4 06 00 00 0d a2 06 00
			// 00 37 a8 06 00 02 51 3b 06 00 00 00 00 06 00 00 81 53 06 00 01 98 dd 06 00 00
			// 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00
			// 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 ff ff \n" +
			// "00 01 00 01 00 01 04 02 c4 02 c1 00 00 00 00 03 00 82 03 f4 00 06 00 00 00
			// 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 0f 00
			// 00 00 fe a7 00 11 00 06 00 02 42 b1 06 00 00 00 00 06 00 02 42 b1 05 00 02 42
			// b1 06 00 00 0d a2 06 00 00 37 ac 06 00 02 51 48 06 00 00 00 00 06 00 00 81 53
			// 06 00 01 98 ea 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06
			// 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe
			// a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08
			// 1e 0a 0b ff 0f 1e 00 00 fe a7 00 11 00 06 00 02 42 ba 06 00 00 00 00 06 00 02
			// 42 ba 05 00 02 42 ba 06 00 00 0d a2 06 00 00 37 b0 06 00 02 51 53 06 00 00 00
			// 00 06 00 00 81 53 06 00 01 98 f3 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00
			// 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07
			// 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff
			// 09 02 16 09 0c 08 1e 0a 0b ff 10 00 00 00 fe a7 00 11 00 06 00 02 42 ce 06 00
			// 00 00 00 06 00 02 42 ce 05 00 02 42 ce 06 00 00 0d a2 06 00 00 37 b4 06 00 02
			// 51 68 06 00 00 00 00 06 00 00 81 53 06 00 01 99 08 06 00 00 29 b9 06 00 00 00
			// 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd
			// 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff
			// ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 10 1e 00 00 fe a7 00 11 00 06
			// 00 02 43 0e 06 00 00 00 00 06 00 02 43 0e 05 00 02 43 0e 06 00 00 0d a3 06 00
			// 00 37 b6 06 00 02 51 a8 06 00 00 00 00 06 00 00 81 53 06 00 01 99 47 06 00 00
			// 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00
			// 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09
			// 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 11 00 00 00
			// fe a7 00 11 00 06 00 02 43 55 06 00 00 00 00 06 00 02 43 55 05 00 02 43 55 06
			// 00 00 0d a5 06 00 00 37 b6 06 00 02 51 ef 06 00 00 00 00 06 00 00 81 53 06 00
			// 01 99 8e 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00
			// 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00
			// 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a
			// 0b ff 11 1e 00 00 fe a7 00 11 00 06 00 02 43 8d 06 00 00 00 00 06 00 02 43 8d
			// 05 00 02 43 8d 06 00 00 0d a6 06 00 00 37 b7 06 00 02 52 27 06 00 00 00 00 06
			// 00 00 81 8b 06 00 01 99 8e 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00
			// 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f
			// 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02
			// 16 09 0c 08 1e 0a 0b ff 12 00 00 00 fe a7 00 11 00 06 00 02 43 96 06 00 00 00
			// 00 06 00 02 43 96 05 00 02 43 96 06 00 00 0d a6 06 00 00 37 ba 06 00 02 52 31
			// 06 00 00 00 00 06 00 00 81 94 06 00 01 99 8e 06 00 00 29 b9 06 00 00 00 00 06
			// 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c
			// 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff
			// ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 12 1e 00 00 fe a7 00 11 00 06 00 02
			// 43 99 06 00 00 00 00 06 00 02 43 99 05 00 02 43 99 06 00 ff ff \n" +
			// "00 01 00 01 00 01 04 02 c4 02 c1 00 00 00 00 04 00 82 03 f4 00 0d a6 06 00
			// 00 37 bb 06 00 02 52 34 06 00 00 00 00 06 00 00 81 97 06 00 01 99 8e 06 00 00
			// 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00
			// 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09
			// 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 13 00 00 00
			// fe a7 00 11 00 06 00 02 43 9c 06 00 00 00 00 06 00 02 43 9c 05 00 02 43 9c 06
			// 00 00 0d a6 06 00 00 37 bb 06 00 02 52 36 06 00 00 00 00 06 00 00 81 99 06 00
			// 01 99 8e 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00
			// 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00
			// 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a
			// 0b ff 13 1e 00 00 fe a7 00 11 00 06 00 02 43 9e 06 00 00 00 00 06 00 02 43 9e
			// 05 00 02 43 9e 06 00 00 0d a6 06 00 00 37 bb 06 00 02 52 39 06 00 00 00 00 06
			// 00 00 81 9c 06 00 01 99 8e 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00
			// 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f
			// 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02
			// 16 09 0c 08 1e 0a 0b ff 14 00 00 00 fe a7 00 11 00 06 00 02 43 a1 06 00 00 00
			// 00 06 00 02 43 a1 05 00 02 43 a1 06 00 00 0d a6 06 00 00 37 bb 06 00 02 52 3c
			// 06 00 00 00 00 06 00 00 81 9f 06 00 01 99 8e 06 00 00 29 b9 06 00 00 00 00 06
			// 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c
			// 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff
			// ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 14 1e 00 00 fe a7 00 11 00 06 00 02
			// 43 a4 06 00 00 00 00 06 00 02 43 a4 05 00 02 43 a4 06 00 00 0d a6 06 00 00 37
			// bb 06 00 02 52 3e 06 00 00 00 00 06 00 00 81 a1 06 00 01 99 8e 06 00 00 29 b9
			// 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06
			// 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00 00 00 00 09 0c ff
			// ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff 15 00 00 00 fe a7
			// 00 11 00 06 00 02 43 a6 06 00 00 00 00 06 00 02 43 a6 05 00 02 43 a6 06 00 00
			// 0d a6 06 00 00 37 bb 06 00 02 52 41 06 00 00 00 00 06 00 00 81 a4 06 00 01 99
			// 8e 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00
			// 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00 00 fe a7 00 06 00
			// 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09 0c 08 1e 0a 0b ff
			// 15 1e 00 00 fe a7 00 11 00 06 00 02 43 a9 06 00 00 00 00 06 00 02 43 a9 05 00
			// 02 43 a9 06 00 00 0d a6 06 00 00 37 bb 06 00 02 52 44 06 00 00 00 00 06 00 00
			// 81 a6 06 00 01 99 8e 06 00 00 29 b9 06 00 00 00 00 06 00 00 00 00 06 00 00 00
			// 00 06 00 00 00 00 06 00 00 00 00 06 00 00 0a bd 09 0c 08 1e 0a 07 05 0f 31 00
			// 00 fe a7 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 16 09
			// 0c 08 1e 0a 0b ff 16 00 00 00 fe a7 00 11 00 06 00 02 43 ab 06 00 00 00 00 06
			// 00 02 43 ab 05 00 02 43 ab 06 00 00 0d a6 06 00 00 37 bb 06 00 02 52 46 06 00
			// 00 00 00 06 00 00 81 a9 06 00 01 99 8e 06 00 00 29 b9 06 00 00 00 00 06 00 00
			// 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 ff ff \n" +
			// "00 01 00 70 00 01 00 1f c2 01 0c 08 1e 0b 14 05 03 10 09 ff 80 00 02 00 01
			// 00 00 61 8a 00 ff 02 06 02 00 00 00 ff ff \n" +
			// "00 01 00 01 00 01 00 0c c4 02 c1 01 1e 0b 14 05 01 13 ff ff ";
			//
			// new LoadProfile1DataParser().processLoadProfileObisData(obisData);

			System.out.println(Integer.decode("0x" + "070b"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
