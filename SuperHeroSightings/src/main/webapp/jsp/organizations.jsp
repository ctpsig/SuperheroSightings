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
                    <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                </ul>    
            </div>
            <br />
            <div class="row">
                <div class="col-8">
                    <h2>All Organizations</h2>
                </div>
                <div class="col-4">
                    <h2>Add Organizations</h2>
                    <form>
                        <div class="form-group">
                            <label for="name">Hero Name:</label>
                            <input type="text" class="form-control" id="date" placeholder="Enter Organization Name">
                        </div>
                        <div class="form-group">
                            <label for="description">Description:</label>
                            <textarea class="form-control" id="description" placeholder="Enter Description" rows="5"></textarea>
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
                    </form>
                </div>
            </div>
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
