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
@Service("bookingService_")
public class BookingService_ {
    @Autowired
    @Qualifier("bookingRepository")
    private IBookingRepository bookingRepository;

    public List<Booking> findAll() {
        return new ArrayList<>(bookingRepository.findAll());
    }

    public Booking getOne(long id) {
        return bookingRepository.getOne(id);
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
