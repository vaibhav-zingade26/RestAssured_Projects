package api.test.UDEMY;

import api.payload.PayLoad;
import api.utilities.ReUsableMethods;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ComplexJSONScenarios {
    /*1. Print No of courses returned by API
    2.Print Purchase Amount
    3. Print Title of the first course
    4. Print All course titles and their respective Prices
    5. Print no of copies sold by RPA Course
    6. Verify if Sum of all Course prices matches with Purchase Amount*/

    @Test
    public void complex(){
        JsonPath js= ReUsableMethods.rawToJASON(PayLoad.complexJSONBody());
        int count = js.getInt("courses.size()");
        System.out.println("Print No of courses returned by API = "+count);

        int purchaseAmount=js.getInt("dashboard.purchaseAmount");
        System.out.println("Print Purchase Amount : "+purchaseAmount);

        String FirstTitle=js.getString("courses[0].title");
        System.out.println("Print Title of the first course : "+FirstTitle);

        String LastTitle=js.getString("courses[2].title");
        System.out.println("Print Title of the last course : "+LastTitle);

        System.out.println("*************************************");
        System.out.println("Print All course titles and their respective Prices : ");

        for(int i=0;i<count;i++){
            String cource=js.getString("courses["+i+"].title");
            int price=js.getInt("courses["+i+"].price");
            System.out.println(cource+":"+price);
        }

        System.out.println("*************************************");

        for(int i=0;i<count;i++){
            String cource=js.getString("courses["+i+"].title");
            if(cource.equalsIgnoreCase("RPA")){
                int copies=js.getInt("courses["+i+"].copies");
                System.out.println("Print no of copies sold by RPA Course : "+copies);
                break;
            }
        }

        /*int copies=js.getInt("courses[2].copies");
        System.out.println("Print no of copies sold by RPA Course : "+copies);*/

        System.out.println("*************************************");
        System.out.println("Verify if Sum of all Course prices matches with Purchase Amount");
        int sum=0;
        for(int i=0;i<count;i++){
            int copies=js.getInt("courses["+i+"].copies");
            int price=js.getInt("courses["+i+"].price");
            sum=sum+(copies*price);
        }

        Assert.assertEquals(sum,purchaseAmount,"sum and purchaseAmount are matching");











    }
}
