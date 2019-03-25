package travel.tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import travel.tool.repository.*;
import travel.tool.repository.dao.*;
import travel.tool.service.CustomerService;

import javax.sql.DataSource;

/**
 * @author ipop
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class ApplicationConfigurationTest {

    @Value("${db.name}")
    private String dataSourceDatabase;
    @Value("${db.host}")
    private String dataSourceHost;
    @Value("${db.username}")
    private String dataSourceUsername;
    @Value("${db.password}")
    private String dataSourcePassword;


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
    public ICustomerRepository customerDao() {
        return new JdbcTemplateCustomer(dataSource());
    }

    @Bean
    public ICompanyRepository companyDao() {
        return new JdbcTemplateCompany(dataSource());
    }

    @Bean
    public IEmployeeRepository employeeDao() {
        return new JdbcTemplateEmployee(dataSource());
    }

    @Bean
    public ILandmarkRepository landmarkDao() {
        return new JdbcTemplateLandmark(dataSource());
    }

    @Bean
    public ITripRepository tripDao() {
        return new JdbcTemplateTrip(dataSource());
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }

}
