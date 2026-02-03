package api.test.UDEMY;

import api.payload.POJO.Api;
import api.payload.POJO.GetCourse;
import api.payload.POJO.WebAutomation;
import api.utilities.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        GetCourse response=given()
                .queryParam("access_token",accessToken)
                .log().all()
                .when()
                .get("/oauthapi/getCourseDetails")
                .then().log().all().extract().as(GetCourse.class);

        System.out.println(response.getUrl());
        System.out.println(response.getLinkedIn());
        System.out.println(response.getCourses().getApi().get(1).getCourseTitle());

        //get price of web automation cypress price

        List<WebAutomation> web=response.getCourses().getWebAutomation();
        for(WebAutomation a:web){
            if(a.getCourseTitle().equalsIgnoreCase("cypress")){
                System.out.println("price of "+a.getCourseTitle()+" is "+a.getPrice());
            }
        }

        //get cources of api cypress price
        List<Api> apis=response.getCourses().getApi();
        System.out.println("Cources in api's ");
        for(Api a:apis){
            System.out.println(a.getCourseTitle());
        }

        //now compare the course with given webautomation array
        String [] webCources={"Selenium Webdriver Java","Cypress","Protractor"};
        List<WebAutomation> webAuto=response.getCourses().getWebAutomation();
        ArrayList<String> c=new ArrayList<>();

        for(WebAutomation b:webAuto){
            c.add(b.getCourseTitle());
        }

        //convert given array to arrayList first so that we can compare easily
        List<String> wc=Arrays.asList(webCources);

        Assert.assertEquals(wc, c);


    }

}
