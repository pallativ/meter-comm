package com.hes.lis.mltd.vo;

import java.sql.Timestamp;

/**
 * MeterReplica entity. @author MyEclipse Persistence Tools
 */

public class MeterReplica implements java.io.Serializable {

	private static final long serialVersionUID = 8182862221581260255L;
	
	private String meterSerialNumber;
	private String signalStrength;
	private String manufactureName;
	private String meterFirmwareVersion;
	private String meterProgramFirmWareVersion;
	private String meterType;
	private String modemFirmwareVersion;
	private String modemImeiNumber;
	private String simDialNumber;
	private String simSerialNumber;
	private Timestamp lastMeterDateTime;
	private String relayStatus;
	private Timestamp lastInstantDatetime;
	private Timestamp lastBillingDatetime;
	private Timestamp lastLoadsurveyDatetime;
	private Timestamp lastMidnightSnapDatetime;
	private Timestamp lastCommDatetime;
	
	private String consumerId;
	private String consumerName;
	private String consumerNo;
	private String forgivenessTime;
	private String mdResetDate;
	private String approvedDemand;
	private String demandIntegrationPeriod;
	private String demandIntegrationPeriodCount;
	private String loadProfileCapturePeriod;
	private String treshHoldValue;
	private String apnName;
	private String ipAddress;
	private String portNumber;
	private String gprsIntervalTime;
	private String heartBeat;

	// Constructors

	/** default constructor */
	public MeterReplica() {
	}

	/** minimal constructor */
	public MeterReplica(String meterSerialNumber) {
		this.meterSerialNumber = meterSerialNumber;
	}

	/** full constructor */
	public MeterReplica(String meterSerialNumber, String signalStrength,
			String manufactureName, String meterFirmwareVersion,
			String meterProgramFirmWareVersion, String meterType,
			String modemFirmwareVersion, String modemImeiNumber,
			String simDialNumber, String simSerialNumber,
			Timestamp lastMeterDateTime, String relayStatus,
			Timestamp lastInstantDatetime, Timestamp lastBillingDatetime,
			Timestamp lastLoadsurveyDatetime,
			Timestamp lastMidnightSnapDatetime, Timestamp lastCommDatetime) {
		this.meterSerialNumber = meterSerialNumber;
		this.signalStrength = signalStrength;
		this.manufactureName = manufactureName;
		this.meterFirmwareVersion = meterFirmwareVersion;
		this.meterProgramFirmWareVersion = meterProgramFirmWareVersion;
		this.meterType = meterType;
		this.modemFirmwareVersion = modemFirmwareVersion;
		this.modemImeiNumber = modemImeiNumber;
		this.simDialNumber = simDialNumber;
		this.simSerialNumber = simSerialNumber;
		this.lastMeterDateTime = lastMeterDateTime;
		this.relayStatus = relayStatus;
		this.lastInstantDatetime = lastInstantDatetime;
		this.lastBillingDatetime = lastBillingDatetime;
		this.lastLoadsurveyDatetime = lastLoadsurveyDatetime;
		this.lastMidnightSnapDatetime = lastMidnightSnapDatetime;
		this.lastCommDatetime = lastCommDatetime;
	}

	// Property accessors

	public String getMeterSerialNumber() {
		return this.meterSerialNumber;
	}

	public void setMeterSerialNumber(String meterSerialNumber) {
		this.meterSerialNumber = meterSerialNumber;
	}

	public String getSignalStrength() {
		return this.signalStrength;
	}

	public void setSignalStrength(String signalStrength) {
		this.signalStrength = signalStrength;
	}

	public String getManufactureName() {
		return this.manufactureName;
	}

	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

	public String getMeterFirmwareVersion() {
		return this.meterFirmwareVersion;
	}

	public void setMeterFirmwareVersion(String meterFirmwareVersion) {
		this.meterFirmwareVersion = meterFirmwareVersion;
	}

	public String getMeterProgramFirmWareVersion() {
		return this.meterProgramFirmWareVersion;
	}

