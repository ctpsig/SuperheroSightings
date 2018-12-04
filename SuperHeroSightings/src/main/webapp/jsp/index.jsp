<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
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
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                </ul>    
            </div>
            <br/>
            <div class="row">
                <div class="col-8">
                    <h2 id="stuff">All the latest Superhero, Villain hot gossip</h2>
                    <p>
                        Mysterious energies are pervading the world. Beings of powers super 
                        are popping up from New Orleans to Timbuktu.
                    </p>
                    <p>
                        These demi-gods, mutants, and extremely wealthy people seem to be playing out 
                        some kind of cosmic ideological war of good and evil right in the 
                        middle of our major cities. What that means, I think we all know. 
                        Do we? I don't know. But we all want to keep up to date on the action, 
                        the drama, who's hot, and who's not, right here at Superhero Sightings.
                    </p>
                    <hr />
                    <div class="row">
                        <div class="col-6">
                            <blockquote class="blockquote">
                                <p class="mb-0">
                                    Superhero Sightings is where I go to keep up to date on the latest
                                    coming's and going's of Earth's coolest heroes and villains.
                                </p>
                                <footer class="blockquote-footer">Superman</footer>
                            </blockquote>
                        </div>
                        <div class="col-6">
                        <img src="https://media.comicbook.com/2018/03/niccage-superman-1092732-1280x0.jpeg" class="img-fluid rounded"/>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Sighted!</h5>
                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Special title treatment</h5>
                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/index.js"</script>
    </body>
</html>

