package example;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;

import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;


public class API01 {
    public static void main(String[] args) throws IOException {

        RestAssured.baseURI = "https://reqres.in";
        System.out.println("*************************** GET ***********************************");
        Response response = given().header("Content-Type", "application/jason")
                .when().get("/api/users/3");
        // .then().log().all().statusCode(200).extract().response().asString();

        System.out.println("************ Parsing with jsonPath() ***********************");
        String data=response.jsonPath().getString("data");
        String id=response.jsonPath().getString("data.id");
        String email=response.jsonPath().getString("data.email");
        String url=response.jsonPath().getString("support.url");
        System.out.println("data: "+data);
        System.out.println("id: "+id);
        System.out.println("email: "+email);
        System.out.println("url: "+url);

//        System.out.println(response.asString());
//
        System.out.println("*************************** Put response into a MAP ***********************************");
        Map<String, Object> map = response.jsonPath().getMap("");

        //1st way:
        if(map.containsKey("data")) {
            System.out.println(map.get("data"));
        }

        //2nd way:
        Set<String> set = map.keySet();
        for (String s : set) {
            String value = map.get(s).toString();  //convert object into string
            if (value.contains("Emma")) {
                System.out.println(s + " - " + map.get(s));
                break;
            }
        }

        System.out.println("+++++++++++ Writing response to a json file ***********************");
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(new File("src/test/java/files/regresData.json"), response.asString());



        System.out.println("+++++++++++ Read from json file and write to a MAP ***********************");
        //File jsonFile = new File("src/test/java/files/AddPlaceJson.json");
        Map<String, Object> newMap = mapper.readValue(new File("src/test/java/files/AddPlaceJson.json"), Map.class);
        System.out.println(newMap);

        System.out.println("+++++++++++ Write from Map to a json file ***********************");
        mapper.writeValue(new File("src/test/java/files/regresData2.json"), newMap);

    }
}
