package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class Authorization {
    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String urlAuth = "https://allegro.pl/auth/oauth/token";


    private ResponseOptions<Response> ExecuteAPI(String url) {
        String clientId = "4a2cce9ff28b4e249e710ecde1f0a228";
        String client_secret = "AcDywNJdDra8ybvMHjJI2TdF94VGr2sN0WsxJIHmeju7iuzwnEU2juGQJb0odmIj";
        String authorization = encode(clientId, client_secret);

        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = given();
        request.contentType("application/vnd.allegro.public.v1+json");
        request.header("Authorization", "Basic "+ authorization);
        request.spec(requestSpecification);
        return request.post(url);
    }

    public String Authorization(Map<String, String> queryParams) {
        builder.addQueryParams(queryParams);
        return ExecuteAPI(urlAuth).getBody().jsonPath().get("access_token");
        }

    private String encode(String clientId, String client_secret) {
        return new String(Base64.getEncoder().encode((clientId+":"+client_secret).getBytes(StandardCharsets.UTF_8)));
    }
}
