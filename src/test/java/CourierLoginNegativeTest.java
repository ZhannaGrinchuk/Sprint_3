import com.example.*;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


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

        Courier courier = Courier.getRandom();
        String message = courierClient.loginWithIncorrectLogin(courier);
        assertThat(message, equalTo("Учетная запись не найдена"));
    }

    //если какого-то поля нет, запрос возвращает ошибку
    @Test
    public void checkCourierLoginWithoutLogin() {

        CourierWithPasswordAndFirstName courierWithPasswordAndFirstName = CourierWithPasswordAndFirstName.getRandom();

        String message = courierClient.loginWithoutPassword(courierWithPasswordAndFirstName);

        assertThat(message, equalTo("Недостаточно данных для входа"));
    }


    //если авторизоваться под несуществующим пользователем, запрос возвращает ошибку
    @Test
    public void checkLoginNonExistentCourier() {

        Courier courier = Courier.getRandom();

        String message = courierClient.loginNonExistentCourier(courier);

        assertThat(message, equalTo("Учетная запись не найдена"));
    }

    }







