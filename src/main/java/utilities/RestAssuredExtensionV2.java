package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredExtensionV2 {

    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String method;
    private String url;

    /**
     * RestAssuredExtensionV2 constructor to pass the initial settings for the following method
     * @param uri
     * @param method
     * @param token
     */
    public RestAssuredExtensionV2(String uri, String method, String token) {
        //Formulate the API url
        this.url = "http://localhost:8000" + uri;
        this.method = method;

        if(token != null) {
            builder.addHeader("Authorization", "Bearer " + token);
        }
    }

    /**
     * @executeAPI() to execute the API for GET/POST/DELETE
     * @return ResponseOptions<Response>
     */
    private ResponseOptions<Response> executeAPI() {
        RequestSpecification rs = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(rs);

        switch (this.method) {
            case APIConstant.ApiMethods.GET:
                return request.get(this.url);
            case APIConstant.ApiMethods.POST:
                return request.post(this.url);
            case APIConstant.ApiMethods.DELETE:
                return request.delete(this.url);
            default:
                return null;
        }
    }

    /**
     * Authenticate to get the token variable
     * @param body
     * @return String token
     */
    public String authenticate(Object body) {
        builder.setBody(body);
        return executeAPI().getBody().jsonPath().get("access_token");
    }

    /**
     * Executing API with query params being passed as the input of it
     * @param queryParams
     * @return ResponseOptions<Response>
     */
    public ResponseOptions<Response> executeWithQueryParams(Map<String,String> queryParams) {
        builder.addQueryParams(queryParams);
        return executeAPI();
    }

    /**
     * Executing API with path params being passed as the input of it
     * @param pathParams
     * @return ResponseOptions<Response>
     */
    public ResponseOptions<Response> executeWithPathParams(Map<String,String> pathParams) {
        builder.addQueryParams(pathParams);
        return executeAPI();
    }

    /**
     * executeWithPathParamsAndBody
     * @param pathParams
     * @param body
     * @return
     */
    public ResponseOptions<Response> executeWithPathParamsAndBody(Map<String, String> pathParams, Map<String, String> body) {
        builder.addPathParams(pathParams);
        builder.setBody(body);
        return executeAPI();
    }
}
