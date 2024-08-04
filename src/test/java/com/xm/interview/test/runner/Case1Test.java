package com.xm.interview.test.runner;

import com.xm.interview.test.base.BaseTest;
import com.xm.interview.test.Film;
import com.xm.interview.test.Person;
import io.restassured.path.json.JsonPath;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class Case1Test extends BaseTest {


    @Test
    public void testByName() {
        // Perform a GET request to the "/people/" endpoint with a search parameter "Vader"
        JsonPath vaderJson = given()
                .queryParam("search", "Vader")
                .when()
                .get("/people/")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath();

        // Assert that there is only one result in the "results" list
        Assert.assertEquals(1, vaderJson.getList("results").size());

        Person vader = vaderJson.getObject("results[0]", Person.class);
        System.out.println("________________________________________________________");

        // Assert that the name of the person is "Darth Vader"
        Assert.assertEquals("Darth Vader",vader.getName());

        System.out.printf("The full Name of the named Character is %s\n", vader.getName());

        List<Film> films = new ArrayList<>();

        // Retrieve the list of films that Darth Vader appeared in
        for (String film : vader.getFilms()) {
            JsonPath filmJson = given()
                    .when()
                    .get(film)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response()
                    .jsonPath();
            Film filmObj = filmJson.getObject("", Film.class);

            films.add(filmObj);
        }

        // Find the film with the least number of planets
        Film leastPlanetsFilm = null;
        int minPlanets = Integer.MAX_VALUE;
        for (Film film : films) {
            int numPlanets = film.getPlanets().size();
            if (numPlanets < minPlanets) {
                minPlanets = numPlanets;
                leastPlanetsFilm = film;
            }
        }

        System.out.println("________________________________________________________");
        Assert.assertNotNull(leastPlanetsFilm, "No films found for Darth Vader.");
        System.out.printf("%s features the fewest planets of any film, with only %d planets shown.\n", leastPlanetsFilm.getTitle(), leastPlanetsFilm.getPlanets().size());
        System.out.println("________________________________________________________");

        Assert.assertEquals(minPlanets, leastPlanetsFilm.getPlanets().size());

        boolean starshipFound = false;

        // Check if any of the starships appeared in any of the films
        for (int i = 0; !starshipFound && i < vader.getStarships().size(); i++) {
            String starshipUrl = vader.getStarships().get(i);
            if (leastPlanetsFilm.getStarships().contains(starshipUrl)) {
                starshipFound = true;
            }
        }

        // Assert that one of Vader's starship appeared in any of the films
        Assert.assertTrue(starshipFound, "Vader's starship is not found in any of the films.");

    }
}
