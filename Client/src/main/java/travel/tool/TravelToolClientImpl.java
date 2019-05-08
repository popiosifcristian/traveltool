package travel.tool;

import org.apache.log4j.Logger;
import travel.tool.model.*;
import travel.tool.protocol.Request;
import travel.tool.protocol.Response;
import travel.tool.protocol.Type;
import util.IClientProtocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static travel.tool.protocol.Type.*;

/**
 * @author ipop
 */
public class TravelToolClientImpl implements IClientProtocol {
    private static final Logger LOGGER = Logger.getLogger(TravelToolClientImpl.class);
    private volatile boolean connected;
    private Socket socket;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public TravelToolClientImpl() {
        try {
            connected = true;
            LOGGER.info("Started client on port: " + getSocketPort());
            socket = new Socket("localhost", getSocketPort());
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRequest(Request request) {
        try {
            writer.writeObject(request);
            writer.flush();
            LOGGER.info("Sent request: " + request.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Response receiveResponse(Request request) {
        Response response = null;
        try {
            response = (Response) reader.readObject();
            LOGGER.info("Received response: " + response.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (request.getType().equals(response.getType())) {
            return response;
        } else {
            LOGGER.error("Bad response: " + response);
            LOGGER.error("For request: " + request);
            return response;
        }
    }

    private Response handleCommunication(Object object, Type type) {
        Request request = new Request(object, type);
        sendRequest(request);
        return receiveResponse(request);
    }

    @Override
    public List<Booking> getAllBooking() {
        Response response = handleCommunication(null, GET_ALL_BOOKING);
        if (response != null) {
            return (List<Booking>) response.getData();
        }
        return new ArrayList<>();
    }

    @Override
    public Booking findBookingById(long id) {
        Response response = handleCommunication(id, FIND_BOOKING_BY_ID);
        if (response != null) {
            return (Booking) response.getData();
        }
        return null;
    }

    @Override
    public Booking updateBooking(Booking model) {
        Response response = handleCommunication(model, UPDATE_BOOKING);
        if (response != null) {
            return (Booking) response.getData();
        }
        return null;
    }

    @Override
    public boolean deleteBooking(Booking model) {
        Response response = handleCommunication(model, DELETE_BOOKING);
        if (response != null) {
            return (Boolean) response.getData();
        }
        return false;
    }

    @Override
    public List<Company> getAllCompany() {
        Response response = handleCommunication(null, GET_ALL_COMPANY);
        if (response != null) {
            return (List<Company>) response.getData();
        }
        return new ArrayList<>();
    }

    @Override
    public Company findCompanyById(long id) {
        Response response = handleCommunication(id, FIND_COMPANY_BY_ID);
        if (response != null) {
            return (Company) response.getData();
        }
        return null;
    }

    @Override
    public Company updateCompany(Company model) {
        Response response = handleCommunication(model, UPDATE_COMPANY);
        if (response != null) {
            return (Company) response.getData();
        }
        return null;
    }

    @Override
    public boolean deleteCompany(Company model) {
        Response response = handleCommunication(model, DELETE_COMPANY);
        if (response != null) {
            return (Boolean) response.getData();
        }
        return false;
    }

    @Override
    public List<Customer> getAllCustomer() {
        Response response = handleCommunication(null, GET_ALL_CUSTOMER);
        if (response != null) {
            return (List<Customer>) response.getData();
        }
        return new ArrayList<>();
    }

    @Override
    public Customer findCustomerById(long id) {
        Response response = handleCommunication(id, FIND_CUSTOMER_BY_ID);
        if (response != null) {
            return (Customer) response.getData();
        }
        return null;
    }

    @Override
    public Customer updateCustomer(Customer model) {
        Response response = handleCommunication(model, UPDATE_CUSTOMER);
        if (response != null) {
            return (Customer) response.getData();
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(Customer model) {
        Response response = handleCommunication(model, DELETE_CUSTOMER);
        if (response != null) {
            return (Boolean) response.getData();
        }
        return false;
    }

    @Override
    public List<Employee> getAllEmployee() {
        Response response = handleCommunication(null, GET_ALL_EMPLOYEE);
        if (response != null) {
            return (List<Employee>) response.getData();
        }
        return new ArrayList<>();
    }

    @Override
    public Employee findEmployeeById(long id) {
        Response response = handleCommunication(id, FIND_EMPLOYEE_BY_ID);
        if (response != null) {
            return (Employee) response.getData();
        }
        return null;
    }

    @Override
    public Employee updateEmployee(Employee model) {
        Response response = handleCommunication(model, UPDATE_EMPLOYEE);
        if (response != null) {
            return (Employee) response.getData();
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(Employee model) {
        Response response = handleCommunication(model, DELETE_EMPLOYEE);
        if (response != null) {
            return (Boolean) response.getData();
        }
        return false;
    }

    @Override
    public List<Landmark> getAllLandmark() {
        Response response = handleCommunication(null, GET_ALL_LANDMARK);
        if (response != null) {
            return (List<Landmark>) response.getData();
        }
        return new ArrayList<>();
    }

    @Override
    public Landmark findLandmarkById(long id) {
        Response response = handleCommunication(id, FIND_LANDMARK_BY_ID);
        if (response != null) {
            return (Landmark) response.getData();
        }
        return null;
    }

    @Override
    public Landmark updateLandmark(Landmark model) {
        Response response = handleCommunication(model, UPDATE_LANDMARK);
        if (response != null) {
            return (Landmark) response.getData();
        }
        return null;
    }

    @Override
    public boolean deleteLandmark(Landmark model) {
        Response response = handleCommunication(model, DELETE_LANDMARK);
        if (response != null) {
            return (Boolean) response.getData();
        }
        return false;
    }

    @Override
    public List<Trip> getAllTrip() {
        Response response = handleCommunication(null, GET_ALL_TRIP);
        if (response != null) {
            return (List<Trip>) response.getData();
        }
        return new ArrayList<>();
    }

    @Override
    public Trip findTripById(long id) {
        Response response = handleCommunication(id, FIND_TRIP_BY_ID);
        if (response != null) {
            return (Trip) response.getData();
        }
        return null;
    }

    @Override
    public Trip updateTrip(Trip model) {
        Response response = handleCommunication(model, UPDATE_TRIP);
        if (response != null) {
            return (Trip) response.getData();
        }
        return null;
    }

    @Override
    public boolean deleteTrip(Trip model) {
        Response response = handleCommunication(model, DELETE_TRIP);
        if (response != null) {
            return (Boolean) response.getData();
        }
        return false;
    }

    @Override
    public int getTicketsById(long id) {
        Response response = handleCommunication(id, GET_TICKETS_BY_ID);
        if (response != null) {
            return (Integer) response.getData();
        }
        return 0;
    }

    @Override
    public Employee findByUsername(String username) {
        Response response = handleCommunication(username, FIND_BY_USERNAME);
        if (response != null) {
            return (Employee) response.getData();
        }
        return null;
    }

    @Override
    public List<Trip> searchByNameDateAndTime(String name, LocalDate date, LocalTime startTime) {
        Response response = handleCommunication(new Object[]{name, date, startTime}, SEARCH_BY_NAME_DATE_AND_TIME);
        if (response != null) {
            return (List<Trip>) response.getData();
        }
        return new ArrayList<>();
    }

    @Override
    public void updateAvailablePlaces(long id, int availablePlaces) {
        Response response = handleCommunication(new Object[]{id, availablePlaces}, UPDATE_AVAILABLE_PLACES);
    }

    @Override
    public int getAvailablePlaces(long id) {
        Response response = handleCommunication(id, GET_AVAILABLE_PLACES);
        if (response != null) {
            return (Integer) response.getData();
        }
        return 0;
    }

    @Override
    public boolean authenticate(String username, String password) {
        Response response = handleCommunication(new Object[]{username, password}, AUTHENTICATE);
        if (response != null) {
            return (Boolean) response.getData();
        }
        return false;
    }

    private int getSocketPort() {
        return Integer.valueOf(ResourceBundle.getBundle("Bundle").getString("socket.port"));
    }
}
