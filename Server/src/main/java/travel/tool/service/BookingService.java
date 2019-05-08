package travel.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import travel.tool.model.Booking;
import travel.tool.repository.IBookingRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ipop
 */
@Service
public class BookingService {
    @Autowired
    @Qualifier("jdbcTemplateBooking")
    private IBookingRepository bookingRepository;

    public List<Booking> getAll() {
        return new ArrayList<>(bookingRepository.getAll());
    }

    public Booking findById(long id) {
        return bookingRepository.findById(id);
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    public int getTicketsById(long id) {
        return bookingRepository.getTicketsById(id);
    }
}
