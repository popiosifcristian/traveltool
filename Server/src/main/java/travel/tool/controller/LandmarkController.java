package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import travel.tool.epo.LandmarkEpo;
import travel.tool.mapper.LandmarkEpoMapper;
import travel.tool.model.Landmark;
import travel.tool.service.LandmarkService;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin()
@RequestMapping(value = "/landmark")
public class LandmarkController {
    @Autowired
    private LandmarkEpoMapper landmarkEpoMapper;
    @Autowired
    private LandmarkService landmarkService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Collection<LandmarkEpo> findAll() {
        return landmarkEpoMapper.toExternals(landmarkService.findAll());
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public LandmarkEpo getOne(@RequestParam Long id) {
        return landmarkEpoMapper.toExternal(landmarkService.getOne(id));
    }

    @PostMapping(value = "/save")
    public LandmarkEpo save(@RequestBody LandmarkEpo model) {
        return landmarkEpoMapper.toExternal(landmarkService.save(landmarkEpoMapper.toInternal(model)));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody LandmarkEpo model) {
        landmarkService.delete(landmarkEpoMapper.toInternal(model));
    }
}
