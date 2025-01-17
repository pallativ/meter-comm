/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parser.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "BILLING_DATA")
public class BillingHistory {
    @EmbeddedId
    private BillingHistoryId id;
    @Column(name = "MODIFIED_DATETIME")
    private LocalDateTime updatedAt;
    @Column(name = "ACT_EGY_IMP")
    private float activeEnergyImport;
    @Column(name = "ACT_EGY_EXP")
    private float activeEnergyExport;
    @Column(name = "ACT_EGY_COMBINED_TOTAL")
    private float activeEnergyCombinedTotal;
    @Column(name = "ACT_EGY_NET_TOTAL")
    private float activeEnergyNetTotal;
    @Column(name = "ACT_EGY_IMP_RT_1")
    private float activeEnergyImportT1;
    @Column(name = "ACT_EGY_IMP_RT_2")
    private float activeEnergyImportT2;
    @Column(name = "ACT_EGY_IMP_RT_3")
    private float activeEnergyImportT3;
    @Column(name = "ACT_EGY_IMP_RT_4")
    private float activeEnergyImportT4;
    @Column(name = "ACT_EGY_EXP_RT_1")
    private float activeEnergyExportT1;
    @Column(name = "ACT_EGY_EXP_RT_2")
    private float activeEnergyExportT2;
    @Column(name = "ACT_EGY_EXP_RT_3")
    private float activeEnergyExportT3;
    @Column(name = "ACT_EGY_EXP_RT_4")
    private float activeEnergyExportT4;
    @Column(name = "APP_EGY_IMP")
    private float apparentEnergyImport;
    @Column(name = "APP_EGY_EXP")
    private float apparentEnergyExport;
    @Column(name = "APP_EGY_IMP_RT_1")
    private float apparentEnergyImportT1;
    @Column(name = "APP_EGY_IMP_RT_2")
    private float apparentEnergyImportT2;
    @Column(name = "APP_EGY_IMP_RT_3")
    private float apparentEnergyImportT3;
    @Column(name = "APP_EGY_IMP_RT_4")
    private float apparentEnergyImportT4;
    @Column(name = "APP_EGY_EXP_RT_1")
    private float apparentEnergyExportT1;
    @Column(name = "APP_EGY_EXP_RT_2")
    private float apparentEnergyExportT2;
    @Column(name = "APP_EGY_EXP_RT_3")
    private float apparentEnergyExportT3;
    @Column(name = "APP_EGY_EXP_RT_4")
    private float apparentEnergyExportT4;
    @Column(name = "ACT_EGY_COMB_TOT_RT_1")
    private float activeEnergyCombinedTotalT1;
    @Column(name = "ACT_EGY_COMB_TOT_RT_2")
    private float activeEnergyCombinedTotalT2;
    @Column(name = "ACT_EGY_COMB_TOT_RT_3")
    private float activeEnergyCombinedTotalT3;
    @Column(name = "ACT_EGY_COMB_TOT_RT_4")
    private float activeEnergyCombinedTotalT4;
    @Column(name = "ACT_EGY_NET_TOT_RT_1")
    private float activeEnergyNetTotalT1;
    @Column(name = "ACT_EGY_NET_TOT_RT_2")
    private float activeEnergyNetTotalT2;
    @Column(name = "ACT_EGY_NET_TOT_RT_3")
    private float activeEnergyNetTotalT3;
    @Column(name = "ACT_EGY_NET_TOT_RT_4")
    private float activeEnergyNetTotalT4;
    @Column(name = "MAX_DMD_REG_1_ACT_EGY_IMP")
    private float maxActivePowerImport;
    @Column(name = "MAX_DMD_REG_1_ACT_EGY_IMP_TIME")
    private LocalDateTime maxActivePowerImportAt;
    @Column(name = "MAX_DMD_REG_6_ACT_EGY_EXP")
    private float maxActivePowerExport;
    @Column(name = "MAX_DMD_REG_6_ACT_EGY_EXP_TIME")
    private LocalDateTime maxActivePowerExportAt;
    @Column(name = "APP_MD_EXP")
    private float maxApparentPowerImport;
    @Column(name = "APP_MD_EXP_OCCURRING_TIME")
    private LocalDateTime maxApparentPowerImportAt;
    @Column(name = "APP_MD_IMP")
    private float maxApparentPowerExport;
    @Column(name = "APP_MD_IMP_OCCURRING_TIME")
    private LocalDateTime maxApparentPowerExportAt;

    public BillingHistoryId getId() {
        return id;
    }

