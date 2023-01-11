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
            var billingHistoryModels = parser.Parse(fileName);
            var list = new ArrayList<BillingHistory>();
            for (BillingHistoryModel billingHistoryModel : billingHistoryModels) {
                list.add(Transform(billingHistoryModel));
            }
            repository.saveAll(list);
        } catch (Exception ex) {
            Logger.getLogger(BillingDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BillingHistory Transform(BillingHistoryModel model) {
        var billingHistory = new BillingHistory();
        billingHistory.setMeterNumber(model.getMeterNumber());
        var parameter = model.getValue("0.0.1.0.0.255");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        LocalDateTime dateTime = LocalDateTime.parse(parameter.get().getValue(), formatter);
        billingHistory.setBillingMonth(dateTime);
        billingHistory.setUpdatedAt(LocalDateTime.now());
        billingHistory.setResetMethod(1);

        billingHistory.setActiveEnergyCombinedTotal(0);
        billingHistory.setActiveEnergyCombinedTotalT1(0);
        billingHistory.setActiveEnergyCombinedTotalT2(0);
        billingHistory.setActiveEnergyCombinedTotalT3(0);
        billingHistory.setActiveEnergyCombinedTotalT4(0);

        billingHistory.setActiveEnergyExport(model.getLongValue("01 00 01 08 00 FF"));
        billingHistory.setActiveEnergyExportT1(0);
        billingHistory.setActiveEnergyExportT2(0);
        billingHistory.setActiveEnergyExportT3(0);
        billingHistory.setActiveEnergyExportT4(0);

        billingHistory.setActiveEnergyImport(model.getLongValue("01 00 02 08 00 FF"));
        billingHistory.setActiveEnergyImportT1(0);
        billingHistory.setActiveEnergyImportT2(0);
        billingHistory.setActiveEnergyImportT3(0);
        billingHistory.setActiveEnergyImportT4(0);

        billingHistory.setActiveEnergyNetTotal(0);
        billingHistory.setActiveEnergyNetTotalT1(0);
        billingHistory.setActiveEnergyNetTotalT2(0);
        billingHistory.setActiveEnergyNetTotalT3(0);
        billingHistory.setActiveEnergyNetTotalT4(0);

        billingHistory.setApparentEnergyExport(0);
        billingHistory.setApparentEnergyExportT1(0);
        billingHistory.setApparentEnergyExportT2(0);
        billingHistory.setApparentEnergyExportT3(0);
        billingHistory.setApparentEnergyExportT4(0);

        billingHistory.setApparentEnergyImport(0);
        billingHistory.setApparentEnergyImportT1(0);
        billingHistory.setApparentEnergyImportT2(0);
        billingHistory.setApparentEnergyImportT3(0);
        billingHistory.setApparentEnergyImportT4(0);

        billingHistory.setMaxActivePowerExport(0);
        billingHistory.setMaxActivePowerExportAt(LocalDateTime.now());
        billingHistory.setMaxActivePowerImport(0);
        billingHistory.setMaxActivePowerImportAt(LocalDateTime.now());

        billingHistory.setMaxApparentPowerExport(0);
        billingHistory.setMaxApparentPowerExportAt(LocalDateTime.now());
        billingHistory.setMaxApparentPowerImport(0);
        billingHistory.setMaxApparentPowerImportAt(LocalDateTime.now());

        return billingHistory;
    }
}
