package ru.yandex.praktikum;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.CourierClient;
import ru.yandex.praktikum.util.*;
import ru.yandex.praktikum.model.Courier;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.model.Courier.getRandomCourier;

public class CreateCourierTest {
    Courier courier;
    int courierId;
    CourierClient courierClient = new CourierClient();

    @Test
    @DisplayName("Creating a courier")
    @Description("Check status code of the creation a courier")
    public void createCourier() {
        Response responseCreateCourier = courierClient.createCourier(courier);
        assertEquals(SC_CREATED, responseCreateCourier.statusCode());
        assertTrue(responseCreateCourier.body().jsonPath().getBoolean("ok"));
    }

    @Test
    @DisplayName("Creating identical couriers")
    @Description("Checking the status code when creating identical couriers") // описание теста
    public void creatingIdenticalACourier() {
        courierClient.createCourier(courier);
        IdenticalCourier identicalCourier = new IdenticalCourier(courier.getLogin(), courier.getPassword());
        Response responseIdenticalCourier = courierClient.createIdenticalCourier(identicalCourier);
        assertEquals(SC_CONFLICT, responseIdenticalCourier.statusCode());
        assertEquals("Этот логин уже используется. Попробуйте другой.", responseIdenticalCourier.body().jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Creating a courier without required login")
    @Description("Checking the status code when creating a courier without mandatory fields - login")
    public void requiredLoginWhenCreateCourier() {
        courierClient.createCourier(courier);
        RequiredLogin requiredLogin = new RequiredLogin(courier.getLogin());
        Response responseRequiredLogin = courierClient.requiredLoginCreateCouriers(requiredLogin);
        assertEquals(SC_BAD_REQUEST, responseRequiredLogin.statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", responseRequiredLogin.body().jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Creating a courier without required password")
    @Description("Checking the status code when creating a courier without mandatory fields - password")
    public void requiredPasswordWhenCreateCourier() {
        courierClient.createCourier(courier);
        RequiredPassword requiredPassword = new RequiredPassword(courier.getPassword());
        Response responseRequiredLogin = courierClient.requiredPasswordCreateCouriers(requiredPassword);
        assertEquals(SC_BAD_REQUEST, responseRequiredLogin.statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", responseRequiredLogin.body().jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Creating a courier with the same username")
    @Description("Checking the status code when creating a courier with the same username")
    public void createCourierWithSameLogin() {
        courierClient.createCourier(courier);
        SameCourierLogin sameCourierLogin = new SameCourierLogin(courier.getLogin(), courier.getPassword());
        Response responseSameLogin = courierClient.checkSameLogin(sameCourierLogin);
        assertEquals(SC_CONFLICT, responseSameLogin.statusCode());
        assertEquals("Этот логин уже используется. Попробуйте другой.", responseSameLogin.body().jsonPath().getString("message"));
    }

    @After
    public void deleteCourier() {
        CourierCredentials courierCredentials = new CourierCredentials(courier.getLogin(), courier.getPassword());
        Response responseLogin = courierClient.loginCourier(courierCredentials);
        courierId = responseLogin.body().jsonPath().getInt("id");
        if (courierId > 0) {
            courierClient.deleteCourier(courierId);
        }
        courierId = 0;
        assertEquals(SC_OK, responseLogin.statusCode());}

    @Before
    public void ini() {
        courier = getRandomCourier();
    }}
