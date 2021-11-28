import com.example.*;


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
        courierClient.create(courier);
    }

    //нельзя создать двух одинаковых курьеров
    @Test
    public void doubleCreateCourier() {

        String message = courierClient.doubleCreate(courier);
        assertThat(message, equalTo("Этот логин уже используется. Попробуйте другой."));

    }

    //если создать пользователя с логином, который уже есть, возвращается ошибка
    @Test
    public void createCourierAlreadyExistAndCheckResponse() {

        String message = courierClient.createWithTheSameLogin(courier);
        assertThat(message, equalTo("Этот логин уже используется. Попробуйте другой."));

    }

    //если одного из полей нет, запрос возвращает ошибку (создание без пароля)
    @Test
    public void createCourierWithoutLoginAndCheckResponse() {

        CourierWithPasswordAndFirstName courierWithPasswordAndFirstName = CourierWithPasswordAndFirstName.getRandom();
        String message = courierClient.createWithoutPassword(courierWithPasswordAndFirstName);
        assertThat(message, equalTo("Недостаточно данных для создания учетной записи"));
    }

    //если одного из полей нет, запрос возвращает ошибку (создание без логина)
    @Test
    public void createCourierWithoutPasswordAndCheckResponse() {

        CourierWithLoginAndFirstName courierWithLoginAndFirstName = CourierWithLoginAndFirstName.getRandom();
        String message = courierClient.createWithoutLogin(courierWithLoginAndFirstName);
        assertThat(message, equalTo("Недостаточно данных для создания учетной записи"));
    }
}

