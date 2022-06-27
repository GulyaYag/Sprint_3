package ru.yandex.praktikum;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.*;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.Courier.getRandomCourier;

public class LoginCourierTest {
    int courierId;
    Courier courier;

    private final String LOGIN = "Gomer";
    private final String PASSWORD = "123";


    CourierClient courierClient = new CourierClient();

    @Test
    @DisplayName("Checking the status code when authorizing the courier /api/v1/courier/login")
    @Description("Verification of successful authorization of the courier in the system")

    public void successLoginCourier(){
        courierClient.createCourier(courier);
        CourierCredentials courierCredentials = new CourierCredentials(courier.getLogin(), courier.getPassword());
        Response responseLogin = courierClient.loginCourier(courierCredentials);
        courierId = responseLogin.body().jsonPath().getInt("id");
        assertEquals(SC_OK, responseLogin.statusCode());
        assertEquals(courierId, responseLogin.body().jsonPath().getInt("id"));
    }

    @Test
    @DisplayName("Check status code of the logging only with login /api/v1/courier/login")
    @Description("Logging with only one mandatory field - login")

    public void checkLoginRequiredFields() {
        LoginRequiredField loginRequiredField = new LoginRequiredField(courier.getLogin());
        Response responseLogin = courierClient.loginRequiredFields(loginRequiredField);
        assertEquals(SC_BAD_REQUEST, responseLogin.statusCode());
        assertEquals("Недостаточно данных для входа", responseLogin.body().jsonPath().getString("message"));
    }

        @Test
        @DisplayName("Check status code of the logging only with password /api/v1/courier/login")
        @Description("Logging with only one mandatory field - password")

        public void checkPasswordRequiredFields(){
        PasswordRequiredField passwordRequiredField = new PasswordRequiredField(courier.getPassword());
        Response responsePassword = courierClient.passwordRequiredFields(passwordRequiredField);
        assertEquals(SC_BAD_REQUEST, responsePassword.statusCode());
        assertEquals("Недостаточно данных для входа", responsePassword.body().jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Checking the status of the code when logging in with an incorrect data /api/v1/courier/login")
    @Description("Authorization with an incorrect data")

    public void checkCorrectLoginAndPassword(){
        CorrectLoginAndPassword incorrectLogin = new CorrectLoginAndPassword(courier.getLogin()+1, courier.getPassword());
        Response responseInCorrectLogin = courierClient.checkLoginAndPassword(incorrectLogin);
        assertEquals(SC_NOT_FOUND, responseInCorrectLogin.statusCode());
        assertEquals("Учетная запись не найдена", responseInCorrectLogin.body().jsonPath().getString("message"));

        CorrectLoginAndPassword incorrectPassword = new CorrectLoginAndPassword(courier.getLogin(), courier.getPassword()+1);
        Response responseInCorrectPassword = courierClient.checkLoginAndPassword(incorrectPassword);
        assertEquals(SC_NOT_FOUND, responseInCorrectPassword.statusCode());
        assertEquals("Учетная запись не найдена", responseInCorrectPassword.body().jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Checking the status code when logging in without a password or login /api/v1/courier/login")
    @Description("Authorization without login or password")

    public void missingField(){
        MissingLoginAndPassword loginRequired = new MissingLoginAndPassword("", courier.getPassword());
        Response responseLogin = courierClient.checkMissingLoginAndPassword(loginRequired);
        assertEquals(SC_BAD_REQUEST, responseLogin.statusCode());
        assertEquals("Недостаточно данных для входа", responseLogin.body().jsonPath().getString("message"));

        MissingLoginAndPassword passwordRequired = new MissingLoginAndPassword(courier.getLogin(), "");
        Response responsePassword = courierClient.checkMissingLoginAndPassword(passwordRequired);
        assertEquals(SC_BAD_REQUEST, responsePassword.statusCode());
        assertEquals("Недостаточно данных для входа", responsePassword.body().jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Checking the status of the code when logging in under a non-existent user /api/v1/courier/login")
    @Description("Authorization under a non-existent user")

    public void loginWithNonExistentUser() {
        courierClient.createCourier(courier);
        NonExistentUser nonExistentUser = new NonExistentUser(LOGIN, PASSWORD);
        Response responseNonExistentUser = courierClient.loginCourierWithNonExistentUser(nonExistentUser);
        assertEquals(SC_NOT_FOUND, responseNonExistentUser.statusCode());
        assertEquals("Учетная запись не найдена", responseNonExistentUser.body().jsonPath().getString("message"));
    }

    @After
    public void clear(){
        courierClient.deleteCourier(courierId);
    }

    @Before
    public void ini(){
        courier = getRandomCourier();
    }
}