	public void setMeterProgramFirmWareVersion(
			String meterProgramFirmWareVersion) {
		this.meterProgramFirmWareVersion = meterProgramFirmWareVersion;
	}

	public String getMeterType() {
		return this.meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public String getModemFirmwareVersion() {
		return this.modemFirmwareVersion;
	}

	public void setModemFirmwareVersion(String modemFirmwareVersion) {
		this.modemFirmwareVersion = modemFirmwareVersion;
	}

	public String getModemImeiNumber() {
		return this.modemImeiNumber;
	}

	public void setModemImeiNumber(String modemImeiNumber) {
		this.modemImeiNumber = modemImeiNumber;
	}

	public String getSimDialNumber() {
		return this.simDialNumber;
	}

	public void setSimDialNumber(String simDialNumber) {
		this.simDialNumber = simDialNumber;
	}

	public String getSimSerialNumber() {
		return this.simSerialNumber;
	}

	public void setSimSerialNumber(String simSerialNumber) {
		this.simSerialNumber = simSerialNumber;
	}

	public Timestamp getLastMeterDateTime() {
		return this.lastMeterDateTime;
	}

	public void setLastMeterDateTime(Timestamp lastMeterDateTime) {
		this.lastMeterDateTime = lastMeterDateTime;
	}

	public String getRelayStatus() {
		return this.relayStatus;
	}

	public void setRelayStatus(String relayStatus) {
		this.relayStatus = relayStatus;
	}

	public Timestamp getLastInstantDatetime() {
		return this.lastInstantDatetime;
	}

	public void setLastInstantDatetime(Timestamp lastInstantDatetime) {
		this.lastInstantDatetime = lastInstantDatetime;
	}

	public Timestamp getLastBillingDatetime() {
		return this.lastBillingDatetime;
	}

	public void setLastBillingDatetime(Timestamp lastBillingDatetime) {
		this.lastBillingDatetime = lastBillingDatetime;
	}

	public Timestamp getLastLoadsurveyDatetime() {
		return this.lastLoadsurveyDatetime;
	}

	public void setLastLoadsurveyDatetime(Timestamp lastLoadsurveyDatetime) {
		this.lastLoadsurveyDatetime = lastLoadsurveyDatetime;
	}

	public Timestamp getLastMidnightSnapDatetime() {
		return this.lastMidnightSnapDatetime;
	}

	public void setLastMidnightSnapDatetime(Timestamp lastMidnightSnapDatetime) {
		this.lastMidnightSnapDatetime = lastMidnightSnapDatetime;
	}

	public Timestamp getLastCommDatetime() {
		return this.lastCommDatetime;
	}

	public void setLastCommDatetime(Timestamp lastCommDatetime) {
		this.lastCommDatetime = lastCommDatetime;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getConsumerNo() {
		return consumerNo;
	}

	public void setConsumerNo(String consumerNo) {
		this.consumerNo = consumerNo;
	}

	public String getForgivenessTime() {
		return forgivenessTime;
	}

	public void setForgivenessTime(String forgivenessTime) {
		this.forgivenessTime = forgivenessTime;
	}

	public String getMdResetDate() {
		return mdResetDate;
	}

	public void setMdResetDate(String mdResetDate) {
		this.mdResetDate = mdResetDate;
	}

	public String getApprovedDemand() {
		return approvedDemand;
	}

	public void setApprovedDemand(String approvedDemand) {
		this.approvedDemand = approvedDemand;
	}

	public String getDemandIntegrationPeriod() {
		return demandIntegrationPeriod;
	}

	public void setDemandIntegrationPeriod(String demandIntegrationPeriod) {
		this.demandIntegrationPeriod = demandIntegrationPeriod;
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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
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

	public String getDemandIntegrationPeriodCount() {
		return demandIntegrationPeriodCount;
	}

	public void setDemandIntegrationPeriodCount(String demandIntegrationPeriodCount) {
		this.demandIntegrationPeriodCount = demandIntegrationPeriodCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}