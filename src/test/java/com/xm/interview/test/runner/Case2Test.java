package com.xm.interview.test.runner;

import com.xm.interview.test.base.BaseTest;
import com.xm.interview.test.Person;
import io.restassured.path.json.JsonPath;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


import static io.restassured.RestAssured.given;

/**
 * The Case2Test class is used to test the method to find the oldest person among the common characters in all films.
 * It extends the BaseTest class.
 */
public class Case2Test extends BaseTest {
    /**
     * This method tests the oldest person among the common characters in all films.
     */
    @Test
    public void testOldestPersonInAllFilms() {
        String next = "/people/";
        int requestCounter = 0;

        List<Person> allPeople = new ArrayList<>();

        do {
            JsonPath peopleJson = given()
                    .when()
                    .get(next)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response()
                    .jsonPath();


            next = peopleJson.getString("next");

            List<Person> personList = peopleJson.getList("results", Person.class);
            allPeople.addAll(personList);
            requestCounter++;
        }
        while (next != null);

        //Sort the list of people by the number of films they played in, in descending order.
        allPeople.sort((p1, p2) -> p2.getFilms().size() - p1.getFilms().size());
        int starWarsFilmCount = allPeople.get(0).getFilms().size();


        Person oldestPerson = null;

        for (Person person : allPeople) {
            if (person.getFilms().size() == starWarsFilmCount) {
                if (oldestPerson == null) {
                    oldestPerson = person;
                } else {
                    String birthYear1 = person.getBirthYear();
                    String birthYear2 = oldestPerson.getBirthYear();

                    if (birthYear1.equals("unknown")) {
                        continue;
                    }
                    if (birthYear2.equals("unknown")) {
                        oldestPerson = person;
                        continue;
                    }

                    int year1 = Integer.parseInt(birthYear1.replace("BBY", "").replace("ABY", "").trim());
                    int year2 = Integer.parseInt(birthYear2.replace("BBY", "").replace("ABY", "").trim());

                    // Adjusting for "BBY" and "ABY"
                    if (birthYear1.endsWith("BBY")) {
                        year1 = -year1;
                    }
                    if (birthYear2.endsWith("BBY")) {
                        year2 = -year2;
                    }

                    if (year1 < year2) {
                        oldestPerson = person;
                    }
                }
            }
        }

        if (oldestPerson != null) {
            System.out.println("________________________________________________________");
            System.out.printf("The oldest character featured in all the films is %s, who was born in %s%n", oldestPerson.getName(), oldestPerson.getBirthYear());
            System.out.println("________________________________________________________");
        }

        Assert.assertTrue(10 >= requestCounter, "More than 10 requests were made");

    }
}
