import com.example.*;

import org.junit.Before;
import org.junit.Test;



import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;


public class CreateCourierPositiveTest {

    private CourierClient courierClient;
    private int courierId;


    @Before
    public void setUp() {
        courierClient = new CourierClient();

    }

    //курьера можно создать
    @Test
    public void checkCourierCanBeCreated() {
        Courier courier = Courier.getRandom();
        boolean isCourierCreated = courierClient.create(courier);
        assertTrue("Сourier is not created", isCourierCreated);
    }

    //успешный запрос возвращает id
    @Test
    public void checkIdWhenCreateCourier() {

        Courier courier = Courier.getRandom();

        courierClient.create(courier);
        courierId = courierClient.login(CourierCredentials.from(courier));

        assertThat("Courier ID is incorrect", courierId, is(not(0)));
    }


    //чтобы создать курьера, нужно передать в ручку все обязательные поля
    @Test
    public void checkCourierCanBeCreatedWithLoginAndPassword() {

        CourierWithLoginAndPassword courierWithLoginAndPassword = CourierWithLoginAndPassword.getRandom();
        boolean isCourierCreatedWithRequiredFields = courierClient.createWithRequiredFields(courierWithLoginAndPassword);
        assertTrue("Сourier is not created", isCourierCreatedWithRequiredFields);
    }


    //запрос возвращает правильный код ответа
    @Test
    public void createCourierAndCheckCode() {

        Courier courier = Courier.getRandom();
        courierClient.createAndCheckCode(courier);
    }


    //успешный запрос возвращает ok: true
    @Test
    public void createCourierAndCheckResponse() {

        Courier courier = Courier.getRandom();
        courierClient.createAndCheckOkTrue(courier);

    }

}