    public void setId(BillingHistoryId id) {
        this.id = id;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public float getActiveEnergyImport() {
        return activeEnergyImport;
    }

    public void setActiveEnergyImport(float activeEnergyImport) {
        this.activeEnergyImport = activeEnergyImport;
    }

    public float getActiveEnergyExport() {
        return activeEnergyExport;
    }

    public void setActiveEnergyExport(float activeEnergyExport) {
        this.activeEnergyExport = activeEnergyExport;
    }

    public float getActiveEnergyCombinedTotal() {
        return activeEnergyCombinedTotal;
    }

    public void setActiveEnergyCombinedTotal(float activeEnergyCombinedTotal) {
        this.activeEnergyCombinedTotal = activeEnergyCombinedTotal;
    }

    public float getActiveEnergyNetTotal() {
        return activeEnergyNetTotal;
    }

    public void setActiveEnergyNetTotal(float activeEnergyNetTotal) {
        this.activeEnergyNetTotal = activeEnergyNetTotal;
    }

    public float getActiveEnergyImportT1() {
        return activeEnergyImportT1;
    }

    public void setActiveEnergyImportT1(float activeEnergyImportT1) {
        this.activeEnergyImportT1 = activeEnergyImportT1;
    }

    public float getActiveEnergyImportT2() {
        return activeEnergyImportT2;
    }

    public void setActiveEnergyImportT2(float activeEnergyImportT2) {
        this.activeEnergyImportT2 = activeEnergyImportT2;
    }

    public float getActiveEnergyImportT3() {
        return activeEnergyImportT3;
    }

    public void setActiveEnergyImportT3(float activeEnergyImportT3) {
        this.activeEnergyImportT3 = activeEnergyImportT3;
    }

    public float getActiveEnergyImportT4() {
        return activeEnergyImportT4;
    }

    public void setActiveEnergyImportT4(float activeEnergyImportT4) {
        this.activeEnergyImportT4 = activeEnergyImportT4;
    }

    public float getActiveEnergyExportT1() {
        return activeEnergyExportT1;
    }

    public void setActiveEnergyExportT1(float activeEnergyExportT1) {
        this.activeEnergyExportT1 = activeEnergyExportT1;
    }

    public float getActiveEnergyExportT2() {
        return activeEnergyExportT2;
    }

    public void setActiveEnergyExportT2(float activeEnergyExportT2) {
        this.activeEnergyExportT2 = activeEnergyExportT2;
    }

    public float getActiveEnergyExportT3() {
        return activeEnergyExportT3;
    }

    public void setActiveEnergyExportT3(float activeEnergyExportT3) {
        this.activeEnergyExportT3 = activeEnergyExportT3;
    }

    public float getActiveEnergyExportT4() {
        return activeEnergyExportT4;
    }

    public void setActiveEnergyExportT4(float activeEnergyExportT4) {
        this.activeEnergyExportT4 = activeEnergyExportT4;
    }

    public float getApparentEnergyImport() {
        return apparentEnergyImport;
    }

    public void setApparentEnergyImport(float apparentEnergyImport) {
        this.apparentEnergyImport = apparentEnergyImport;
    }

    public float getApparentEnergyExport() {
        return apparentEnergyExport;
    }

    public void setApparentEnergyExport(float apparentEnergyExport) {
        this.apparentEnergyExport = apparentEnergyExport;
    }

    public float getApparentEnergyImportT1() {
        return apparentEnergyImportT1;
    }

    public void setApparentEnergyImportT1(float apparentEnergyImportT1) {
        this.apparentEnergyImportT1 = apparentEnergyImportT1;
    }

    public float getApparentEnergyImportT2() {
        return apparentEnergyImportT2;
    }

    public void setApparentEnergyImportT2(float apparentEnergyImportT2) {
        this.apparentEnergyImportT2 = apparentEnergyImportT2;
    }

    public float getApparentEnergyImportT3() {
        return apparentEnergyImportT3;
    }

    public void setApparentEnergyImportT3(float apparentEnergyImportT3) {
        this.apparentEnergyImportT3 = apparentEnergyImportT3;
    }

    public float getApparentEnergyImportT4() {
        return apparentEnergyImportT4;
    }

    public void setApparentEnergyImportT4(float apparentEnergyImportT4) {
        this.apparentEnergyImportT4 = apparentEnergyImportT4;
    }

    public float getApparentEnergyExportT1() {
        return apparentEnergyExportT1;
    }

    public void setApparentEnergyExportT1(float apparentEnergyExportT1) {
        this.apparentEnergyExportT1 = apparentEnergyExportT1;
    }

    public float getApparentEnergyExportT2() {
        return apparentEnergyExportT2;
    }

    public void setApparentEnergyExportT2(float apparentEnergyExportT2) {
        this.apparentEnergyExportT2 = apparentEnergyExportT2;
    }

    public float getApparentEnergyExportT3() {
        return apparentEnergyExportT3;
    }

    public void setApparentEnergyExportT3(float apparentEnergyExportT3) {
        this.apparentEnergyExportT3 = apparentEnergyExportT3;
    }

    public float getApparentEnergyExportT4() {
        return apparentEnergyExportT4;
    }

    public void setApparentEnergyExportT4(float apparentEnergyExportT4) {
        this.apparentEnergyExportT4 = apparentEnergyExportT4;
    }

    public float getActiveEnergyCombinedTotalT1() {
        return activeEnergyCombinedTotalT1;
    }

    public void setActiveEnergyCombinedTotalT1(float activeEnergyCombinedTotalT1) {
        this.activeEnergyCombinedTotalT1 = activeEnergyCombinedTotalT1;
    }

    public float getActiveEnergyCombinedTotalT2() {
        return activeEnergyCombinedTotalT2;
    }

    public void setActiveEnergyCombinedTotalT2(float activeEnergyCombinedTotalT2) {
        this.activeEnergyCombinedTotalT2 = activeEnergyCombinedTotalT2;
    }

    public float getActiveEnergyCombinedTotalT3() {
        return activeEnergyCombinedTotalT3;
    }

    public void setActiveEnergyCombinedTotalT3(float activeEnergyCombinedTotalT3) {
        this.activeEnergyCombinedTotalT3 = activeEnergyCombinedTotalT3;
    }

    public float getActiveEnergyCombinedTotalT4() {
        return activeEnergyCombinedTotalT4;
    }

    public void setActiveEnergyCombinedTotalT4(float activeEnergyCombinedTotalT4) {
        this.activeEnergyCombinedTotalT4 = activeEnergyCombinedTotalT4;
    }

    public float getActiveEnergyNetTotalT1() {
        return activeEnergyNetTotalT1;
    }

    public void setActiveEnergyNetTotalT1(float activeEnergyNetTotalT1) {
        this.activeEnergyNetTotalT1 = activeEnergyNetTotalT1;
    }

    public float getActiveEnergyNetTotalT2() {
        return activeEnergyNetTotalT2;
    }

    public void setActiveEnergyNetTotalT2(float activeEnergyNetTotalT2) {
        this.activeEnergyNetTotalT2 = activeEnergyNetTotalT2;
    }

    public float getActiveEnergyNetTotalT3() {
        return activeEnergyNetTotalT3;
    }

    public void setActiveEnergyNetTotalT3(float activeEnergyNetTotalT3) {
        this.activeEnergyNetTotalT3 = activeEnergyNetTotalT3;
    }

    public float getActiveEnergyNetTotalT4() {
        return activeEnergyNetTotalT4;
    }

    public void setActiveEnergyNetTotalT4(float activeEnergyNetTotalT4) {
        this.activeEnergyNetTotalT4 = activeEnergyNetTotalT4;
    }

    public float getMaxActivePowerImport() {
        return maxActivePowerImport;
    }

    public void setMaxActivePowerImport(float maxActivePowerImport) {
        this.maxActivePowerImport = maxActivePowerImport;
    }

    public LocalDateTime getMaxActivePowerImportAt() {
        return maxActivePowerImportAt;
    }

    public void setMaxActivePowerImportAt(LocalDateTime maxActivePowerImportAt) {
        this.maxActivePowerImportAt = maxActivePowerImportAt;
    }

    public float getMaxActivePowerExport() {
        return maxActivePowerExport;
    }

    public void setMaxActivePowerExport(float maxActivePowerExport) {
        this.maxActivePowerExport = maxActivePowerExport;
    }

    public LocalDateTime getMaxActivePowerExportAt() {
        return maxActivePowerExportAt;
    }

    public void setMaxActivePowerExportAt(LocalDateTime maxActivePowerExportAt) {
        this.maxActivePowerExportAt = maxActivePowerExportAt;
    }

    public float getMaxApparentPowerImport() {
        return maxApparentPowerImport;
    }

    public void setMaxApparentPowerImport(float maxApparentPowerImport) {
        this.maxApparentPowerImport = maxApparentPowerImport;
    }

    public LocalDateTime getMaxApparentPowerImportAt() {
        return maxApparentPowerImportAt;
    }

    public void setMaxApparentPowerImportAt(LocalDateTime maxApparentPowerImportAt) {
        this.maxApparentPowerImportAt = maxApparentPowerImportAt;
    }

    public float getMaxApparentPowerExport() {
        return maxApparentPowerExport;
    }

    public void setMaxApparentPowerExport(float maxApparentPowerExport) {
        this.maxApparentPowerExport = maxApparentPowerExport;
    }

    public LocalDateTime getMaxApparentPowerExportAt() {
        return maxApparentPowerExportAt;
    }

    public void setMaxApparentPowerExportAt(LocalDateTime maxApparentPowerExportAt) {
        this.maxApparentPowerExportAt = maxApparentPowerExportAt;
    }

    @Override
    public String toString() {
        return "BillingHistory{"
                + " meterNumber=" + id.getMeterNumber() + ", \n"
                + "billingMonth=" + id.getBillingMonth() + ", \n"
                + "updatedAt=" + updatedAt + ", \n"
                + "activeEnergyImport=" + activeEnergyImport + ", \n"
                + "activeEnergyExport=" + activeEnergyExport + ", "
                + "activeEnergyCombinedTotal=" + activeEnergyCombinedTotal + ", \n"
                + "activeEnergyNetTotal=" + activeEnergyNetTotal + ", \n"
                + "activeEnergyImportT1=" + activeEnergyImportT1 + ", \n"
                + "activeEnergyImportT2=" + activeEnergyImportT2 + ", \n"
                + "activeEnergyImportT3=" + activeEnergyImportT3 + ", \n"
                + "activeEnergyImportT4=" + activeEnergyImportT4 + ", \n"
                + "activeEnergyExportT1=" + activeEnergyExportT1 + ", \n"
                + "activeEnergyExportT2=" + activeEnergyExportT2 + ", \n"
                + "activeEnergyExportT3=" + activeEnergyExportT3 + ", \n"
                + "activeEnergyExportT4=" + activeEnergyExportT4 + ", \n"
                + "apparentEnergyImport=" + apparentEnergyImport + ", \n"
                + "apparentEnergyExport=" + apparentEnergyExport + ", \n"
                + "apparentEnergyImportT1=" + apparentEnergyImportT1 + ", \n"
                + "apparentEnergyImportT2=" + apparentEnergyImportT2 + ", \n"
                + "apparentEnergyImportT3=" + apparentEnergyImportT3 + ", \n"
                + "apparentEnergyImportT4=" + apparentEnergyImportT4 + ", \n"
                + "apparentEnergyExportT1=" + apparentEnergyExportT1 + ", \n"
                + "apparentEnergyExportT2=" + apparentEnergyExportT2 + ", \n"
                + "apparentEnergyExportT3=" + apparentEnergyExportT3 + ", \n"
                + "apparentEnergyExportT4=" + apparentEnergyExportT4 + ", \n"
                + "activeEnergyCombinedTotalT1=" + activeEnergyCombinedTotalT1 + ", \n"
                + "activeEnergyCombinedTotalT2=" + activeEnergyCombinedTotalT2 + ", \n"
                + "activeEnergyCombinedTotalT3=" + activeEnergyCombinedTotalT3 + ", \n"
                + "activeEnergyCombinedTotalT4=" + activeEnergyCombinedTotalT4 + ", \n"
                + "activeEnergyNetTotalT1=" + activeEnergyNetTotalT1 + ", \n"
                + "activeEnergyNetTotalT2=" + activeEnergyNetTotalT2 + ", \n"
                + "activeEnergyNetTotalT3=" + activeEnergyNetTotalT3 + ", \n"
                + "activeEnergyNetTotalT4=" + activeEnergyNetTotalT4 + ", \n"
                + "maxActivePowerImport=" + maxActivePowerImport + ", \n"
                + "maxActivePowerImportAt=" + maxActivePowerImportAt + ", \n"
                + "maxActivePowerExport=" + maxActivePowerExport + ", \n"
                + "maxActivePowerExportAt=" + maxActivePowerExportAt + ", \n"
                + "maxApparentPowerImport=" + maxApparentPowerImport + ", \n"
                + "maxApparentPowerImportAt=" + maxApparentPowerImportAt + ", \n"
                + "maxApparentPowerExport=" + maxApparentPowerExport + ", \n"
                + "maxApparentPowerExportAt=" + maxApparentPowerExportAt + '}';
    }

}
