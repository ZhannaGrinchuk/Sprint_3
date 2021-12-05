package Couriers;

import Courier.Courier;
import Client.CourierClient;
import Client.CourierCredentials;
import io.restassured.response.ValidatableResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;



public class CourierLoginPositiveTest {

    private CourierClient courierClient;
    private int courierId;
    Courier courier;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
       courierClient.delete(courierId);
    }

    //курьер может авторизоваться
    @Test
    public void checkCourierLogin() {

        courier = Courier.getRandom();
        courierClient.create(courier);

        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));

        int statusCode = response.extract().statusCode();
        int courierId = response.extract().path("id");


        assertThat("Status code is incorrect", statusCode, equalTo(200));
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
    }


    //успешный запрос возвращает id
    @Test
    public void checkIdWhenCreateCourier() {

        Courier courier = Courier.getRandom();

        courierClient.create(courier);
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));

        int statusCode = response.extract().statusCode();
        int courierId = response.extract().path("id");

        assertThat("Status code is incorrect", statusCode, equalTo(200));
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
    }


    //для авторизации нужно передать все обязательные поля
    @Test
    public void checkCourierLoginWithRequiredFields() {

        Courier courier = Courier.getWithLoginAndPassword();
        courierClient.create(courier);

        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));

        int statusCode = response.extract().statusCode();
        int courierId = response.extract().path("id");

        assertThat("Status code is incorrect", statusCode, equalTo(200));
        assertThat("Courier ID is incorrect", courierId, is(not(0)));

    }
}





