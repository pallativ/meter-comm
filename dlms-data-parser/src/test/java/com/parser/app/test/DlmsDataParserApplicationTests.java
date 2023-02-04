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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DlmsDataParserApplicationTests {

    @Autowired
    private BillingDataParser billingParserService;
    @Test
    public void ParseBillingData() throws Exception {
        var fileName = "C:\\Dev\\MeterComm\\MRD\\20418017_BILL_2041801701022023113427215_S.MD";
        ParseBilling(fileName);
    }
    private void ParseBilling(String fileName) throws Exception {
        var result = billingParserService.parse(fileName);
//        File file = new File("C:\\Sample.json");
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(file, result);
        for (BillingHistoryModel billingRecord : result) {
            System.out.println("Billing Record:" + billingRecord.getIndex());
            for (MeterParameter meterParameter : billingRecord.getParameters()) {
                assertNotNull(meterParameter.getCode(), "FileName:" + fileName);
                assertNotNull(meterParameter.getHexValue());
                assertNotNull(meterParameter.getValue());
            }
        }
    }
}
