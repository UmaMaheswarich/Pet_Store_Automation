package basics;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ValidateXmlResponseData {
	
	
	@Test(priority =1)
	public void checkXmlResponseDataUsingThen() {
		
		//Approach 1
		given()
		.contentType(ContentType.XML)
		.when()
		.get("https://www.w3schools.com/xml/simple.xml")
		.then()
		.body("breakfast_menu.food[0].name", equalTo("Belgian Waffles"))
		.header("Content-Type", "text/xml");
		
		
	}
	
	@Test(priority=2)
	public void validateResponseDaraUsingAssertion() {
		
		//Approach 2
		
		
		Response res = given()
			.contentType(ContentType.XML)
		.when()
			.get("https://www.w3schools.com/xml/simple.xml");
		
		
		Assert.assertEquals(res.getStatusCode(),200);
		
		Assert.assertEquals(res.getHeader("Content-Type"), "text/xml");
		
		Assert.assertEquals(res.xmlPath().get("breakfast_menu.food[0].name"),"Belgian Waffles");
		
		//read all the data
		
		XmlPath xp = new XmlPath(res.asString());
		
		boolean status = false;
		
	List<String> foodnames = xp.getList("breakfast_menu.food.name");
	
	for(String food: foodnames) {
		
		System.out.println(food);
		
		if(food.equals("French Toast")) {
			
			System.out.println("Thank you for nice food");
			
			status = true;
			break;
		}
	}
	
	Assert.assertEquals(status, true);		
	}

}
