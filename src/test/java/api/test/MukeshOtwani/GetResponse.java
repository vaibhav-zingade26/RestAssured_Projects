package api.test.MukeshOtwani;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetResponse {

    final String url = "https://rahulshettyacademy.com/maps/api/place/get/json?key=qaclick123&place_id=3f52dd28bec61dbd03b26ae4ab5934ba";
    @Test
    public void getResponse(){
        Response res=RestAssured.get(url);
        int status= res.statusCode();
        String data=res.toString();
        System.out.println("Status code is "+status);
        System.out.println("************");
        System.out.println(data);
        Assert.assertEquals(status,200);
    }

    @Test
    public void getBody(){
        String response=given().baseUri(url)
                .when().get()
                .then().extract().response().toString();

        System.out.println(response);
    }

    @Test
    public void easyWay(){
        int statusCode=get(url).getStatusCode();
        long Time=get(url).getTime();
        System.out.println("Time : "+Time);
        System.out.println("statusCode : "+statusCode);
    }

    @Test
    public void easyWay1(){
        String response=get(url).asString();
        System.out.println(response);
    }
}
