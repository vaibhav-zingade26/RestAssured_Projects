package api.utilities;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

    public static JsonPath rawToJASON(String response){
        JsonPath js= new JsonPath(response);
        return js;
    }
}
