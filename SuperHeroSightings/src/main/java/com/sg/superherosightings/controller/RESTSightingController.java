package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperHeroDao;
import com.sg.superherosightings.model.HeroVillain;
import com.sg.superherosightings.model.Sighting;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RESTSightingController {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Inject
    private SuperHeroDao dao;

    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Sighting getSighting(@PathVariable("id") int id) {
        return dao.getSighting(id);
    }

    @RequestMapping(value = "/sighting", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Sighting createSighting(@RequestBody Map<String, Object> s) {
        Sighting sight = new Sighting();
        String date = (String) s.get("date");
        LocalDate sightDate = LocalDate.parse(date, formatter);
        sight.setSightingDate(sightDate);
        sight.setLocation(dao.getLocation(Integer.parseInt((String) s.get("location"))));
        List<String> heroesStr = (List<String>) s.get("heroes");
        List<HeroVillain> heroes = new ArrayList<>();
        if (heroesStr != null) {
            for (String hero : heroesStr) {
                HeroVillain hv = dao.getHero(Integer.parseInt(hero));
                heroes.add(hv);
            }
            sight.setHeroes(heroes);
        }
        return dao.addSighting(sight);
    }

    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSighting(@PathVariable("id") int id) {
        dao.deleteSighting(id);
    }

    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSighting(@PathVariable("id") int id, @RequestBody Map <String, Object> s) {
        Sighting sight = dao.getSighting(id);
        String date = (String) s.get("sightingDate");
        LocalDate sightDate = LocalDate.parse(date, formatter);
        sight.setSightingDate(sightDate);
        sight.setLocation(dao.getLocation(Integer.parseInt((String) s.get("location"))));
        List<String> heroesStr = (List<String>) s.get("heroes");
        List<HeroVillain> heroes = new ArrayList<>();
        if (heroesStr != null) {
            for (String hero : heroesStr) {
                HeroVillain hv = dao.getHero(Integer.parseInt(hero));
                heroes.add(hv);
            }
            sight.setHeroes(heroes);
        }
        dao.updateSighting(sight);
    }

    @RequestMapping(value = "/sightings", method = RequestMethod.GET)
    @ResponseBody
    public List<Sighting> getAllSightings() {
        return dao.getAllSightings();
    }

    @RequestMapping(value = "/lastTenSightings", method = RequestMethod.GET)
    @ResponseBody
    public List<Sighting> getLastTen() {
        return dao.getLastTen();
    }

}
