package client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static util.Addresses.BASE_URI;

public class BaseApiClient {
    // Спецификация для использования JSON в запросе"
    public static RequestSpecification getJSON() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .log(LogDetail.ALL)
                .setContentType(ContentType.JSON).build();
    }

    // Спецификация для использования Bearer-токена в запросе
    public static RequestSpecification getToken(String bearerToken) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .log(LogDetail.ALL)
                .build().header("Authorization", bearerToken);
    }
}
