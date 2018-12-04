/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    loadOrganizationsList();

    loadHeroesToCards();
    $('#submit-hero').click(function (event) {
        $.ajax({
            type: 'POST',
            url: 'hero',
            data: JSON.stringify({
                name: $('#hero-name').val(),
                description: $('#hero-description').val(),
                superpower: $('#hero-superpower').val(),
                organizations: $('#organizations-select').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (data) {
                $('#all-heroes').empty();
                loadHeroesToCards();
            },
            error: function () {
                alert("fail!");
            }
        });
    });
});

//how to deal with null gets
function loadHeroesToCards() {
    $.ajax({
        type: 'GET',
        url: 'heroes',
        success: function (heroArray) {
            $.each(heroArray, function (index, hero) {
                var id = hero.id;
                var name = hero.name;
                var description = hero.description;
                var superpower = hero.superpower;
                var orgs = hero.organizations;
                var displayOrgsString = 'Organizations: ';
                $.each(orgs, function (index, org) {
                    displayOrgsString += ' * ' + org.name + ' * ';
                });

                var card = '<div id="' + id + name + '"class="card"><div class="card-body">';
                card += '<h4 class="card-title">' + name + '</h4>';
                card += '<p class="card-text">Superpower: ' + superpower + '</p>';
                card += '<p class="card-text">' + description + '</p>';
                card += '<p class="card-text">' + displayOrgsString + '</p>';
                card += '<button type="button" class="btn btn-info" id="edit' + id + '" data-toggle="modal" data-target="#editHeroesModal">Edit</button>';
                card += '<button type="button" class="btn btn-danger" id="delete' + id + '">Delete</button></div></div>';

                $('#all-heroes').prepend(card);

                $('#delete' + id).on('click', function () {
                    $.ajax({
                        type: 'DELETE',
                        url: 'hero/' + id,
                        success: function () {
                            $('#all-heroes').empty();
                            loadHeroesToCards();
                        },
                        error: function () {
                            alert("It didn't happen.");
                        }
                    });
                });

                $('#edit' + id).on('click', function () {
                    $.ajax({
                        type: 'GET',
                        url: 'hero/' + id,
                        success: function (hero) {
                            var choiceId = hero.id;
                            var choiceName = hero.name;
                            var choiceDescription = hero.description;
                            var choiceSuperpower = hero.superpower;
                            var choiceOrgs = hero.organizations;
                            var title = "Edit " + name;

                            $("#hiddenId").val(choiceId);
                            $('#editHeroesTitle').text(title);
                            $('#edit-hero-name').val(choiceName);
                            $('#edit-hero-description').val(choiceDescription);
                            $('#edit-organizations-select').val();
                            $('#edit-hero-superpower').val(choiceSuperpower);
                            $.ajax({
                                type: 'GET',
                                url: 'organizations',
                                success: function (organizations) {
                                    $('#edit-organizations-select').empty();
                                    $.each(organizations, function (index, organization) {
                                        var isTrue = false;
                                        var organizationId = organization.id;
                                        var organizationName = organization.name;
                                        $.each(choiceOrgs, function (index, choiceOrg) {
                                            var orgId = choiceOrg.id;
                                            var orgName = choiceOrg.name;
                                            if (organization.id == choiceOrg.id) {
                                                var editCheck = '<option value="' + orgId + '" id="edit-' + orgName + '" selected>';
                                                editCheck += orgName + '</option>';
                                                $('#edit-organizations-select').prepend(editCheck);
                                                isTrue = true;
                                            }
                                        });
                                        if (isTrue == false) {
                                            var check = '<option value="' + organizationId + '" id="edit-' + organizationName + '">';
                                            check += organizationName + '</option>';
                                            $('#edit-organizations-select').prepend(check);
                                        }
                                    });
                                },
                                error: function () {
                                    alert("fail!");
                                }
                            });
                            $('#save-button').on('click', function () {
                                $.ajax({
                                    type: 'PUT',
                                    url: 'hero/' + $('#hiddenId').val(),
                                    data: JSON.stringify({
                                        name: $('#edit-hero-name').val(),
                                        description: $('#edit-hero-description').val(),
                                        superpower: $('#edit-hero-superpower').val(),
                                        organizations: $('#edit-organizations-select').val()
                                    }),
                                    headers: {
                                        'Accept': 'application/json',
                                        'Content-Type': 'application/json'
                                    },
                                    'dataType': 'json',
                                    success: function (data) {
                                        $('#all-heroes').empty();
                                        loadHeroesToCards();
                                    },
                                    error: function () {
                                        console.log("fail!");
                                    }
                                });
                            });
                        }
                    });
                });
            });
        },
        error: function () {
            alert("Fail!");
        }
    });
}
function loadOrganizationsList() {
    $.ajax({
        type: 'GET',
        url: 'organizations',
        success: function (organizations) {
            $('#organizations-select').empty();
            $.each(organizations, function (index, organization) {
                var id = organization.id;
                var name = organization.name;

                var check = '<option value="' + id + '" id="' + name + '">';
                check += name + '</option>';
                $('#organizations-select').prepend(check);
            });
        },
        error: function () {
            alert("fail!");
        }
    });
}
