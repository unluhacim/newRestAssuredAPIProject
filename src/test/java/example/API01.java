package example;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class API01 {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://reqres.in";
        System.out.println("*************************** GET ***********************************");
        Response response = given().header("Content-Type", "application/jason")
                .when().get("/api/users/3");
        // .then().log().all().statusCode(200).extract().response().asString();

        System.out.println(response.asString());

        System.out.println("*************************** Put response into a MAP ***********************************");
        Map<String, Object> map = response.jsonPath().getMap("");

        //1st way:
//        if(map.containsKey("data")) {
//            System.out.println(map.get("data"));
//        }

        //2nd way:
        Set<String> set = map.keySet();
        for (String s : set) {

            String value = map.get(s).toString();  //convert object into string
            if (value.contains("Emma")) {
                System.out.println(s + " - " + map.get(s));
                break;
            }


        }
    }
}
