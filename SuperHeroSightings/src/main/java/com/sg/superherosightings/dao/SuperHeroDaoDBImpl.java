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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charlie
 */
@Repository
public class SuperHeroDaoDBImpl implements SuperHeroDao {

    @Inject
    private JdbcTemplate jdbc;

    private static final String SQL_INSERT_HERO
            = "insert into hero "
            + "(name, description, superpower) "
            + "values (?, ?, ?)";

    private static final String SQL_INSERT_HERO_ORGANIZATION
            = "insert into heroOrganization "
            + "(heroId, organizationId) "
            + "values(?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public HeroVillain addHero(HeroVillain hero) {
        jdbc.update(SQL_INSERT_HERO,
                hero.getName(),
                hero.getDescription(),
                hero.getSuperpower());

        int newId = jdbc.queryForObject("select LAST_INSERT_ID()",
                Integer.class);

        hero.setId(newId);

        addHeroOrganization(hero);
        return hero;
    }

    private void addHeroOrganization(HeroVillain hero) {
        for (Organization o : hero.getOrganizations()) {
            jdbc.update(SQL_INSERT_HERO_ORGANIZATION, hero.getId(), o.getId());
        }
    }

    private static final String SQL_SELECT_ALL_HEROES
            = "select * from hero";

    @Override
    public List<HeroVillain> getAllHeroes() {
        List<HeroVillain> heroes = jdbc.query(SQL_SELECT_ALL_HEROES,
                new HeroMapper());
        populateHeroes(heroes);
        return heroes;
    }

    private void populateHeroes(List<HeroVillain> heroes) {
        for (HeroVillain hv : heroes) {
            hv.setOrganizations(getOrganizationsByHeroId(hv.getId()));
        }
    }

    private static final String SQL_SELECT_ONE_HERO
            = "select * from hero "
            + "where id = ?";

    @Override
    public HeroVillain getHero(int heroId) {
        try {
            HeroVillain hero = jdbc.queryForObject(SQL_SELECT_ONE_HERO, new HeroMapper(), heroId);
            hero.setOrganizations(getOrganizationsByHeroId(heroId));
            return hero;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_UPDATE_HERO
            = "update hero set "
            + "name = ?, description = ?, superpower = ? "
            + "where id = ?";
    private static final String SQL_DELETE_HERO_ORGANIZATION
            = "delete from heroOrganization where heroId = ?";
    private static final String SQL_DELETE_HERO_FROM_HERO_SIGHTING
            = "delete from heroSighting where heroId = ?";

    @Override
    @Transactional
    public void updateHero(HeroVillain hero) {
        jdbc.update(SQL_UPDATE_HERO,
                hero.getName(),
                hero.getDescription(),
                hero.getSuperpower(),
                hero.getId());

        jdbc.update(SQL_DELETE_HERO_ORGANIZATION, hero.getId());
        addHeroOrganization(hero);
    }

    private static final String SQL_DELETE_HERO
            = "delete from Hero where id = ?";

    @Override
    @Transactional
    public void deleteHero(int heroId) {
        jdbc.update(SQL_DELETE_HERO_ORGANIZATION, heroId);
        jdbc.update(SQL_DELETE_HERO_FROM_HERO_SIGHTING, heroId);
        jdbc.update(SQL_DELETE_HERO, heroId);
    }

    private static final String SQL_INSERT_LOCATION
            = "insert into location "
            + "(name, description, address, city, state, country, longitude, latitude) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    @Transactional
    public Location addLocation(Location location) {
        jdbc.update(SQL_INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getCity(),
                (location.getState() == null) ? null : location.getState(),
                location.getCountry(),
                location.getLongitude(),
                location.getLatitude());

        int newId = jdbc.queryForObject("select LAST_INSERT_ID()",
                Integer.class);

        location.setId(newId);

        return location;
    }

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from location";

    @Override
    public List<Location> getAllLocations() {
        return jdbc.query(SQL_SELECT_ALL_LOCATIONS,
                new LocationMapper());
    }

    private static final String SQL_SELECT_LOCATION
            = "select * from location where id = ?";

    @Override
    public Location getLocation(int locationId) {
        try {
            Location l = jdbc.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), locationId);
            return l;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_UPDATE_LOCATION
            = "update location set "
            + "name = ?, description = ?,"
            + "address = ?, city = ?, state = ?,"
            + "country = ?, longitude = ?, latitude = ?"
            + "where id = ?";

    @Override
    @Transactional
    public void updateLocation(Location location) {
        jdbc.update(SQL_UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getCity(),
                (location.getState() == null) ? null : location.getState(),
                location.getCountry(),
                location.getLongitude(),
                location.getLatitude(),
                location.getId());
    }

    private static final String SQL_DELETE_LOCATION
            = "delete from location where id = ?";
    private static final String UPDATE_LOCATION_ID_ORGANIZATION
            = "update organization set locationId = null where locationId = ?";
    private static final String DELETE_LOCATION_ID_SIGHTING
            = "delete from sighting where locationId = ?";
    private static final String DELETE_SIGHTING_FROM_HERO_SIGHTING_BY_LOCATION
            = "delete hs from heroSighting hs "
            + "join sighting s "
            + "on hs.sightingId = s.id "
            + "where s.locationId = ?";
    //delete herosighting table relationship

    @Override
    public void deleteLocation(int locationId) {
        jdbc.update(UPDATE_LOCATION_ID_ORGANIZATION, locationId);
        jdbc.update(DELETE_SIGHTING_FROM_HERO_SIGHTING_BY_LOCATION, locationId);
        jdbc.update(DELETE_LOCATION_ID_SIGHTING, locationId);
        jdbc.update(SQL_DELETE_LOCATION, locationId);
    }

    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting "
            + "(locationId, date) "
            + "values(?, ?)";

    private static final String SQL_INSERT_HERO_SIGHTING
            = "insert into HeroSighting "
            + "(heroId, sightingId) "
            + "values(?, ?)";

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        jdbc.update(SQL_INSERT_SIGHTING,
                sighting.getLocation().getId(),
                sighting.getSightingDate());

        int newId = jdbc.queryForObject("select LAST_INSERT_ID()",
                Integer.class);

        sighting.setId(newId);

        addHeroSighting(sighting);

        return sighting;
    }

    private void addHeroSighting(Sighting sighting) {
        for (HeroVillain hv : sighting.getHeroes()) {
            jdbc.update(SQL_INSERT_HERO_SIGHTING,
                    hv.getId(),
                    sighting.getId());
        }
    }

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sighting";

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightings = jdbc.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
        populateSightings(sightings);

        return sightings;
    }

    private void populateSightings(List<Sighting> sightings) {
        for (Sighting s : sightings) {
            s.setHeroes(getAllHeroesBySightingId(s.getId()));
            s.setLocation(getLocationBySightingId(s.getId()));
        }
    }

    private static final String SELECT_HEROES_BY_SIGHTING
            = "select h.* from hero h join heroSighting hs on h.id = hs.heroId WHERE hs.sightingId = ?";

    private List<HeroVillain> getAllHeroesBySightingId(int sightingId) {
        try {
            List<HeroVillain> heroes = jdbc.query(SELECT_HEROES_BY_SIGHTING, new HeroMapper(), sightingId);
            populateHeroes(heroes);
            return heroes;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_SELECT_SIGHTING
            = "select * from sighting where id = ?";

    @Override
    public Sighting getSighting(int sightingId) {
        try {
            Sighting s = jdbc.queryForObject(SQL_SELECT_SIGHTING, new SightingMapper(), sightingId);
            s.setHeroes(getAllHeroesBySightingId(sightingId));
            s.setLocation(getLocationBySightingId(sightingId));
            return s;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_SELECT_LOCATION_BY_SIGHTING
            = "select l.* from location l join sighting s on l.id = s.locationId where s.id = ?";

    private Location getLocationBySightingId(int sightingId) {
        try {
            return jdbc.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING, new LocationMapper(), sightingId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_UPDATE_SIGHTING
            = "update sighting set "
            + "locationId = ?, date = ? "
            + "where id = ?";

    @Override
    @Transactional
    public void updateSighting(Sighting sighting) {
        jdbc.update(SQL_UPDATE_SIGHTING,
                sighting.getLocation().getId(),
                sighting.getSightingDate(),
                sighting.getId());

        jdbc.update(DELETE_SIGHTING_FROM_HERO_SIGHTING, sighting.getId());
        addHeroSighting(sighting);
    }

    private static final String SQL_DELETE_SIGHTING
            = "delete from Sighting where id = ?";
    private static final String DELETE_SIGHTING_FROM_HERO_SIGHTING
            = "delete from HeroSighting where sightingId = ?";

    @Override
    public void deleteSighting(int sightingId) {
        jdbc.update(DELETE_SIGHTING_FROM_HERO_SIGHTING, sightingId);
        jdbc.update(SQL_DELETE_SIGHTING, sightingId);
    }

    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organization "
            + "(name, description, locationId)"
            + "values(?, ?, ?)";

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        jdbc.update(SQL_INSERT_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                (organization.getLocation() == null) ? null : organization.getLocation().getId());

        int newId = jdbc.queryForObject("select LAST_INSERT_ID()",
                Integer.class);

        organization.setId(newId);

        return organization;
    }

    private static final String SQL_SELECT_ALL_FROM_ORGANIZATIONS
            = "select * from organization";

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> organizations = jdbc.query(SQL_SELECT_ALL_FROM_ORGANIZATIONS,
                new OrganizationMapper());
        populateOrganizations(organizations);

        return organizations;
    }

    private void populateOrganizations(List<Organization> organizations) {
        for (Organization o : organizations) {
            o.setLocation(getLocationByOrganizationId(o.getId()));
        }
    }

    private static final String SELECT_LOCATION_BY_ORGANIZATION
            = "select l.* from location l join organization o on l.id = o.locationId where o.id = ?";

    private Location getLocationByOrganizationId(int id) {
        try {
            return jdbc.queryForObject(SELECT_LOCATION_BY_ORGANIZATION, new LocationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from organization where id = ?";

    @Override
    public Organization getOrganization(int organizationId) {
        try {
            Organization o = jdbc.queryForObject(SQL_SELECT_ORGANIZATION, new OrganizationMapper(), organizationId);
            o.setLocation(getLocationByOrganizationId(organizationId));
            return o;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organization set "
            + "name = ?, description = ?, locationId = ? "
            + "where id = ?";

    @Override
    public void updateOrganization(Organization o) {
        jdbc.update(SQL_UPDATE_ORGANIZATION,
                o.getName(),
                o.getDescription(),
                (o.getLocation() == null) ? null : o.getLocation().getId(),
                o.getId());
    }

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where id = ?";
    private static final String DELETE_ORGANIZATION_FROM_HERO_ORGANIZATION
            = "delete from HeroOrganization where organizationId = ?";

    @Override
    public void deleteOrganization(int organizationId) {
        jdbc.update(DELETE_ORGANIZATION_FROM_HERO_ORGANIZATION, organizationId);
        jdbc.update(SQL_DELETE_ORGANIZATION, organizationId);
    }

    private static final String SQL_GET_HEROES_BY_ORGANIZATION_LOCATION
            = "select h.* from hero h "
            + "join heroOrganization ho "
            + "on h.id = ho.heroId "
            + "join organization o "
            + "on ho.organizationId = o.id "
            + "where locationId = ?";

    private static final String SQL_GET_HEROES_BY_SIGHTING_LOCATION
            = "select h.* from hero h "
            + "join heroSighting hs "
            + "on h.id = hs.heroId "
            + "join sighting s "
            + "on hs.sightingId = s.id "
            + "where locationId = ?";

    @Override
    public Set<HeroVillain> getAllHeroesByLocationId(int locationId) {
        List<HeroVillain> heroes = jdbc.query(SQL_GET_HEROES_BY_SIGHTING_LOCATION, new HeroMapper(), locationId);
        populateHeroes(heroes);
        Set<HeroVillain> heroSet = new HashSet<>();
        heroes.forEach((hv) -> {
            heroSet.add(hv);
        });
        return heroSet;
    }

    private static final String SELECT_LOCATIONS_BY_HERO
            = "select l.* from location l "
            + "join sighting s "
            + "on l.id = s.locationId "
            + "join heroSighting hs "
            + "on s.id = sightingId "
            + "join hero h "
            + "on hs.heroId = h.id "
            + "where h.id = ?";

    @Override
    public Set<Location> getAllLocationsByHeroId(int heroId) {
        List<Location> locs = jdbc.query(SELECT_LOCATIONS_BY_HERO, new LocationMapper(), heroId);
        Set<Location> locSet = new HashSet<>();
        locs.forEach((l) -> {
            locSet.add(l);
        });
        return locSet;
    }

    private static final String SELECT_SIGHTINGS_BY_DATE
            = "select * from sighting where date = ?";

    private static final String SELECT_SIGHTINGS_LAST_TEN
            = "select * from sighting "
            + "order by date desc limit 10";
    
    @Override
    public List<Sighting> getLastTen() {
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_LAST_TEN, new SightingMapper());
        populateSightings(sightings);
        return sightings;
    }

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate sightingLd) {
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_BY_DATE, new SightingMapper(), sightingLd);
        populateSightings(sightings);
        return sightings;
    }

    private static final String SELECT_HEROES_BY_ORGANIZATION
            = "select h.* from hero h "
            + "join heroOrganization ho "
            + "on h.id = ho.heroId "
            + "join organization o "
            + "on ho.organizationId = o.id "
            + "where o.id = ?";

    @Override
    public List<HeroVillain> getAllHeroesByOrganization(int organizationId) {
        List<HeroVillain> hvs = jdbc.query(SELECT_HEROES_BY_ORGANIZATION, new HeroMapper(), organizationId);
        populateHeroes(hvs);
        return hvs;
    }

    private static final String SELECT_ORGANIZATIONS_FOR_HERO
            = "select o.* from organization o join heroOrganization ho on o.id = ho.organizationId where ho.heroId = ?";

    @Override
    public List<Organization> getOrganizationsByHeroId(int heroId) {
        List<Organization> organizations = jdbc.query(SELECT_ORGANIZATIONS_FOR_HERO,
                new OrganizationMapper(), heroId);

        populateOrganizations(organizations);
        return organizations;
    }

    private static final String SELECT_SIGHTINGS_BY_HERO
            = "select s.* from sighting s "
            + "join heroSighting hs "
            + "on s.id = hs.sightingId "
            + "join hero h "
            + "on hs.heroId = h.id "
            + "where heroId = ?";

    public List<Sighting> getAllSightingsOfAHero(int heroId) {
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_BY_HERO, new SightingMapper(), heroId);
        populateSightings(sightings);
        return sightings;
    }

    private static final class HeroMapper implements RowMapper<HeroVillain> {

        public HeroVillain mapRow(ResultSet rs, int rowNum) throws SQLException {
            HeroVillain hero = new HeroVillain();
            hero.setId(rs.getInt("id"));
            hero.setName(rs.getString("name"));
            hero.setDescription(rs.getString("description"));
            hero.setSuperpower(rs.getString("superpower"));
            return hero;
        }
    }

    private static final class LocationMapper implements RowMapper<Location> {

        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location l = new Location();
            l.setId(rs.getInt("id"));
            l.setName(rs.getString("name"));
            l.setDescription(rs.getString("description"));
            l.setAddress(rs.getString("address"));
            l.setCity(rs.getString("city"));
            l.setState(rs.getString("state"));
            l.setCountry(rs.getString("country"));
            l.setLongitude(rs.getDouble("longitude"));
            l.setLatitude(rs.getDouble("latitude"));
            return l;
        }
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization o = new Organization();
            o.setId(rs.getInt("id"));
            o.setName(rs.getString("name"));
            o.setDescription(rs.getString("description"));
            return o;
        }
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting s = new Sighting();
            s.setId(rs.getInt("id"));
            s.setSightingDate(rs.getDate("date").toLocalDate());
            return s;
        }
    }
}
