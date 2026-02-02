package api.test.UDEMY;

import api.utilities.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.json.Json;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Oauth {

    String accessToken;
    @Test
    public void TC_AuthorizationServer(){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response=given()
                .formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .formParam("scope","trust")
                .log().all()
                .when()
                .post("/oauthapi/oauth2/resourceOwner/token")
                .then().log().all()
                .extract().asString();

        JsonPath js=ReUsableMethods.rawToJASON(response);
        accessToken=js.getString("access_token");
    }

    @Test(dependsOnMethods = "TC_AuthorizationServer")
    public void GetCourseDetails(){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response=given()
                .queryParam("access_token",accessToken)
                .log().all()
                .when()
                .get("/oauthapi/getCourseDetails")
                .then().log().all().extract().asString();

        System.out.println(response);
    }

}
