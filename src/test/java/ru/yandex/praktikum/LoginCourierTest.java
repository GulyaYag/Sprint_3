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
import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.model.Courier.getRandomCourier;

public class LoginCourierTest {
    int courierId;
    Courier courier;
    private final String LOGIN = "Gomer";
    private final String PASSWORD = "123";
    CourierClient courierClient = new CourierClient();
    @Test
    @DisplayName("Verification of successful authorization")
    @Description("Checking the status code when authorizing the courier")
    public void successLoginCourier() {
        CourierCredentials courierCredentials = new CourierCredentials(courier.getLogin(), courier.getPassword());
        Response responseLogin = courierClient.loginCourier(courierCredentials);
        courierId = responseLogin.body().jsonPath().getInt("id");
        assertEquals(SC_OK, responseLogin.statusCode());
        assertEquals(courierId, responseLogin.body().jsonPath().getInt("id"));
    }
    @Test
    @DisplayName("Logging with only one field - login")
    @Description("Check status code of the logging only with login")
    public void checkLoginRequiredFields() {
        LoginRequiredField loginRequiredField = new LoginRequiredField(courier.getLogin());
        Response responseLogin = courierClient.loginRequiredFields(loginRequiredField);
        assertEquals(SC_BAD_REQUEST, responseLogin.statusCode());
        assertEquals("Недостаточно данных для входа", responseLogin.body().jsonPath().getString("message"));
    }
    @Test
    @DisplayName("Logging with only one field - password")
    @Description("Check status code of the logging only with password")
    public void checkPasswordRequiredFields() {
        PasswordRequiredField passwordRequiredField = new PasswordRequiredField(courier.getPassword());
        Response responsePassword = courierClient.passwordRequiredFields(passwordRequiredField);
        assertEquals(SC_BAD_REQUEST, responsePassword.statusCode());
        assertEquals("Недостаточно данных для входа", responsePassword.body().jsonPath().getString("message"));
    }
    @Test
    @DisplayName("Authorization with an incorrect username")
    @Description("Checking the status of the code when logging in with an incorrect username")
    public void checkIncorrectLogin() {
        IncorrectLogin incorrectLogin = new IncorrectLogin(courier.getLogin() + 1, courier.getPassword());
        Response responseInCorrectLogin = courierClient.checkIncorrectLogin(incorrectLogin);
        assertEquals(SC_NOT_FOUND, responseInCorrectLogin.statusCode());
        assertEquals("Учетная запись не найдена", responseInCorrectLogin.body().jsonPath().getString("message"));
    }
    @Test
    @DisplayName("Authorization with an incorrect password")
    @Description("Checking the status of the code when logging in with an incorrect password")
    public void checkIncorrectPassword() {
        IncorrectPassword incorrectPassword = new IncorrectPassword(courier.getLogin(), courier.getPassword() + 1);
        Response responseInCorrectPassword = courierClient.checkIncorrectPassword(incorrectPassword);
        assertEquals(SC_NOT_FOUND, responseInCorrectPassword.statusCode());
        assertEquals("Учетная запись не найдена", responseInCorrectPassword.body().jsonPath().getString("message"));
    }
    @Test
    @DisplayName("Authorization without login")
    @Description("Checking the status code when logging without login")
    public void missingFieldUsername() {
        MissingLogin missingLogin = new MissingLogin("", courier.getPassword());
        Response responseLogin = courierClient.checkMissingLogin(missingLogin);
        assertEquals(SC_BAD_REQUEST, responseLogin.statusCode());
        assertEquals("Недостаточно данных для входа", responseLogin.body().jsonPath().getString("message"));
    }
    @Test
    @DisplayName("Authorization without password")
    @Description("Checking the status code when logging without password")
    public void missingFieldPassword() {
        MissingPassword missingPassword = new MissingPassword(courier.getLogin(), "");
        Response responsePassword = courierClient.checkMissingPassword(missingPassword);
        assertEquals(SC_BAD_REQUEST, responsePassword.statusCode());
        assertEquals("Недостаточно данных для входа", responsePassword.body().jsonPath().getString("message"));
    }
    @Test
    @DisplayName("Authorization under a non-existent user")
    @Description("Checking the status of the code when logging in under a non-existent user")
    public void loginWithNonExistentUser() {
        NonExistentUser nonExistentUser = new NonExistentUser(LOGIN, PASSWORD);
        Response responseNonExistentUser = courierClient.loginCourierWithNonExistentUser(nonExistentUser);
        assertEquals(SC_NOT_FOUND, responseNonExistentUser.statusCode());
        assertEquals("Учетная запись не найдена", responseNonExistentUser.body().jsonPath().getString("message"));
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
        courierClient.createCourier(courier);
    }
}
