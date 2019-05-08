package travel.tool.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import travel.tool.model.Booking;
import travel.tool.model.Trip;
import util.IClientProtocol;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * @author ipop
 */
@Controller
public class BookingController extends AbstractFxController<Booking> {
    @Autowired
    private IClientProtocol server;
    private ObservableList<Trip> tripSearchList = FXCollections.observableArrayList();


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
    public ListView<Trip> searchResults;


    @Override
    protected void loadDetails() {
        entityList.clear();
        entityList.addAll(server.getAllBooking());

        bookingsTable.setItems(entityList);
    }

    @Override
    protected void setColumnProperties() {
        lId.setCellValueFactory(new PropertyValueFactory<>("id"));
        lTrip.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTrip().getLandmark().getName()));
        lCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        lPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
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
            server.updateAvailablePlaces(entity.getTrip().getId(),
                    server.getAvailablePlaces(entity.getTrip().getId()) + entity.getTickets());
            server.deleteBooking(entity);
        }

        loadDetails();
    }

    @Override
    protected void clearFields() {
        id.setText("");
        tId.setText("");
        tName.setText("");
        cName.clear();
        cPhoneNumber.clear();
        tickets.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSearchResultsListProperties();
        bookingsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadDetails();
    }

    public void submit(ActionEvent actionEvent) {
        Booking booking;
        if (notEmptyValidation("Trip", tId.getText().isEmpty())
                && notEmptyValidation("Customer Name", cName.getText().isEmpty())
                && notEmptyValidation("Customer Number", cPhoneNumber.getText().isEmpty())
                && notEmptyValidation("Tickets Number", tickets.getText().isEmpty())) {
            if (id.getText() == null || id.getText().isEmpty()) {
                int availablePlaces = server.getAvailablePlaces(getTrip().getId());
                if (availablePlaces < getTickets()) {
                    validationAlert("Available Places", false);
                    return;
                }
                booking = new Booking(getTrip(), getCustomer(), getPhoneNumber(), getTickets());
                server.updateAvailablePlaces(booking.getTrip().getId(),
                        booking.getTrip().getAvailablePlaces() - booking.getTickets());
                saveAlert();
            } else {
                booking = new Booking(getId(), getTripId(), getCustomer(), getPhoneNumber(), getTickets());
                int oldTicketsValue = server.getTicketsById(booking.getId());
                int ticketsDifference;
                if (booking.getTickets() > oldTicketsValue) {
                    ticketsDifference = booking.getTickets() - oldTicketsValue;
                    int availablePlaces = server.getAvailablePlaces(booking.getTrip().getId());
                    if (availablePlaces >= ticketsDifference) {
                        server.updateAvailablePlaces(booking.getTrip().getId(),
                                availablePlaces - ticketsDifference);
                    } else {
                        validationAlert("Available Places", false);
                        return;
                    }
                } else {
                    ticketsDifference = oldTicketsValue - booking.getTickets();
                    server.updateAvailablePlaces(booking.getTrip().getId(),
                            server.getAvailablePlaces(booking.getTrip().getId()) + ticketsDifference);
                }
                updateAlert(booking);
            }
            server.updateBooking(booking);
            if (stName.getText() != null) {
                searchTrip(actionEvent);
            }
            clearFields();
            loadDetails();
        }
    }

    public void searchTrip(ActionEvent actionEvent) {
        tripSearchList.clear();
        searchResults.getSelectionModel().clearSelection();
        searchResults.getItems().clear();
        tripSearchList.addAll(server.searchByNameDateAndTime(stName.getText(), stDate.getValue(), LocalTime.parse(stStartTrip.getText())));
        searchResults.setItems(tripSearchList);
    }

    private StringConverter<Trip> getTripStringConverter() {
        return new StringConverter<Trip>() {
            @Override
            public String toString(Trip object) {
                return object.getLandmark().getName();
            }

            @Override
            public Trip fromString(String string) {
                return tripSearchList.stream().filter(t -> t.getLandmark().getName().equals(string)).findFirst().orElse(null);
            }
        };
    }

    private void setSearchResultsListProperties() {
        searchResults.setCellFactory(param -> new ListCell<Trip>() {
            @Override
            protected void updateItem(Trip trip, boolean empty) {
                super.updateItem(trip, empty);

                if (empty || trip == null || trip.getLandmark().getName() == null) {
                    setText(null);
                } else {
                    setText(trip.getLandmark().getName() + " | " + trip.getAvailablePlaces() + " places left | price " +
                            trip.getPrice() + " | " + trip.getTransportCompany().getName());
                    if (trip.getAvailablePlaces() == 0) {
                        setTextFill(Color.RED);
                    } else{
                        setTextFill(Color.BLACK);
                    }
                }
            }
        });
        searchResults.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        tId.setText(Long.toString(newValue.getId()));
                        tName.setText(newValue.getLandmark().getName());
                    } else {
                        tId.setText(null);
                        tName.setText(null);
                    }
                });
    }

    private long getId() {
        return Long.parseLong(id.getText());
    }

    private int getTickets() {
        return Integer.valueOf(tickets.getText());
    }

    private String getPhoneNumber() {
        return cPhoneNumber.getText();
    }

    private String getCustomer() {
        return cName.getText();
    }

    private Trip getTrip() {
        return searchResults.getSelectionModel().getSelectedItem();
    }

    private Trip getTripId() {
        return new Trip(Long.parseLong(tId.getText()), null, null, null, null, 0, 0);
    }
}
