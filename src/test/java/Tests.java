import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

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
        System.out.println(json.toJSONString());
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
    @Test
    public void Register_valid_user()
    {
        JSONObject j1son =new JSONObject();
        j1son.put("password","pistol");
        j1son.put("email","eve.holt@reqres.in");

//        System.out.println(j1son.toJSONString());
        baseURI="https://reqres.in/api";

        given().header("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .body(j1son.toJSONString())
                .when()
                .post("/register")
                .then()
                .statusCode(200);
    }
    @Test
    public void Register_Invalid_user()
    {
        JSONObject j1son =new JSONObject();
        j1son.put("email","sydney@fife");

//        System.out.println(j1son.toJSONString());
        baseURI="https://reqres.in/api";

        given().header("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .body(j1son.toJSONString())
                .when()
                .post("/register")
                .then()
                .statusCode(400);
    }
    @Test
    public void Valid_Login()
    {
        JSONObject j1son =new JSONObject();
        j1son.put("email","eve.holt@reqres.in");
        j1son.put("password","cityslicka");

//        System.out.println(j1son.toJSONString());
        baseURI="https://reqres.in/api";

        given().header("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .body(j1son.toJSONString())
                .when()
                .post("/login")
                .then()
                .statusCode(200);
    }
    @Test
    public void Invalid_Login()
    {
        JSONObject j1son =new JSONObject();
        j1son.put("email","peter@klaven");

//        System.out.println(j1son.toJSONString());
        baseURI="https://reqres.in/api";

        given().header("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .body(j1son.toJSONString())
                .when()
                .post("/login")
                .then()
                .statusCode(400);
    }
    @Test
    public void Delay_Response()
    {
        JSONObject j1son =new JSONObject();
        j1son.put("email","peter@klaven");

//        System.out.println(j1son.toJSONString());
        baseURI="https://reqres.in/api";

        given().header("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .body(j1son.toJSONString())
                .when()
                .get("/users?delay=3")
                .then()
                .statusCode(200);
    }

    }


