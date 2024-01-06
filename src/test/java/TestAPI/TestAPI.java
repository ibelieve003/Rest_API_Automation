package TestAPI;

import org.testng.annotations.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestAPI {
	@Test
	public void test_1() {
		baseURI = "https://reqres.in/";
		Response resonse = given().get("api/users/2");
		System.out.println(resonse.getBody().asPrettyString());
	}
	
	
	@Test
	public void test_2() {
		baseURI = "https://reqres.in/";
		given().
			get("api/users?page=2").
		then().
			statusCode(200).
			body("data[1].id",equalTo(8)).
			log().all();
	}
}
