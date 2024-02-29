package api.test;

import api.endpoints.userEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserTests {
    Faker faker;
    User userPayLoad;

    @BeforeClass
    public void setupData(){
        faker = new Faker();
        userPayLoad=new User();
        userPayLoad.setId(faker.idNumber().hashCode());
        userPayLoad.setUsername(faker.name().username());
        userPayLoad.setFirstname(faker.name().firstName());
        userPayLoad.setLastname(faker.name().lastName());
        userPayLoad.setEmail(faker.internet().emailAddress());
        userPayLoad.setPassword(faker.internet().password());
        userPayLoad.setPhone(faker.phoneNumber().cellPhone());
    }
    @Test(priority = 1)
    public void testPostUser(){
        Response response=userEndPoints.createUser(userPayLoad);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void testGetUserByName(){
        Response response= userEndPoints.readUser(this.userPayLoad.getUsername());
        response.then().log().all();
       // System.out.println("status code : "+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 3)
    public void testUpdateUserByName(){
        //update the payload using faker
        userPayLoad.setUsername(faker.name().username());
        userPayLoad.setFirstname(faker.name().firstName());
        userPayLoad.setLastname(faker.name().lastName());
        userPayLoad.setEmail(faker.internet().emailAddress());
        Response response = userEndPoints.updateUser(this.userPayLoad.getUsername(),userPayLoad);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
        //checking the data after data
        Response responseAfterUpdate=userEndPoints.readUser(this.userPayLoad.getUsername());
        responseAfterUpdate.then().log().all();
    }

    @Test(priority = 4)
    public void testDeleteUserByName(){
        Response response=userEndPoints.deleteUser(this.userPayLoad.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test
    public void amrit(){
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
       // request.pathParams(username);
        JSONObject json = new JSONObject();
        json.put("id",26);
        json.put("title","Selenium Webdriver");
        json.put("author","Learn Automation");

        request.body(json.toString());

        Response response = request.get("https://petstore.swagger.io/v2/user");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }

}
