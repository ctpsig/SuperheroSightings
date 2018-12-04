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
                    <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/displayHeroesPage">Heroes</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                </ul>    
            </div>
            <br />
            <div class="row">
                <div class="col-8">
                    <h4 class="display-4">All Heroes</h4>
                </div>
                <div class="col-4">
                    <h4 class="display-4">Add Heroes</h4>
                    <form>
                        <div class="form-group">
                            <label for="name">Hero Name:</label>
                            <input type="text" class="form-control" id="date" placeholder="Enter Hero Name">
                        </div>
                        <div class="form-group">
                            <label for="description">Description:</label>
                            <textarea class="form-control" id="description" placeholder="Enter Description" rows="5"></textarea>
                        </div>
                        <label for="superpower">Superpower:</label>
                        <input type="text" class="form-control" id="superpower" placeholder="Enter Superpower">
                        <label for="organizations">Organizations (Check all that apply):</label>
                        <div id="organizations">
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
                            <div class="alert alert-info">If you do not see a organization you are looking for, add it <a href="${pageContext.request.contextPath}/displayLocationsPage">here</a>.</div>
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
