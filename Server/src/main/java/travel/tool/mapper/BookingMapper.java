package travel.tool.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.tool.epo.BookingEpo;
import travel.tool.model.Booking;

/**
 * @author ipop
 */
@Service
public class BookingMapper extends GenericMapper<Booking, BookingEpo> {
    @Autowired
    private TripMapper tripMapper;

    @Override
    public Booking toInternal(BookingEpo epo) {
        return new Booking(epo.getId(), tripMapper.toInternal(epo.getTrip()), epo.getCustomer(), epo.getPhoneNumber(), epo.getTickets());
    }

    @Override
    public BookingEpo toExternal(Booking model) {
        return new BookingEpo(model.getId(), tripMapper.toExternal(model.getTrip()), model.getCustomer(), model.getPhoneNumber(), model.getTickets());
    }
}
