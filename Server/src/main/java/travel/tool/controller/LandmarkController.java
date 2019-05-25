package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import travel.tool.epo.LandmarkEpo;
import travel.tool.mapper.LandmarkMapper;
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
    private LandmarkMapper landmarkMapper;
    @Autowired
    private LandmarkService landmarkService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Collection<LandmarkEpo> findAll() {
        return landmarkMapper.toExternal(landmarkService.findAll());
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public LandmarkEpo getOne(@RequestParam Long id) {
        return landmarkMapper.toExternal(landmarkService.getOne(id));
    }

    @PostMapping(value = "/save")
    public LandmarkEpo save(@RequestBody LandmarkEpo model) {
        return landmarkMapper.toExternal(landmarkService.save(landmarkMapper.toInternal(model)));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody LandmarkEpo model) {
        landmarkService.delete(landmarkMapper.toInternal(model));
    }
}
