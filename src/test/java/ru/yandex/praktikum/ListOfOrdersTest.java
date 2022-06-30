package ru.yandex.praktikum;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.ContentType;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import ru.yandex.praktikum.util.OrderList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.yandex.praktikum.client.BaseClient.BASE_URL;

public class ListOfOrdersTest {
    @Step("Getting a list of orders: GET request to /api/v1/orders")
    @Test
    @DisplayName("Returning the order list")
    @Description("Checking that the list of orders is returned to the response body")
    public void getOrderList(){
        List<OrderList> orderLists = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL + "/api/v1/orders")
                .then().log().all()
                .extract()
                .body().jsonPath().getList("orders", OrderList.class);
        MatcherAssert.assertThat(orderLists, notNullValue());
    }
}
