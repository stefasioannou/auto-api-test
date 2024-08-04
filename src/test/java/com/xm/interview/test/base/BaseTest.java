package com.xm.interview.test.base;

import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    // Set up the base URL for REST API's
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://swapi.dev/api/";
    }
    // Tear down the base URL for REST API's
    @AfterClass
    public void tearDown() {

    }
}

