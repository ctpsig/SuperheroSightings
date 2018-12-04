<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Heroes</title>
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link href='https://fonts.googleapis.com/css?family=Bungee Shade' rel='stylesheet'>
        <style>
            h1 {
                font-family: 'Bungee Shade';font-size: 65px;
                color: red;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <a class="nav-link" href="${pageContext.request.contextPath}/"><h1>Superhero Sightings</h1></a>
            <div>
                <ul class="nav nav-tabs nav-fill justify-content-center">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/displayHeroesPage">Heroes</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                    <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                </ul>    
            </div>
            <br />
            <div class="row">
                <div class="col-8">
                    <h4 class="display-4">All Locations</h4>
                </div>
                <div class="col-4">
                    <h4 class="display-4">Add Locations</h4>
                    <form>
                        <div class="form-group">
                            <!--                            <label for="name">Location Name:</label>-->
                            <input type="text" class="form-control" id="name" placeholder="Enter Location Name">
                        </div>
                        <div class="form-group">
                            <!--                            <label for="description">Description:</label>-->
                            <textarea class="form-control" id="description" placeholder="Enter Description" rows="5"></textarea>
                        </div>
                        <div class="form-group">
                            <!--                            <label for="address">Address:</label>-->
                            <input type="text" class="form-control" id="address" placeholder="Enter Address">
                        </div>
                        <div class="form-row">
                            <div class="form-group col-8">
                                <!--                            <label for="city">City:</label>-->
                                <input type="text" class="form-control" id="city" placeholder="Enter City">
                            </div>
                            <div class="form-group col-4">
                                <!--                            <label for="state">State:</label>-->
                                <input type="text" class="form-control" id="state" placeholder="Enter State">
                            </div>
                        </div>
                        <div class="form-group">
                            <!--                            <label for="country">Country:</label>-->
                            <input type="text" class="form-control" id="country" placeholder="Enter Country">
                        </div>
                        <div class="form-row">
                            <div class="form-group col-6">
                                <input type="text" class="form-control" id="longitude" placeholder="Enter Longitude">
                            </div>
                            <div class="form-group col-6">
                                <input type="text" class="form-control" id="latitude" placeholder="Enter Latitude">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
                <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
                </body>
                </html>
