package util;

import travel.tool.model.*;
import travel.tool.protocol.Request;
import travel.tool.protocol.Response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author ipop
 */
public interface IServerProtocol {
    Response getAllBooking(Request request);

    Response findBookingById(Request request);

    Response updateBooking(Request request);

    Response deleteBooking(Request request);

    Response getAllCompany(Request request);

    Response findCompanyById(Request request);

    Response updateCompany(Request request);

    Response deleteCompany(Request request);

    Response getAllCustomer(Request request);

    Response findCustomerById(Request request);

    Response updateCustomer(Request request);

    Response deleteCustomer(Request request);

    Response getAllEmployee(Request request);

    Response findEmployeeById(Request request);

    Response updateEmployee(Request request);

    Response deleteEmployee(Request request);

    Response getAllLandmark(Request request);

    Response findLandmarkById(Request request);

    Response updateLandmark(Request request);

    Response deleteLandmark(Request request);

    Response getAllTrip(Request request);

    Response findTripById(Request request);

    Response updateTrip(Request request);

    Response deleteTrip(Request request);

    Response getTicketsById(Request request);

    Response findByUsername(Request request);

    Response searchByNameDateAndTime(Request request);

    Response updateAvailablePlaces(Request request);

    Response getAvailablePlaces(Request request);

    Response authenticate(Request request);
}
