package travel.tool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import travel.tool.repository.ICustomerRepository;
import travel.tool.repository.dao.JdbcTemplateCustomer;

import javax.sql.DataSource;

/**
 * @author ipop
 */
@Configuration
public class ApplicationConfiguration {

    //    @Value("${db.name}")
    private String dataSourceDatabase = "traveltool";
    //    @Value("${db.host}")
    private String dataSourceHost = "localhost";
    //    @Value("${spring.datasource.username}")
    private String dataSourceUsername = "postgres";
    //    @Value("${spring.datasource.password}")
    private String dataSourcePassword = "postgres";


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
    public ICustomerRepository customerDAO() {
        return new JdbcTemplateCustomer(dataSource());
    }
}
