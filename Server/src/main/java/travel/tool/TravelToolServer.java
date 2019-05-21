package travel.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ipop
 */
@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class})
//@EnableAutoConfiguration
@ComponentScan({"travel.tool"})
@EntityScan("travel.tool")
@EnableJpaRepositories("travel.tool.repository.orm")
@EnableScheduling
public class TravelToolServer {
    public static void main(String[] args) {
        SpringApplication.run(TravelToolServer.class, args);
    }
}