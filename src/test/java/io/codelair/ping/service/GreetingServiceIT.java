package io.codelair.ping.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 *
 * @author Hassan Nazar, hassenasse @ github (2019)
 */
@DisplayName("Integration-Testing GreetingService")
public class GreetingServiceIT
{
    final static String SERVICE_URL = System.getenv( "SERVICE_URL" );

    @Test
    void getGreeting_shouldReturnCorrectGreetings(){
        System.out.println("Calling " + SERVICE_URL + "/ping");
        given()
        .when()
            .get(SERVICE_URL + "/ping")
        .then()
            .statusCode( 200 )
            .body( "message", equalTo("Hello from Pingservice-staging") );

    }
}
