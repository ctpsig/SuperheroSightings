drop database if exists superfriends_test;

create database superfriends_test;

use superfriends_test;

create table hero
(
	id int primary key not null auto_increment,
    `name` varchar(45) not null,
    description varchar(300) not null,
    superpower varchar(45) not null
);

create table location
(
	id int primary key not null auto_increment,
    `name` varchar(45) not null,
    description varchar(300) not null,
    address varchar(45) not null,
    city varchar(45) not null,
    state varchar(45) not null,
    country varchar(45) not null,
    longitude decimal(9,6) not null,
    latitude decimal(9,6) not null
);

create table organization
(
	id int primary key not null auto_increment,
    `name` varchar(45) not null,
    description varchar(300) not null,
    locationId int,
    foreign key(locationId) references location(id)
);

create table sighting
(
	id int primary key not null auto_increment,
    locationId int not null,
    `date` date not null,
    foreign key(locationId) references location(id)
);

create table HeroSighting
(
	heroId int,
    sightingId int,
    primary key(heroId, sightingId),
    foreign key(heroId) references hero(id),
    foreign key(sightingId) references sighting(id)
);

create table HeroOrganization
(
	heroId int,
    organizationId int,
    primary key(heroId, organizationId),
    foreign key(heroId) references hero(id),
    foreign key(organizationId) references organization(id)
);