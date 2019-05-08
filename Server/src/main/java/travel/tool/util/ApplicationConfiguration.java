package travel.tool.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import travel.tool.TravelToolServerImpl;

import javax.sql.DataSource;
import java.util.ResourceBundle;

/**
 * @author ipop
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class ApplicationConfiguration {

    @Value("${db.name}")
    private String dataSourceDatabase;
    @Value("${db.host}")
    private String dataSourceHost;
    @Value("${db.username}")
    private String dataSourceUsername;
    @Value("${db.password}")
    private String dataSourcePassword;
    @Autowired
    @Lazy
    private TravelToolServerImpl server;

    @Bean
    public DataSource dataSource() {
        String url = new StringBuilder()
                .append("jdbc:")
                .append("postgresql")
                .append("://")
                .append(dataSourceHost)
                .append(":")
                .append("5432")
                .append("/")
                .append(dataSourceDatabase)
                .append("?user=")
                .append(dataSourceUsername)
                .append("&password=")
                .append(dataSourcePassword).toString();

        return new SingleConnectionDataSource(url, false);
    }

//    @Bean
//    public ICustomerRepository customerRepository() {
//        return new JdbcTemplateCustomer(dataSource());
//    }
//
//    @Bean
//    public ICompanyRepository companyRepository() {
//        return new JdbcTemplateCompany(dataSource());
//    }
//
//    @Bean
//    public IEmployeeRepository employeeRepository() {
//        return new JdbcTemplateEmployee(dataSource());
//    }
//
//    @Bean
//    public ILandmarkRepository landmarkRepository() {
//        return new JdbcTemplateLandmark(dataSource());
//    }
//
//    @Bean
//    public ITripRepository tripRepository() {
//        return new JdbcTemplateTrip(dataSource());
//    }
//
//    @Bean
//    public IBookingRepository bookingRepository() {
//        return new JdbcTemplateBooking(dataSource());
//    }

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public CommandLineRunner schedulingRunner(TaskExecutor executor) {
        return strings -> executor.execute(server);
    }


}