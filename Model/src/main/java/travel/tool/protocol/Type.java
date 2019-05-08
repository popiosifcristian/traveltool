package travel.tool.protocol;

/**
 * @author ipop
 */
public enum Type {
    GET_ALL_BOOKING("getAllBooking"),
    FIND_BOOKING_BY_ID("findBookingById"),
    UPDATE_BOOKING("updateBooking"),
    DELETE_BOOKING("deleteBooking"),
    GET_ALL_COMPANY("getAllCompany"),
    FIND_COMPANY_BY_ID("findCompanyById"),
    UPDATE_COMPANY("updateCompany"),
    DELETE_COMPANY("deleteCompany"),
    GET_ALL_CUSTOMER("getAllCustomer"),
    FIND_CUSTOMER_BY_ID("findCustomerById"),
    UPDATE_CUSTOMER("updateCustomer"),
    DELETE_CUSTOMER("deleteCustomer"),
    GET_ALL_EMPLOYEE("getAllEmployee"),
    FIND_EMPLOYEE_BY_ID("findEmployeeById"),
    UPDATE_EMPLOYEE("updateEmployee"),
    DELETE_EMPLOYEE("deleteEmployee"),
    GET_ALL_LANDMARK("getAllLandmark"),
    FIND_LANDMARK_BY_ID("findLandmarkById"),
    UPDATE_LANDMARK("updateLandmark"),
    DELETE_LANDMARK("deleteLandmark"),
    GET_ALL_TRIP("getAllTrip"),
    FIND_TRIP_BY_ID("findTripById"),
    UPDATE_TRIP("updateTrip"),
    DELETE_TRIP("deleteTrip"),
    GET_TICKETS_BY_ID("getTicketsById"),
    FIND_BY_USERNAME("findByUsername"),
    SEARCH_BY_NAME_DATE_AND_TIME("searchByNameDateAndTime"),
    UPDATE_AVAILABLE_PLACES("updateAvailablePlaces"),
    GET_AVAILABLE_PLACES("getAvailablePlaces"),
    AUTHENTICATE("authenticate");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return type;
    }
}
