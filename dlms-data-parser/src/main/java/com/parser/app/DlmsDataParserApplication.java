package com.parser.app;



import com.parser.app.repositories.BillingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DlmsDataParserApplication implements CommandLineRunner {

    @Autowired
    private BillingHistoryRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DlmsDataParserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var records = repository.findAll();
        records.forEach(System.out::println);
    }

}
