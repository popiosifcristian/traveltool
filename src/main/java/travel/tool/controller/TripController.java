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
import travel.tool.entity.*;
import travel.tool.service.CompanyService;
import travel.tool.service.CustomerService;
import travel.tool.service.LandmarkService;
import travel.tool.service.TripService;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author ipop
 */
@Controller
public class TripController extends AbstractFxController<Trip> {
    @Autowired
    private TripService tripService;
    @Autowired
    private LandmarkService landmarkService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    private ObservableList<Landmark> landmarkObservableList = FXCollections.observableArrayList();
    private ObservableList<Company> companyObservableList = FXCollections.observableArrayList();
    private ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();


    @FXML
    public Label id;
    @FXML
    public ChoiceBox<Landmark> landmark;
    @FXML
    public ChoiceBox<Company> company;
    @FXML
    public DatePicker date;
    @FXML
    public TextField startTime;
    @FXML
    public TextField price;
    @FXML
    public TextField availablePlaces;
    @FXML
    public ListView customerList;
    @FXML
    public Button submit;
    @FXML
    public Button clear;
    @FXML
    public TableView<Trip> tripTable;
    @FXML
    public TableColumn<Trip, Long> lId;
    @FXML
    public TableColumn<Trip, String> lLandmark;
    @FXML
    public TableColumn<Trip, String> lCompany;
    @FXML
    public TableColumn<Trip, String> lDate;
    @FXML
    public TableColumn<Trip, String> lStartTime;
    @FXML
    public TableColumn<Trip, Double> lPrice;
    @FXML
    public TableColumn<Trip, Integer> lAvailablePlaces;
    @FXML
    public TableColumn<Trip, String> lCustomerList;
    @FXML
    public TableColumn<Trip, Boolean> lEdit;
    @FXML
    public TableColumn<Trip, Boolean> lDelete;

    @Override
    protected void loadDetails() {
        updateCompanyList();
        updateLandmarkList();
        entityList.clear();
        entityList.addAll(tripService.getAll());

        tripTable.setItems(entityList);
    }

    @Override
    protected void setColumnProperties() {
        lId.setCellValueFactory(new PropertyValueFactory<>("id"));
        lLandmark.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLandmark().getName()));
        lCompany.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTransportCompany().getName()));
        lDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));
        lStartTime.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartTime().toString()));
        lPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        lAvailablePlaces.setCellValueFactory(new PropertyValueFactory<>("availablePlaces"));
        lCustomerList.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerList().toString()));
        lEdit.setCellFactory(editCellFactory);
        lDelete.setCellFactory(deleteCellFactory);
    }

    @Override
    protected void update(Trip entity) {
        id.setText(Long.toString(entity.getId()));
        landmark.setValue(entity.getLandmark());
        company.setValue(entity.getTransportCompany());
        date.setValue(LocalDate.of(entity.getDate().getYear(), entity.getDate().getMonthValue(), entity.getDate().getDayOfMonth()));
        startTime.setText(entity.getStartTime().toString());
        price.setText(String.valueOf(entity.getPrice()));
        availablePlaces.setText(String.valueOf(entity.getAvailablePlaces()));
        customerList.setItems(FXCollections.observableArrayList(entity.getCustomerList()));
    }

    @Override
    protected void delete(Trip entity) {
        if (deleteAlert()) {
            tripService.delete(entity);
        }

        loadDetails();
    }

    @Override
    protected void clearFields() {
        id.setText(null);
        date.getEditor().clear();
        date.setValue(null);
        startTime.clear();
        price.clear();
        availablePlaces.clear();
        customerList.getItems().clear();
        updateCompanyList();
        updateLandmarkList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        landmark.setConverter(getLandmarkStringConverter());
        company.setConverter(getCompanyStringConverter());
        updateLandmarkList();
        updateCompanyList();
        tripTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadDetails();
    }

    public void submit(ActionEvent actionEvent) {
        Trip trip;
        if (notEmptyValidation("Company", company.getSelectionModel().isEmpty()) &&
                notEmptyValidation("Landmark", landmark.getSelectionModel().isEmpty())) {
            if (id.getText() == null || id.getText().isEmpty()) {
                trip = new Trip(getLandmark(), getCompany(), getDate(), getStartTime(), getPrice(),
                        getAvailablePlaces(), getCustomerList());
                saveAlert();
            } else {
                trip = new Trip(getId(), getLandmark(), getCompany(), getDate(), getStartTime(), getPrice(),
                        getAvailablePlaces(), getCustomerList());
                updateAlert(trip);
            }
            tripService.update(trip);

            clearFields();
            loadDetails();
        }
    }

    private void updateLandmarkList() {
        landmarkObservableList.clear();
        landmark.getSelectionModel().clearSelection();
        landmark.getItems().clear();
        landmarkObservableList.addAll(landmarkService.getAll());
        landmark.setItems(landmarkObservableList);
    }


    private void updateCompanyList() {
        companyObservableList.clear();
        company.getSelectionModel().clearSelection();
        company.getItems().clear();
        companyObservableList.addAll(companyService.getAll().stream()
                .filter(company -> company.getCompanyType().equals(CompanyType.TRANSPORT)).collect(Collectors.toList()));
        company.setItems(companyObservableList);
    }

    private StringConverter<Company> getCompanyStringConverter() {
        return new StringConverter<Company>() {
            @Override
            public String toString(Company object) {
                return object.getName();
            }

            @Override
            public Company fromString(String string) {
                return companyObservableList.stream().filter(c -> c.getName().equals(string)).findFirst().orElse(null);
            }
        };
    }

    private StringConverter<Landmark> getLandmarkStringConverter() {
        return new StringConverter<Landmark>() {
            @Override
            public String toString(Landmark object) {
                return object.getName();
            }

            @Override
            public Landmark fromString(String string) {
                return landmarkObservableList.stream().filter(c -> c.getName().equals(string)).findFirst().orElse(null);
            }
        };
    }

    private Long getId() {
        return Long.parseLong(id.getText());
    }

    private Landmark getLandmark() {
        return landmark.getSelectionModel().getSelectedItem();
    }

    private Company getCompany() {
        return company.getSelectionModel().getSelectedItem();
    }

    private LocalDate getDate() {
        return date.getValue();
    }

    private LocalTime getStartTime() {
        return LocalTime.parse(startTime.getText());
    }

    private Double getPrice() {
        return Double.valueOf(price.getText());
    }

    private Integer getAvailablePlaces() {
        return Integer.valueOf(availablePlaces.getText());
    }

    private List<Customer> getCustomerList() {
        return customerList.getItems();
    }
}
