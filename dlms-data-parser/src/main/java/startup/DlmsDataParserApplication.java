package startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import repositories.BillingHistoryRepository;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories()
public class DlmsDataParserApplication implements CommandLineRunner {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired(required=true)
    private BillingHistoryRepository billingHistoryRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(DlmsDataParserApplication.class, args);
        
    }
    
    @Override
    public void run(String... args) throws Exception {
        
        var items = billingHistoryRepository.findAll();
        items.forEach(System.out::println);
        
//        String sql = "SELECT * FROM SMART_HES.BILLING_HISTORY";
//        
//        List<BillingHistory> students = jdbcTemplate.query(sql,
//                BeanPropertyRowMapper.newInstance(BillingHistory.class));
//        
//        students.forEach(System.out::println);
    }
    
}
