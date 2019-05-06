package travel.tool;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @author ipop
 */
public class TravelToolServer implements IServerProtocol {
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

    public TravelToolServer() {
        try {
            serverSocket = new ServerSocket(444);
            clientSocket = serverSocket.accept();
            writer = new ObjectOutputStream(clientSocket.getOutputStream());
            reader = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private Response handleRequest(Request request) {
        Response response = null;
        try {
            Method method = this.getClass().getDeclaredMethod(request.getType().getTypeName(), Response.class);
            response = (Response) method.invoke(this, request);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

    private void sendResponse(Response response) {
        System.out.println("Sending response: " + response);
        try {
            writer.writeObject(response);
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private Request receiveRequest() {
        Request request = null;
        try {
            request = (Request) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public Response getAllBooking(Request request) {
        return createResponse(bookingService.getAll(), request);
    }

    @Override
    public Response findBookingById(Request request) {
        return createResponse(bookingService.findById((Long) request.getData()), request);
    }

    @Override
    public Response updateBooking(Request request) {
        return createResponse(bookingService.update((Booking) request.getData()), request);
    }

    @Override
    public Response deleteBooking(Request request) {
        return createResponse(bookingService.delete((Booking) request.getData()), request);
    }

    @Override
    public Response getAllCompany(Request request) {
        return createResponse(companyService.getAll(), request);
    }

    @Override
    public Response findCompanyById(Request request) {
        return createResponse(companyService.findById((Long) request.getData()), request);
    }

    @Override
    public Response updateCompany(Request request) {
        return createResponse(companyService.update((Company) request.getData()), request);
    }

    @Override
    public Response deleteCompany(Request request) {
        return createResponse(companyService.delete((Company) request.getData()), request);
    }

    @Override
    public Response getAllCustomer(Request request) {
        return createResponse(customerService.getAll(), request);
    }

    @Override
    public Response findCustomerById(Request request) {
        return createResponse(customerService.findById((Long) request.getData()), request);
    }

    @Override
    public Response updateCustomer(Request request) {
        return createResponse(customerService.update((Customer) request.getData()), request);
    }

    @Override
    public Response deleteCustomer(Request request) {
        return createResponse(customerService.delete((Customer) request.getData()), request);
    }

    @Override
    public Response getAllEmployee(Request request) {
        return createResponse(employeeService.getAll(), request);
    }

    @Override
    public Response findEmployeeById(Request request) {
        return createResponse(employeeService.findById((Long) request.getData()), request);
    }

    @Override
    public Response updateEmployee(Request request) {
        return createResponse(employeeService.update((Employee) request.getData()), request);
    }

    @Override
    public Response deleteEmployee(Request request) {
        return createResponse(employeeService.delete((Employee) request.getData()), request);
    }

    @Override
    public Response getAllLandmark(Request request) {
        return createResponse(landmarkService.getAll(), request);
    }

    @Override
    public Response findLandmarkById(Request request) {
        return createResponse(landmarkService.findById((Long) request.getData()), request);
    }

    @Override
    public Response updateLandmark(Request request) {
        return createResponse(landmarkService.update((Landmark) request.getData()), request);
    }

    @Override
    public Response deleteLandmark(Request request) {
        return createResponse(landmarkService.delete((Landmark) request.getData()), request);
    }

    @Override
    public Response getAllTrip(Request request) {
        return createResponse(tripService.getAll(), request);
    }

    @Override
    public Response findTripById(Request request) {
        return createResponse(tripService.findById((Long) request.getData()), request);
    }

    @Override
    public Response updateTrip(Request request) {
        return createResponse(tripService.update((Trip) request.getData()), request);
    }

    @Override
    public Response deleteTrip(Request request) {
        return createResponse(tripService.delete((Trip) request.getData()), request);
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
        Object [] parameters = new Object[]{1,2,3};
        return createResponse(tripService.searchByNameDateAndTime((String)request.getData()[0],o[1]));
    }

    @Override
    public Response updateAvailablePlaces(Request request) {
        return null;
    }

    @Override
    public Response getAvailablePlaces(Request request) {
        return null;
    }

    @Override
    public Response authenticate(Request request) {
        return null;
    }

    private Response createResponse(Object data, Request request) {
        return new Response(data, request.getType());
    }
}
