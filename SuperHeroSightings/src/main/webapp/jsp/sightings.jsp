<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sightings</title>
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
                    <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/displayHeroesPage">Heroes</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                </ul>    
            </div>
            <br />
            <div class="row">
                <div class="col-8">
                    <h4 class="display-4">All Sightings</h4>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Card title</h5>
                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <div class="text-right">
                                <a href="#" class="btn btn-info text-right">Edit</a>
                                <a href="#" class="btn btn-danger text-right">Delete</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <h4 class="display-4">Add Sighting</h4>
                    <form>
                        <div class="form-group">
                            <label for="date">Date of Sighting:</label>
                            <input type="text" class="form-control" id="date" placeholder="mm/dd/yyyy">
                        </div>
                        <div class="form-group">
                            <label for="location">Location:</label>
                            <select class="custom-select" id="location">
                                <option selected>Location(Choose One)</option>
                                <option value="1">One</option>
                                <option value="2">Two</option>
                                <option value="3">Three</option>
                            </select>
                            <div class="alert alert-info">If you do not see the location you are looking for, add it <a href="${pageContext.request.contextPath}/displayLocationsPage">here</a>.</div>
                        </div>
                        <label for="heroes">Heroes Sighted: (Check all that apply)</label>
                        <div id="heroes">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1">
                                <label class="form-check-label" for="inlineCheckbox1">1</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2">
                                <label class="form-check-label" for="inlineCheckbox2">2</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="inlineCheckbox3" value="option3">
                                <label class="form-check-label" for="inlineCheckbox3">3</label>
                            </div>
                            <div class="alert alert-info">If you do not see a hero you are looking for, add them <a href="${pageContext.request.contextPath}/displayHeroesPage">here</a>.</div>
                        </div>
                        <div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
