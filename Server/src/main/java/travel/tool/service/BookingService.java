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
public class BookingService implements IBookingRepository {
    @Autowired
    @Qualifier("jdbcTemplateBooking")
    private IBookingRepository bookingRepository;

    @Override
    public List<Booking> getAll() {
        return new ArrayList<>(bookingRepository.getAll());
    }

    @Override
    public Booking findById(long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Booking update(Booking booking) {
        return bookingRepository.update(booking);
    }

    @Override
    public boolean delete(Booking booking) {
        return bookingRepository.delete(booking);
    }

    @Override
    public int getTicketsById(long id) {
        return bookingRepository.getTicketsById(id);
    }
}
