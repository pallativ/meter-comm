package com.parser.app;

import com.parser.app.models.BillingHistoryModel;
import com.parser.app.repositories.BillingHistoryRepository;
import com.parser.app.parsers.BillingDataParserImpl;
import com.parser.app.services.BillingDataService;
import java.io.File;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DlmsDataParserApplication implements CommandLineRunner {

    @Autowired
    private BillingDataService billingDataService;


    public static void main(String[] args) {
        SpringApplication.run(DlmsDataParserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var billingFiles = new File(args[0]).listFiles();
        var failedFiles = new ArrayList<String>();
        var successFiles = new ArrayList<String>();
        for (File billingFile : billingFiles) {
            try {
                System.out.println("Processing: " + billingFile.getName());
                billingDataService.Process(billingFile.getAbsolutePath());
                successFiles.add(billingFile.getAbsolutePath());
            } catch (Exception ex) {
                failedFiles.add(billingFile.getAbsolutePath());
            }
        }
        //var fileName = "C:\\Users\\Veera\\source\\repos\\MeterComm\\MRD\\\\20417406_BILL_2041740603012023225648963_S.MD";
        //billingDataService.Process(fileName);
    }

}
