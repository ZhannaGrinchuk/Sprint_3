package Orders;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.restassured.response.Response;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private String filePath;

    public CreateOrderTest(String filePath) {
        this.filePath = filePath;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                {"src/test/resources/scooterColorGreyBlack.json"},
                {"src/test/resources/scooterColorBlack.json"},
                {"src/test/resources/scooterWithoutColors.json"},
        });
    }

    @Test
    public void selectionScooterColorGreyBlackCheckStatusCode201AndReturnTrack() {
        File json = new File(filePath);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("https://qa-scooter.praktikum-services.ru/api/v1/orders");
        response.then().assertThat()
                .statusCode(201)
                .and()
                .body("track", notNullValue());
    }
}
