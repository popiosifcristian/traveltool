package travel.tool.controller;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import travel.tool.model.Company;
import travel.tool.model.CompanyType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ipop
 */
class CompanyControllerTest {
    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 1212;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void whenRequestGet_thenOK(){
        when().request("GET", "/company/findAll").then().statusCode(200);
    }

    @Test
    public void createAndDelete(){
        given().log().all()
        .with().body(new Company(100,"Test assured","Test assured","Test assured","Test assured","Test assured","Test assured", CompanyType.AGENCY)).
        when().request("POST","/company/save")
                .then().statusCode(200);

//        when().request("GET", "/company/getOne?id=100").then().assertThat().body("name", equalTo("Test assured"));
    }
}