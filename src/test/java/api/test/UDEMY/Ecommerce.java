package api.test.UDEMY;

import api.payload.ECommerce.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class Ecommerce {

    String token;
    String user;
    String productId;
    String orderId;

    @Test
    public void Login(){
        LoginRequest loginReq= new LoginRequest();
        loginReq.setUserEmail("dada@api.com");
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
        String response=given().header("Authorization",token)
                .param("productName", "Mobile")
                .param("productAddedBy", user).param("productCategory", "fashion")
                .param("productSubCategory", "shirts").param("productPrice", "2999")
                .param("productDescription", "Lenova").param("productFor", "men")
                .multiPart("productImage",new File("C:\\Users\\vacz2\\Downloads\\baby.jpg"))
                .when().post("/api/ecom/product/add-product")
                .then().log().all()
                .extract().response().asString();
        JsonPath js= new JsonPath(response);
        productId=js.getString("productId");
        System.out.println("Product order Id : "+productId);

    }

    @Test(dependsOnMethods = "AddProduct")
    public void createOrder(){
        OrderDetails orderDetails=new OrderDetails();
        orderDetails.setProductOrderedId(productId);
        orderDetails.setCountry("India");

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(orderDetails);
        Orders orders= new Orders();
        orders.setOrders(orderDetailsList);

        baseURI="https://rahulshettyacademy.com";
        CreatedOrders createdOrders=given().header("Authorization",token)
                .header("Content-Type","application/json")
                .body(orders)
                .when().post("/api/ecom/order/create-order")
                .then().statusCode(201)
                .log().all()
                .extract().response().as(CreatedOrders.class);


        System.out.println(createdOrders.getMessage());

        String [] orderIds= createdOrders.getOrders();
        orderId=orderIds[0];
        System.out.println("OrderId is "+orderId);
        System.out.println("*************************************************");

    }

    @Test(dependsOnMethods = "createOrder")
    public void deleteOrder(){
        baseURI="https://rahulshettyacademy.com";
        given().header("Authorization",token)
                .pathParams("orderId", orderId)
                .when().delete("/api/ecom/order/delete-order/{orderId}")
                .then().log().all().extract().response();
    }

    @Test(dependsOnMethods = "deleteOrder")
    public void deleteCreatedProduct(){
       /* baseURI="https://rahulshettyacademy.com";
        given().pathParams()*/

        RequestSpecification deleteProdBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token).setContentType(ContentType.JSON)
                .build();

        RequestSpecification deleteProdReq =given().log().all().spec(deleteProdBaseReq).pathParam("productId",productId);

       String response= deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().
                extract().response().asString();

        System.out.println(response);
    }

}
