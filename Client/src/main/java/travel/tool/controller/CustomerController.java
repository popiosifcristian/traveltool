package travel.tool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import travel.tool.model.Customer;
import util.IClientProtocol;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ipop
 */
@Controller
public class CustomerController extends AbstractFxController<Customer> {
    @Autowired
    private IClientProtocol server;

    @FXML
    public Label id;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public TextField email;
    @FXML
    public TextField phoneNumber;
    @FXML
    public Button submit;
    @FXML
    public Button clear;
    @FXML
    public TableView<Customer> customerTable;
    @FXML
    public TableColumn<Customer, Long> lId;
    @FXML
    public TableColumn<Customer, String> lFirstName;
    @FXML
    public TableColumn<Customer, String> lLastName;
    @FXML
    public TableColumn<Customer, String> lEmail;
    @FXML
    public TableColumn<Customer, String> lPhoneNumber;
    @FXML
    public TableColumn<Customer, Boolean> lEdit;
    @FXML
    public TableColumn<Customer, Boolean> lDelete;

    @Override
    protected void loadDetails() {
        entityList.clear();
        entityList.addAll(server.getAllCustomer());

        customerTable.setItems(entityList);
    }

    @Override
    protected void setColumnProperties() {
        lId.setCellValueFactory(new PropertyValueFactory<>("id"));
        lFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        lPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        lEdit.setCellFactory(editCellFactory);
        lDelete.setCellFactory(deleteCellFactory);
    }

    @Override
    protected void update(Customer entity) {
        id.setText(Long.toString(entity.getId()));
        firstName.setText(entity.getFirstName());
        lastName.setText(entity.getLastName());
        email.setText(entity.getEmail());
        phoneNumber.setText(entity.getPhoneNumber());
    }

    @Override
    protected void delete(Customer entity) {
        if (deleteAlert()) {
            server.deleteCustomer(entity);
        }

        loadDetails();
    }

    @Override
    protected void clearFields() {
        id.setText(null);
        firstName.clear();
        lastName.clear();
        email.clear();
        phoneNumber.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadDetails();
    }

    @FXML
    public void submit(ActionEvent actionEvent) {
        Customer customer;
        if (id.getText() == null || id.getText().isEmpty()) {
            customer = new Customer(getFirstName(), getLastName(), getEmail(), getPhoneNumber());
            saveAlert();
        } else {
            customer = new Customer(getId(), getFirstName(), getLastName(), getEmail(), getPhoneNumber());
            updateAlert(customer);
        }
        server.updateCustomer(customer);

        clearFields();
        loadDetails();
    }

    private Long getId() {
        return Long.parseLong(id.getText());
    }

    private String getFirstName() {
        return firstName.getText();
    }

    private String getLastName() {
        return lastName.getText();
    }

    private String getEmail() {
        return email.getText();
    }

    private String getPhoneNumber() {
        return phoneNumber.getText();
    }
}
