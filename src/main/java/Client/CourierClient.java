package Client;

import Courier.Courier;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestAssuredClient {

    private static final String COURIER_PATH = "api/v1/courier/";


    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .log().body()
                .when()
                .post(COURIER_PATH)
                .then()
                .log().body();
    }


    public ValidatableResponse login(CourierCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .log().body()
                .when()
                .post(COURIER_PATH + "login/")
                .then()
                .log().body();

    }


    public ValidatableResponse delete(int courierId) {
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + courierId)
                .then();
    }

}