/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    loadSightings();

});

function loadSightings() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperHeroSightings/lastTenSightings',
        success: function (sightingArray) {
            $.each(sightingArray, function (index, sighting) {
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

                var card = '<div id="' + id + '"class="card border-info mb-3 text-center"><div class="card-body text-info">';
                card += '<h5 class="card-title">SIGHTED!</h5>';
                card += '<p class="card-text">' + hDisplay + '</p>';
                card += '<p class="card-text">' + displayDate + '</p>';
                card += '<p class="card-text">' + locationName + '</p></div></div>';

                $('#lastTen').append(card);
            });
        },
        error: function () {
            alert("Server Not Running");

        }
    });
}
;
