package basics;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Map;


public class Cookies {
	
	@Test(priority = 1)
	public void validateCookies() {
		
		given()
		.when()
		.get("https://www.google.com/")
		.then()
		.statusCode(200)
		.cookie("AEC","AVYB7cr9Hny2kxgHxUXoAT0AwJH30rO6kmuIEcPlSVfwukPZpA37aeCZSzg")
		.log().all();
		
		
	}

	@Test (priority = 2)
	
	public void getCookiesInfo() {
		
		Response res= when().get("https://www.google.com/");
		
		//get the single cookie value
		// by providing the key name in the method
		
		String cookie_value =res.getCookie("AEC");
		System.out.println(cookie_value);
		
		//get all cookie values
		//by this , will get both key and value pairs
		
		Map<String, String>cookiesInfo =  res.getCookies();
		
		//capture the individual values of all cookies by providing the key names . can get key names from keyset() of map
		
		for (String cookiesKey : cookiesInfo.keySet()) {
			
			String CookieValues = res.getCookie(cookiesKey);
			
			System.out.printf("The value of %s : %s",cookiesKey, CookieValues);
			System.out.println();
			
		}
		
		
		
	}
}
