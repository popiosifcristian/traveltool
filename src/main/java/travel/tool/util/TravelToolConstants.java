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

}
