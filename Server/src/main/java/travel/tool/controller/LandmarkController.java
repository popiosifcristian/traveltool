package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.tool.epo.LandmarkEpo;
import travel.tool.mapper.LandmarkEpoMapper;
import travel.tool.model.Landmark;
import travel.tool.service.LandmarkService;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin(origins = "http://localhost:2121/landmark")
@RequestMapping(value = "/landmark")
public class LandmarkController implements ICrudController<LandmarkEpo> {
    @Autowired
    private LandmarkEpoMapper landmarkEpoMapper;
    @Autowired
    private LandmarkService landmarkService;

    @Override
    public Collection<LandmarkEpo> findAll() {
        return landmarkEpoMapper.toExternals(landmarkService.findAll());
    }

    @Override
    public LandmarkEpo getOne(Long id) {
        return landmarkEpoMapper.toExternal(landmarkService.getOne(id));
    }

    @Override
    public LandmarkEpo save(LandmarkEpo model) {
        return landmarkEpoMapper.toExternal(landmarkService.save(landmarkEpoMapper.toInternal(model)));
    }

    @Override
    public void delete(LandmarkEpo model) {
        landmarkService.delete(landmarkEpoMapper.toInternal(model));
    }
}
