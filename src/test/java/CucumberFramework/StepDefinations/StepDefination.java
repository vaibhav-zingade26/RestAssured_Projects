package CucumberFramework.StepDefinations;

import api.payload.GoogleMapPOJO.AddressBody;
import api.payload.GoogleMapPOJO.Location;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class StepDefination {
    RequestSpecification req;
    ResponseSpecification res;
    Response response;
    JsonPath js;

    @Given("Add place payload {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) {
        AddressBody ab= new AddressBody();
        ab.setName(name);
        ab.setAccuracy(83.99);
        ab.setAddress(address);
        ab.setWebSites("www.wefjwr.com");
        ab.setLanguage(language);
        ab.setPhone_number("+90 233994");
        String[] t={"abc","dhaka"};
        ab.setTypes(t);
        Location l= new Location();
        l.setLat(-12.44555);
        l.setLng(12.34566);
        ab.setLocation(l);

        req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                // .addParam("key","qaclick123")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON)
                .build();



        req= given().spec(req).log().all().body(ab);
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String string, String string2) {
        res=new ResponseSpecBuilder().expectStatusCode(200).build();

        if(string2.equalsIgnoreCase("post")){
            response=req.when().post("/maps/api/place/add/");
        } else if (string2.equalsIgnoreCase("get")) {
            response=req.when().get("/maps/api/place/get/json");
        }

    }
    @Then("the API got status code {string}")
    public void the_api_got_status_code(String string) {
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String string, String string2) {
        js= new JsonPath(response.toString());
        Assert.assertEquals(js.getString(string),string2);

    }
    @Then("Verify place_id is created maps to {string} using {string}")
    public void verify_place_id_is_created_maps_to_using(String expectedName, String resource) {
        String placeId=js.getString("place_id");
        String actualName=js.getString("name");
      req=given().spec(req).queryParam("place_id",placeId);

        res=new ResponseSpecBuilder().expectStatusCode(200).build();
        Response response1=req.when().get("/maps/api/place/get/json");
        JsonPath js1= new JsonPath(resource.toString());
        String expectedName2=js1.getString("name");

        Assert.assertEquals(actualName,expectedName2);


    }











}
