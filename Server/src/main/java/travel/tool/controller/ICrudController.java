package travel.tool.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author ipop
 */
public interface ICrudController<T> {
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    Collection<T> findAll();

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    T getOne(@RequestParam Long id);

    @PostMapping(value = "/save")
    T save(@RequestBody T model);

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    void delete(@RequestBody T model);
}
