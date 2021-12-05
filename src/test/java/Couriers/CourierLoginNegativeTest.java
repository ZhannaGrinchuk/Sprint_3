package Couriers;

import Client.CourierCredentials;
import Courier.Courier;
import Client.CourierClient;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CourierLoginNegativeTest {

    private CourierClient courierClient;
    Courier courier;


    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = Courier.getRandom();
        courierClient.create(courier);
    }


    //система вернёт ошибку, если неправильно указать логин или пароль
    @Test
    public void checkCourierLoginWithIncorrectLogin() {

        courier = Courier.getRandom();

        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertThat("Status code is incorrect", statusCode, Matchers.equalTo(404));
        assertThat(errorMessage, equalTo("Учетная запись не найдена"));
    }

    //если какого-то поля нет, запрос возвращает ошибку (логин только с логином)
    @Test (timeout=5000)
    public void checkCourierLoginWithLoginOnly() {

        courier = Courier.getWithLoginOnly();

        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertThat("Status code is incorrect", statusCode, Matchers.equalTo(400));
        assertThat(errorMessage, equalTo("Недостаточно данных для входа"));

    }

    //если какого-то поля нет, запрос возвращает ошибку (логин только с паролем)
    @Test
    public void checkCourierLoginWithPasswordOnly() {

        courier = Courier.getWithPasswordOnly();

        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertThat("Status code is incorrect", statusCode, Matchers.equalTo(400));
        assertThat(errorMessage, equalTo("Недостаточно данных для входа"));
    }


    //если авторизоваться под несуществующим пользователем, запрос возвращает ошибку
    @Test
    public void checkLoginNonExistentCourier() {

        Courier courier = Courier.getRandom();

        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertThat("Status code is incorrect", statusCode, Matchers.equalTo(404));
        assertThat(errorMessage, equalTo("Учетная запись не найдена"));
    }

}
