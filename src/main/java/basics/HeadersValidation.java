package basics;

import static io.restassured.RestAssured.*;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;



import org.testng.annotations.Test;


public class HeadersValidation {
	@Test(priority = 1)
	 public void validateHeaders() {
		 
		 given()
		 .when()
		 .get("https://www.google.com/")
		 .then()
         .header("Content-Type", "text/html; charset=ISO-8859-1")
         .and()
         .header("Content-Encoding", "gzip");
        
	 }
	
	@Test(priority = 2)
	public void getHeadersInfo() {
		
		
		
		Response res = 
				when()
		.get("https://www.google.com/");
		
		
		//get single header info
		
		String headerValue = res.getHeader("Content-Type");
		
		System.out.println("The value of header Content-Type is : " +headerValue);
		
		
		//get multiple headers info
		
		
		Headers getheadersInfo = res.getHeaders();
		
		for(Header headerInfo: getheadersInfo) {
			
			System.out.println(headerInfo.getName()+ "       " + headerInfo.getValue());
			
			
		}
	}

}
