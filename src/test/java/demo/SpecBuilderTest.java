package demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {
    public static void main(String[] args) {
        //RestAssured.baseURI="https://rahulshettyacademy.com";

        AddPlace p=new AddPlace();

        p.setAccuracy(50);
        p.setName("Frontline house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress("29, side layout, cohen 09");
        p.setWebsite("http://google.com");
        p.setLanguage("French-IN");

        Location l=new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        List<String> list=new ArrayList<String>();
        list.add("shoe park");
        list.add("shop");
        p.setTypes(list);


        RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key ","qaclick123").setContentType(ContentType.JSON).build();

        ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        String responseString=given().spec(reqSpec).body(p)
                .when().post("/maps/api/place/add/json")
                .then().spec(resSpec).log().all().extract().response().asString();

        System.out.println(responseString);

//        String responseString=given().queryParam("key ","qaclick123").header("Content-Type","application/json")
//                .body(p)
//                .when().post("/maps/api/place/add/json")   //.asString();
//               .then().log().all().assertThat().statusCode(200).extract().response().asString();







    }
}
