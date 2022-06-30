package ru.yandex.praktikum.client;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.util.*;
import ru.yandex.praktikum.model.Courier;
import ru.yandex.praktikum.model.Order;
import static io.restassured.RestAssured.given;

public class CourierClient extends BaseClient {
    @Step("Create courier: POST request to /api/v1/courier")
    public Response createCourier(Courier courier) {
        return given()
                .spec(getRequestSpec())
                .body(courier)
                .when()
                .post(BASE_URL + "/api/v1/courier");
    }
    @Step("Create courier with identical data")
    public Response createIdenticalCourier(IdenticalCourier identicalCourier) {
        return given()
                .spec(getRequestSpec())
                .body(identicalCourier)
                .when()
                .post(BASE_URL+ "/api/v1/courier");
    }
    @Step("Create courier only with login")
    public Response requiredLoginCreateCouriers(RequiredLogin requiredLogin) {
        return given()
                .spec(getRequestSpec())
                .body(requiredLogin)
                .when()
                .post(BASE_URL+ "/api/v1/courier");
    }
    @Step("Create courier only with password")
    public Response requiredPasswordCreateCouriers(RequiredPassword requiredPassword) {
        return given()
                .spec(getRequestSpec())
                .body(requiredPassword)
                .when()
                .post(BASE_URL+ "/api/v1/courier");
    }
    @Step("Courier authorization: POST request to /api/v1/courier/login")
    public Response loginCourier(CourierCredentials courierCredentials) {
        return given()
                .spec(getRequestSpec())
                .body(courierCredentials)
                .when()
                .post(BASE_URL + "/api/v1/courier/login");
    }
    @Step("Courier authorization only with login")
    public Response loginRequiredFields(LoginRequiredField loginRequiredField) {
        return given()
                .spec(getRequestSpec())
                .body(loginRequiredField)
                .when()
                .post(BASE_URL+ "/api/v1/courier/login");
    }
    @Step("Courier authorization only with password")
    public Response passwordRequiredFields(PasswordRequiredField passwordRequiredField) {
        return given()
                .spec(getRequestSpec())
                .body(passwordRequiredField)
                .when()
                .post(BASE_URL+ "/api/v1/courier/login");
    }
    @Step("Courier authorization with incorrect login")
    public Response checkIncorrectLogin(IncorrectLogin incorrectLogin) {
        return given()
                .spec(getRequestSpec())
                .body(incorrectLogin)
                .when()
                .post(BASE_URL + "/api/v1/courier/login");
    }
    @Step("Courier authorization with incorrect password")
    public Response checkIncorrectPassword(IncorrectPassword incorrectPassword) {
        return given()
                .spec(getRequestSpec())
                .body(incorrectPassword)
                .when()
                .post(BASE_URL + "/api/v1/courier/login");
    }
    @Step("Courier authorization with same username")
    public Response checkSameLogin(SameCourierLogin sameCourierLogin) {
        return given()
                .spec(getRequestSpec())
                .body(sameCourierLogin)
                .when()
                .post(BASE_URL+ "/api/v1/courier");
    }
    @Step("Courier authorization with nonexistent user")
    public Response loginCourierWithNonExistentUser(NonExistentUser nonExistentUser) {
        return given()
                .spec(getRequestSpec())
                .body(nonExistentUser)
                .when()
                .post(BASE_URL + "/api/v1/courier/login");
    }
    @Step("Courier authorization without login")
    public Response checkMissingLogin(MissingLogin missingLogin){
        return given()
                .spec(getRequestSpec())
                .body(missingLogin)
                .when()
                .post(BASE_URL + "/api/v1/courier/login");
    }
    @Step("Courier authorization without password")
    public Response checkMissingPassword(MissingPassword missingPassword){
        return given()
                .spec(getRequestSpec())
                .body(missingPassword)
                .when()
                .post(BASE_URL + "/api/v1/courier/login");
    }
    @Step("Create order: post request to /api/v1/orders")
    public Response createNewOrder(Order createOrder){
        return given()
                .spec(getRequestSpec())
                .body(createOrder)
                .when()
                .post(BASE_URL + "/api/v1/orders");
    }
    @Step("Delete courier: DELETE request to /api/v1/courier/")
    public Response deleteCourier(int courier) {
        return given()
                .spec(getRequestSpec())
                .when()
                .delete(BASE_URL + "/api/v1/courier/" + courier);
    }
}



