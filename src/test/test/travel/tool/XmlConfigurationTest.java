package travel.tool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import travel.tool.service.CompanyService;

/**
 * @author ipop
 */
public class XmlConfigurationTest {
    private static ApplicationContext context =
            new ClassPathXmlApplicationContext("application_context_test.xml");

    public static CompanyService companyService(){
        return context.getBean(CompanyService.class, "companyService");
    }
}
