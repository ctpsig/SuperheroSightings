/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.HeroVillain;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Charlie
 */
@RunWith(SpringJUnit4ClassRunner.class) //Actually loads up the beans
@ContextConfiguration(locations = "classpath:test-applicationContext.xml") //Says which beans to create
public class SuperHeroDaoDBImplTest {

    @Inject
    SuperHeroDao dao;

    public SuperHeroDaoDBImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<HeroVillain> heroes = dao.getAllHeroes();
        for (HeroVillain hv : heroes) {
            dao.deleteHero(hv.getId());
        }
        List<Location> locations = dao.getAllLocations();
        for (Location l : locations) {
            dao.deleteLocation(l.getId());
        }
        List<Organization> organizations = dao.getAllOrganizations();
        for (Organization o : organizations) {
            dao.deleteOrganization(o.getId());
        }
        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting s : sightings) {
            dao.deleteSighting(s.getId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addHero method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testAddGetHero() {
        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");

        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");

        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        o.setLocation(l);
        o = dao.addOrganization(o);
        List<Organization> spideyOrgs = new ArrayList<>();
        spideyOrgs.add(o);
        hero.setOrganizations(spideyOrgs);

        hero = dao.addHero(hero);

        HeroVillain fromDao = dao.getHero(hero.getId());

        assertEquals(hero, fromDao);
    }

    /**
     * Test of getAllHeroes method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testGetAllHeroes() {
        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");

        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");

        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        o.setLocation(l);
        o = dao.addOrganization(o);
        List<Organization> spideyOrgs = new ArrayList<>();
        spideyOrgs.add(o);
        hero.setOrganizations(spideyOrgs);

        hero = dao.addHero(hero);

        HeroVillain hero2 = new HeroVillain();
        hero2.setName("Batman");
        hero2.setDescription("He's a rich bat.");
        hero2.setSuperpower("Money");

        Organization o2 = new Organization();
        o2.setDescription("promoting justice?");
        o2.setName("Justice League");

        Location l2 = new Location();
        l2.setName("League HQ");
        l2.setAddress("444 1st Ave");
        l2.setCity("New York");
        l2.setState("NY");
        l2.setCountry("USA");
        l2.setDescription("made up place");
        l2.setLatitude(40.730610);
        l2.setLongitude(-73.935242);
        l2 = dao.addLocation(l2);

        o2.setLocation(l2);
        o2 = dao.addOrganization(o2);
        List<Organization> batOrgs = new ArrayList<>();
        batOrgs.add(o2);
        hero2.setOrganizations(batOrgs);

        hero2 = dao.addHero(hero2);
        assertEquals(dao.getAllHeroes().size(), 2);
    }

    /**
     * Test of updateHero method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testUpdateHero() {
        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");

        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");

        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        o.setLocation(l);
        o = dao.addOrganization(o);
        List<Organization> spideyOrgs = new ArrayList<>();
        spideyOrgs.add(o);
        hero.setOrganizations(spideyOrgs);

        hero = dao.addHero(hero);
        hero.setName("Spiderdude");
        dao.updateHero(hero);

        HeroVillain fromDao = dao.getHero(hero.getId());

        assertEquals(hero, fromDao);
    }

    /**
     * Test of deleteHero method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testDeleteHero() {
        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");

        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");

        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        o.setLocation(l);
        o = dao.addOrganization(o);
        List<Organization> spideyOrgs = new ArrayList<>();
        spideyOrgs.add(o);
        hero.setOrganizations(spideyOrgs);

        hero = dao.addHero(hero);

        HeroVillain fromDao = dao.getHero(hero.getId());

        assertEquals(hero, fromDao);

        dao.deleteHero(hero.getId());

        fromDao = dao.getHero(hero.getId());

        assertNull(fromDao);
    }

    /**
     * Test of addLocation method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testAddGetLocation() {
        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        Location fromDao = dao.getLocation(l.getId());

        assertEquals(l, fromDao);
    }

    /**
     * Test of getAllLocations method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testGetAllLocations() {
        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        Location l2 = new Location();
        l2.setName("League HQ");
        l2.setAddress("444 1st Ave");
        l2.setCity("New York");
        l2.setState("NY");
        l2.setCountry("USA");
        l2.setDescription("made up place");
        l2.setLatitude(40.730610);
        l2.setLongitude(-73.935242);
        l2 = dao.addLocation(l2);

        assertEquals(dao.getAllLocations().size(), 2);
    }

    /**
     * Test of updateLocation method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testUpdateLocation() {
        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        l.setAddress("123 Lookame St.");

        dao.updateLocation(l);

        Location fromDao = dao.getLocation(l.getId());

        assertEquals(l, fromDao);
    }

    /**
     * Test of deleteLocation method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testDeleteLocation() {
        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);
        
        Sighting s = new Sighting();
        s.setSightingDate(LocalDate.parse("2009-12-18"));
        s.setLocation(l);

        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");

        HeroVillain h2 = new HeroVillain();
        h2.setName("Spiderdude");
        h2.setDescription("Spiderman knockoff");
        h2.setSuperpower("vry smart?");

        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");
        o.setLocation(l);
        o = dao.addOrganization(o);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(o);
        hero.setOrganizations(organizations);
        h2.setOrganizations(organizations);
        hero = dao.addHero(hero);
        h2 = dao.addHero(h2);

        List<HeroVillain> heroes = new ArrayList<>();
        heroes.add(hero);
        heroes.add(h2);
        s.setHeroes(heroes);

        s = dao.addSighting(s);

        Location fromDao = dao.getLocation(l.getId());

        assertEquals(l, fromDao);

        dao.deleteLocation(l.getId());

        fromDao = dao.getLocation(l.getId());

        assertNull(fromDao);
    }

    /**
     * Test of addSighting method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testAddGetDeleteSighting() {
        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        Sighting s = new Sighting();
        s.setSightingDate(LocalDate.parse("2009-12-18"));
        s.setLocation(l);

        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");
        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");
        o.setLocation(l);
        o = dao.addOrganization(o);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o);
        hero.setOrganizations(organizations);
        hero = dao.addHero(hero);
        List<HeroVillain> heroes = new ArrayList<>();
        heroes.add(hero);
        s.setHeroes(heroes);

        s = dao.addSighting(s);

        Sighting fromDao = dao.getSighting(s.getId());

        assertEquals(s, fromDao);

        dao.deleteSighting(s.getId());

        fromDao = dao.getSighting(s.getId());

        assertNull(fromDao);
    }

    /**
     * Test of getAllSightings method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testGetAllSightings() {
        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        Sighting s = new Sighting();
        s.setSightingDate(LocalDate.parse("2009-12-18"));
        s.setLocation(l);

        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");
        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");
        o.setLocation(l);
        o = dao.addOrganization(o);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o);
        hero.setOrganizations(organizations);
        hero = dao.addHero(hero);
        List<HeroVillain> heroes = new ArrayList<>();
        heroes.add(hero);
        s.setHeroes(heroes);

        s = dao.addSighting(s);

        Location l2 = new Location();
        l2.setName("Justice HQ");
        l2.setAddress("444 1st Ave");
        l2.setCity("New York");
        l2.setState("NY");
        l2.setCountry("USA");
        l2.setDescription("made up place");
        l2.setLatitude(40.730610);
        l2.setLongitude(-73.935242);
        l2 = dao.addLocation(l2);

        Sighting s2 = new Sighting();
        s2.setSightingDate(LocalDate.parse("2009-12-14"));
        s2.setLocation(l2);

        List<HeroVillain> heroes2 = new ArrayList<>();
        heroes2.add(hero);
        s2.setHeroes(heroes2);

        s2 = dao.addSighting(s2);

        assertEquals(dao.getAllSightings().size(), 2);
    }

    /**
     * Test of updateSighting method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testUpdateSighting() {
        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        Sighting s = new Sighting();
        s.setSightingDate(LocalDate.parse("2009-12-18"));
        s.setLocation(l);

        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");
        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");
        o.setLocation(l);
        o = dao.addOrganization(o);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o);
        hero.setOrganizations(organizations);
        hero = dao.addHero(hero);
        List<HeroVillain> heroes = new ArrayList<>();
        heroes.add(hero);
        s.setHeroes(heroes);

        s = dao.addSighting(s);

        s.setSightingDate(LocalDate.parse("1999-04-17"));
        l.setAddress("123 Lookame St.");
        dao.updateLocation(l);
        dao.updateSighting(s);

        Sighting fromDao = dao.getSighting(s.getId());

        assertEquals(s, fromDao);
    }

    /**
     * Test of addOrganization method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testAddGetDeleteOrganization() {
        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");

        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        o.setLocation(l);
        o = dao.addOrganization(o);

        Organization fromDao = dao.getOrganization(o.getId());

        assertEquals(o, fromDao);

        dao.deleteOrganization(o.getId());

        fromDao = dao.getOrganization(o.getId());
        assertNull(fromDao);
    }

    /**
     * Test of getAllOrganizations method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testGetAllOrganizations() {
        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");

        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        o.setLocation(l);
        o = dao.addOrganization(o);

        Organization o2 = new Organization();
        o2.setDescription("All about justice, bros, and potatoes");
        o2.setName("Justice Brotatoes");

        Location l2 = new Location();
        l2.setName("Dumpster");
        l2.setAddress("336 Sixth St");
        l2.setCity("New York");
        l2.setState("NY");
        l2.setCountry("USA");
        l2.setDescription("very special place");
        l2.setLatitude(40.730610);
        l2.setLongitude(-73.935242);
        l2 = dao.addLocation(l2);

        o2.setLocation(l2);
        o2 = dao.addOrganization(o2);

        assertEquals(dao.getAllOrganizations().size(), 2);
    }

    /**
     * Test of updateOrganization method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testUpdateOrganization() {
        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");

        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        o.setLocation(l);
        o = dao.addOrganization(o);

        Organization fromDao = dao.getOrganization(o.getId());

        assertEquals(o, fromDao);

        l.setName("Avengeers HQ");
        o.setName("The Avengeers");
        dao.updateLocation(l);
        dao.updateOrganization(o);

        fromDao = dao.getOrganization(o.getId());

        assertEquals(o, fromDao);
    }

    /**
     * Test of getAllHeroesAtLocationId method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testGetAllHeroesAtLocationId() {
        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        Sighting s = new Sighting();
        s.setSightingDate(LocalDate.parse("2009-12-18"));
        s.setLocation(l);

        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");

        HeroVillain h2 = new HeroVillain();
        h2.setName("Spiderdude");
        h2.setDescription("Spiderman knockoff");
        h2.setSuperpower("vry smart?");

        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");
        o.setLocation(l);
        o = dao.addOrganization(o);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(o);
        hero.setOrganizations(organizations);
        h2.setOrganizations(organizations);
        hero = dao.addHero(hero);
        h2 = dao.addHero(h2);

        List<HeroVillain> heroes = new ArrayList<>();
        heroes.add(hero);
        heroes.add(h2);
        s.setHeroes(heroes);

        s = dao.addSighting(s);

        assertEquals(dao.getAllHeroesByLocationId(l.getId()).size(), 2);

        HeroVillain h3 = new HeroVillain();
        h3.setName("Ironman");
        h3.setDescription("made of iron");
        h3.setSuperpower("Very Rich");
        h3.setOrganizations(organizations);
        h3 = dao.addHero(h3);

        Sighting s2 = new Sighting();
        s2.setSightingDate(LocalDate.parse("2009-12-19"));
        s2.setLocation(l);
        List<HeroVillain> heroes2 = new ArrayList<>();
        heroes2.add(h3);
        s2.setHeroes(heroes2);
        s2 = dao.addSighting(s2);
        assertEquals(dao.getAllHeroesByLocationId(l.getId()).size(), 3);
        heroes2.add(hero);
        s2.setHeroes(heroes2);
        dao.updateSighting(s2);
        assertEquals(dao.getAllHeroesByLocationId(l.getId()).size(), 3);
    }

    /**
     * Test of getAllLocationsByHeroId method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testGetAllLocationsByHeroId() {
        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        Location l2 = new Location();
        l2.setName("New place");
        l2.setAddress("unknown");
        l2.setCity("New York");
        l2.setState("NY");
        l2.setCountry("USA");
        l2.setDescription("made up place");
        l2.setLatitude(40.730610);
        l2.setLongitude(-73.935242);
        l2 = dao.addLocation(l2);

        Sighting s = new Sighting();
        s.setSightingDate(LocalDate.parse("2009-12-18"));
        s.setLocation(l);

        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");

        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");
        o.setLocation(l);
        o = dao.addOrganization(o);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(o);
        hero.setOrganizations(organizations);
        hero = dao.addHero(hero);

        List<HeroVillain> heroes = new ArrayList<>();
        heroes.add(hero);
        s.setHeroes(heroes);

        s = dao.addSighting(s);

        Sighting s2 = new Sighting();
        s2.setSightingDate(LocalDate.parse("2009-12-19"));
        s2.setLocation(l2);
        s2.setHeroes(heroes);
        s2 = dao.addSighting(s2);

        assertEquals(dao.getAllLocationsByHeroId(hero.getId()).size(), 2);
    }

    /**
     * Test of getAllSightingsByDate method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testGetAllSightingsByDate() {
        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        Location l2 = new Location();
        l2.setName("New place");
        l2.setAddress("unknown");
        l2.setCity("New York");
        l2.setState("NY");
        l2.setCountry("USA");
        l2.setDescription("made up place");
        l2.setLatitude(40.730610);
        l2.setLongitude(-73.935242);
        l2 = dao.addLocation(l2);

        Sighting s = new Sighting();
        s.setSightingDate(LocalDate.parse("2009-12-18"));
        s.setLocation(l);

        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");

        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");
        o.setLocation(l);
        o = dao.addOrganization(o);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(o);
        hero.setOrganizations(organizations);
        hero = dao.addHero(hero);

        List<HeroVillain> heroes = new ArrayList<>();
        heroes.add(hero);
        s.setHeroes(heroes);

        s = dao.addSighting(s);

        Sighting s2 = new Sighting();
        s2.setSightingDate(LocalDate.parse("2009-12-18"));
        s2.setLocation(l2);
        s2.setHeroes(heroes);
        s2 = dao.addSighting(s2);
        
        assertEquals(dao.getAllSightingsByDate(LocalDate.parse("2009-12-18")).size(), 2);
    }

    /**
     * Test of getAllHeroesByOrganization method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testGetAllHeroesByOrganization() {
        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");

        HeroVillain h2 = new HeroVillain();
        h2.setName("Spiderdude");
        h2.setDescription("Spiderman knockoff");
        h2.setSuperpower("vry smart?");

        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");
        o.setLocation(l);
        o = dao.addOrganization(o);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(o);
        hero.setOrganizations(organizations);
        h2.setOrganizations(organizations);
        hero = dao.addHero(hero);
        h2 = dao.addHero(h2);

        HeroVillain h3 = new HeroVillain();
        h3.setName("Ironman");
        h3.setDescription("made of iron");
        h3.setSuperpower("Very Rich");
        h3.setOrganizations(organizations);
        h3 = dao.addHero(h3);

        assertEquals(dao.getAllHeroesByOrganization(o.getId()).size(), 3);
    }

    /**
     * Test of getAllOrganizationsForAHero method, of class SuperHeroDaoDBImpl.
     */
    @Test
    public void testGetAllOrganizationsForAHero() {
        Location l = new Location();
        l.setName("Avengers HQ");
        l.setAddress("555 1st Ave");
        l.setCity("New York");
        l.setState("NY");
        l.setCountry("USA");
        l.setDescription("made up place");
        l.setLatitude(40.730610);
        l.setLongitude(-73.935242);
        l = dao.addLocation(l);

        HeroVillain hero = new HeroVillain();
        hero.setName("Spiderman");
        hero.setDescription("Just your friendly, neighborhood Spiderman");
        hero.setSuperpower("Spidey Sense");

        Organization o = new Organization();
        o.setDescription("Defending the world and stuff");
        o.setName("The Avengers");
        o.setLocation(l);
        o = dao.addOrganization(o);
        
        Organization o2 = new Organization();
        o2.setDescription("All the stuff");
        o2.setName("Not the avengers");
        o2.setLocation(l);
        o2 = dao.addOrganization(o2);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(o);
        organizations.add(o2);
        hero.setOrganizations(organizations);
        hero = dao.addHero(hero);
        
        assertEquals(dao.getOrganizationsByHeroId(hero.getId()).size(), 2);
    }
}
