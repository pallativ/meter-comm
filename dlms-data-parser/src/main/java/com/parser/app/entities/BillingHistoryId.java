package com.parser.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Veera
 */

@Embeddable
public class BillingHistoryId implements Serializable {
    @Column(name = "METER_SERIAL_NUMBER")
    private String meterNumber;

    @Column(name = "BILLING_DATETIME")
    private LocalDateTime billingMonth;

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public LocalDateTime getBillingMonth() {
        return billingMonth;
    }

    public void setBillingMonth(LocalDateTime billingMonth) {
        this.billingMonth = billingMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillingHistoryId that = (BillingHistoryId) o;

        if (!Objects.equals(meterNumber, that.meterNumber)) return false;
        return Objects.equals(billingMonth, that.billingMonth);
    }

    @Override
    public int hashCode() {
        int result = meterNumber != null ? meterNumber.hashCode() : 0;
        result = 31 * result + (billingMonth != null ? billingMonth.hashCode() : 0);
        return result;
    }
}
