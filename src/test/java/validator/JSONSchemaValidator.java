package validator;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;

public class JSONSchemaValidator {
	@Test
	public void doGet() {
		baseURI = "https://reqres.in/";
		
		given().					
			get("api/users?page=2").
		then().
			assertThat().
				body(matchesJsonSchemaInClasspath("schema.json")).
			statusCode(200).
			log().all();
	}
	

}
