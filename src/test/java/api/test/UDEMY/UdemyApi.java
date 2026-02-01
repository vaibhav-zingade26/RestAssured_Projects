package api.test.UDEMY;

import api.payload.PayLoad;
import api.utilities.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class UdemyApi {
    String placeId;
    final String path="place_id";
    @Test
    public void POST_01(){
        /*validate post method
       given(): all input details
       when() :submit the api
        then : validate the response*/
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given()
                .log().all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(PayLoad.body())
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat()
                .statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)");
    }

    @Test(priority = 0)
    public void POST_02(){
        baseURI="https://rahulshettyacademy.com";
        String response=given()
                .log().all()
                .queryParam("key","qaclick123")
                .body(PayLoad.body())
                .header("Content-Type","application/json")
                .when().post("/maps/api/place/add/json")
                .then().log().all().extract().response().asString();

        System.out.println(response);
        JsonPath js= ReUsableMethods.rawToJASON(response);
        System.out.println(js);
        System.out.println("*********************************");
        placeId=js.get(path);
    }

    @Test
    public void GET_03(){
        baseURI="https://rahulshettyacademy.com";
        String responce=given()
                .queryParam("key","qaclick123")
                .queryParam(path,placeId)
                .when().get("/maps/api/place/get/json")
                .then()
                .extract().response().asString();

        System.out.println(responce);
        JsonPath js= ReUsableMethods.rawToJASON(responce);
        String accuracy=js.get("accuracy");
        System.out.println("accuracy is : "+accuracy);
    }

    @Test(dependsOnMethods = "POST_02")
    public void PUT_04(){
        System.out.println(PayLoad.putBody(placeId));

        baseURI="https://rahulshettyacademy.com";
        given()
                .log().all()
                .queryParam("key","qaclick123")
                .body(PayLoad.putBody(placeId))
                .header("Content-Type","application/json")
                .when().put("/maps/api/place/update/json")
                .then().assertThat()
                .log().all()
                .statusCode(200)
                .body("msg",equalTo("Address successfully updated"));
    }

    @Test(dependsOnMethods = "PUT_04")
    public void GET_05(){
        baseURI="https://rahulshettyacademy.com";
        String responce=given()
                .queryParam("key","qaclick123")
                .queryParam(path,placeId)
                .log().all()
                .when().get("/maps/api/place/get/json")
                .then()
                .extract().response().asString();

        System.out.println(responce);
        JsonPath js= ReUsableMethods.rawToJASON(responce);
        String address=js.get("address");
        System.out.println("address is : "+address);
    }

    @Test
    public void name(){
        System.out.println("::::");
    }

}
