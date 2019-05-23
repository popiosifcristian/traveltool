package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.tool.model.Landmark;
import travel.tool.service.LandmarkService;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin(origins = "http://localhost:2121/landmark")
@RequestMapping(value = "/landmark")
public class LandmarkController implements ICrudController<Landmark> {
    @Autowired
    private LandmarkService landmarkService;

    @Override
    public Collection<Landmark> findAll() {
        return landmarkService.findAll();
    }

    @Override
    public Landmark getOne(Long id) {
        return landmarkService.getOne(id);
    }

    @Override
    public Landmark save(Landmark model) {
        return landmarkService.save(model);
    }

    @Override
    public void delete(Landmark model) {
        landmarkService.delete(model);
    }
}
