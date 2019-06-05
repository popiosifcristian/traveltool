package travel.tool.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel.tool.model.*;
import travel.tool.protocol.Request;
import travel.tool.protocol.Response;
import travel.tool.service.*;
import util.IServerProtocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * @author ipop
 */
public class TravelToolServerImpl implements IServerProtocol, Runnable {
    private static final Logger LOGGER = Logger.getLogger(TravelToolServerImpl.class);
    @Autowired
    private BookingService bookingService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private LandmarkService landmarkService;
    @Autowired
    private TripService tripService;

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public TravelToolServerImpl() {
        try {
            serverSocket = new ServerSocket(getSocketPort());
            LOGGER.info("Started server on port: " + getSocketPort());
            LOGGER.info("Waiting client to connect: " + getSocketPort());
            clientSocket = serverSocket.accept();
            LOGGER.info("Client connected server on port: " + getSocketPort());
            writer = new ObjectOutputStream(clientSocket.getOutputStream());
            reader = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Request request;
        while (!serverSocket.isClosed()) {
            request = receiveRequest();
            sendResponse(handleRequest(request));
        }
    }

    private synchronized Response handleRequest(Request request) {
        Response response = null;
        try {
            Method method = this.getClass().getDeclaredMethod(request.getType().getTypeName(), Request.class);
            response = (Response) method.invoke(this, request);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return response;
    }

    private synchronized void sendResponse(Response response) {
        try {
            writer.writeObject(response);
            writer.flush();
            LOGGER.info("Sent response: " + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized Request receiveRequest() {
        Request request = null;
        try {
            request = (Request) reader.readObject();
            LOGGER.info("Received request: " + request.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public Response getAllBooking(Request request) {
        return createResponse(bookingService.findAll(), request);
    }

    @Override
    public Response findBookingById(Request request) {
        return createResponse(bookingService.getOne((Long) request.getData()), request);
    }

    @Override
    public Response updateBooking(Request request) {
        return createResponse(bookingService.save((Booking) request.getData()), request);
    }

    @Override
    public Response deleteBooking(Request request) {
        bookingService.delete((Booking) request.getData());
        return createResponse(true, request);
    }

    @Override
    public Response getAllCompany(Request request) {
        return createResponse(companyService.findAll(), request);
    }

    @Override
    public Response findCompanyById(Request request) {
        return createResponse(companyService.getOne((Long) request.getData()), request);
    }

    @Override
    public Response updateCompany(Request request) {
        return createResponse(companyService.save((Company) request.getData()), request);
    }

    @Override
    public Response deleteCompany(Request request) {
        companyService.delete((Company) request.getData());
        return createResponse(true, request);
    }

    @Override
    public Response getAllCustomer(Request request) {
        return createResponse(customerService.findAll(), request);
    }

    @Override
    public Response findCustomerById(Request request) {
        return createResponse(customerService.getOne((Long) request.getData()), request);
    }

    @Override
    public Response updateCustomer(Request request) {
        return createResponse(customerService.save((Customer) request.getData()), request);
    }

    @Override
    public Response deleteCustomer(Request request) {
        customerService.delete((Customer) request.getData());
        return createResponse(true, request);
    }

    @Override
    public Response getAllEmployee(Request request) {
        return createResponse(employeeService.findAll(), request);
    }

    @Override
    public Response findEmployeeById(Request request) {
        return createResponse(employeeService.getOne((Long) request.getData()), request);
    }

    @Override
    public Response updateEmployee(Request request) {
        return createResponse(employeeService.save((Employee) request.getData()), request);
    }

    @Override
    public Response deleteEmployee(Request request) {
        employeeService.delete((Employee) request.getData());
        return createResponse(true, request);
    }

    @Override
    public Response getAllLandmark(Request request) {
        return createResponse(landmarkService.findAll(), request);
    }

    @Override
    public Response findLandmarkById(Request request) {
        return createResponse(landmarkService.getOne((Long) request.getData()), request);
    }

    @Override
    public Response updateLandmark(Request request) {
        return createResponse(landmarkService.save((Landmark) request.getData()), request);
    }

    @Override
    public Response deleteLandmark(Request request) {
        landmarkService.delete((Landmark) request.getData());
        return createResponse(true, request);
    }

    @Override
    public Response getAllTrip(Request request) {
        return createResponse(tripService.findAll(), request);
    }

    @Override
    public Response findTripById(Request request) {
        return createResponse(tripService.getOne((Long) request.getData()), request);
    }

    @Override
    public Response updateTrip(Request request) {
        return createResponse(tripService.save((Trip) request.getData()), request);
    }

    @Override
    public Response deleteTrip(Request request) {
        tripService.delete((Trip) request.getData());
        return createResponse(true, request);
    }

    @Override
    public Response getTicketsById(Request request) {
        return createResponse(bookingService.getTicketsById((Long) request.getData()), request);
    }

    @Override
    public Response findByUsername(Request request) {
        return createResponse(employeeService.findByUsername((String) request.getData()), request);
    }

    @Override
    public Response searchByNameDateAndTime(Request request) {
        Object[] parameters = (Object[]) request.getData();
        return createResponse(tripService.searchByNameDateAndTime((String) parameters[0], (LocalDate) parameters[1], (LocalTime) parameters[2]), request);
    }

    @Override
    public Response updateAvailablePlaces(Request request) {
        Object[] parameters = (Object[]) request.getData();
        return createResponse(tripService.updateAvailablePlaces((Long) parameters[0], (Integer) parameters[1]), request);
    }

    @Override
    public Response getAvailablePlaces(Request request) {
        return createResponse(tripService.getAvailablePlaces((Long) request.getData()), request);
    }

    @Override
    public Response authenticate(Request request) {
        Object[] parameters = (Object[]) request.getData();
        return createResponse(employeeService.authenticate((String) parameters[0], (String) parameters[1]), request);
    }

    private Response createResponse(Object data, Request request) {
        return new Response(data, request.getType());
    }

    private int getSocketPort() {
        return Integer.valueOf(ResourceBundle.getBundle("Bundle").getString("socket.port"));
    }
}
