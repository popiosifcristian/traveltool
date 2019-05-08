package travel.tool.util;

import java.util.ResourceBundle;

public enum FxmlView {

    COMPANY {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("company.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/company.fxml";
        }
    },
    CUSTOMER {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("customer.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/customer.fxml";
        }
    },
    BOOKING {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("booking.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/booking.fxml";
        }
    },
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
    LANDMARK {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("landmark.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/landmark.fxml";
        }
    },
    TRIP {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("trip.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/trip.fxml";
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
