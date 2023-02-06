package com.parser.app;

import com.parser.app.services.BillingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@SpringBootApplication
@EnableJpaRepositories
public class DlmsDataParserApplication implements CommandLineRunner {

    private final BillingDataService billingDataService;

    private final Environment env;

    public DlmsDataParserApplication(Environment env, BillingDataService billingDataService) {
        this.env = env;
        this.billingDataService = billingDataService;
    }


    public static void main(String[] args) {
        SpringApplication.run(DlmsDataParserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length != 0) {
            var billingFiles = new File(args[0]).listFiles();
            var filesRootFolder = env.getProperty("dlms.data.parser.filesRootFolder");
            var successFilesFolder = env.getProperty("dlms.data.parser.processedFilesFolder");
            var failedFilesFolder = env.getProperty("dlms.data.parser.failedFilesFolder");
            var failedFiles = new ArrayList<String>();
            var successFiles = new ArrayList<String>();
            var totalFiles = 0;
            for (File billingFile : billingFiles) {
                if(billingFile.isDirectory())
                    continue;
                totalFiles++;
                var source = Paths.get(billingFile.getAbsolutePath());
                var failedTarget = Paths.get(filesRootFolder, "/", failedFilesFolder + "/" + billingFile.getName());
                Files.createDirectories(failedTarget.getParent());
                var successTarget = Paths.get(filesRootFolder, "/", successFilesFolder + "/" + billingFile.getName());
                Files.createDirectories(successTarget.getParent());
                try {
                    System.out.print("Processing: " + billingFile.getName());
                    billingDataService.Process(billingFile.getAbsolutePath());
                    successFiles.add(billingFile.getAbsolutePath());
                    Files.move(source, successTarget);
                    System.out.println("Status: Success");
                } catch (Exception ex) {
                    failedFiles.add(billingFile.getAbsolutePath());
                    Files.move(source, failedTarget);
                    System.out.println("Status: Failed");
                }
            }
            System.out.println("*************************************");
            System.out.println("Total Files : " + totalFiles);
            System.out.println("Success     : " + successFiles.size());
            System.out.println("Failed      : " + failedFiles.size());
        }
    }

}
