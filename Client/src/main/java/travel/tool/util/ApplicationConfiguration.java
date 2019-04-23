package travel.tool.util;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

/**
 * @author ipop
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class ApplicationConfiguration {

    @Autowired
    private SpringFXMLLoader springFXMLLoader;

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
