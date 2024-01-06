package validator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class XMLSchemaValidator {
	@Test
	public void validateSoapXML() throws IOException {
		File file = new File("./SoapRequest/numberToDollar.xml");
		if(file.exists()) {
			System.out.println(" >> File exists");
		} else {
			throw new IOException();
		}
		FileInputStream fileInputStream = new FileInputStream(file);
		String requestBody = IOUtils.toString(fileInputStream,"UTF-8");
		
		baseURI = "https://www.dataaccess.com/";
		
		given().
			contentType("text/xml").
			accept(ContentType.XML).
			body(requestBody).
		when().
			post("webservicesserver/NumberConversion.wso").
		then().
			statusCode(200).
			log().all().
		and().
			body("//*:NumberToDollarsResult.text()", equalTo("fifty thousand five hundred and five dollars")).
		and().
			assertThat().
				body(matchesXsdInClasspath("numberToDollar.xsd")).
			log().all();
	}
}
