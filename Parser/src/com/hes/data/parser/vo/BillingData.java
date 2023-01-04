package com.hes.data.parser.vo;

import java.sql.Timestamp;

/**
 *
 * @author psvr
 *
 */
public class BillingData implements java.io.Serializable {

    private static final long serialVersionUID = -6921674865617104985L;
    private BillingDataId id;
    private Double actEgyImp;
    private Double actEgyExp;
    private Double actEgyCombinedTotal;
    private Double actEgyNetTotal;
    private Double appEgyImp;
    private Double appEgyExp;
    private Double actEgyImpRt1;
    private Double actEgyImpRt2;
    private Double actEgyImpRt3;
    private Double actEgyImpRt4;
    private Double actEgyExpRt1;
    private Double actEgyExpRt2;
    private Double actEgyExpRt3;
    private Double actEgyExpRt4;
    private Double appEgyImpRt1;
    private Double appEgyImpRt2;
    private Double appEgyImpRt3;
    private Double appEgyImpRt4;
    private Double appEgyExpRt1;
    private Double appEgyExpRt2;
    private Double appEgyExpRt3;
    private Double appEgyExpRt4;
    private Double actEgyCombTotRt1;
    private Double actEgyCombTotRt2;
    private Double actEgyCombTotRt3;
    private Double actEgyCombTotRt4;
    private Double actEgyNetTotRt1;
    private Double actEgyNetTotRt2;
    private Double actEgyNetTotRt3;
    private Double actEgyNetTotRt4;
    private Double maxDmdReg1ActEgyImp;
    private Timestamp maxDmdReg1ActEgyImpTime;
    private Double maxDmdReg6ActEgyExp;
    private Timestamp maxDmdReg6ActEgyExpTime;
    private Double appMdImp;
    private Timestamp appMdImpOccurringTime;
    private Double appMdExp;
    private Timestamp appMdExpOccurringTime;
    private Timestamp modifiedDatetime;
    private Timestamp billingDatetimeIst;

    // Constructors
    /**
     * default constructor
     */
    public BillingData() {
    }

    /**
     * minimal constructor
     */
    public BillingData(BillingDataId id) {
        this.id = id;
    }

    /**
     * full constructor
     */
    public BillingData(BillingDataId id, Double actEgyImp, Double actEgyExp,
            Double actEgyCombinedTotal, Double actEgyNetTotal,
            Double appEgyImp, Double appEgyExp, Double actEgyImpRt1,
            Double actEgyImpRt2, Double actEgyImpRt3, Double actEgyImpRt4,
            Double actEgyExpRt1, Double actEgyExpRt2, Double actEgyExpRt3,
            Double actEgyExpRt4, Double appEgyImpRt1, Double appEgyImpRt2,
            Double appEgyImpRt3, Double appEgyImpRt4, Double appEgyExpRt1,
            Double appEgyExpRt2, Double appEgyExpRt3, Double appEgyExpRt4,
            Double actEgyCombTotRt1, Double actEgyCombTotRt2,
            Double actEgyCombTotRt3, Double actEgyCombTotRt4,
            Double actEgyNetTotRt1, Double actEgyNetTotRt2,
            Double actEgyNetTotRt3, Double actEgyNetTotRt4,
            Double maxDmdReg1ActEgyImp, Timestamp maxDmdReg1ActEgyImpTime,
            Double maxDmdReg6ActEgyExp, Timestamp maxDmdReg6ActEgyExpTime,
            Double appMdImp, Timestamp appMdImpOccurringTime, Double appMdExp,
            Timestamp appMdExpOccurringTime, Timestamp modifiedDatetime, Timestamp billingDatetimeIst) {
        this.id = id;
        this.actEgyImp = actEgyImp;
        this.actEgyExp = actEgyExp;
        this.actEgyCombinedTotal = actEgyCombinedTotal;
        this.actEgyNetTotal = actEgyNetTotal;
        this.appEgyImp = appEgyImp;
        this.appEgyExp = appEgyExp;
        this.actEgyImpRt1 = actEgyImpRt1;
        this.actEgyImpRt2 = actEgyImpRt2;
        this.actEgyImpRt3 = actEgyImpRt3;
        this.actEgyImpRt4 = actEgyImpRt4;
        this.actEgyExpRt1 = actEgyExpRt1;
        this.actEgyExpRt2 = actEgyExpRt2;
        this.actEgyExpRt3 = actEgyExpRt3;
        this.actEgyExpRt4 = actEgyExpRt4;
        this.appEgyImpRt1 = appEgyImpRt1;
        this.appEgyImpRt2 = appEgyImpRt2;
        this.appEgyImpRt3 = appEgyImpRt3;
        this.appEgyImpRt4 = appEgyImpRt4;
        this.appEgyExpRt1 = appEgyExpRt1;
        this.appEgyExpRt2 = appEgyExpRt2;
        this.appEgyExpRt3 = appEgyExpRt3;
        this.appEgyExpRt4 = appEgyExpRt4;
        this.actEgyCombTotRt1 = actEgyCombTotRt1;
        this.actEgyCombTotRt2 = actEgyCombTotRt2;
        this.actEgyCombTotRt3 = actEgyCombTotRt3;
        this.actEgyCombTotRt4 = actEgyCombTotRt4;
        this.actEgyNetTotRt1 = actEgyNetTotRt1;
        this.actEgyNetTotRt2 = actEgyNetTotRt2;
        this.actEgyNetTotRt3 = actEgyNetTotRt3;
        this.actEgyNetTotRt4 = actEgyNetTotRt4;
        this.maxDmdReg1ActEgyImp = maxDmdReg1ActEgyImp;
        this.maxDmdReg1ActEgyImpTime = maxDmdReg1ActEgyImpTime;
        this.maxDmdReg6ActEgyExp = maxDmdReg6ActEgyExp;
        this.maxDmdReg6ActEgyExpTime = maxDmdReg6ActEgyExpTime;
        this.appMdImp = appMdImp;
        this.appMdImpOccurringTime = appMdImpOccurringTime;
        this.appMdExp = appMdExp;
        this.appMdExpOccurringTime = appMdExpOccurringTime;
        this.modifiedDatetime = modifiedDatetime;
        this.billingDatetimeIst = billingDatetimeIst;
    }

