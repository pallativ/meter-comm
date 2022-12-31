package com.hes.data.parser.vo;

import java.sql.Timestamp;

/**
 * 
 * @author psvr
 *
 */
public class InstantDataId implements java.io.Serializable {

	private static final long serialVersionUID = 4376822015944080299L;
	private Timestamp instantDatetime;
	private String meterSerialNumber;

	// Constructors

	/** default constructor */
	public InstantDataId() {
	}

	/** full constructor */
	public InstantDataId(Timestamp instantDatetime, String meterSerialNumber) {
		this.instantDatetime = instantDatetime;
		this.meterSerialNumber = meterSerialNumber;
	}

	// Property accessors

	public Timestamp getInstantDatetime() {
		return this.instantDatetime;
	}

	public void setInstantDatetime(Timestamp instantDatetime) {
		this.instantDatetime = instantDatetime;
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
		if (!(other instanceof InstantDataId))
			return false;
		InstantDataId castOther = (InstantDataId) other;

		return ((this.getInstantDatetime() == castOther.getInstantDatetime()) || (this
				.getInstantDatetime() != null
				&& castOther.getInstantDatetime() != null && this
				.getInstantDatetime().equals(castOther.getInstantDatetime())))
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
				+ (getInstantDatetime() == null ? 0 : this.getInstantDatetime()
						.hashCode());
		result = 37
				* result
				+ (getMeterSerialNumber() == null ? 0 : this
						.getMeterSerialNumber().hashCode());
		return result;
	}

}