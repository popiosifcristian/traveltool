package travel.tool.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * @author ipop
 */
public interface ICrudController<T> {
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    Collection<T> findAll();

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    T getOne(@RequestBody long id);

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    T save(@RequestBody T model);

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    void delete(@RequestBody T model);
}
