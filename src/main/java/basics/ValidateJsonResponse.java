package basics;
import static io.restassured.RestAssured.*;

import io.opentelemetry.api.trace.StatusCode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.matcher.RestAssuredMatchers.*;





public class ValidateJsonResponse {
	
	//@Test
	public void parsingJsonResponsedata() {
		
		//Approach 1 -- validate response data in then()
		
		given()
		.contentType(ContentType.JSON)
		.when().get("http://localhost:3000/college")
		.then()
		.body("Students[1].lastname",equalTo("Singh"))
		.statusCode(200)
		.header("Content-Type","application/json")
		.log().all();
		
		
		
	}
	
@Test
	public void parsingresponsedata2() {
		
		// Approach 2 -- validate response data by using testng Assertions
		// with this approach we can also use control flow statements also
		
		Response res = given()
		.contentType(ContentType.JSON)
		.when().get("http://localhost:3000/college");
		
		//validate the status code from the response
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//validate the header
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json");
		
		//validate the body
		
		Assert.assertEquals(res.jsonPath().get("Students[1].lastname"), "Singh");
		
		//read the firstname from the each object in the array of json
		//converting the res from response type to json object type
		
		
		JSONObject ob = new JSONObject(res.asString());
		boolean isname= false;
		
		
		for(int i =0; i<ob.getJSONArray("Students").length();i++) {
			
			
			String Firstnames = ob.getJSONArray("Students").getJSONObject(i).get("firstname").toString();
			
			
			if (Firstnames.equals("Subash")) {
				
				isname = true;
				break;
			}
		}
		
		Assert.assertEquals(isname, true);
		
		
		
		
	}


}
