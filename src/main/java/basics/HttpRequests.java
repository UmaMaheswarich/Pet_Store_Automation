package basics;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

import java.util.HashMap;


public class HttpRequests {
	
	int iD;
	
	@Test
	public void getallUsers() {
		
		//List of users
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
			
		
		.then() 
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
		
				
	}
	
	
	@Test
	public void getSingleUser() {
		//get single user
		given()
		.when()
			.get("https://reqres.in/api/users/2")
		.then()
		    .statusCode(200)
		    .log().all();
			
				
	}
	
	//Create User
	
	@Test
	public void createUser() {
		
		HashMap data = new HashMap();
		
		data.put("name", "uma");
		data.put("job", "Tester");
		
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.log().all();
		
		}

	
	//Capture ID , while creating the  User ( then() method is not required because here it is used for validations)
	
	@Test
	public void captureIDcreateUser() {
		
		HashMap data = new HashMap();
		
		data.put("name", "umamaheswari");
		data.put("job", "Tester");
		
		
		iD = given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
			
		
		
		}
	
	@Test
	public void updateUser() {
						
			HashMap data = new HashMap();
			
			data.put("name", "uma");
			data.put("job", "Developer");
			
			
			given()
				.contentType("application/json")
				.body(data)
			
			.when()
				.post("https://reqres.in/api/users/"+iD)
				
			.then()
				.statusCode(201)
				.log().all();
			
			}
	
	@Test
	public void deleteUser() {
		given()
		.when()
			.delete("https://reqres.in/api/users/"+iD)
		.then()
			.statusCode(204)
			.log().all();
	}
	
	}

