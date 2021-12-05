package Couriers;

import Courier.Courier;
import Client.CourierClient;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class CreateCourierNegativeTest {

    private CourierClient courierClient;
    private Courier courier;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = Courier.getRandom();
    }

    //нельзя создать двух одинаковых курьеров
    @Test
    public void duplicateCourierCanNotBeCreated() {

        courierClient.create(courier);

        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");


        assertThat("Status code is incorrect", statusCode, equalTo(409));
        assertThat("Courier ID is incorrect", errorMessage, equalTo("Этот логин уже используется. Попробуйте другой."));

    }

    //если создать пользователя с логином, который уже есть, возвращается ошибка
    @Test
    public void createCourierAlreadyExistAndCheckResponse() {

        courierClient.create(courier);

        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");


        assertThat("Status code is incorrect", statusCode, equalTo(409));
        assertThat("Courier ID is incorrect", errorMessage, equalTo("Этот логин уже используется. Попробуйте другой."));

    }

    //если одного из полей нет, запрос возвращает ошибку (создание без пароля)
    @Test
    public void createCourierWithLoginOnlyAndCheckResponse() {
        Courier courier = Courier.getWithLoginOnly();

        ValidatableResponse response = new CourierClient().create(courier);

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertThat("Status code is incorrect", statusCode, equalTo(400));
        assertThat(errorMessage, equalTo("Недостаточно данных для создания учетной записи"));

    }

    //если одного из полей нет, запрос возвращает ошибку (создание без логина)
    @Test
    public void createCourierWithPasswordOnlyAndCheckResponse() {

        Courier courier = Courier.getWithPasswordOnly();

        ValidatableResponse response = new CourierClient().create(courier);

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertThat("Status code is incorrect", statusCode, equalTo(400));
        assertThat(errorMessage, equalTo("Недостаточно данных для создания учетной записи"));

    }

}

