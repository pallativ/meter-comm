package com.hes.lis.mltd.vo;

import java.sql.Timestamp;

/**
 * Masterdata entity. @author MyEclipse Persistence Tools
 */

public class Masterdata implements java.io.Serializable {
	
	private static final long serialVersionUID = -2028752884439067173L;
	
	private String meterNo;
	private String discom;
	private String board;
	private String blockNep;
	private String locationMas;
	private String consumerNo;
	private String consumerId;
	private String consumerName;
	private String tariffCategory;
	private String phase;
	private String cdKva;
	private String cdKw;
	private String officeno;
	private String mobileno1;
	private String mobileno2;
	private String email;
	private String parentCompany;
	private String mnfrname;
	private String simnumber;
	private String modemserialno;
	private String mdresetdate;
	private String remarks;
	private String isactive;
	private String customerName;
	private String customerAddress;
	private String customerContactNo;
	private String meterType;
	private String simDialNumber;
	private String ipAddress;
	private String portNumber;
	private String lastUsedBy;
	private Timestamp lastUsedDatetime;
	private String lastUsedFor;
	private Integer billingDay;
	private Double approvedDemandVal;
	private String gprsIntervalTime;
	private String heartBeat;
	private String forgivenessTime;
	private String demandIntegrationPeriod;
	private String demandIntegrationPeriodCount;
	private String loadProfileCapturePeriod;
	private String treshHoldValue;
	private String apnName;

	// Constructors

	/** default constructor */
	public Masterdata() {
	}

	/** minimal constructor */
	public Masterdata(String meterNo, String discom, String board,
			String blockNep, String locationMas, String consumerNo,
			String consumerId, String consumerName, String tariffCategory,
			String phase, String cdKva, String cdKw, String mdresetdate) {
		this.meterNo = meterNo;
		this.discom = discom;
		this.board = board;
		this.blockNep = blockNep;
		this.locationMas = locationMas;
		this.consumerNo = consumerNo;
		this.consumerId = consumerId;
		this.consumerName = consumerName;
		this.tariffCategory = tariffCategory;
		this.phase = phase;
		this.cdKva = cdKva;
		this.cdKw = cdKw;
		this.mdresetdate = mdresetdate;
	}

	/** full constructor */
	public Masterdata(String meterNo, String discom, String board,
			String blockNep, String locationMas, String consumerNo,
			String consumerId, String consumerName, String tariffCategory,
			String phase, String cdKva, String cdKw, String officeno,
			String mobileno1, String mobileno2, String email,
			String parentCompany, String mnfrname, String simnumber,
			String modemserialno, String mdresetdate, String remarks,
			String isactive, String customerName, String customerAddress,
			String customerContactNo, String meterType, String simDialNumber,
			String ipAddress, String portNumber, String lastUsedBy,
			Timestamp lastUsedDatetime, String lastUsedFor,Integer billingDay,Double approvedDemandVal) {
		this.meterNo = meterNo;
		this.discom = discom;
		this.board = board;
		this.blockNep = blockNep;
		this.locationMas = locationMas;
		this.consumerNo = consumerNo;
		this.consumerId = consumerId;
		this.consumerName = consumerName;
		this.tariffCategory = tariffCategory;
		this.phase = phase;
		this.cdKva = cdKva;
		this.cdKw = cdKw;
		this.officeno = officeno;
		this.mobileno1 = mobileno1;
		this.mobileno2 = mobileno2;
		this.email = email;
		this.parentCompany = parentCompany;
		this.mnfrname = mnfrname;
		this.simnumber = simnumber;
		this.modemserialno = modemserialno;
		this.mdresetdate = mdresetdate;
		this.remarks = remarks;
		this.isactive = isactive;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerContactNo = customerContactNo;
		this.meterType = meterType;
		this.simDialNumber = simDialNumber;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
		this.lastUsedBy = lastUsedBy;
		this.lastUsedDatetime = lastUsedDatetime;
		this.lastUsedFor = lastUsedFor;
		this.billingDay=billingDay;
		this.approvedDemandVal=approvedDemandVal;
	}

	// Property accessors

