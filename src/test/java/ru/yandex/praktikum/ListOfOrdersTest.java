package ru.yandex.praktikum;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.ContentType;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.yandex.praktikum.BaseClient.BASE_URL;

public class ListOfOrdersTest {

    @Test
    @DisplayName("Checking that the list of orders is returned to the response body /api/v1/orders")
    @Description("Returning the order list")
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
