package ru.yandex.praktikum;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.client.CourierClient;
import ru.yandex.praktikum.util.CreateOrder;
import ru.yandex.praktikum.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.model.Order.*;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    Order order;
    int orderId;
    CourierClient courierClient = new CourierClient();
    public String black;
    public String grey;

    public CreateOrderTest(String black, String grey) {
        this.black = black;
        this.grey = grey;
    }
    @Parameterized.Parameters(name = "index: {0} {1}")
    public static Object[][] getColorData() {
        return new Object[][]{
                {"BLACK",""},
                {"GREY",""},
                {"BLACK","GREY"},
                {"",""}
        };
    }
    @DisplayName("Creating an order with different colors")
    @Description("Checking the status of the code when creating an order with colors")
    @Test
    public void createOrderWithColor(){
        order = getRandomOrder();
        CreateOrder createOrder = new CreateOrder(order.getFirstName(), order.getLastName(), order.getAddress(), order.getMetroStation(), order.getPhone(), order.getRentTime(), order.getDeliveryDate(), order.getComment(), (ArrayList<String>) order.getColor());
        List<String> color = Stream.of(black,grey).filter(x->!x.isBlank()).collect(Collectors.toList());
        order.setColor(color);
        Response responseCreateOrder = courierClient.createNewOrder(order);
        orderId = responseCreateOrder.body().jsonPath().getInt( "track");
        assertEquals(SC_CREATED, responseCreateOrder.statusCode());
        assertEquals(orderId, responseCreateOrder.body().jsonPath().getInt("track"));
    }
}