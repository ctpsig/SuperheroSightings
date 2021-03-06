/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperHeroDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Charlie
 */
@Controller
public class RESTLocationController {
    
    @Inject
    private SuperHeroDao dao;

    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Location getLocation(@PathVariable("id") int id) {
        return dao.getLocation(id);
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Location createLocation(@RequestBody Location location) {
        return dao.addLocation(location);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") int id) {
        dao.deleteLocation(id);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLocation(@PathVariable("id") int id, @RequestBody Location location) {
        // favor the path variable over the id in the object if they differ
        location.setId(id);
        dao.updateLocation(location);
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    @ResponseBody
    public List<Location> getAllLocations() {
        return dao.getAllLocations();
    }
}
