package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class RestAssuredExtension {

    private RequestSpecBuilder builder;
    private String method;
    private String url;
    private String token;


    public RestAssuredExtension(String uri, String method, String token) {
        this.url = "https://api.allegro.pl" + uri;
        this.method = method;
        this.builder = new RequestSpecBuilder();
        this.token = token;

        if (token != null)
            builder.addHeader("Authorization", "Bearer " + token);
    }

    /**
     * Execute API to execute the API for GET/POST/DELETE
     * @return ReponseOptions<Response></Response>
     */
    private ResponseOptions<Response> ExecuteAPI(String url) {
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = given();
        request.contentType("application/vnd.allegro.public.v1+json");
        request.spec(requestSpecification);

        if (this.method.equalsIgnoreCase((APIConstant.ApiMethods.POST)))
            return request.post(url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE))
            return request.delete(url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.GET))
            return request.get(url);
        return null;
    }

    public String Authenticate(Map<String, String> queryParams) {
        String clientId = "4a2cce9ff28b4e249e710ecde1f0a228";
        String client_secret = "AcDywNJdDra8ybvMHjJI2TdF94VGr2sN0WsxJIHmeju7iuzwnEU2juGQJb0odmIj";
        String authorization = encode(clientId, client_secret);

        builder.addHeader("Authorization", "Basic "+ authorization);
        builder.addQueryParams(queryParams);
        return ExecuteAPI(this.url).getBody().jsonPath().get("access_token");
    }

    /**
     * Executing API with queryParams
     * @param queryParams
     * @return
     */
    public ResponseOptions<Response> ExecuteAPIWithQueryParams(Map<String, String> queryParams) {
        builder.addQueryParams(queryParams);
        return ExecuteAPI(this.url);
    }

    private String encode(String clientId, String client_secret) {
        return new String(Base64.getEncoder().encode((clientId + ":" + client_secret).getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Execute with pathParam
     * @param pathParams
     * @return
     */
    public ResponseOptions<Response> ExecuteAPIWithPathParams(Map<String, String> pathParams) {
        builder.addHeader("Accept","application/vnd.allegro.public.v1+json");
        builder.addPathParams(pathParams);
        return ExecuteAPI(this.url);
    }
    public ResponseOptions<Response> ExecuteAPICall() {
        builder.addHeader("Accept","application/vnd.allegro.public.v1+json");
        return ExecuteAPI(this.url);
    }

    /**
     * Execute with pathParams and body
     * @param pathParams
     * @param body
     * @return
     */
    public ResponseOptions<Response> ExecuteAPIWithPathParamsAndBody(Map<String, String> pathParams,Map<String, String> body) {
        builder.addPathParams(pathParams);
        builder.setBody(body);
        return ExecuteAPI(this.url);
    }
}
