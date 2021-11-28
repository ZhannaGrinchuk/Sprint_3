import com.example.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;


public class CourierLoginPositiveTest {

    private CourierClient courierClient;
    private int courierId;
    Courier courier;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = Courier.getRandom();
        courierClient.create(courier);
    }

    @After
    public void tearDown() {
       courierClient.delete(courierId);
    }

    //курьер может авторизоваться
    @Test
    public void checkCourierLogin() {

        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password));
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
    }

    //успешный запрос возвращает id
    @Test
    public void checkIdWhenCourierLogin() {

        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password));
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
    }


    //для авторизации нужно передать все обязательные поля

    @Test
    public void checkCourierLoginWithRequiredFields() {

        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password));
        assertThat("Courier ID is incorrect", courierId, is(not(0)));

    }
}





