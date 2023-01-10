/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parser.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author Veera
 */
@Entity
@Table(name = "BILLING_HISTORY", schema = "SMART_HES")
public class BillingHistory {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "METER_NUMBER")
    private String meterNumber;

    @Column(name = "BILLING_MONTH")
    private LocalDateTime billingMonth;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "BillingHistory{" + "id=" + id + ", meterNumber=" + meterNumber + ", billingMonth=" + billingMonth + ", updatedAt=" + updatedAt + '}';
    }
    
}
