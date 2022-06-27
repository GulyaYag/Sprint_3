package ru.yandex.praktikum;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.Courier.getRandomCourier;

public class CreatingACourierTest {
    int courierId;
    Courier courier;

    CourierClient courierClient = new CourierClient();

    @Test
    @DisplayName("Check status code of the creation a courier /api/v1/courier")
    @Description("Creating a courier")

    public void creatingACourier() {
        Response responseCreateCourier = courierClient.createCourier(courier);
        assertEquals(SC_CREATED, responseCreateCourier.statusCode());
        assertTrue(responseCreateCourier.body().jsonPath().getBoolean("ok"));

        CourierCredentials courierCredentials = new CourierCredentials(courier.getLogin(), courier.getPassword());
        Response responseLogin = courierClient.loginCourier(courierCredentials);
        courierId = responseLogin.body().jsonPath().getInt("id");
    }

    @Test
    @DisplayName("Checking the status code when creating identical couriers /api/v1/courier")
    @Description("Creating identical couriers") // описание теста

    public void creatingAnIdenticalACourier() {
        courierClient.createCourier(courier);
        IdenticalCourier identicalCourier = new IdenticalCourier(courier.getLogin(), courier.getPassword());
        Response responseIdenticalCourier = courierClient.createIdenticalCourier(identicalCourier);
        assertEquals(SC_CONFLICT, responseIdenticalCourier.statusCode());
        assertEquals("Этот логин уже используется. Попробуйте другой.", responseIdenticalCourier.body().jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Checking the status code when creating a courier without mandatory fields - login /api/v1/courier")
    @Description("Creating a courier without required fields")
    public void requiredLoginWhenCreatingCourier() {
        RequiredLogin requiredLogin = new RequiredLogin(courier.getLogin());
        Response responseRequiredLogin = courierClient.requiredLoginCreateCouriers(requiredLogin);
        assertEquals(SC_BAD_REQUEST, responseRequiredLogin.statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", responseRequiredLogin.body().jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Checking the status code when creating a courier without mandatory fields - password /api/v1/courier")
    @Description("Creating a courier without required fields")
    public void requiredPasswordWhenCreatingCourier() {
        RequiredPassword requiredPassword = new RequiredPassword(courier.getPassword());
        Response responseRequiredLogin = courierClient.requiredPasswordCreateCouriers(requiredPassword);
        assertEquals(SC_BAD_REQUEST, responseRequiredLogin.statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", responseRequiredLogin.body().jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Checking the status code when creating a courier with the same usernames /api/v1/courier")
    @Description("Creating a courier with the same username") // описание теста
    public void createCourierWithSameLogin() {
        courierClient.createCourier(courier);
        SameCourierLogin sameCourierLogin = new SameCourierLogin(courier.getLogin(), courier.getPassword());
        Response responseSameLogin = courierClient.checkSameLogin(sameCourierLogin);
        assertEquals(SC_CONFLICT, responseSameLogin.statusCode());
        assertEquals("Этот логин уже используется. Попробуйте другой.", responseSameLogin.body().jsonPath().getString("message"));
    }

@After
    public void deleteCourierAfter(){
            courierClient.deleteCourier(courierId);
    }

    @Before
    public void ini(){
        courier = getRandomCourier();
    }
}












