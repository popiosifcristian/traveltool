package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.tool.model.Booking;
import travel.tool.service.BookingService_;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin(origins = "http://localhost:2121/booking")
@RequestMapping(value = "/booking")
public class BookingController implements ICrudController<Booking> {
    @Autowired
    @Qualifier("bookingService_")
    private BookingService_ bookingService;

    @Override
    public Collection<Booking> findAll() {
        return bookingService.findAll();
    }

    @Override
    public Booking getOne(Long id) {
        return bookingService.getOne(id);
    }

    @Override
    public Booking save(Booking model) {
        return bookingService.save(model);
    }

    @Override
    public void delete(Booking model) {
        bookingService.delete(model);
    }
}
