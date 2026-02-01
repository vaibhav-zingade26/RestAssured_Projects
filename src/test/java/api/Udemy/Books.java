package api.Udemy;

import api.payload.PayLoad;
import api.utilities.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static io.restassured.RestAssured.given;

public class Books {


    //List<String> ids= new ArrayList<>();
    Set<String> ids = new LinkedHashSet<>();
    //List<String> authors= new ArrayList<>();
    Set<String> authors = new LinkedHashSet<>();

    @Test(dataProvider = "getData")
    public void Post_info(String name, String isbn, int aisle, String author) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String postMeg = given()
                .header("Content-Type", "application/json")
                .body(PayLoad.bookInfoBody(name, isbn, aisle, author))
                .log().all()
                .when()
                .post("Library/Addbook.php")
                .then()
                .assertThat().statusCode(200)
                .extract().response()
                .asString();

        JsonPath js = ReUsableMethods.rawToJASON(postMeg);
        System.out.println(js.getString("Msg"));
        String ID = js.getString("ID");
        System.out.println(ID);
        ids.add(ID);
        authors.add(author);
    }

    @Test(dependsOnMethods = "Post_info")
    public void Get_Book_By_ID() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        for(String id:ids) {
            String GetMsg = given()
                    .header("Content-Type", "application/json")
                    .queryParam("ID", id)
                    .log().all()
                    .when()
                    .get("Library/GetBook.php")
                    .then()
                    .log().all()
                    .assertThat().statusCode(200)
                    .extract().response()
                    .asString();
            System.out.println(GetMsg);
            JsonPath js = ReUsableMethods.rawToJASON(GetMsg);
            String bookName = js.get("[0].book_name");
            String author = js.get("[0].author");
            System.out.println("Get_Book_By_ID : " + bookName + ": By ~" + author);
        }
    }

    @Test(dependsOnMethods = "Post_info")
    public void Get_By_Author() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        for(String author:authors) {
            String GetMsg = given()
                    .header("Content-Type", "application/json")
                    .queryParam("AuthorName", author)
                    .log().all()
                    .when()
                    .get("/Library/GetBook.php")
                    .then()
                    .log().all()
                    .extract().asString();
            JsonPath js = ReUsableMethods.rawToJASON(GetMsg);
            String bookName = js.get("[0].book_name");
            String realAuthor = js.get("[0].author");
            System.out.println("Get_Book_By_ID : " + bookName + ": By ~" + realAuthor);
        }
    }

    @Test(dependsOnMethods = {"Get_By_Author", "Get_Book_By_ID"})
    public void delete_info() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        for(String id:ids) {
            String GetMsg = given()
                    .header("Content-Type", "application/json")
                    .body(PayLoad.deleteBook(id))
                    .log().all()
                    .when()
                    .delete("/Library/DeleteBook.php")
                    .then()
                    .log().all()
                    .extract().asString();
            JsonPath js = ReUsableMethods.rawToJASON(GetMsg);
            System.out.println(js.getString("msg"));
        }
    }

    @Test
    public void PostViaExternalJSON() throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String postMsg=given()
                .body(new String(Files.readAllBytes(Path.of("C:\\Users\\vacz2\\OneDrive\\Desktop\\RestAssured_Project\\RestAssured_Projects\\testData\\body.json"))))
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .post("Library/Addbook.php")
                .then()
                .assertThat().statusCode(200)
                .extract().response()
                .asString();
        System.out.println(postMsg);
    }

    //String name,String isbn,int aisle,String author
    @DataProvider
    public Object[][] getData() {
        return new Object[][]{{"classmate", "zxcv", 5555, "dada01"}, {"navneet", "nmjn", 4444, "pada"}};
    }
}
