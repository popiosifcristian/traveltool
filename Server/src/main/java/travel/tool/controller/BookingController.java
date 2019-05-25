package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import travel.tool.model.Booking;
import travel.tool.service.BookingService_;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin()
@RequestMapping(value = "/booking")
public class BookingController {
    @Autowired
    @Qualifier("bookingService_")
    private BookingService_ bookingService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Collection<Booking> findAll() {
        return bookingService.findAll();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Booking getOne(@RequestParam Long id) {
        return bookingService.getOne(id);
    }

    @PostMapping(value = "/save", consumes = "application/json")
    public Booking save(@RequestBody Booking model) {
        return bookingService.save(model);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody Booking model) {
        bookingService.delete(model);
    }
}
