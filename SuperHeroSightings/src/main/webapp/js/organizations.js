/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadOrganizationsToCards();
    locationsToDropDown();
    $('#submit-organization').click(function (event) {
        $.ajax({
            type: 'POST',
            url: 'organization',
            data: JSON.stringify({
                name: $('#organization-name').val(),
                description: $('#organization-description').val(),
                locationId: $('#location-select').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (data) {
                $('#all-organizations').empty();
                loadOrganizationsToCards();
            },
            error: function () {
                console.log("fail!");
            }
        });
    });
});

function locationsToDropDown() {
    $.ajax({
        type: 'GET',
        url: 'locations',
        success: function (locationArray) {
            $.each(locationArray, function (index, location) {
                var id = location.id;
                var name = location.name;
                var locSelection = '<option value="' + id + '">' + name + '</option>'
                $('#location-select').prepend(locSelection);
            });
        },
        error: function () {
            alert("fail!");
        }
    });
}
;

function loadOrganizationsToCards() {
    $.ajax({
        type: 'GET',
        url: 'organizations',
        success: function (organizationArray) {
            $.each(organizationArray, function (index, organization) {
                var id = organization.id;
                var name = organization.name;
                var description = organization.description;
                var locationName = organization.location.name;

                var card = '<div id="' + id + name + '"class="card"><div class="card-body">';
                card += '<h4 class="card-title">' + name + '</h4>';
                card += '<p class="card-text">Head Quarters: ' + locationName + '</p>';
                card += '<p class="card-text">' + description + '</p>';
                card += '<button type="button" class="btn btn-info" id="edit' + id + '" data-toggle="modal" data-target="#editOrganizationsModal">Edit</button>';
                card += '<button type="button" class="btn btn-danger" id="delete' + id + '">Delete</button></div></div>';

                $('#all-organizations').prepend(card);

                $('#delete' + id).on('click', function () {
                    $.ajax({
                        type: 'DELETE',
                        url: 'organization/' + id,
                        success: function () {
                            $('#all-organizations').empty();
                            loadOrganizationsToCards();
                        },
                        error: function () {
                            alert("It didn't happen.");
                        }
                    });
                });

                $('#edit' + id).on('click', function () {
                    $.ajax({
                        type: 'GET',
                        url: 'organization/' + id,
                        success: function (organization) {
                            var choiceId = organization.id;
                            var choiceName = organization.name;
                            var choiceDescription = organization.description;
                            var choiceLocation = organization.location.name;

                            var title = 'Edit ' + choiceName;
                            $('#editOrganizationTitle').text(title);
                            $('#edit-organization-name').val(choiceName);
                            $('#edit-organization-description').val(choiceDescription);
                            $.ajax({
                                type: 'GET',
                                url: 'locations',
                                success: function (locationArray) {
                                    $.each(locationArray, function (index, location) {
                                        var id = location.id;
                                        var name = location.name;
                                        var locSelection = '<option value="' + id + '">' + name + '</option>'
                                        $('#edit-location-select').prepend(locSelection);
                                    });
                                },
                                error: function () {
                                    alert("fail!");
                                }
                            });
                            $('#edit-location-select').val(choiceLocation);
                            $('#save-button').on('click', function () {
                                $.ajax({
                                    type: 'PUT',
                                    url: 'organization/' + choiceId,
                                    data: JSON.stringify({
                                        name: $('#edit-organization-name').val(),
                                        description: $('#edit-organization-description').val(),
                                        Location: $('#edit-location-select').val(),
                                    }),
                                    headers: {
                                        'Accept': 'application/json',
                                        'Content-Type': 'application/json'
                                    },
                                    'dataType': 'json',
                                    success: function (data) {
                                        $('#all-organization').empty();
                                        loadOrganizationsToCards();
                                    },
                                    error: function () {
                                        console.log("fail!");
                                    }
                                });
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
            alert("fail!");
        }
    });
}
;