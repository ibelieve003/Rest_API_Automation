package TestAPI;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class API_PutPatchAndDelete {
	@Test
	public void testPut() {
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
			put("api/users/2").
		then().
			statusCode(200).log().all();
	}
	
	
	@Test
	public void testPatch() {
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
			patch("api/users/2").
		then().
			statusCode(200).log().all();
	}
	
	@Test
	public void testDelete() {
		
		baseURI = "https://reqres.in/";
		
		when().
			delete("api/users/2").
		then().
			statusCode(204).log().all();
	}
}
