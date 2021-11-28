package com.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class CourierClient extends RestAssuredClient {

    private static final String COURIER_PATH = "api/v1/courier/";

    public boolean create(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");
    }


    public void createAndCheckCode(Courier courier) {
         given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().statusCode(201);
    }

    public void createAndCheckOkTrue(Courier courier) {
        given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then()
                .assertThat().body("ok",equalTo(true));
    }


    public int login(CourierCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(COURIER_PATH + "login/")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");

    }

    public String loginWithoutPassword(CourierWithPasswordAndFirstName courierWithPasswordAndFirstName) {
        return given()
                .spec(getBaseSpec())
                .body(courierWithPasswordAndFirstName)
                .when()
                .post(COURIER_PATH + "login/")
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");

    }

    public String loginNonExistentCourier(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH + "login/")
                .then()
                .assertThat()
                .statusCode(404)
                .extract()
                .path("message");

    }

    public boolean delete(int courierId) {
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + courierId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");

    }

    public String doubleCreate(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then()
                .assertThat()
                .statusCode(409)
                .extract()
                .path("message");
    }

    public String createWithTheSameLogin(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then()
                .assertThat()
                .statusCode(409)
                .extract()
                .path("message");
    }

    public boolean createWithRequiredFields(CourierWithLoginAndPassword courierWithLoginAndPassword) {
        return given()
                .spec(getBaseSpec())
                .body(courierWithLoginAndPassword)
                .when()
                .post(COURIER_PATH)
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");

    }

    public String createWithoutPassword(CourierWithPasswordAndFirstName courierWithPasswordAndFirstName) {
        return given()
                .spec(getBaseSpec())
                .body(courierWithPasswordAndFirstName)
                .when()
                .post(COURIER_PATH)
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");


    }

    public String createWithoutLogin(CourierWithLoginAndFirstName courierWithLoginAndFirstName) {
        return given()
                .spec(getBaseSpec())
                .body(courierWithLoginAndFirstName)
                .when()
                .post(COURIER_PATH)
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");
    }

    public String loginWithIncorrectLogin(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH + "login/")
                .then()
                .assertThat()
                .statusCode(404)
                .extract()
                .path("message");
    }
}