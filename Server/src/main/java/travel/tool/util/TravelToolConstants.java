package travel.tool.util;

/**
 * @author ipop
 */
public final class TravelToolConstants {
    //Customer queries for dao
    public static final String CUSTOMER_GET_ALL = "SELECT c.id, c.first_name, c.last_name, c.email, c.phone_number FROM customer c ";
    public static final String CUSTOMER_FIND_BY_ID = CUSTOMER_GET_ALL + "WHERE c.id=?";
    public static final String CUSTOMER_DELETE_BY_ID = "DELETE FROM customer WHERE id=?";
    public static final String CUSTOMER_UPDATE = "UPDATE customer SET first_name=?, last_name=?, email=?, phone_number=? WHERE id=? returning id";
    public static final String CUSTOMER_SAVE = "INSERT INTO customer (first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?) returning id";

    //Company queries for dao
    public static final String COMPANY_GET_ALL = "SELECT c.id, c.name, c.address, c.website, c.email, c.phone_number, c.description, c.type FROM company c ";
    public static final String COMPANY_FIND_BY_ID = COMPANY_GET_ALL + "WHERE c.id=?";
    public static final String COMPANY_DELETE_BY_ID = "DELETE FROM company WHERE id=?";
    public static final String COMPANY_UPDATE = "UPDATE company SET name=?, address=?, website=?, email=?, phone_number=?, description=?, type=? WHERE id=? returning id";
    public static final String COMPANY_SAVE = "INSERT INTO company (name, address, website, email, phone_number, description, type) VALUES (?, ?, ?, ?, ?, ?, ?) returning id";

    //Employee queries for dao
    public static final String EMPLOYEE_GET_ALL = "SELECT e.id, e.username, e.password, e.email, e.first_name, e.last_name, e.phone_number, e.company FROM employee e ";
    public static final String EMPLOYEE_FIND_BY_ID = EMPLOYEE_GET_ALL + "WHERE e.id=?";
    public static final String EMPLOYEE_DELETE_BY_ID = "DELETE FROM employee WHERE id=?";
    public static final String EMPLOYEE_UPDATE = "UPDATE employee SET username=?, password=?, email=?, first_name=?, last_name=?, phone_number=?, company=? WHERE id=? returning id";
    public static final String EMPLOYEE_SAVE = "INSERT INTO employee (username, password, email, first_name, last_name, phone_number, company) VALUES (?, ?, ?, ?, ?, ?, ?) returning id";
    public static final String EMPLOYEE_FIND_BY_USERNAME = EMPLOYEE_GET_ALL + "WHERE e.username=?";

    //Landmark queries for dao
    public static final String LANDMARK_GET_ALL = "SELECT l.id, l.name, l.location, l.description FROM landmark l ";
    public static final String LANDMARK_FIND_BY_ID = LANDMARK_GET_ALL + "WHERE l.id=?";
    public static final String LANDMARK_DELETE_BY_ID = "DELETE FROM landmark WHERE id=?";
    public static final String LANDMARK_UPDATE = "UPDATE landmark SET name=?, location=?, description=? WHERE id=? returning id";
    public static final String LANDMARK_SAVE = "INSERT INTO landmark (name, location, description) VALUES (?, ?, ?) returning id";

    //Trip queries for dao
    public static final String TRIP_GET_ALL = "SELECT t.id, t.landmark, t.company, t.date, t.start_time, t.price, t.available_places FROM trip t ";
    public static final String TRIP_FIND_BY_ID = TRIP_GET_ALL + "WHERE t.id=?";
    public static final String TRIP_DELETE_BY_ID = "DELETE FROM trip WHERE id=?";
    public static final String TRIP_UPDATE = "UPDATE trip SET landmark=?, company=?, date=?, start_time=?, price=?, available_places=? WHERE id=? returning id";
    public static final String TRIP_SAVE = "INSERT INTO trip (landmark, company, date, start_time, price, available_places) VALUES (?, ?, ?, ?, ?, ?) returning id";
    public static final String TRIP_GET_ALL_BY_NAME_DATE_TIME = TRIP_GET_ALL + "JOIN landmark l on l.id=t.landmark WHERE l.name=? AND t.date=? AND t.start_time=?";
    public static final String TRIP_UPDATE_AVAILABLE_PLACES ="UPDATE trip SET available_places=? WHERE id=?";
    public static final String TRIP_GET_AVAILABLE_PLACES = "SELECT t.available_places FROM trip t WHERE t.id=?";

    //Booking queries for dao
    public static final String BOOKING_GET_ALL = "SELECT b.id, b.trip, b.customer, b.phone_number, b.tickets FROM booking b ";
    public static final String BOOKING_FIND_BY_ID = BOOKING_GET_ALL + "WHERE b.id=?";
    public static final String BOOKING_DELETE_BY_ID = "DELETE FROM booking WHERE id=?";
    public static final String BOOKING_UPDATE = "UPDATE booking SET trip=?, customer=?, phone_number=?, tickets=? WHERE id=? returning id";
    public static final String BOOKING_SAVE = "INSERT INTO booking (trip, customer, phone_number, tickets) VALUES (?, ?,  ?, ?) returning id";
    public static final String BOOKING_GET_TICKETS = "SELECT b.tickets FROM booking b WHERE b.id=?";
}
