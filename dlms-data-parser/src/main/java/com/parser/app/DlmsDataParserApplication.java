package com.parser.app;

import com.parser.app.services.BillingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.util.ArrayList;

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
    }

}
