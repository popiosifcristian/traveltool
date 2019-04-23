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
import travel.tool.model.Company;
import travel.tool.model.CompanyType;

import util.IServer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ipop
 */
@Controller
public class CompanyController extends AbstractFxController<Company> {

    @Autowired
    private IServer server;
    private ObservableList<CompanyType> companyTypes = FXCollections.observableArrayList(CompanyType.values());

    @FXML
    public Label id;
    @FXML
    public TextField name;
    @FXML
    public TextField address;
    @FXML
    public TextField website;
    @FXML
    public TextField email;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField description;
    @FXML
    public ChoiceBox<CompanyType> type;
    @FXML
    public Button submit;
    @FXML
    public Button clear;
    @FXML
    public TableView<Company> companyTable;
    @FXML
    public TableColumn<Company, Long> lId;
    @FXML
    public TableColumn<Company, String> lName;
    @FXML
    public TableColumn<Company, String> lAddress;
    @FXML
    public TableColumn<Company, String> lWebsite;
    @FXML
    public TableColumn<Company, String> lEmail;
    @FXML
    public TableColumn<Company, String> lPhoneNumber;
    @FXML
    public TableColumn<Company, String> lDescription;
    @FXML
    public TableColumn<Company, String> lType;
    @FXML
    public TableColumn<Company, Boolean> lEdit;
    @FXML
    public TableColumn<Company, Boolean> lDelete;

    @Override
    protected void loadDetails() {
        entityList.clear();
        entityList.addAll(server.getAllCompany());

        companyTable.setItems(entityList);
    }

    @Override
    protected void setColumnProperties() {
        lId.setCellValueFactory(new PropertyValueFactory<>("id"));
        lName.setCellValueFactory(new PropertyValueFactory<>("name"));
        lAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        lWebsite.setCellValueFactory(new PropertyValueFactory<>("website"));
        lEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        lPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        lDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        lType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCompanyType().name()));
        lEdit.setCellFactory(editCellFactory);
        lDelete.setCellFactory(deleteCellFactory);
    }

    @Override
    protected void update(Company entity) {
        id.setText(Long.toString(entity.getId()));
        name.setText(entity.getName());
        address.setText(entity.getAddress());
        website.setText(entity.getWebsite());
        email.setText(entity.getEmail());
        phoneNumber.setText(entity.getPhoneNumber());
        description.setText(entity.getDescription());
        type.setValue(entity.getCompanyType());
    }

    @Override
    protected void delete(Company entity) {
        if (deleteAlert()) {
            server.deleteCompany(entity);
        }

        loadDetails();
    }

    @Override
    protected void clearFields() {
        id.setText(null);
        name.clear();
        address.clear();
        email.clear();
        website.clear();
        phoneNumber.clear();
        description.clear();
        type.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.setConverter(getCompanyTypeStringConverter());
        type.setItems(companyTypes);
        companyTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadDetails();
    }

    @FXML
    public void submit(ActionEvent actionEvent) {
        Company company;
        if (notEmptyValidation("Type", type.getSelectionModel().isEmpty())) {
            if (id.getText() == null || id.getText().isEmpty()) {
                company = new Company(getName(), getAddress(), getWebsite(), getEmail(), getPhoneNumber(), getDescription(), getType());
                saveAlert();
            } else {
                company = new Company(getId(), getName(), getAddress(), getWebsite(), getEmail(), getPhoneNumber(), getDescription(), getType());
                updateAlert(company);
            }
            server.updateCompany(company);

            clearFields();
            loadDetails();
        }

    }

    private Long getId() {
        return Long.parseLong(id.getText());
    }

    private String getName() {
        return name.getText();
    }

    private String getAddress() {
        return address.getText();
    }

    private String getWebsite() {
        return website.getText();
    }

    private String getEmail() {
        return email.getText();
    }

    private String getPhoneNumber() {
        return phoneNumber.getText();
    }

    private String getDescription() {
        return description.getText();
    }

    private CompanyType getType() {
        return type.getSelectionModel().getSelectedItem();
    }

    private StringConverter<CompanyType> getCompanyTypeStringConverter() {
        return new StringConverter<CompanyType>() {
            @Override
            public String toString(CompanyType object) {
                return object.name();
            }

            @Override
            public CompanyType fromString(String string) {
                return CompanyType.valueOf(string);
            }
        };
    }
}
