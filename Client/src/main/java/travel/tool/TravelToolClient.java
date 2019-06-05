package travel.tool;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import travel.tool.util.FxmlView;
import travel.tool.util.StageManager;

/**
 * @author ipop
 */
@SpringBootApplication(scanBasePackages = {"travel.tool"}, exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class})
public class TravelToolClient extends Application {

    protected ConfigurableApplicationContext springContext;

    protected StageManager stageManager;

    public static void main(final String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        springContext = springBootApplicationContext();
    }

    @Override
    public void start(Stage stage) {
        stageManager = springContext.getBean(StageManager.class, stage);
        displayInitialScene();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    /**
     * Useful to override this method by sub-classes wishing to change the first
     * Scene to be displayed on startup. Example: Functional tests on main
     * window.
     */
    protected void displayInitialScene() {
        stageManager.switchScene(FxmlView.LOGIN);
    }


    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(TravelToolClient.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        return builder.run(args);
    }

}