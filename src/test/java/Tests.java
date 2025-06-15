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
    public void GetUsers(){
    given().header("x-api-key","reqres-free-v1")
            .get("https://reqres.in/api/users?page=2")
            .then()
            .statusCode(200);
    }


    //get Request for user No #3
    @Test
    public void Get_Spec_User(){
        given().header("x-api-key","reqres-free-v1")
                .get("https://reqres.in/api/users?page=2")
                .then()
                .body("data[2].id",equalTo(9));

    }

    //Create a New User
    @Test
    public void Add_User(){


        JSONObject json =new JSONObject();
        json.put("name","Abdo");
        json.put("Job","Tester");

        baseURI="https://reqres.in/api";

        given().header("x-api-key","reqres-free-v1")
            .body(json.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201).log().all();
    }

    @Test
    public void Update_User()
    {
        JSONObject json =new JSONObject();
        json.put("name","AbdelrahmanAyman");
        json.put("Job","Software Tester");

        baseURI="https://reqres.in/api";

        given().header("x-api-key","reqres-free-v1")
                .body(json.toJSONString())
                .when()
                .put("/users/2")
                .then()
                .statusCode(200).log().all();

    }
    @Test
    public void Delete_User()
    {
        baseURI="https://reqres.in/api";
        given().header("x-api-key","reqres-free-v1")
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204).log().all();
    }

    }


