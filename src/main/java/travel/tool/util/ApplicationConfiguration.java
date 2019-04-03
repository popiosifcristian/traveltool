package travel.tool.util;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import travel.tool.repository.*;
import travel.tool.repository.dao.*;

import javax.sql.DataSource;
import java.io.IOException;
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
    private SpringFXMLLoader springFXMLLoader;

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

    @Bean
    public ICustomerRepository customerRepository() {
        return new JdbcTemplateCustomer(dataSource());
    }

    @Bean
    public ICompanyRepository companyRepository() {
        return new JdbcTemplateCompany(dataSource());
    }

    @Bean
    public IEmployeeRepository employeeRepository() {
        return new JdbcTemplateEmployee(dataSource());
    }

    @Bean
    public ILandmarkRepository landmarkRepository() {
        return new JdbcTemplateLandmark(dataSource());
    }

    @Bean
    public ITripRepository tripRepository() {
        return new JdbcTemplateTrip(dataSource());
    }

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    @Bean
    @Lazy //Stage only created after Spring context bootstap
    public StageManager stageManager(Stage stage) {
        return new StageManager(springFXMLLoader, stage);
    }
}
