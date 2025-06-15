import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Tests {

    @Test
    //Verify that Status Code Is '200' OK
    //get All Users .
    public void Test_1(){
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        Assert.assertEquals(response.getStatusCode(),200);
    }


    //get Request for user No #3
    @Test
    public void Test_2(){
        given().get("https://reqres.in/api/users?page=2")
                .then().body("data[2].id",equalTo(9));

    }

    //Create a New User
    @Test
    public void Test_3(){


        JSONObject json =new JSONObject();
        json.put("name","Abdo");
        json.put("Job","Tester");
        System.out.println(json.toJSONString());
        baseURI="https://reqres.in/api";

        given()
            .body(json.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201);
    }


}
