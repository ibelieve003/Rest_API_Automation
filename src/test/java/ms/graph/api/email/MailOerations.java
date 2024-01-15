package ms.graph.api.email;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class MailOerations {
	public MailOerations() {
		baseURI = "https://graph.microsoft.com";
	}
	
	
	public void getOutlookEmails(String accessToken) {
        String apiPath ="/v1.0/me/messages";

        Response emailResponse = given()
            .header("Authorization", "Bearer " + accessToken)
            .get(apiPath);

        System.out.println("Email API Response: " + emailResponse.getBody().prettyPrint());
    }
	
	
	public static String getToken() {
		String clientId = "d690701b-f576-43ff-a091-af15dd18f663";
        String clientSecret = "yjt8Q~QgCx-ERxN5NIZalQnjhnuQXS63JV3vqbXL";
        String tenantId = "e552d893-e6ad-4076-b2c0-96a5c0c9d894";
        String tokenEndpoint = "https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token";

        Response response = given()
            .urlEncodingEnabled(false)
            .param("grant_type", "client_credentials")
            .param("client_id", clientId)
            .param("client_secret", clientSecret)
//            .param("scope", "api://d690701b-f576-43ff-a091-af15dd18f663/.default")
            .param("scope", "https://graph.microsoft.com/.default")
            .when()
            .post(tokenEndpoint);
        
        System.out.println(response.getBody().asPrettyString());
        return response.jsonPath().getString("access_token").toString();
	}
	
	
	public void sendEmail(String accessToken) {
        String apiPath = "/v1.0/me/sendMail";

        String emailBody = "This is the body of the email.";
        String recipientEmail = "ibiswas003@gmail.com";

        String jsonBody = "{\n" +
                "  \"message\": {\n" +
                "    \"subject\": \"Test Email\",\n" +
                "    \"body\": {\n" +
                "      \"contentType\": \"Text\",\n" +
                "      \"content\": \"" + emailBody + "\"\n" +
                "    },\n" +
                "    \"toRecipients\": [\n" +
                "      {\n" +
                "        \"emailAddress\": {\n" +
                "          \"address\": \"" + recipientEmail + "\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        System.out.println("Message body: "+ jsonBody);
        Response emailResponse = 
        	given()
            	.header("Authorization", "Bearer " + accessToken)
            	.header("Content-Type", "application/json")
            	.body(jsonBody)
            .when()	
            	.post(apiPath);
            	
        System.out.println("Email API Response: " + emailResponse.getBody().prettyPrint());
    }
	
	
	public static void main(String[] args) {
		MailOerations mail = new MailOerations();
		
		mail.getOutlookEmails(getToken());
//		mail.sendEmail(getToken());
	}
	
	
	

}
