package com.hes.data.parser.vo;

import java.sql.Timestamp;

/**
 * 
 * @author psvr
 *
 */
public class BillingDataId implements java.io.Serializable {

	private static final long serialVersionUID = 1428289234046077341L;
	
	private Timestamp billingDatetime;
	private String meterSerialNumber;

	// Constructors

	/** default constructor */
	public BillingDataId() {
	}

	/** full constructor */
	public BillingDataId(Timestamp billingDatetime, String meterSerialNumber) {
		this.billingDatetime = billingDatetime;
		this.meterSerialNumber = meterSerialNumber;
	}

	// Property accessors

	public Timestamp getBillingDatetime() {
		return this.billingDatetime;
	}

	public void setBillingDatetime(Timestamp billingDatetime) {
		this.billingDatetime = billingDatetime;
	}

	public String getMeterSerialNumber() {
		return this.meterSerialNumber;
	}

	public void setMeterSerialNumber(String meterSerialNumber) {
		this.meterSerialNumber = meterSerialNumber;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BillingDataId))
			return false;
		BillingDataId castOther = (BillingDataId) other;

		return ((this.getBillingDatetime() == castOther.getBillingDatetime()) || (this
				.getBillingDatetime() != null
				&& castOther.getBillingDatetime() != null && this
				.getBillingDatetime().equals(castOther.getBillingDatetime())))
				&& ((this.getMeterSerialNumber() == castOther
						.getMeterSerialNumber()) || (this
						.getMeterSerialNumber() != null
						&& castOther.getMeterSerialNumber() != null && this
						.getMeterSerialNumber().equals(
								castOther.getMeterSerialNumber())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getBillingDatetime() == null ? 0 : this.getBillingDatetime()
						.hashCode());
		result = 37
				* result
				+ (getMeterSerialNumber() == null ? 0 : this
						.getMeterSerialNumber().hashCode());
		return result;
	}

}