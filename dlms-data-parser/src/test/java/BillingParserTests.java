/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import models.BillingRecord;
import models.MeterParameter;
import services.BillingDataParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Veera
 */
public class BillingParserTests {

    public BillingParserTests() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void ParseBillingData() throws IOException, Exception {
        var fileName = "C:\\Users\\Veera\\source\\repos\\MeterComm\\MRD\\\\20417972_BILL_2041797203012023225516511_S.MD";
        ParseBilling(fileName);
    }

    private void ParseBilling(String fileName) throws IOException, Exception {
        var billingParserService = new BillingDataParser(fileName);
        var result = billingParserService.Parse();
        File file = new File("C:\\Sample.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, result);
        for (BillingRecord billingRecord : result) {
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
        var billingFiles = new File("C:\\Users\\Veera\\source\\repos\\MeterComm\\MRD\\").listFiles();
        var failedFiles = new ArrayList<String>();
        var successFiles = new ArrayList<String>();
        for (File billingFile : billingFiles) {
            try {
                var billingParserService = new BillingDataParser(billingFile.getAbsolutePath());
                var result = billingParserService.Parse();
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
