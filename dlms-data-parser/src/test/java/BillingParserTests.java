import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.app.models.BillingHistoryModel;
import com.parser.app.models.MeterParameter;
import com.parser.app.parsers.BillingDataParserImpl;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author Veera
 */
public class BillingParserTests {

    public BillingParserTests() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() {
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
//    @Test
//    public void ParseBillingData() throws Exception {
//        var fileName = "C:\\Dev\\MeterComm\\MRD\\20418017_BILL_2041801701022023113427215_S.MD";
//        ParseBilling(fileName);
//    }
//
//    private void ParseBilling(String fileName) throws Exception {
//        var billingParserService = new BillingDataParserImpl();
//        var result = billingParserService.parse(fileName);
//        File file = new File("C:\\Sample.json");
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(file, result);
//        for (BillingHistoryModel billingRecord : result) {
//            System.out.println("Billing Record:" + billingRecord.getIndex());
//            for (MeterParameter meterParameter : billingRecord.getParameters()) {
//                assertNotNull(meterParameter.getCode(), "FileName:" + fileName);
//                assertNotNull(meterParameter.getHexValue());
//                assertNotNull(meterParameter.getValue());
//            }
//        }
//    }
//

}
