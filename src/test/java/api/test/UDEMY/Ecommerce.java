package api.test.UDEMY;

import api.payload.ECommerce.LoginRequest;
import api.payload.ECommerce.LoginResponse;
import api.payload.ECommerce.OrderDetails;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class Ecommerce {

    String token;
    String user;
    String productId;

    @Test
    public void Login(){

        LoginRequest loginReq= new LoginRequest();
        loginReq.setUserEmail("api@testing.com");
        loginReq.setUserPassword("VacZ@9464");
        RestAssured.baseURI="https://rahulshettyacademy.com";
        LoginResponse response=given().body(loginReq)
                .header("Content-Type","application/json")
                .log().all()
                .when().post("/api/ecom/auth/login")
                .then().assertThat().statusCode(200)
                .log().all()
                .extract().response().as(LoginResponse.class);
        token= response.getToken();
        user=response.getUserId();
        System.out.println(response.getMessage());

    }

    @Test(dependsOnMethods = "Login")
    public void AddProduct(){
        baseURI="https://rahulshettyacademy.com";
        String reaponse=given().header("Authorization",token)
                .param("productName", "Laptop")
                .param("productAddedBy", user).param("productCategory", "fashion")
                .param("productSubCategory", "shirts").param("productPrice", "11500")
                .param("productDescription", "Lenova").param("productFor", "men")
                .multiPart("productImage",new File("C:\\Users\\vacz2\\Downloads\\pirate.jpg"))
                .when().post("/api/ecom/product/add-product")
                .then().log().all()
                .extract().response().asString();
        JsonPath js= new JsonPath(reaponse);
        productId=js.getString("productId");

    }

    public void createOrder(){
        baseURI="https://rahulshettyacademy.com";

    }

}
