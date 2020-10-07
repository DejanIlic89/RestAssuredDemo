package steps;

import io.restassured.http.ContentType;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;

public class BBDStyledMethod {

    public static void simpleGETPost(String postNumber){
        given().contentType(ContentType.JSON)
                .when().get(String.format("http://localhost:3000/posts/%s", postNumber))
                .then().body("author", is("Dejan Ilic")).statusCode(200);
    }

    public static void performContainsCollection(){
        given().contentType(ContentType.JSON)
                .when().get("http://localhost:3000/posts/1")
                .then().body("author", containsInAnyOrder("Dejan Ilic", "Dejan Ilic", null));
    }

    public static void performPathParameter(){
        given().contentType(ContentType.JSON)
                .with().pathParams("post", 2)
                .when().get("http://localhost:3000/posts/{post}")
                .then().body("author", containsString("Dejan Ilic"));
    }

    public static void performQueryParameter(){
        given().contentType(ContentType.JSON)
                .queryParam("id", 1)
                .when().get("http://localhost:3000/posts/")
                .then().body("author", hasItem("Dejan Ilic"));
    }

    public static void performPOSTWithBodyParameter(){
        HashMap<String, String> postContent = new HashMap<String, String>();
        postContent.put("id", "4");
        postContent.put("title", "Robotium course");
        postContent.put("author", "ExecuteAutomation");

        given()
                .contentType(ContentType.JSON).
        with()
                .body(postContent).
        when()
                .post("http://localhost:3000/posts").
        then()
                .body("author", is("ExecuteAutomation"));
    }

}
