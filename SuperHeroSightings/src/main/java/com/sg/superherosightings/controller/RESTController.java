/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperHeroDao;
import com.sg.superherosightings.model.HeroVillain;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@Controller
public class RESTController {

    private SuperHeroDao dao;

    @Inject
    public RESTController(SuperHeroDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/herovillain/{id}", method = RequestMethod.GET)
    @ResponseBody
    public HeroVillain getHero(@PathVariable("id") int id) {
        return dao.getHero(id);
    }

    @RequestMapping(value = "/herovillain", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public HeroVillain createHero(@RequestBody HeroVillain hero) {
        return dao.addHero(hero);
    }

    @RequestMapping(value = "/herovillain/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHero(@PathVariable("id") int id) {
        dao.deleteHero(id);
    }

    @RequestMapping(value = "/herovillain/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateHero(@PathVariable("id") int id, @RequestBody HeroVillain hero) {
        // favor the path variable over the id in the object if they differ
        hero.setId(id);
        dao.updateHero(hero);
    }

    @RequestMapping(value = "/herovillains", method = RequestMethod.GET)
    @ResponseBody
    public List<HeroVillain> getAllContacts() {
        return dao.getAllHeroes();
    }
}
