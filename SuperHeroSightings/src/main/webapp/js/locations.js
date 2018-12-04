/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    loadLocationsToCards();

    $('#submit-location').click(function (event) {
//        var createForm = $('#create-location');
//            if (createForm.checkValidity() === false) {
//                event.preventDefault();
//                event.stopPropagation();
//            }
        $.ajax({
            type: 'POST',
            url: 'location',
            data: JSON.stringify({
                name: $('#location-name').val(),
                description: $('#location-description').val(),
                address: $('#location-address').val(),
                city: $('#location-city').val(),
                state: $('#location-state').val(),
                country: $('#location-country').val(),
                longitude: $('#longitude').val(),
                latitude: $('#latitude').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (data) {
                $('#all-locations').empty();
                loadLocationsToCards();
            },
            error: function () {
                console.log("fail!");
            }
            
        });
    }
    );
    
}
);
function loadLocationsToCards() {
    $.ajax({
        type: 'GET',
        url: 'locations',
        success: function (locationArray) {
            $.each(locationArray, function (index, location) {
                var id = location.id;
                var name = location.name;
                var description = location.description;
                var address = location.address;
                var city = location.city;
                var state = location.state;
                var country = location.country;
                var longitude = location.longitude;
                var latitude = location.latitude;

                var card = '<div id="' + id + name + '"class="card"><div class="card-body">';
                card += '<h4 class="card-title">' + name + '</h4>';
                card += '<p class="card-text">' + address + '<br/>';
                card += city + ', ' + state + ', ' + country + '<br/>';
                card += latitude + ', ' + longitude + '</p>';
                card += '<p class="card-text">Description: ' + description + '</p>';
                card += '<button type="button" class="btn btn-info" id="edit' + id + '" value="' + id + '" data-toggle="modal" data-target="#editLocationModal">Edit</button>';
                card += '<button type="button" class="btn btn-danger" id="delete' + id + '">Delete</button></div></div>';

                $('#all-locations').prepend(card);

                $('#delete' + id).on('click', function () {
                    $.ajax({
                        type: 'DELETE',
                        url: 'location/' + id,
                        success: function () {
                            $('#all-locations').empty();
                            loadLocationsToCards();
                        },
                        error: function () {
                            alert("It didn't happen.");
                        }
                    });
                });

                $('#edit' + id).on('click', function () {
                    $.ajax({
                        type: 'GET',
                        url: 'location/' + id,
                        success: function (location) {
                            var choiceId = location.id;
                            var choiceName = location.name;
                            var choiceDescription = location.description;
                            var choiceAddress = location.address;
                            var choiceCity = location.city;
                            var choiceState = location.state;
                            var choiceCountry = location.country;
                            var choiceLongitude = location.longitude;
                            var choiceLatitude = location.latitude;

                            var title = 'Edit ' + choiceName;
                            $('#editLocationTitle').text(title);
                            $('#edit-location-name').val(choiceName);
                            $('#edit-location-description').val(choiceDescription);
                            $('#edit-location-address').val(choiceAddress);
                            $('#edit-location-city').val(choiceCity);
                            $('#edit-location-state').val(choiceState);
                            $('#edit-location-country').val(choiceCountry);
                            $('#edit-longitude').val(choiceLongitude);
                            $('#edit-latitude').val(choiceLatitude);

                            $('#save-button').on('click', function (event) {
                                var editLocationForm = document.getElementById('edit-location');
                                if (editLocationForm.checkValidity() === true) {
                                    $.ajax({
                                        type: 'PUT',
                                        url: 'location/' + choiceId,
                                        data: JSON.stringify({
                                            name: $('#edit-location-name').val(),
                                            description: $('#edit-location-description').val(),
                                            address: $('#edit-location-address').val(),
                                            city: $('#edit-location-city').val(),
                                            state: $('#edit-location-state').val(),
                                            country: $('#edit-location-country').val(),
                                            longitude: $('#edit-longitude').val(),
                                            latitude: $('#edit-latitude').val()
                                        }),
                                        headers: {
                                            'Accept': 'application/json',
                                            'Content-Type': 'application/json'
                                        },
                                        'dataType': 'json',
                                        success: function (data) {
                                            $('#all-locations').empty();
                                            loadLocationsToCards();
                                        },
                                        error: function () {
                                            console.log("fail!");
                                        }
                                    });
                                }
                                ;
                                if (editLocationForm.checkValidity() === false) {
                                    event.preventDefault();
                                    event.stopPropagation();
                                }
                            });
                        },
                        error: function () {
                            alert("fail Loser!");
                        }
                    });

                });

            });
        },
        error: function () {
            console.log("fail!");
        }
    });
}
;
