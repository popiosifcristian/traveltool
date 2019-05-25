package travel.tool.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import travel.tool.controller.TravelToolServerImpl;
import util.IServerProtocol;

import javax.sql.DataSource;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * @author ipop
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class ApplicationConfiguration {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/traveltool");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("postgres");
        return dataSourceBuilder.build();
    }

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

//    @Bean
//    public IServerProtocol getServer() {
//        return new TravelToolServerImpl();
//    }
//
//    @Bean
//    public TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor();
//    }
//
//    @Bean
//    public CommandLineRunner schedulingRunner(TaskExecutor executor) {
//        return strings -> executor.execute(getServer());
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

