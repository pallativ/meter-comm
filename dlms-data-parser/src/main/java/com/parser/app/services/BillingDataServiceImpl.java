/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parser.app.services;

import com.parser.app.entities.BillingHistory;
import com.parser.app.entities.BillingHistoryId;
import com.parser.app.models.BillingHistoryModel;
import com.parser.app.parsers.BillingDataParser;
import com.parser.app.repositories.BillingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Veera
 */
@Service
public class BillingDataServiceImpl implements BillingDataService {

    @Autowired
    private BillingHistoryRepository repository;

    @Autowired
    private BillingDataParser parser;

    @Override
    public void Process(String fileName) throws Exception {
        try {
            var billingHistoryModels = parser.parse(fileName);
            var list = new ArrayList<BillingHistory>();
            for (BillingHistoryModel billingHistoryModel : billingHistoryModels) {
                list.add(Transform(billingHistoryModel));
            }
            repository.saveAll(list);
        } catch (Exception ex) {
            Logger.getLogger(BillingDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    private BillingHistory Transform(BillingHistoryModel model) throws Exception {
        var billingHistory = new BillingHistory();
        billingHistory.setId(new BillingHistoryId());
        var scalarValues = ScalarValueService.read();
        billingHistory.getId().setMeterNumber(model.getMeterNumber());
        billingHistory.getId().setBillingMonth(model.getDateTime("00 00 01 00 00 ff"));
        billingHistory.setUpdatedAt(LocalDateTime.now());

        billingHistory.setActiveEnergyCombinedTotal(model.getFloat("01 00 0F 08 00 FF", scalarValues));
        billingHistory.setActiveEnergyCombinedTotalT1(model.getFloat("01 00 0F 08 01 FF", scalarValues));
        billingHistory.setActiveEnergyCombinedTotalT2(model.getFloat("01 00 0F 08 02 FF", scalarValues));
        billingHistory.setActiveEnergyCombinedTotalT3(model.getFloat("01 00 0F 08 03 FF", scalarValues));
        billingHistory.setActiveEnergyCombinedTotalT4(model.getFloat("01 00 0F 08 04 FF", scalarValues));

        billingHistory.setActiveEnergyExport(model.getFloat("01 00 02 08 00 FF", scalarValues));
        billingHistory.setActiveEnergyExportT1(model.getFloat("01 00 02 08 01 FF", scalarValues));
        billingHistory.setActiveEnergyExportT2(model.getFloat("01 00 02 08 02 FF", scalarValues));
        billingHistory.setActiveEnergyExportT3(model.getFloat("01 00 02 08 03 FF", scalarValues));
        billingHistory.setActiveEnergyExportT4(model.getFloat("01 00 02 08 04 FF", scalarValues));

        billingHistory.setActiveEnergyImport(model.getFloat("01 00 01 08 00 FF", scalarValues));
        billingHistory.setActiveEnergyImportT1(model.getFloat("01 00 01 08 01 FF", scalarValues));
        billingHistory.setActiveEnergyImportT2(model.getFloat("01 00 01 08 02 FF", scalarValues));
        billingHistory.setActiveEnergyImportT3(model.getFloat("01 00 01 08 03 FF", scalarValues));
        billingHistory.setActiveEnergyImportT4(model.getFloat("01 00 01 08 04 FF", scalarValues));

        billingHistory.setActiveEnergyNetTotal(model.getFloat("01 00 10 08 00 FF", scalarValues));
        billingHistory.setActiveEnergyNetTotalT1(model.getFloat("01 00 10 08 01 FF", scalarValues));
        billingHistory.setActiveEnergyNetTotalT2(model.getFloat("01 00 10 08 02 FF", scalarValues));
        billingHistory.setActiveEnergyNetTotalT3(model.getFloat("01 00 10 08 03 FF", scalarValues));
        billingHistory.setActiveEnergyNetTotalT4(model.getFloat("01 00 10 08 04 FF", scalarValues));

        billingHistory.setApparentEnergyExport(model.getFloat("01 00 0A 08 00 FF", scalarValues));
        billingHistory.setApparentEnergyExportT1(model.getFloat("01 00 0A 08 01 FF", scalarValues));
        billingHistory.setApparentEnergyExportT2(model.getFloat("01 00 0A 08 02 FF", scalarValues));
        billingHistory.setApparentEnergyExportT3(model.getFloat("01 00 0A 08 03 FF", scalarValues));
        billingHistory.setApparentEnergyExportT4(model.getFloat("01 00 0A 08 04 FF", scalarValues));

        billingHistory.setApparentEnergyImport(model.getFloat("01 00 09 08 00 FF", scalarValues));
        billingHistory.setApparentEnergyImportT1(model.getFloat("01 00 09 08 01 FF", scalarValues));
        billingHistory.setApparentEnergyImportT2(model.getFloat("01 00 09 08 02 FF", scalarValues));
        billingHistory.setApparentEnergyImportT3(model.getFloat("01 00 09 08 03 FF", scalarValues));
        billingHistory.setApparentEnergyImportT4(model.getFloat("01 00 09 08 04 FF", scalarValues));

        billingHistory.setMaxActivePowerImport(model.getFloat("01 00 01 06 00 FF", scalarValues));
        billingHistory.setMaxActivePowerImportAt(model.getDateTime("01 00 01 06 00 FF"));
        billingHistory.setMaxActivePowerExport(model.getFloat("01 00 02 06 00 FF", scalarValues));
        billingHistory.setMaxActivePowerExportAt(model.getDateTime("01 00 02 06 00 FF"));

        billingHistory.setMaxApparentPowerExport(model.getFloat("01 00 09 06 00 FF", scalarValues));
        billingHistory.setMaxApparentPowerExportAt(model.getDateTime("01 00 09 06 00 FF"));
        billingHistory.setMaxApparentPowerImport(model.getFloat("01 00 0A 06 00 FF", scalarValues));
        billingHistory.setMaxApparentPowerImportAt(model.getDateTime("01 00 0A 06 00 FF"));

        return billingHistory;
    }
}
