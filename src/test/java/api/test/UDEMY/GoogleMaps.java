package api.test.UDEMY;

import api.payload.GoogleMapPOJO.AddressBody;
import api.payload.GoogleMapPOJO.Location;
import api.payload.GoogleMapPOJO.PlaceId;
import api.utilities.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;


public class GoogleMaps {

    String placeId;
    @Test
    public void postDetails(){

        AddressBody ab= new AddressBody();
        ab.setName("Vaibhav DOn");
        ab.setAccuracy(83.99);
        ab.setAddress("palladium hom23es");
        ab.setWebSites("www.wefjwr.com");
        ab.setLanguage("Hing32lis");
        ab.setPhone_number("+90 233994");
        String[] t={"abc","dhaka"};
        ab.setTypes(t);
        Location l= new Location();
        l.setLat(-12.44555);
        l.setLng(12.34566);
        ab.setLocation(l);

        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
               // .addParam("key","qaclick123")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON)
                .build();

        ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).build();

        String response = given().spec(req).log().all()
                .body(ab)
                .when().post("/maps/api/place/add/json")
                .then().spec(res).log().all()
                .extract().asString();

        System.out.println(response);
        JsonPath js= ReUsableMethods.rawToJASON(response);
        placeId=js.getString("place_id");
        System.out.println("Place_id : "+placeId);

    }

    @Test(dependsOnMethods = "postDetails")
    public void getAddress(){
        RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .addQueryParam("place_id",placeId).build();

        ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).build();

        String response=given().spec(req).log().all()
                .when().get("maps/api/place/get/json")
                .then().spec(res)
                .log().all()
                .extract().asString();

        System.out.println(response);

    }

    @Test(dependsOnMethods = "getAddress" )
    public void updateTheAddress(){

    }

    @Test(dependsOnMethods = "getAddress")
    public void deleteAddress(){

        PlaceId id= new PlaceId();
        id.setPlace_id(placeId);

        RequestSpecification res=new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON)
                .build();

        String response=given().spec(res).body(id)
                .when().delete("/maps/api/place/delete/json")
                .then().log().all()
                .extract().asString();

        System.out.println(response);


    }










    @Test(enabled = false)
    public void post2(){
        baseURI="https://rahulshettyacademy.com";

        String abc=given().queryParam("key","qaclick123")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"abc fgh\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side 2e2e, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}").log().all()
                .when().post("/maps/api/place/add/json")
                .then().log().all()
                .extract().asString();

        System.out.println(abc);
    }

    //schemaBuilder

}
