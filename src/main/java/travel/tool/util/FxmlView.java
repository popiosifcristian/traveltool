package travel.tool.util;

import java.util.ResourceBundle;

public enum FxmlView {

    EMPLOYEE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("employee.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/employee.fxml";
        }
    },
    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/login.fxml";
        }
    };

    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
