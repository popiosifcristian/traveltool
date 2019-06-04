package travel.tool.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author ipop
 */
@Ignore
public abstract class AbstractRestControllerTest<T> {
    protected long actualId = 11111;
    protected String endpointName;

    public abstract T generateTestData();

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 1212;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void findAll_thenOK() {
        when().request("GET", "/" + endpointName + "/findAll").then().statusCode(200);
    }

    @Test
    public void createGetByIdDelete_thenOK() {
        create();
        getById_thenOK();
        delete_thenOK();
    }

    public void create() {
        Response response = given().body(generateTestData())
                .contentType(ContentType.JSON)
                .when().post("/" + endpointName + "/save");
        actualId = response.jsonPath().getLong("id");
        Assertions.assertEquals(200, response.getStatusCode());
    }

    public void getById_thenOK() {
        when().request("GET", "/" + endpointName + "/getOne?id=" + actualId)
                .then().assertThat().body("id", equalTo((int) actualId)).statusCode(200);
    }

    public void delete_thenOK() {
        given().log().all()
                .with().body(generateTestData())
                .contentType(ContentType.JSON)
                .when().request("DELETE", "/" + endpointName + "/delete")
                .then().statusCode(200);
    }
}
