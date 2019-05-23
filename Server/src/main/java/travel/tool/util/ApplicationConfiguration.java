package travel.tool.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * @author ipop
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class ApplicationConfiguration {

//    @Value("${db.name}")
//    private String dataSourceDatabase;
//    @Value("${db.url}")
//    private String dataSourceUrl;
//    @Value("${db.host}")
//    private String dataSourceHost;
//    @Value("${db.username}")
//    private String dataSourceUsername;
//    @Value("${db.password}")
//    private String dataSourcePassword;
//    @Autowired
//    @Lazy
//    private TravelToolServerImpl server;

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

//    @Bean
//    public TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor();
//    }
//
//    @Bean
//    public CommandLineRunner schedulingRunner(TaskExecutor executor) {
//        return strings -> executor.execute(server);
//    }

    @Bean
    public FormattingConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        registrar.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm:ss"));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        registrar.registerFormatters(conversionService);

        return conversionService;
    }
}

