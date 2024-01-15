package getapitoken;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetApiToken {

    public static void main(String[] args) {
        String clientId = "d690701b-f576-43ff-a091-af15dd18f663";
        String clientSecret = "yjt8Q~QgCx-ERxN5NIZalQnjhnuQXS63JV3vqbXL";
        String tenantId = "e552d893-e6ad-4076-b2c0-96a5c0c9d894";
        String tokenEndpoint = "https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token";

        Response response = given()
            .urlEncodingEnabled(false)
            .param("grant_type", "client_credentials")
            .param("client_id", clientId)
            .param("client_secret", clientSecret)
            .param("scope", "https://graph.microsoft.com/.default")
            .when()
            .post(tokenEndpoint);
        
        System.out.println(response.getBody().asPrettyString());

        System.out.println(response.jsonPath().getString("access_token"));
    }
}