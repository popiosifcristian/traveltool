package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import travel.tool.model.Trip;
import travel.tool.service.TripService_;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin()
@RequestMapping(value = "/trip")
public class TripController {
    @Autowired
    @Qualifier("tripService_")
    private TripService_ tripService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Collection<Trip> findAll() {
        return tripService.findAll();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Trip getOne(@RequestParam Long id) {
        return tripService.getOne(id);
    }

    @PostMapping(value = "/save")
    public Trip save(@RequestBody Trip model) {
        return tripService.save(model);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody Trip model) {
        tripService.delete(model);
    }
}
