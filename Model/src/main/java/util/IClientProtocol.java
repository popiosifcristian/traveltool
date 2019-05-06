package util;

import travel.tool.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author ipop
 */
public interface IClientProtocol {
    List<Booking> getAllBooking();

    Booking findBookingById(long id);

    Booking updateBooking(Booking model);

    boolean deleteBooking(Booking model);

    List<Company> getAllCompany();

    Company findCompanyById(long id);

    Company updateCompany(Company model);

    boolean deleteCompany(Company model);

    List<Customer> getAllCustomer();

    Customer findCustomerById(long id);

    Customer updateCustomer(Customer model);

    boolean deleteCustomer(Customer model);

    List<Employee> getAllEmployee();

    Employee findEmployeeById(long id);

    Employee updateEmployee(Employee model);

    boolean deleteEmployee(Employee model);

    List<Landmark> getAllLandmark();

    Landmark findLandmarkById(long id);

    Landmark updateLandmark(Landmark model);

    boolean deleteLandmark(Landmark model);

    List<Trip> getAllTrip();

    Trip findTripById(long id);

    Trip updateTrip(Trip model);

    boolean deleteTrip(Trip model);

    int getTicketsById(long id);

    Employee findByUsername(String username);

    List<Trip> searchByNameDateAndTime(String name, LocalDate date, LocalTime startTime);

    void updateAvailablePlaces(long id, int availablePlaces);

    int getAvailablePlaces(long id);

    boolean authenticate(String username, String password);
}
