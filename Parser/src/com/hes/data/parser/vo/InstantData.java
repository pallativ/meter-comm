package com.hes.data.parser.vo;

import java.sql.Timestamp;

/**
 *
 * @author psvr
 *
 */
public class InstantData implements java.io.Serializable {

    private static final long serialVersionUID = 3454065887318076069L;
    private InstantDataId id;
    private Double sumImportActiveEnergy;
    private Double sumExportActiveEnergy;
    private Double activeEnergyCombinedTotal;
    private Double activeEnergyNetTotal;
    private Double apparentEnergyImport;
    private Double apparentEnergyExport;
    private Double voltageL1;
    private Double voltageL2;
    private Double voltageL3;
    private Double currentL1;
    private Double currentL2;
    private Double currentL3;
    private Double netFrequency;
    private Double activeImportPower;
    private Double activeExportPower;
    private Double reactiveImportPower;
    private Double reactiveExportPower;
    private Double apparentImportPower;
    private Double apparentExportPower;
    private Double powerFactorL1;
    private Double powerFactorL2;
    private Double powerFactorL3;
    private Double phaseAngleL1;
    private Double phaseAngleL2;
    private Double phaseAngleL3;
    private Timestamp modifiedDatetime;

    // Constructors
    /**
     * default constructor
     */
    public InstantData() {
    }

    /**
     * minimal constructor
     */
    public InstantData(InstantDataId id) {
        this.id = id;
    }

    /**
     * full constructor
     */
    public InstantData(InstantDataId id, Double sumImportActiveEnergy,
            Double sumExportActiveEnergy, Double activeEnergyCombinedTotal,
            Double activeEnergyNetTotal, Double apparentEnergyImport,
            Double apparentEnergyExport, Double voltageL1, Double voltageL2,
            Double voltageL3, Double currentL1, Double currentL2,
            Double currentL3, Double netFrequency, Double activeImportPower,
            Double activeExportPower, Double reactiveImportPower,
            Double reactiveExportPower, Double apparentImportPower,
            Double apparentExportPower, Double powerFactorL1,
            Double powerFactorL2, Double powerFactorL3, Double phaseAngleL1,
            Double phaseAngleL2, Double phaseAngleL3, Timestamp modifiedDatetime) {
        this.id = id;
        this.sumImportActiveEnergy = sumImportActiveEnergy;
        this.sumExportActiveEnergy = sumExportActiveEnergy;
        this.activeEnergyCombinedTotal = activeEnergyCombinedTotal;
        this.activeEnergyNetTotal = activeEnergyNetTotal;
        this.apparentEnergyImport = apparentEnergyImport;
        this.apparentEnergyExport = apparentEnergyExport;
        this.voltageL1 = voltageL1;
        this.voltageL2 = voltageL2;
        this.voltageL3 = voltageL3;
        this.currentL1 = currentL1;
        this.currentL2 = currentL2;
        this.currentL3 = currentL3;
        this.netFrequency = netFrequency;
        this.activeImportPower = activeImportPower;
        this.activeExportPower = activeExportPower;
        this.reactiveImportPower = reactiveImportPower;
        this.reactiveExportPower = reactiveExportPower;
        this.apparentImportPower = apparentImportPower;
        this.apparentExportPower = apparentExportPower;
        this.powerFactorL1 = powerFactorL1;
        this.powerFactorL2 = powerFactorL2;
        this.powerFactorL3 = powerFactorL3;
        this.phaseAngleL1 = phaseAngleL1;
        this.phaseAngleL2 = phaseAngleL2;
        this.phaseAngleL3 = phaseAngleL3;
        this.modifiedDatetime = modifiedDatetime;
    }

    // Property accessors
    public InstantDataId getId() {
        return this.id;
    }

    public void setId(InstantDataId id) {
        this.id = id;
    }

    public Double getSumImportActiveEnergy() {
        return this.sumImportActiveEnergy;
    }

