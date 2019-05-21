package travel.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ipop
 */
@SpringBootApplication
@ComponentScan({"travel.tool"})
@EntityScan("travel.tool")
@EnableJpaRepositories("travel.tool.repository.orm")
@EnableScheduling
public class TravelToolServer {
    public static void main(String[] args) {
        SpringApplication.run(TravelToolServer.class, args);
    }
}