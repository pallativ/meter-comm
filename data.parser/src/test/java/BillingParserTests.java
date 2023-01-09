/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.coralinnovations.data.parser.models.MeterParameter;
import com.coralinnovations.data.parser.services.BillingDataParser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
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
        var fileName = "C:\\Users\\Veera\\source\\repos\\MeterComm\\MRD\\20417916_BILL_2041791603012023225230545_S.MD";
        ParseBilling(fileName);
    }

    private void ParseBilling(String fileName) throws IOException, Exception {
        var billingParserService = new BillingDataParser(fileName);
        var result = billingParserService.Parse();
        for (Map.Entry<Integer, ArrayList<MeterParameter>> billingRecord : result.entrySet()) {
            System.out.println("Billing Record:" + billingRecord.getKey());
            for (MeterParameter meterParameter : billingRecord.getValue()) {
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
    }
}