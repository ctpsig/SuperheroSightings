package com.sg.superherosightings.controller;

import com.mysql.cj.xdevapi.JsonArray;
import com.sg.superherosightings.dao.SuperHeroDao;
import com.sg.superherosightings.model.HeroVillain;
import com.sg.superherosightings.model.Organization;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
public class RESTHeroController {

    private SuperHeroDao dao;

    @Inject
    public RESTHeroController(SuperHeroDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/hero/{id}", method = RequestMethod.GET)
    @ResponseBody
    public HeroVillain getHero(@PathVariable("id") int id) {
        return dao.getHero(id);
    }

    @RequestMapping(value = "/hero", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public HeroVillain createHero(@RequestBody Map<String, Object> h) {
        HeroVillain hero = new HeroVillain();
        hero.setName((String) h.get("name"));
        hero.setDescription((String) h.get("description"));
        hero.setSuperpower((String) h.get("superpower"));
        List<String> orgs = (List<String>) h.get("organizations");
        List<Organization> heroOrgs = new ArrayList<>();
        for (String oInt : orgs) {
            Organization o = dao.getOrganization(Integer.parseInt(oInt));
            heroOrgs.add(o);
        }
        hero.setOrganizations(heroOrgs);
        return dao.addHero(hero);
    }

    @RequestMapping(value = "/hero/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHero(@PathVariable("id") int id) {
        dao.deleteHero(id);
    }

    @RequestMapping(value = "/hero/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateHero(@PathVariable("id") int id, @RequestBody Map<String, Object> h) {
        HeroVillain hero = dao.getHero(id);
        hero.setName((String) h.get("name"));
        hero.setDescription((String) h.get("description"));
        hero.setSuperpower((String) h.get("superpower"));
        List<String> orgs = (List<String>) h.get("organizations");
        List<Organization> heroOrgs = new ArrayList<>();
        if (orgs != null) {
            for (String oInt : orgs) {
                Organization o = dao.getOrganization(Integer.parseInt(oInt));
                heroOrgs.add(o);
            }
            hero.setOrganizations(heroOrgs);
        }
        dao.updateHero(hero);
    }

    @RequestMapping(value = "/heroes", method = RequestMethod.GET)
    @ResponseBody
    public List<HeroVillain> getAllContacts() {
        return dao.getAllHeroes();
    }
}
