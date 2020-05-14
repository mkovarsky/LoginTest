package ru.netology.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class RegistrationDataGenerator {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static void setUpAll(RegistrationData registrationData) {
        given()
                .spec(requestSpec)
                .body(registrationData)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static RegistrationData generateActiveUser() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationData registrationData = new RegistrationData(faker.name().firstName(), faker.internet().password(), "active");
        setUpAll(registrationData);
        return registrationData;
    }

    public static RegistrationData generateBlockedUser() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationData registrationData = new RegistrationData(faker.name().firstName(), faker.internet().password(), "blocked");
        setUpAll(registrationData);
        return registrationData;
    }
    public static RegistrationData generateUnregisteredUser() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationData registrationData = new RegistrationData(faker.name().firstName(), faker.internet().password(), "blocked");
        return registrationData;
    }
}