	public String getMeterNo() {
		return this.meterNo;
	}

	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}

	public String getDiscom() {
		return this.discom;
	}

	public void setDiscom(String discom) {
		this.discom = discom;
	}

	public String getBoard() {
		return this.board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getBlockNep() {
		return this.blockNep;
	}

	public void setBlockNep(String blockNep) {
		this.blockNep = blockNep;
	}

	public String getLocationMas() {
		return this.locationMas;
	}

	public void setLocationMas(String locationMas) {
		this.locationMas = locationMas;
	}

	public String getConsumerNo() {
		return this.consumerNo;
	}

	public void setConsumerNo(String consumerNo) {
		this.consumerNo = consumerNo;
	}

	public String getConsumerId() {
		return this.consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getConsumerName() {
		return this.consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getTariffCategory() {
		return this.tariffCategory;
	}

	public void setTariffCategory(String tariffCategory) {
		this.tariffCategory = tariffCategory;
	}

	public String getPhase() {
		return this.phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getCdKva() {
		return this.cdKva;
	}

	public void setCdKva(String cdKva) {
		this.cdKva = cdKva;
	}

	public String getCdKw() {
		return this.cdKw;
	}

	public void setCdKw(String cdKw) {
		this.cdKw = cdKw;
	}

	public String getOfficeno() {
		return this.officeno;
	}

	public void setOfficeno(String officeno) {
		this.officeno = officeno;
	}

	public String getMobileno1() {
		return this.mobileno1;
	}

	public void setMobileno1(String mobileno1) {
		this.mobileno1 = mobileno1;
	}

	public String getMobileno2() {
		return this.mobileno2;
	}

	public void setMobileno2(String mobileno2) {
		this.mobileno2 = mobileno2;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getParentCompany() {
		return this.parentCompany;
	}

	public void setParentCompany(String parentCompany) {
		this.parentCompany = parentCompany;
	}

	public String getMnfrname() {
		return this.mnfrname;
	}

	public void setMnfrname(String mnfrname) {
		this.mnfrname = mnfrname;
	}

	public String getSimnumber() {
		return this.simnumber;
	}

	public void setSimnumber(String simnumber) {
		this.simnumber = simnumber;
	}

	public String getModemserialno() {
		return this.modemserialno;
	}

	public void setModemserialno(String modemserialno) {
		this.modemserialno = modemserialno;
	}

	public String getMdresetdate() {
		return this.mdresetdate;
	}

	public void setMdresetdate(String mdresetdate) {
		this.mdresetdate = mdresetdate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return this.customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerContactNo() {
		return this.customerContactNo;
	}

	public void setCustomerContactNo(String customerContactNo) {
		this.customerContactNo = customerContactNo;
	}

	public String getMeterType() {
		return this.meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public String getSimDialNumber() {
		return this.simDialNumber;
	}

	public void setSimDialNumber(String simDialNumber) {
		this.simDialNumber = simDialNumber;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPortNumber() {
		return this.portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public String getLastUsedBy() {
		return this.lastUsedBy;
	}

	public void setLastUsedBy(String lastUsedBy) {
		this.lastUsedBy = lastUsedBy;
	}

	public Timestamp getLastUsedDatetime() {
		return this.lastUsedDatetime;
	}

	public void setLastUsedDatetime(Timestamp lastUsedDatetime) {
		this.lastUsedDatetime = lastUsedDatetime;
	}

	public String getLastUsedFor() {
		return this.lastUsedFor;
	}

	public void setLastUsedFor(String lastUsedFor) {
		this.lastUsedFor = lastUsedFor;
	}

	public Integer getBillingDay() {
		return billingDay;
	}

	public void setBillingDay(Integer billingDay) {
		this.billingDay = billingDay;
	}

	public Double getApprovedDemandVal() {
		return approvedDemandVal;
	}

	public void setApprovedDemandVal(Double approvedDemandVal) {
		this.approvedDemandVal = approvedDemandVal;
	}

	public String getGprsIntervalTime() {
		return gprsIntervalTime;
	}

	public void setGprsIntervalTime(String gprsIntervalTime) {
		this.gprsIntervalTime = gprsIntervalTime;
	}

	public String getHeartBeat() {
		return heartBeat;
	}

	public void setHeartBeat(String heartBeat) {
		this.heartBeat = heartBeat;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getForgivenessTime() {
		return forgivenessTime;
	}

	public void setForgivenessTime(String forgivenessTime) {
		this.forgivenessTime = forgivenessTime;
	}

	public String getDemandIntegrationPeriod() {
		return demandIntegrationPeriod;
	}

	public void setDemandIntegrationPeriod(String demandIntegrationPeriod) {
		this.demandIntegrationPeriod = demandIntegrationPeriod;
	}

	public String getDemandIntegrationPeriodCount() {
		return demandIntegrationPeriodCount;
	}

	public void setDemandIntegrationPeriodCount(String demandIntegrationPeriodCount) {
		this.demandIntegrationPeriodCount = demandIntegrationPeriodCount;
	}

	public String getLoadProfileCapturePeriod() {
		return loadProfileCapturePeriod;
	}

	public void setLoadProfileCapturePeriod(String loadProfileCapturePeriod) {
		this.loadProfileCapturePeriod = loadProfileCapturePeriod;
	}

	public String getTreshHoldValue() {
		return treshHoldValue;
	}

	public void setTreshHoldValue(String treshHoldValue) {
		this.treshHoldValue = treshHoldValue;
	}

	public String getApnName() {
		return apnName;
	}

	public void setApnName(String apnName) {
		this.apnName = apnName;
	}

}