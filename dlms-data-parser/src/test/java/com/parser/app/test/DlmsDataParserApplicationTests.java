package com.parser.app.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.app.models.BillingHistoryModel;
import com.parser.app.models.MeterParameter;
import com.parser.app.parsers.BillingDataParser;
import com.parser.app.parsers.BillingDataParserImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DlmsDataParserApplicationTests {

    @Autowired
    private BillingDataParser billingParserService;
    @Test
    public void ParseBillingData() throws Exception {
        var fileName = "C:\\Dev\\MeterComm\\MRD\\20417905_BILL_2041790505022023140009771_S.MD";
        ParseBilling(fileName);
    }
    @Test
    public void ParseBillingData1() throws Exception {
        var fileName = "C:\\Dev\\MeterComm\\MRD\\20418022_BILL_204180220301202323065716_S.MD";
        ParseBilling(fileName);
    }

//    @Test
//    public void ParseBillingData2() throws Exception {
//        var fileName = "C:\\Dev\\MeterComm\\MRD\\20418017_BILL_2041801716012023172101491_S.MD";
//        ParseBilling(fileName);
//    }
    private void ParseBilling(String fileName) throws Exception {
        var result = billingParserService.parse(fileName);
        for (BillingHistoryModel billingRecord : result) {
            System.out.println("Billing Record:" + billingRecord.getIndex());
            for (MeterParameter meterParameter : billingRecord.getParameters()) {
                assertNotNull(meterParameter.getCode(), "FileName:" + fileName);
                assertNotNull(meterParameter.getHexValue());
                assertNotNull(meterParameter.getValue());
            }
        }
    }
    @Test
    public void ParserAllFiles() {
        var billingFiles = new File("C:\\Dev\\MeterComm\\MRD\\").listFiles();
        var failedFiles = new ArrayList<String>();
        var successFiles = new ArrayList<String>();
        assert billingFiles != null;
        for (File billingFile : billingFiles) {
            try {
                billingParserService.parse(billingFile.getAbsolutePath());
                successFiles.add(billingFile.getAbsolutePath());
            } catch (Exception ex) {
                failedFiles.add(billingFile.getAbsolutePath());
            }
        }
        for (String successFile : successFiles) {
            System.out.println("Success: " + successFile);
        }
        for (String failedFile : failedFiles) {
            System.out.println("Failed: " + failedFile);
        }
        assertEquals(0, failedFiles.size());
    }
}