    // Property accessors
    public BillingDataId getId() {
        return this.id;
    }

    public void setId(BillingDataId id) {
        this.id = id;
    }

    public Double getActEgyImp() {
        return this.actEgyImp;
    }

    public void setActEgyImp(Double actEgyImp) {
        this.actEgyImp = actEgyImp;
    }

    public Double getActEgyExp() {
        return this.actEgyExp;
    }

    public void setActEgyExp(Double actEgyExp) {
        this.actEgyExp = actEgyExp;
    }

    public Double getActEgyCombinedTotal() {
        return this.actEgyCombinedTotal;
    }

    public void setActEgyCombinedTotal(Double actEgyCombinedTotal) {
        this.actEgyCombinedTotal = actEgyCombinedTotal;
    }

    public Double getActEgyNetTotal() {
        return this.actEgyNetTotal;
    }

    public void setActEgyNetTotal(Double actEgyNetTotal) {
        this.actEgyNetTotal = actEgyNetTotal;
    }

    public Double getAppEgyImp() {
        return this.appEgyImp;
    }

    public void setAppEgyImp(Double appEgyImp) {
        this.appEgyImp = appEgyImp;
    }

    public Double getAppEgyExp() {
        return this.appEgyExp;
    }

    public void setAppEgyExp(Double appEgyExp) {
        this.appEgyExp = appEgyExp;
    }

    public Double getActEgyImpRt1() {
        return this.actEgyImpRt1;
    }

    public void setActEgyImpRt1(Double actEgyImpRt1) {
        this.actEgyImpRt1 = actEgyImpRt1;
    }

    public Double getActEgyImpRt2() {
        return this.actEgyImpRt2;
    }

    public void setActEgyImpRt2(Double actEgyImpRt2) {
        this.actEgyImpRt2 = actEgyImpRt2;
    }

    public Double getActEgyImpRt3() {
        return this.actEgyImpRt3;
    }

    public void setActEgyImpRt3(Double actEgyImpRt3) {
        this.actEgyImpRt3 = actEgyImpRt3;
    }

    public Double getActEgyImpRt4() {
        return this.actEgyImpRt4;
    }

    public void setActEgyImpRt4(Double actEgyImpRt4) {
        this.actEgyImpRt4 = actEgyImpRt4;
    }

    public Double getActEgyExpRt1() {
        return this.actEgyExpRt1;
    }

    public void setActEgyExpRt1(Double actEgyExpRt1) {
        this.actEgyExpRt1 = actEgyExpRt1;
    }

    public Double getActEgyExpRt2() {
        return this.actEgyExpRt2;
    }

    public void setActEgyExpRt2(Double actEgyExpRt2) {
        this.actEgyExpRt2 = actEgyExpRt2;
    }

    public Double getActEgyExpRt3() {
        return this.actEgyExpRt3;
    }