    public void setSumImportActiveEnergy(Double sumImportActiveEnergy) {
        this.sumImportActiveEnergy = sumImportActiveEnergy;
    }

    public Double getSumExportActiveEnergy() {
        return this.sumExportActiveEnergy;
    }

    public void setSumExportActiveEnergy(Double sumExportActiveEnergy) {
        this.sumExportActiveEnergy = sumExportActiveEnergy;
    }

    public Double getActiveEnergyCombinedTotal() {
        return this.activeEnergyCombinedTotal;
    }

    public void setActiveEnergyCombinedTotal(Double activeEnergyCombinedTotal) {
        this.activeEnergyCombinedTotal = activeEnergyCombinedTotal;
    }

    public Double getActiveEnergyNetTotal() {
        return this.activeEnergyNetTotal;
    }

    public void setActiveEnergyNetTotal(Double activeEnergyNetTotal) {
        this.activeEnergyNetTotal = activeEnergyNetTotal;
    }

    public Double getApparentEnergyImport() {
        return this.apparentEnergyImport;
    }

    public void setApparentEnergyImport(Double apparentEnergyImport) {
        this.apparentEnergyImport = apparentEnergyImport;
    }

    public Double getApparentEnergyExport() {
        return this.apparentEnergyExport;
    }

    public void setApparentEnergyExport(Double apparentEnergyExport) {
        this.apparentEnergyExport = apparentEnergyExport;
    }

    public Double getVoltageL1() {
        return this.voltageL1;
    }

    public void setVoltageL1(Double voltageL1) {
        this.voltageL1 = voltageL1;
    }

    public Double getVoltageL2() {
        return this.voltageL2;
    }

    public void setVoltageL2(Double voltageL2) {
        this.voltageL2 = voltageL2;
    }

    public Double getVoltageL3() {
        return this.voltageL3;
    }

    public void setVoltageL3(Double voltageL3) {
        this.voltageL3 = voltageL3;
    }

    public Double getCurrentL1() {
        return this.currentL1;
    }

    public void setCurrentL1(Double currentL1) {
        this.currentL1 = currentL1;
    }

    public Double getCurrentL2() {
        return this.currentL2;
    }

    public void setCurrentL2(Double currentL2) {
        this.currentL2 = currentL2;
    }

    public Double getCurrentL3() {
        return this.currentL3;
    }

    public void setCurrentL3(Double currentL3) {
        this.currentL3 = currentL3;
    }

    public Double getNetFrequency() {
        return this.netFrequency;
    }

    public void setNetFrequency(Double netFrequency) {
        this.netFrequency = netFrequency;
    }

    public Double getActiveImportPower() {
        return this.activeImportPower;
    }

    public void setActiveImportPower(Double activeImportPower) {
        this.activeImportPower = activeImportPower;
    }

    public Double getActiveExportPower() {
        return this.activeExportPower;
    }

    public void setActiveExportPower(Double activeExportPower) {
        this.activeExportPower = activeExportPower;
    }

    public Double getReactiveImportPower() {
        return this.reactiveImportPower;
    }

    public void setReactiveImportPower(Double reactiveImportPower) {
        this.reactiveImportPower = reactiveImportPower;
    }

    public Double getReactiveExportPower() {
        return this.reactiveExportPower;
    }

    public void setReactiveExportPower(Double reactiveExportPower) {
        this.reactiveExportPower = reactiveExportPower;
    }

    public Double getApparentImportPower() {
        return this.apparentImportPower;
    }

    public void setApparentImportPower(Double apparentImportPower) {
        this.apparentImportPower = apparentImportPower;
    }

    public Double getApparentExportPower() {
        return this.apparentExportPower;
    }

    public void setApparentExportPower(Double apparentExportPower) {
        this.apparentExportPower = apparentExportPower;
    }

    public Double getPowerFactorL1() {
        return this.powerFactorL1;
    }

    public void setPowerFactorL1(Double powerFactorL1) {
        this.powerFactorL1 = powerFactorL1;
    }

