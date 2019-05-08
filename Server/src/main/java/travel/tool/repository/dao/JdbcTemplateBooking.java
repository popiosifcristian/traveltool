package travel.tool.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import travel.tool.model.Booking;
import travel.tool.repository.IBookingRepository;
import travel.tool.repository.ICustomerRepository;
import travel.tool.repository.ITripRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static travel.tool.util.TravelToolConstants.*;

/**
 * @author ipop
 */
@Repository("jdbcTemplateBooking")
public class JdbcTemplateBooking implements IBookingRepository {
    @Autowired
    @Qualifier("jdbcTemplateCustomer")
    private ICustomerRepository customerRepository;
    @Autowired
    private ITripRepository tripRepository;
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateBooking(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Booking> getAll() {
        return jdbcTemplate.query(BOOKING_GET_ALL, new BookingResultSetExtractor());
    }

    @Override
    public Booking findById(long id) {
        Collection<Booking> bookings = jdbcTemplate.query(BOOKING_FIND_BY_ID, new BookingResultSetExtractor(), id);
        Booking booking;
        if (bookings.size() != 1) {
            booking = null;
        } else {
            booking = bookings.iterator().next();
        }
        return booking;
    }

    @Override
    public Booking update(Booking booking) {
        Long newId;
        if (booking.getId() > 0) {
            newId = jdbcTemplate.queryForObject(BOOKING_UPDATE, new Object[]{
                    booking.getTrip().getId(),
                    booking.getCustomer(),
                    booking.getPhoneNumber(),
                    booking.getTickets(),
                    booking.getId()
            }, (resultSet, i) -> resultSet.getLong(1));
        } else {
            newId = jdbcTemplate.queryForObject(BOOKING_SAVE, new Object[]{
                    booking.getTrip().getId(),
                    booking.getCustomer(),
                    booking.getPhoneNumber(),
                    booking.getTickets()
            }, (resultSet, i) -> resultSet.getLong(1));
        }
        booking.setId(newId);
        return booking;
    }

    @Override
    public boolean delete(Booking booking) {
        return jdbcTemplate.update(BOOKING_DELETE_BY_ID, booking.getId()) > 0;
    }

    @Override
    public int getTicketsById(long id) {
        return jdbcTemplate.queryForObject(BOOKING_GET_TICKETS, new Object[]{id}, (resultSet, i) -> resultSet.getInt(1));
    }

    private class BookingResultSetExtractor implements ResultSetExtractor<Collection<Booking>> {
        @Override
        public Collection<Booking> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Long, Booking> bookingMap = new HashMap<>();

            while (resultSet.next()) {
                if (!bookingMap.keySet().contains(resultSet.getLong("id"))) {
                    Booking booking = new Booking();
                    booking.setId(resultSet.getLong("id"));
                    booking.setTrip(tripRepository.findById(resultSet.getLong("trip")));
                    booking.setCustomer(resultSet.getString("customer"));
                    booking.setPhoneNumber(resultSet.getString("phone_number"));
                    booking.setTickets(resultSet.getInt("tickets"));

                    bookingMap.put(booking.getId(), booking);
                }
            }
            return bookingMap.values();
        }
    }
}
