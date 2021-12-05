package Couriers;

import Courier.Courier;
import Client.CourierClient;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;


public class CreateCourierPositiveTest {

    private CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();

    }

    //курьера можно создать
    @Test
    public void checkCourierCanBeCreated() {

        Courier courier = Courier.getRandom();

        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        boolean isCourierCreated = response.extract().path("ok");

        assertThat("Status code is incorrect", statusCode, equalTo(201));
        assertTrue("Сourier is not created", isCourierCreated);
    }


    //чтобы создать курьера, нужно передать в ручку все обязательные поля
    @Test
    public void checkCourierCanBeCreatedWithLoginAndPassword() {

        Courier courier = Courier.getWithLoginAndPassword();

        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        boolean isCourierCreated = response.extract().path("ok");

        assertThat("Status code is incorrect", statusCode, equalTo(201));
        assertTrue("Сourier is not created", isCourierCreated);

    }

    //запрос возвращает правильный код ответа
    @Test
    public void createCourierAndCheckRightCode() {

        Courier courier = Courier.getRandom();

        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();

        assertThat("Status code is incorrect", statusCode, equalTo(201));

    }

    //успешный запрос возвращает ok: true
    @Test
    public void createCourierAndCheckResponse() {

        Courier courier = Courier.getRandom();

        ValidatableResponse response = courierClient.create(courier);

        boolean isCourierCreated = response.extract().path("ok");

        assertTrue("Сourier is not created", isCourierCreated);

    }

}