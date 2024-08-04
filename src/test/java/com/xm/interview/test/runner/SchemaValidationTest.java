package com.xm.interview.test.runner;

import com.xm.interview.test.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationTest extends BaseTest {

    /**
     * Method for testing the schema validation of the people endpoint.
     * It sends a GET request to the API endpoint and asserts that the response
     * status code is 200 and the body matches the JSON schema defined in the
     * "people-schema.json" file.
     */
    @Test
    public void testPeopleSchemaValidation() {

        // Send a GET request to the people endpoint and validate the response
        given()
                .log().all()
                .when()
                .get("/people/")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("people-schema.json"));
    }
}
