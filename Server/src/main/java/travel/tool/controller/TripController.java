package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.tool.model.Trip;
import travel.tool.service.TripService_;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin(origins = "http://localhost:2121/trip")
@RequestMapping(value = "/trip")
public class TripController implements ICrudController<Trip> {
    @Autowired
    @Qualifier("tripService_")
    private TripService_ tripService;

    @Override
    public Collection<Trip> findAll() {
        return tripService.findAll();
    }

    @Override
    public Trip getOne(Long id) {
        return tripService.getOne(id);
    }

    @Override
    public Trip save(Trip model) {
        return tripService.save(model);
    }

    @Override
    public void delete(Trip model) {
        tripService.delete(model);
    }
}