    public Double getPowerFactorL2() {
        return this.powerFactorL2;
    }

    public void setPowerFactorL2(Double powerFactorL2) {
        this.powerFactorL2 = powerFactorL2;
    }

    public Double getPowerFactorL3() {
        return this.powerFactorL3;
    }

    public void setPowerFactorL3(Double powerFactorL3) {
        this.powerFactorL3 = powerFactorL3;
    }

    public Double getPhaseAngleL1() {
        return this.phaseAngleL1;
    }

    public void setPhaseAngleL1(Double phaseAngleL1) {
        this.phaseAngleL1 = phaseAngleL1;
    }

    public Double getPhaseAngleL2() {
        return this.phaseAngleL2;
    }

    public void setPhaseAngleL2(Double phaseAngleL2) {
        this.phaseAngleL2 = phaseAngleL2;
    }

    public Double getPhaseAngleL3() {
        return this.phaseAngleL3;
    }

    public void setPhaseAngleL3(Double phaseAngleL3) {
        this.phaseAngleL3 = phaseAngleL3;
    }

    public Timestamp getModifiedDatetime() {
        return this.modifiedDatetime;
    }

    public void setModifiedDatetime(Timestamp modifiedDatetime) {
        this.modifiedDatetime = modifiedDatetime;
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
            strb.append(id.getInstantDatetime() + ",");//DATE TIME
            /**
             * 03
             */
            strb.append(sumImportActiveEnergy + ",");//sumImportActiveEnergy
            /**
             * 04
             */
            strb.append(sumExportActiveEnergy + ",");//sumExportActiveEnergy
            /**
             * 05
             */
            strb.append(activeEnergyCombinedTotal + ",");//activeEnergyCombinedTotal
            /**
             * 06
             */
            strb.append(activeEnergyNetTotal + ",");//activeEnergyNetTotal
            /**
             * 07
             */
            strb.append(apparentEnergyImport + ",");//apparentEnergyImport
            /**
             * 08
             */
            strb.append(apparentEnergyExport + ",");//apparentEnergyExport
            /**
             * 09
             */
            strb.append(voltageL1 + ",");//voltageL1
            /**
             * 10
             */
            strb.append(voltageL2 + ",");//voltageL2
            /**
             * 11
             */
            strb.append(voltageL3 + ",");//voltageL3
            /**
             * 12
             */
            strb.append(currentL1 + ",");//currentL1
            /**
             * 13
             */
            strb.append(currentL2 + ",");//currentL2
            /**
             * 14
             */
            strb.append(currentL3 + ",");//currentL3
            /**
             * 15
             */
            strb.append(netFrequency + ",");//netFrequency
            /**
             * 16
             */
            strb.append(activeImportPower + ",");//activeImportPower
            /**
             * 17
             */
            strb.append(activeExportPower + ",");//activeExportPower
            /**
             * 18
             */
            strb.append(reactiveImportPower + ",");//reactiveImportPower
            /**
             * 19
             */
            strb.append(reactiveExportPower + ",");//reactiveExportPower
            /**
             * 20
             */
            strb.append(apparentImportPower + ",");//apparentImportPower
            /**
             * 21
             */
            strb.append(apparentExportPower + ",");//apparentExportPower
            /**
             * 22
             */
            strb.append(powerFactorL1 + ",");//powerFactorL1
            /**
             * 24
             */
            strb.append(powerFactorL2 + ",");//powerFactorL2
            /**
             * 25
             */
            strb.append(powerFactorL3 + ",");//powerFactorL3
            /**
             * 26
             */
            strb.append(phaseAngleL1 + ",");//phaseAngleL1
            /**
             * 27
             */
            strb.append(phaseAngleL2 + ",");//phaseAngleL2
            /**
             * 28
             */
            strb.append(phaseAngleL3 + ",");//phaseAngleL3
//			/**29*/strb.append(instantDatetimeIst+",");//DATE TIME IST
            str = strb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
