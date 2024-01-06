package TestAPI;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
public class SOAP_XML_Request {
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
//		baseURI = "http://www.dneonline.com/";
		
		given().
			contentType("text/xml").
//			header("Accept","text/xml").
			accept(ContentType.XML).
			body(requestBody).
		when().
			post("webservicesserver/NumberConversion.wso").
//			post("calculator.asmx").
		then().
			statusCode(200).
			log().all().
		and().
			body("//*:NumberToDollarsResult.text()", equalTo("fifty thousand five hundred and five dollars"));
//			body("//*:AddResult.text()", equalTo("6"));
	}
}
