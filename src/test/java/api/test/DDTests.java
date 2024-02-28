package api.test;

import api.endpoints.userEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {
    @Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void testPostUser(String userId,String userName,String fname,String lname,String useremail,String pwd,String ph)
    {
        User userPayLoad=new User();
        userPayLoad.setId(Integer.parseInt(userId));
        userPayLoad.setUsername(userName);
        userPayLoad.setFirstname(fname);
        userPayLoad.setLastname(lname);
        userPayLoad.setEmail(useremail);
        userPayLoad.setPassword(pwd);
        userPayLoad.setPhone(ph);
        Response response= userEndPoints.createUser(userPayLoad);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testgetUser(String userName){
        Response response= userEndPoints.readUser(userName);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 3,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testdeleteUser(String username){
        Response response=userEndPoints.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
