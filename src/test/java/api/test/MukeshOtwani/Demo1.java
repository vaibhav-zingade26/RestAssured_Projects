package api.test.MukeshOtwani;

import api.payload.ECommerce.LoginRequest;
import api.payload.ECommerce.LoginResponse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Demo1 {
    String Token;
    String user;

    @BeforeClass
    public void Login(){
        RequestSpecification req= RestAssured.given();
        req.baseUri("https://rahulshettyacademy.com");
        req.header("Content-Type","application/json");
        JSONObject js= new JSONObject();
        js.put("userEmail","dada@api.com");
        js.put("userPassword","VacZ@9464");
        req.body(js.toString());
        Response res=req.post("/api/ecom/auth/login");
        String response=res.asString();
        System.out.println(res.statusCode());
        System.out.println(res.time());
        System.out.println(response);
        JsonPath r = new JsonPath(response);
        Token=r.getString("token");
        user=r.getString("userId");
        System.out.println(Token+" : "+user);
    }

    @Test
    public void AddProduct() {
        baseURI = "https://rahulshettyacademy.com";
        Response response = given().//
                header("Authorization",Token)// auth().oauth2(Token)
                .param("productName", "Mobile")
                .param("productAddedBy", user).param("productCategory", "fashion")
                .param("productSubCategory", "shirts").param("productPrice", "2999")
                .param("productDescription", "Lenova").param("productFor", "men")
                .multiPart("productImage", new File("C:\\Users\\vacz2\\Downloads\\baby.jpg"))
                .when().post("/api/ecom/product/add-product")
                .then().extract().response();

        //System.out.println(response.toString());
        System.out.println(response.statusCode());
        System.out.println(response.jsonPath().prettify());


    }
}
