package travel.tool.controller;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ipop
 */
class CompanyControllerTest {
    @Before
    public void setup() {
        RestAssured.baseURI = "https://localhost";
        RestAssured.port = 1212;
    }

    @Test
    public void whenRequestGet_thenOK(){
        when().request("GET", "/company/findAll").then().statusCode(200);
    }
}