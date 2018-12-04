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
import java.util.List;
import java.util.Set;

/**
 *
 * @author Charlie
 */
public interface SuperHeroDao {
    //hero crud
    HeroVillain addHero(HeroVillain hero);
    List<HeroVillain> getAllHeroes();
    HeroVillain getHero(int heroId);
    void updateHero(HeroVillain hero);
    void deleteHero(int heroId);
    
    //location crud
    Location addLocation(Location location);
    List<Location> getAllLocations();
    Location getLocation(int locationId);
    void updateLocation(Location location);
    void deleteLocation(int locationId);
    
    //sighting crud
    Sighting addSighting(Sighting sighting);
    List<Sighting> getAllSightings();
    List<Sighting> getLastTen();
    Sighting getSighting(int sightingId);
    void updateSighting(Sighting sighting);
    void deleteSighting(int sightingId);
    
    //organization crud
    Organization addOrganization(Organization organization);
    List<Organization> getAllOrganizations();
    Organization getOrganization(int organizationId);
    void updateOrganization(Organization o);
    void deleteOrganization(int organizationId);
    
    //special capabilities
    Set<HeroVillain> getAllHeroesByLocationId(int locationId);
    Set<Location> getAllLocationsByHeroId(int heroId);
    List<Sighting> getAllSightingsByDate(LocalDate sightingLd);
    List<HeroVillain> getAllHeroesByOrganization(int organizationId);
    List<Organization> getOrganizationsByHeroId(int heroId);
}
