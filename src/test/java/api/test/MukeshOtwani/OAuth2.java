package api.test.MukeshOtwani;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class OAuth2 {
    @Test
    public void basic(){
        given().cookie(" ")
                .body("  ")
                .when().post()
                .then().statusCode(200);
    }
}
