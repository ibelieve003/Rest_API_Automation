package TestAPI;

import org.json.simple.JSONObject;
import org.testng.annotations.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class API_GetAndPost {
	@Test
	public void test_Get() {
		baseURI = "https://reqres.in/";
		given().get("api/users?page=2").then().statusCode(200).
		body("data[4].first_name",equalTo("George")).
		body("data.first_name",hasItems("George","Rachel"));
	}
	
	@Test
	public void testPost() {
		Map<String,Object> map = new HashMap<String, Object>();
		
//		map.put("name", "Indrajit");
//		map.put("job","Automation Tester");
//		System.out.println(map);
		
		JSONObject jsonRequest = new JSONObject();
		jsonRequest.put("name", "Indrajit");
		jsonRequest.put("job","Automation Tester");
		
		System.out.println(jsonRequest.toJSONString());
		
		baseURI = "https://reqres.in/";
		given().
			header("Content-Type","application/json").
			accept(ContentType.JSON).
			contentType(ContentType.JSON).
			body(jsonRequest.toJSONString()).
		when().
			post("api/users").
		then().
			statusCode(201).log().all();
	}
}