    public void setActEgyExpRt3(Double actEgyExpRt3) {
        this.actEgyExpRt3 = actEgyExpRt3;
    }

    public Double getActEgyExpRt4() {
        return this.actEgyExpRt4;
    }

    public void setActEgyExpRt4(Double actEgyExpRt4) {
        this.actEgyExpRt4 = actEgyExpRt4;
    }

    public Double getAppEgyImpRt1() {
        return this.appEgyImpRt1;
    }

    public void setAppEgyImpRt1(Double appEgyImpRt1) {
        this.appEgyImpRt1 = appEgyImpRt1;
    }

    public Double getAppEgyImpRt2() {
        return this.appEgyImpRt2;
    }

    public void setAppEgyImpRt2(Double appEgyImpRt2) {
        this.appEgyImpRt2 = appEgyImpRt2;
    }

    public Double getAppEgyImpRt3() {
        return this.appEgyImpRt3;
    }

    public void setAppEgyImpRt3(Double appEgyImpRt3) {
        this.appEgyImpRt3 = appEgyImpRt3;
    }

    public Double getAppEgyImpRt4() {
        return this.appEgyImpRt4;
    }

    public void setAppEgyImpRt4(Double appEgyImpRt4) {
        this.appEgyImpRt4 = appEgyImpRt4;
    }

    public Double getAppEgyExpRt1() {
        return this.appEgyExpRt1;
    }

    public void setAppEgyExpRt1(Double appEgyExpRt1) {
        this.appEgyExpRt1 = appEgyExpRt1;
    }

    public Double getAppEgyExpRt2() {
        return this.appEgyExpRt2;
    }

    public void setAppEgyExpRt2(Double appEgyExpRt2) {
        this.appEgyExpRt2 = appEgyExpRt2;
    }

    public Double getAppEgyExpRt3() {
        return this.appEgyExpRt3;
    }

    public void setAppEgyExpRt3(Double appEgyExpRt3) {
        this.appEgyExpRt3 = appEgyExpRt3;
    }

    public Double getAppEgyExpRt4() {
        return this.appEgyExpRt4;
    }

    public void setAppEgyExpRt4(Double appEgyExpRt4) {
        this.appEgyExpRt4 = appEgyExpRt4;
    }

    public Double getActEgyCombTotRt1() {
        return this.actEgyCombTotRt1;
    }

    public void setActEgyCombTotRt1(Double actEgyCombTotRt1) {
        this.actEgyCombTotRt1 = actEgyCombTotRt1;
    }

    public Double getActEgyCombTotRt2() {
        return this.actEgyCombTotRt2;
    }

    public void setActEgyCombTotRt2(Double actEgyCombTotRt2) {
        this.actEgyCombTotRt2 = actEgyCombTotRt2;
    }

    public Double getActEgyCombTotRt3() {
        return this.actEgyCombTotRt3;
    }

    public void setActEgyCombTotRt3(Double actEgyCombTotRt3) {
        this.actEgyCombTotRt3 = actEgyCombTotRt3;
    }

    public Double getActEgyCombTotRt4() {
        return this.actEgyCombTotRt4;
    }

    public void setActEgyCombTotRt4(Double actEgyCombTotRt4) {
        this.actEgyCombTotRt4 = actEgyCombTotRt4;
    }

    public Double getActEgyNetTotRt1() {
        return this.actEgyNetTotRt1;
    }

    public void setActEgyNetTotRt1(Double actEgyNetTotRt1) {
        this.actEgyNetTotRt1 = actEgyNetTotRt1;
    }

    public Double getActEgyNetTotRt2() {
        return this.actEgyNetTotRt2;
    }

    public void setActEgyNetTotRt2(Double actEgyNetTotRt2) {
        this.actEgyNetTotRt2 = actEgyNetTotRt2;
    }

    public Double getActEgyNetTotRt3() {
        return this.actEgyNetTotRt3;
    }

    public void setActEgyNetTotRt3(Double actEgyNetTotRt3) {
        this.actEgyNetTotRt3 = actEgyNetTotRt3;
    }

    public Double getActEgyNetTotRt4() {
        return this.actEgyNetTotRt4;
    }

    public void setActEgyNetTotRt4(Double actEgyNetTotRt4) {
        this.actEgyNetTotRt4 = actEgyNetTotRt4;
    }

    public Double getMaxDmdReg1ActEgyImp() {
        return this.maxDmdReg1ActEgyImp;
    }

    public void setMaxDmdReg1ActEgyImp(Double maxDmdReg1ActEgyImp) {
        this.maxDmdReg1ActEgyImp = maxDmdReg1ActEgyImp;
    }

