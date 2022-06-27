package ru.yandex.praktikum;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierClient extends BaseClient {

    public Response createCourier(Courier courier) {
        return given()
                .spec(getRequestSpec())
                .body(courier)
                .when()
                .post(BASE_URL + "/api/v1/courier");
    }
    public Response createIdenticalCourier(IdenticalCourier identicalCourier) {
        return given()
                .spec(getRequestSpec())
                .body(identicalCourier)
                .when()
                .post(BASE_URL+ "/api/v1/courier");
    }
    public Response requiredLoginCreateCouriers(RequiredLogin requiredLogin) {
        return given()
                .spec(getRequestSpec())
                .body(requiredLogin)
                .when()
                .post(BASE_URL+ "/api/v1/courier");
    }

    public Response requiredPasswordCreateCouriers(RequiredPassword requiredPassword) {
        return given()
                .spec(getRequestSpec())
                .body(requiredPassword)
                .when()
                .post(BASE_URL+ "/api/v1/courier");
    }

    public Response loginRequiredFields (LoginRequiredField loginRequiredField) {
        return given()
                .spec(getRequestSpec())
                .body(loginRequiredField)
                .when()
                .post(BASE_URL+ "/api/v1/courier/login");
    }

    public Response passwordRequiredFields(PasswordRequiredField passwordRequiredField) {
        return given()
                .spec(getRequestSpec())
                .body(passwordRequiredField)
                .when()
                .post(BASE_URL+ "/api/v1/courier/login");
    }

    public Response checkLoginAndPassword(CorrectLoginAndPassword correctLoginAndPassword) {
        return given()
                .spec(getRequestSpec())
                .body(correctLoginAndPassword)
                .when()
                .post(BASE_URL + "/api/v1/courier/login");
    }

    public Response checkSameLogin(SameCourierLogin sameCourierLogin) {
        return given()
                .spec(getRequestSpec())
                .body(sameCourierLogin)
                .when()
                .post(BASE_URL+ "/api/v1/courier");
    }

    public Response loginCourier(CourierCredentials courierCredentials) {
        return given()
                .spec(getRequestSpec())
                .body(courierCredentials)
                .when()
                .post(BASE_URL + "/api/v1/courier/login");
    }
    public Response loginCourierWithNonExistentUser(NonExistentUser nonExistentUser) {
        return given()
                .spec(getRequestSpec())
                .body(nonExistentUser)
                .when()
                .post(BASE_URL + "/api/v1/courier/login");
    }

    public Response checkMissingLoginAndPassword(MissingLoginAndPassword missingLoginAndPassword){
        return given()
                .spec(getRequestSpec())
                .body(missingLoginAndPassword)
                .when()
                .post(BASE_URL + "/api/v1/courier/login");
    }

    public Response createNewOrder(Order createOrder){
        return given()
                .spec(getRequestSpec())
                .body(createOrder)
                .when()
                .post(BASE_URL + "/api/v1/orders");
    }

    public Response deleteCourier(int courier) {
        return given()
                .spec(getRequestSpec())
                .when()
                .delete(BASE_URL + "/api/v1/courier/" + courier);
    }
}



