package TestAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.*;

import io.restassured.http.ContentType;

public class TestLocalAPI {
	@Test
	public void doGet() {
		baseURI = "http://localhost:3000";
		
		given().
			get("/users").
		then().
			statusCode(200).
			log().all();
	}
	
	
	@Test
	public void doPost() {
		JSONObject jsonRequest = new JSONObject();
		jsonRequest.put("firstname", "Somnath");
		jsonRequest.put("lastname", "Sen");
		jsonRequest.put("designation","Programmer Analyst");
		jsonRequest.put("id", 2078456);
		jsonRequest.put("email", "somnath.sen2@cognizant.com");
		
		baseURI = "http://localhost:3000";
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(jsonRequest.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
	}
	
	
	@Test
	public void doPut() {
		JSONObject jsonRequest = new JSONObject();
		jsonRequest.put("firstname", "Soma");
		jsonRequest.put("lastname", "Sen Gupta");
		jsonRequest.put("designation","Programmer Analyst Trainee");
		jsonRequest.put("email", "soma.sen@cognizant.com");

		baseURI = "http://localhost:3000";
		int id = 2078456;
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(jsonRequest.toJSONString()).
		when().
			put("/users/{id}",id).
		then().
			statusCode(200).
			log().all();
	}
	
	
	@Test
	public void doPatch() {
		JSONObject jsonRequest = new JSONObject();
		jsonRequest.put("designation","Programmer Analyst");
		int id = 2078456;
		
		baseURI = "http://localhost:3000";
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(jsonRequest.toJSONString()).
		when().
			patch("/users/{id}",id).
		then().
			statusCode(200).
			log().all();
	}
	
	
	@Test
	public void doDelete() {
		baseURI = "http://localhost:3000";
		
		int id = 2078456;
		when().
		 	delete("/users/{id}",id).
		then().
			statusCode(200).
			log().all();
	}
}
