/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperHeroDao;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Charlie
 */
@Controller
public class RESTOrganizationController {
    
    @Inject
    private SuperHeroDao dao;

    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Organization getOrganization(@PathVariable("id") int id) {
        return dao.getOrganization(id);
    }

    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Organization createOrganization(@RequestBody Map<String, String> orgs) {
        Organization o = new Organization();
        o.setName(orgs.get("name"));
        o.setDescription(orgs.get("description"));
        o.setLocation(dao.getLocation(Integer.parseInt(orgs.get("locationId"))));
        return dao.addOrganization(o);
    }

    @RequestMapping(value = "/organization/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("id") int id) {
        dao.deleteOrganization(id);
    }

    @RequestMapping(value = "/organization/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrganization(@PathVariable("id") int id, @RequestBody Organization organization) {
        // favor the path variable over the id in the object if they differ
        organization.setId(id);
        dao.updateOrganization(organization);
    }

    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    @ResponseBody
    public List<Organization> getAllOrganizations() {
        return dao.getAllOrganizations();
    }
}
