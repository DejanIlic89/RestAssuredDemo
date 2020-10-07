package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RestAssuredExtension {

    public static RequestSpecification request;

    public RestAssuredExtension() {
        //Arrange
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:8000");
        builder.setContentType(ContentType.JSON);
        RequestSpecification requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);
    }

    public static ResponseOptions<Response> getOpsWithPathParams(String url, Map<String, String> pathParams) {
        //Act
        request.pathParams(pathParams);
        return request.get(url);
    }

    public static ResponseOptions<Response> getOps(String url) {
        //Act
        return request.get(url);
    }

    public static ResponseOptions<Response> getOpsWithToken(String url, String token) {
        //Act
        request.header(new Header("Authorization", "Bearer " + token));
        return request.get(url);
    }

    public static ResponseOptions<Response> getOpsQueryParams(String uri, String quearyParams) {
        request.queryParam(quearyParams);
        return request.get(uri);
    }

    public static ResponseOptions<Response> postOpsWithBodyAndPathParams(String url, Map<String, String> pathParams, Map<String, String> body) {
        request.pathParams(pathParams);
        request.body(body);
        return request.post(url);
    }

    public static ResponseOptions<Response> postOpsWithBody(String url, Map<String, String> body)  {
        request.body(body);
        return request.post(url);
    }

    public static ResponseOptions<Response> deleteOpsWithPathParams(String url, Map<String, String> pathParams)  {
        request.pathParams(pathParams);
        return request.delete(url);
    }

    public static ResponseOptions<Response> putOpsWithBodyAndPathParams(String url, HashMap<String,String> body, Map<String,String> pathParams) {
        request.pathParams(pathParams);
        request.body(body);
        return request.put(url);
    }

    public static ResponseOptions<Response> getOpsWithQueryParamsWithToken(String uri, Map<String, String> quearyParams, String token) {
        request.header(new Header("Authorization", "Bearer " + token));
        request.queryParams(quearyParams);
        return request.get(uri);
    }
}
