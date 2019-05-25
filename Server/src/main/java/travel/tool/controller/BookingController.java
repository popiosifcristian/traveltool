package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import travel.tool.epo.BookingEpo;
import travel.tool.mapper.BookingMapper;
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
    @Autowired
    private BookingMapper bookingMapper;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Collection<BookingEpo> findAll() {
        return bookingMapper.toExternal(bookingService.findAll());
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public BookingEpo getOne(@RequestParam Long id) {
        return bookingMapper.toExternal(bookingService.getOne(id));
    }

    @PostMapping(value = "/save", consumes = "application/json")
    public BookingEpo save(@RequestBody BookingEpo model) {
        return bookingMapper.toExternal(bookingService.save(bookingMapper.toInternal(model)));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody BookingEpo model) {
        bookingService.delete(bookingMapper.toInternal(model));
    }
}
