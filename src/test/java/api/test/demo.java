package api.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class demo {
    @Test
    public void fetchingUserDetails(){
        RestAssured.baseURI="https://reqres.in";
        Response res = given()
                .when().get("https://reqres.in")
                .then()
                .extract()
                .response();
        System.out.println(res);
        String str = res.asPrettyString();
        System.out.println(str);
        int statusCode = res.getStatusCode();
        System.out.println(statusCode);
    }
}
