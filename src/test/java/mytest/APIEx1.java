package mytest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.core.dockerfile.DockerfileStatement;
import demo.AddPlace;
import demo.Location;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.Argument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

import static java.awt.geom.Path2D.contains;
import static org.hamcrest.CoreMatchers.*;

public class APIEx1 {

    public static void main(String[] args) throws IOException {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        System.out.println("************************* POST Add Place *******************************");
        Response response = given().log().all().cookie("sessionId","abcd24").queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("src/test/java/files/AddPlaceJson.json"))))
                .when().post("maps/api/place/add/json");

        response.then().log().all().assertThat().body("status", equalTo("OK"))
                .body("scope", containsString("APP"));


        String placeId=response.jsonPath().getString("place_id");
        System.out.println("PlaceId: "+ placeId);
        String cookieValue=response.getCookie("sessionId");
        System.out.println("cookie: "+cookieValue);

        System.out.println("************************* GET Add Place *******************************");
        Response responseGet=given().log().all().contentType(ContentType.JSON).queryParam("place_id", placeId).queryParam("key","qaclick123")
                .when().get("/maps/api/place/get/json");

        System.out.println("************************* Converting Response to Map *******************************");
//        responseGet.then().log().all().statusCode(200);
//        Map<String, Object> map=responseGet.jsonPath().getMap("");
//        System.out.println("map: "+map);
//
//        Set<String> set=map.keySet();
//        for(String s:set){
//           // String value=map.get(s).toString();
//           // System.out.println(value);
//            System.out.println(s+" - "+map.get(s));
//        }

        System.out.println("************************* Deserialization of GET response*******************************");
        //AddPlace a=new AddPlace();
        Location l=new Location();
//        a=given().log().all().contentType(ContentType.JSON).queryParam("place_id", placeId).queryParam("key","qaclick123")
//                .when().get("/maps/api/place/get/json").as(AddPlace.class);

        AddPlace a=responseGet.as(AddPlace.class);
        String name=a.getName();
        System.out.println("name: "+ name);


    }


}
