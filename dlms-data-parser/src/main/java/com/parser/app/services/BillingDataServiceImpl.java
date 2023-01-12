/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parser.app.services;

import com.parser.app.entities.BillingHistory;
import com.parser.app.models.BillingHistoryModel;
import com.parser.app.parsers.BillingDataParser;
import com.parser.app.repositories.BillingHistoryRepository;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Veera
 */
@Service
public class BillingDataServiceImpl implements BillingDataService {

    @Autowired
    private BillingHistoryRepository repository;

    @Autowired
    private BillingDataParser parser;

    @Override
    public void Process(String fileName) {
        try {
            var billingHistoryModels = parser.parse(fileName);
            var list = new ArrayList<BillingHistory>();
            for (BillingHistoryModel billingHistoryModel : billingHistoryModels) {
                list.add(Transform(billingHistoryModel));
            }
            repository.saveAll(list);
        } catch (Exception ex) {
            Logger.getLogger(BillingDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BillingHistory Transform(BillingHistoryModel model) throws Exception {
        var billingHistory = new BillingHistory();
        billingHistory.setMeterNumber(model.getMeterNumber());
        billingHistory.setBillingMonth(model.getDateTime("00 00 01 00 00 ff"));
        billingHistory.setUpdatedAt(LocalDateTime.now());
        billingHistory.setResetMethod(1);

        billingHistory.setActiveEnergyCombinedTotal(model.getLongValue("01 00 0F 08 00 FF"));
        billingHistory.setActiveEnergyCombinedTotalT1(model.getLongValue("01 00 0F 08 01 FF"));
        billingHistory.setActiveEnergyCombinedTotalT2(model.getLongValue("01 00 0F 08 02 FF"));
        billingHistory.setActiveEnergyCombinedTotalT3(model.getLongValue("01 00 0F 08 03 FF"));
        billingHistory.setActiveEnergyCombinedTotalT4(model.getLongValue("01 00 0F 08 04 FF"));

        billingHistory.setActiveEnergyExport(model.getLongValue("01 00 02 08 00 FF"));
        billingHistory.setActiveEnergyExportT1(model.getLongValue("01 00 02 08 01 FF"));
        billingHistory.setActiveEnergyExportT2(model.getLongValue("01 00 02 08 02 FF"));
        billingHistory.setActiveEnergyExportT3(model.getLongValue("01 00 02 08 03 FF"));
        billingHistory.setActiveEnergyExportT4(model.getLongValue("01 00 02 08 04 FF"));

        billingHistory.setActiveEnergyImport(model.getLongValue("01 00 01 08 00 FF"));
        billingHistory.setActiveEnergyImportT1(model.getLongValue("01 00 01 08 01 FF"));
        billingHistory.setActiveEnergyImportT2(model.getLongValue("01 00 01 08 02 FF"));
        billingHistory.setActiveEnergyImportT3(model.getLongValue("01 00 01 08 03 FF"));
        billingHistory.setActiveEnergyImportT4(model.getLongValue("01 00 01 08 04 FF"));

        billingHistory.setActiveEnergyNetTotal(model.getLongValue("01 00 10 08 00 FF"));
        billingHistory.setActiveEnergyNetTotalT1(model.getLongValue("01 00 10 08 01 FF"));
        billingHistory.setActiveEnergyNetTotalT2(model.getLongValue("01 00 10 08 02 FF"));
        billingHistory.setActiveEnergyNetTotalT3(model.getLongValue("01 00 10 08 03 FF"));
        billingHistory.setActiveEnergyNetTotalT4(model.getLongValue("01 00 10 08 04 FF"));

        billingHistory.setApparentEnergyExport(model.getLongValue("01 00 0A 08 00 FF"));
        billingHistory.setApparentEnergyExportT1(model.getLongValue("01 00 0A 08 01 FF"));
        billingHistory.setApparentEnergyExportT2(model.getLongValue("01 00 0A 08 02 FF"));
        billingHistory.setApparentEnergyExportT3(model.getLongValue("01 00 0A 08 03 FF"));
        billingHistory.setApparentEnergyExportT4(model.getLongValue("01 00 0A 08 04 FF"));

        billingHistory.setApparentEnergyImport(model.getLongValue("01 00 09 08 00 FF"));
        billingHistory.setApparentEnergyImportT1(model.getLongValue("01 00 09 08 01 FF"));
        billingHistory.setApparentEnergyImportT2(model.getLongValue("01 00 09 08 02 FF"));
        billingHistory.setApparentEnergyImportT3(model.getLongValue("01 00 09 08 03 FF"));
        billingHistory.setApparentEnergyImportT4(model.getLongValue("01 00 09 08 04 FF"));

        billingHistory.setMaxActivePowerImport(model.getLongValue("01 00 01 06 00 FF"));
        billingHistory.setMaxActivePowerImportAt(model.getDateTime("01 00 01 06 00 FF"));
        billingHistory.setMaxActivePowerExport(model.getLongValue("01 00 02 06 00 FF"));
        billingHistory.setMaxActivePowerExportAt(model.getDateTime("01 00 02 06 00 FF"));

        billingHistory.setMaxApparentPowerImport(model.getLongValue("01 00 09 06 00 FF"));
        billingHistory.setMaxApparentPowerImportAt(model.getDateTime("01 00 09 06 00 FF"));
        billingHistory.setMaxApparentPowerExport(model.getLongValue("01 00 0A 06 00 FF"));
        billingHistory.setMaxApparentPowerExportAt(model.getDateTime("01 00 0A 06 00 FF"));

        return billingHistory;
    }
}
