/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    heroesToMultiSelect();
    locationsToDropDown();
    loadSightingsToCards();

    $('#submit-sighting').click(function (event) {

        $.ajax({
            type: 'POST',
            url: 'sighting',
            data: JSON.stringify({
                date: $('#sighting-date').val(),
                location: $('#sighting-location').val(),
                heroes: $('#heroes-select').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (data) {
                alert("Success!")
                $('#all-sightings').empty();
                displaySightingsCards(data);
            },
            error: function () {
            }
        });
    });

});

function heroesToMultiSelect() {
    $.ajax({
        type: 'GET',
        url: 'heroes',
        success: function (heroes) {
            $.each(heroes, function (index, hero) {
                var heroId = hero.id;
                var heroName = hero.name;
                var display = '<option value="' + heroId + '" id="edit-' + heroName + '">';
                display += heroName + '</option>';

                $('#heroes-select').prepend(display);
            });
        }
    });
}

function locationsToDropDown() {
    $.ajax({
        type: 'GET',
        url: 'locations',
        success: function (locations) {
            $.each(locations, function (index, location) {
                var locId = location.id;
                var locName = location.name;
                var locSelection = '<option value="' + locId + '">' + locName + '</option>'
                $('#sighting-location').prepend(locSelection);
            });
        },
        error: function () {
            alert("fail!");
        }
    });
}
;

function loadSightingsToCards() {
    $.ajax({
        type: 'GET',
        url: 'sightings',
        success: function (sightings) {
            $.each(sightings, function (index, sighting) {
                var id = sighting.id;
                var month = sighting.sightingDate.month;
                var day = sighting.sightingDate.dayOfMonth;
                var year = sighting.sightingDate.year;
                var displayDate = month + ' ' + day + 'th, ' + year;
                var locationName = sighting.location.name;
                var locationAddress = sighting.location.address;
                var locationCity = sighting.location.city;
                var locationState = sighting.location.state;
                var locationCountry = sighting.location.country;
                var locLatitude = sighting.location.latitude;
                var locLongitude = sighting.location.longitude;

                var heroes = sighting.heroes;
                var hDisplay = ' * ';
                $.each(heroes, function (index, hero) {
                    hDisplay += hero.name + ' * ';
                });

                var card = '<div id="' + id + '"class="card col-6 text-center"><div class="card-body">';
                card += '<h5 class="card-title">SIGHTED!</h5>';
                card += '<p class="card-text" id="heroArray">' + hDisplay + '</p>';
                card += '<p class="card-text">' + displayDate + '</p>';
                card += '<p class="card-text">' + locationName;
                card += '<br/>' + locationAddress;
                card += '<br/>' + locationCity + ', ' + locationState + ', ' + locationCountry;
                card += '<br/>' + locLatitude + ', ' + locLongitude + '</p>';
                card += '<button type="button" class="btn btn-info" id="edit' + id + '" data-toggle="modal" data-target="#editSightingsModal">Edit</button>';
                card += '<button type="button" class="btn btn-danger" id="delete' + id + '">Delete</button></div></div>';
                $('#all-sightings').append(card);

                $('#delete' + id).on('click', function () {
                    $.ajax({
                        type: 'DELETE',
                        url: 'sighting/' + id,
                        success: function () {
                            $('#all-sightings').empty();
                            loadSightingsToCards();
                        },
                        error: function () {
                            alert('fail!!');
                        }
                    });
                });
                $('#edit' + id).on('click', function () {
                    $.ajax({
                        type: 'GET',
                        url: 'sighting/' + id,
                        success: function (sighting) {
                            var choiceId = sighting.id;
                            var choiceMonth = sighting.sightingDate.monthValue;
                            var choiceDay = sighting.sightingDate.dayOfMonth;
                            var choiceYear = sighting.sightingDate.year;
                            var choiceDate = choiceMonth + '/' + choiceDay + '/' + choiceYear;
                            var choiceLocName = sighting.location.name;
                            var choiceLocId = sighting.location.id;
                            var choiceHeroes = sighting.heroes;

                            $('#edit-sighting-date').val(choiceDate);

                            $.ajax({
                                type: 'GET',
                                url: 'locations',
                                success: function (locations) {
                                    $('#edit-sighting-location').empty();
                                    $.each(locations, function (index, location) {
                                        var editLocId = location.id;
                                        var editLocName = location.name;
                                        var editLocSelection = '<option value="' + editLocId + '">' + editLocName + '</option>';
                                        $('#edit-sighting-location').prepend(editLocSelection);
                                        $('#edit-sighting-location').val(choiceLocId);

                                    });
                                },
                                error: function () {
                                    alert("fail!");
                                }
                            });
                            $.ajax({
                                type: 'GET',
                                url: 'heroes',
                                success: function (heroes) {
                                    $('#edit-heroes-select').empty();
                                    $.each(heroes, function (index, hero) {
                                        var isSelect = false;
                                        var editHeroId = hero.id;
                                        var editHeroName = hero.name;
                                        $.each(choiceHeroes, function (index, choiceHero) {
                                            var selectHeroId = choiceHero.id;
                                            var selectHeroName = choiceHero.name;
                                            if (editHeroId == selectHeroId) {
                                                isSelect = true;
                                                var editSelect = '<option value="' + selectHeroId + '" id="edit-' + selectHeroName + '" selected>';
                                                editSelect += selectHeroName + '</option>';
                                                $('#edit-heroes-select').prepend(editSelect);
                                            }
                                        });
                                        if (isSelect == false) {
                                            var editDisplay = '<option value="' + editHeroId + '" id="edit-' + editHeroName + '">';
                                            editDisplay += editHeroName + '</option>';

                                            $('#edit-heroes-select').prepend(editDisplay);
                                        }
                                    });
                                }
                            });
                            $('#save-button').on('click', function () {
                                $.ajax({
                                    type: 'PUT',
                                    url: 'sighting/' + id,
                                    data: JSON.stringify({
                                        sightingDate: $('#edit-sighting-date').val(),
                                        location: $('#edit-sighting-location').val(),
                                        heroes: $('#edit-heroes-select').val()
                                    }),
                                    headers: {
                                        'Accept': 'application/json',
                                        'Content-Type': 'application/json'
                                    },
                                    'dataType': 'json',
                                    success: function (data) {
                                        $('#all-sightings').empty();
                                        loadSightingsToCards();
                                    },
                                    error: function () {
                                        console.log("fail!");
                                    }
                                });
                            })
                        },
                        error: function () {
                            alert('fail!');
                        }
                    });
                });
            });
        },
        error: function () {
//            alert("Server Not Running");
        }
    });
}
;