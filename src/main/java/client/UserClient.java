package client;

import static client.BaseApiClient.*;
import static io.restassured.RestAssured.given;
import static util.Addresses.*;

public class UserClient {
    // Создать пользователя
    public void createUser(User user) {
        given()
                .spec(getJSON())
                .body(user)
                .when()
                .post(API_AUTH_REGISTER);
    }

    // Получить токен
    public String getAccessToken(User user) {
        return given()
                .spec(getJSON())
                .body(user)
                .when()
                .post(API_AUTH_LOGIN)
                .jsonPath()
                .getString("accessToken");
    }

    // Удалить пользователя
    public void deleteUser(User user) {
        String accessToken = getAccessToken(user);
        given()
                .spec(getToken(accessToken))
                .when()
                .delete(API_AUTH_USER);
    }
}