    public Timestamp getMaxDmdReg1ActEgyImpTime() {
        return this.maxDmdReg1ActEgyImpTime;
    }

    public void setMaxDmdReg1ActEgyImpTime(Timestamp maxDmdReg1ActEgyImpTime) {
        this.maxDmdReg1ActEgyImpTime = maxDmdReg1ActEgyImpTime;
    }

    public Double getMaxDmdReg6ActEgyExp() {
        return this.maxDmdReg6ActEgyExp;
    }

    public void setMaxDmdReg6ActEgyExp(Double maxDmdReg6ActEgyExp) {
        this.maxDmdReg6ActEgyExp = maxDmdReg6ActEgyExp;
    }

    public Timestamp getMaxDmdReg6ActEgyExpTime() {
        return this.maxDmdReg6ActEgyExpTime;
    }

    public void setMaxDmdReg6ActEgyExpTime(Timestamp maxDmdReg6ActEgyExpTime) {
        this.maxDmdReg6ActEgyExpTime = maxDmdReg6ActEgyExpTime;
    }

    public Double getAppMdImp() {
        return this.appMdImp;
    }

    public void setAppMdImp(Double appMdImp) {
        this.appMdImp = appMdImp;
    }

    public Timestamp getAppMdImpOccurringTime() {
        return this.appMdImpOccurringTime;
    }

    public void setAppMdImpOccurringTime(Timestamp appMdImpOccurringTime) {
        this.appMdImpOccurringTime = appMdImpOccurringTime;
    }

    public Double getAppMdExp() {
        return this.appMdExp;
    }

    public void setAppMdExp(Double appMdExp) {
        this.appMdExp = appMdExp;
    }

    public Timestamp getAppMdExpOccurringTime() {
        return this.appMdExpOccurringTime;
    }

    public void setAppMdExpOccurringTime(Timestamp appMdExpOccurringTime) {
        this.appMdExpOccurringTime = appMdExpOccurringTime;
    }

    public Timestamp getModifiedDatetime() {
        return this.modifiedDatetime;
    }

    public void setModifiedDatetime(Timestamp modifiedDatetime) {
        this.modifiedDatetime = modifiedDatetime;
    }

    public Timestamp getBillingDatetimeIst() {
        return billingDatetimeIst;
    }

    public void setBillingDatetimeIst(Timestamp billingDatetimeIst) {
        this.billingDatetimeIst = billingDatetimeIst;
    }

    @Override
    public String toString() {
        String str = null;
        try {
            StringBuilder strb = new StringBuilder();
            /**
             * 01
             */
            strb.append(id.getMeterSerialNumber() + ",");//METER SERIAL NUMBER
            /**
             * 02
             */
            strb.append(id.getBillingDatetime() + ",");//DATE TIME
            strb.append(actEgyImp);
            strb.append(actEgyExp);
            strb.append(actEgyCombinedTotal);
            strb.append(actEgyNetTotal);
            strb.append(appEgyImp);
            strb.append(appEgyExp);
            strb.append(actEgyImpRt1);
            strb.append(actEgyImpRt2);
            strb.append(actEgyImpRt3);
            strb.append(actEgyImpRt4);
            strb.append(actEgyExpRt1);
            strb.append(actEgyExpRt2);
            strb.append(actEgyExpRt3);
            strb.append(actEgyExpRt4);
            strb.append(appEgyImpRt1);
            strb.append(appEgyImpRt2);
            strb.append(appEgyImpRt3);
            strb.append(appEgyImpRt4);
            strb.append(appEgyExpRt1);
            strb.append(appEgyExpRt2);
            strb.append(appEgyExpRt3);
            strb.append(appEgyExpRt4);
            strb.append(actEgyCombTotRt1);
            strb.append(actEgyCombTotRt2);
            strb.append(actEgyCombTotRt3);
            strb.append(actEgyCombTotRt4);
            strb.append(actEgyNetTotRt1);
            strb.append(actEgyNetTotRt2);
            strb.append(actEgyNetTotRt3);
            strb.append(actEgyNetTotRt4);
            strb.append(maxDmdReg1ActEgyImp);
            strb.append(maxDmdReg1ActEgyImpTime);
            strb.append(maxDmdReg6ActEgyExp);
            strb.append(maxDmdReg6ActEgyExpTime);
            strb.append(appMdImp);
            strb.append(appMdImpOccurringTime);
            strb.append(appMdExp);
            strb.append(appMdExpOccurringTime);
            strb.append(modifiedDatetime);
            strb.append(billingDatetimeIst);
            str = strb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
