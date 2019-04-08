package travel.tool.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import travel.tool.entity.Company;
import travel.tool.entity.CompanyType;
import travel.tool.entity.Employee;
import travel.tool.service.CompanyService;
import travel.tool.service.EmployeeService;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * ue - stands for update employee in the update form
 * le - stands for list employee in the table view
 *
 * @author ipop
 */
@Controller
public class EmployeeController extends AbstractFxController<Employee> {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompanyService companyService;

    private ObservableList<Company> companyList = FXCollections.observableArrayList();

    @FXML
    private Label ueId;
    @FXML
    private TextField ueUsername;
    @FXML
    private TextField uePassword;
    @FXML
    private TextField ueEmail;
    @FXML
    private TextField ueFirstName;
    @FXML
    private TextField ueLastName;
    @FXML
    private TextField uePhoneNumber;
    @FXML
    private ChoiceBox<Company> ueAgency;
    @FXML
    private Button ueSubmit;
    @FXML
    private Button ueClear;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Long> leId;
    @FXML
    private TableColumn<Employee, String> leUsername;
    @FXML
    private TableColumn<Employee, String> lePassword;
    @FXML
    private TableColumn<Employee, String> leEmail;
    @FXML
    private TableColumn<Employee, String> leFirstName;
    @FXML
    private TableColumn<Employee, String> leLastName;
    @FXML
    private TableColumn<Employee, String> lePhoneNumber;
    @FXML
    private TableColumn<Employee, String> leAgency;
    @FXML
    private TableColumn<Employee, Boolean> leEdit;
    @FXML
    private TableColumn<Employee, Boolean> leDelete;

    @Override
    protected void loadDetails() {
        updateAgencyList();
        entityList.clear();
        entityList.addAll(employeeService.getAll());

        employeeTable.setItems(entityList);
    }

    @Override
    protected void setColumnProperties() {
        leId.setCellValueFactory(new PropertyValueFactory<>("id"));
        leUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        lePassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        leEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        leFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        leLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lePhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        leAgency.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAgency().getName()));
        leEdit.setCellFactory(editCellFactory);
        leDelete.setCellFactory(deleteCellFactory);
    }

    @Override
    protected void update(Employee employee) {
        ueId.setText(Long.toString(employee.getId()));
        ueUsername.setText(employee.getUsername());
        uePassword.setText(employee.getPassword());
        ueEmail.setText(employee.getEmail());
        ueFirstName.setText(employee.getFirstName());
        ueLastName.setText(employee.getLastName());
        uePhoneNumber.setText(employee.getPhoneNumber());
        ueAgency.setValue(employee.getAgency());
    }

    @Override
    protected void delete(Employee entity) {
        if (deleteAlert()) {
            employeeService.delete(entity);
        }

        loadDetails();
    }

    @Override
    protected void clearFields() {
        ueId.setText(null);
        ueUsername.clear();
        uePassword.clear();
        ueEmail.clear();
        ueFirstName.clear();
        ueLastName.clear();
        uePhoneNumber.clear();
        updateAgencyList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ueAgency.setConverter(getCompanyStringConverter());
        updateAgencyList();
        employeeTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadDetails();
    }

    @FXML
    private void ueSubmit(ActionEvent actionEvent) {
        Employee employee;
        if (notEmptyValidation("Agency", ueAgency.getSelectionModel().isEmpty())) {
            if (ueId.getText() == null || ueId.getText().isEmpty()) {
                employee = new Employee(getUsername(), getPassword(), getEmail(), getFirstName(), getLastName(),
                        getPhoneNumber(), getCompany());
                saveAlert();

            } else {
                employee = new Employee(getId(), getUsername(), getPassword(), getEmail(), getFirstName(), getLastName(),
                        getPhoneNumber(), getCompany());
                updateAlert(employee);

            }
            employeeService.update(employee);

            clearFields();
            loadDetails();
        }
    }

    private void updateAgencyList() {
        companyList.clear();
        ueAgency.getSelectionModel().clearSelection();
        ueAgency.getItems().clear();
        companyList.addAll(companyService.getAll().stream()
                .filter(company -> company.getCompanyType().equals(CompanyType.AGENCY)).collect(Collectors.toList()));
        ueAgency.setItems(companyList);
    }

    private Long getId() {
        return Long.parseLong(ueId.getText());
    }

    private String getUsername() {
        return ueUsername.getText();
    }

    private String getPassword() {
        return uePassword.getText();
    }

    private String getEmail() {
        return ueEmail.getText();
    }

    private String getFirstName() {
        return ueFirstName.getText();
    }

    private String getLastName() {
        return ueLastName.getText();
    }

    private String getPhoneNumber() {
        return uePhoneNumber.getText();
    }

    private Company getCompany() {
        return ueAgency.getSelectionModel().getSelectedItem();
    }

    private StringConverter<Company> getCompanyStringConverter() {
        return new StringConverter<Company>() {
            @Override
            public String toString(Company object) {
                return object.getName();
            }

            @Override
            public Company fromString(String string) {
                return companyList.stream().filter(c -> c.getName().equals(string)).findFirst().orElse(null);
            }
        };
    }
}
