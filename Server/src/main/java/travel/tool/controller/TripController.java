package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import travel.tool.epo.TripEpo;
import travel.tool.mapper.TripMapper;
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
    @Autowired
    private TripMapper tripMapper;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Collection<TripEpo> findAll() {
        return tripMapper.toExternal(tripService.findAll());
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public TripEpo getOne(@RequestParam Long id) {
        return tripMapper.toExternal(tripService.getOne(id));
    }

    @PostMapping(value = "/save")
    public TripEpo save(@RequestBody TripEpo model) {
        tripService.save(tripMapper.toInternal(model));
        return model;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody TripEpo model) {
        tripService.delete(tripMapper.toInternal(model));
    }
}
