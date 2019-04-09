package travel.tool.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import travel.tool.entity.Booking;
import travel.tool.service.BookingService;
import travel.tool.service.CustomerService;
import travel.tool.service.TripService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ipop
 */
@Controller
public class BookingController extends AbstractFxController<Booking> {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TripService tripService;

    public Label id;
    public Label tId;
    public Label tName;
    public TextField cName;
    public TextField cPhoneNumber;
    public TextField tickets;
    public Button submit;
    public Button clear;
    public TableView<Booking> bookingsTable;
    public TableColumn<Booking, Long> lId;
    public TableColumn<Booking, String> lTrip;
    public TableColumn<Booking, String> lCustomer;
    public TableColumn<Booking, String> lPhoneNumber;
    public TableColumn<Booking, Integer> lTickets;
    public TableColumn<Booking, Boolean> lEdit;
    public TableColumn<Booking, Boolean> lDelete;
    public TextField stName;
    public TextField stStartTrip;
    public DatePicker stDate;
    public Button searchTrip;


    @Override
    protected void loadDetails() {
        entityList.clear();
        entityList.addAll(bookingService.getAll());

        bookingsTable.setItems(entityList);
    }

    @Override
    protected void setColumnProperties() {
        lId.setCellValueFactory(new PropertyValueFactory<>("id"));
        lTrip.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getTrip().getLandmark().getName()));
        lCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        lCustomer.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        lTickets.setCellValueFactory(new PropertyValueFactory<>("tickets"));
        lEdit.setCellFactory(editCellFactory);
        lDelete.setCellFactory(deleteCellFactory);
    }

    @Override
    protected void update(Booking entity) {
        id.setText(Long.toString(entity.getId()));
        tId.setText(Long.toString(entity.getTrip().getId()));
        tName.setText(entity.getTrip().getLandmark().getName());
        cName.setText(entity.getCustomer());
        cPhoneNumber.setText(entity.getPhoneNumber());
        tickets.setText(Integer.toString(entity.getTickets()));
    }

    @Override
    protected void delete(Booking entity) {
        if (deleteAlert()) {
            bookingService.delete(entity);
        }

        loadDetails();
    }

    @Override
    protected void clearFields() {
        id.setText(null);
        tId.setText(null);
        tName.setText(null);
        cName.clear();
        cPhoneNumber.clear();
        tickets.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookingsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadDetails();
    }

    public void submit(ActionEvent actionEvent) {
        //TODO: FINISH CONTROLLER
    }

    public void searchTrip(ActionEvent actionEvent) {

    }
}
