package basics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import groovy.json.JsonToken;

import static io.restassured.RestAssured.*;

public class DiffwaysToCreate {
	
	//Using Hashmap
	//@Test (priority = 1)
	public void createUserUsingHashmap() {
		
		HashMap data = new HashMap();
		
		data.put("firstname","Snehas");
		data.put("lastname", "Gangula");
		data.put("age",30);
		data.put("id", 6);
		
		String[] courseArr = {"c++","Ruby"};
		
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json").body(data)
		.when()
			.post("http://localhost:3000/Students")
		.then()
			.statusCode(201);
		
		
	}
	
	//Create User using Org.json
	//@Test (priority = 2)
	public void createUserUsingOrgJson() {
		
	JSONObject data = new JSONObject();
	
	data.put("firstname","Mahi");
	data.put("lastname", "Chennupati");
	data.put("age",31);
	data.put("id", 7);
	

	
	String[] courseArr = {"Java","Python"};
	
	data.put("courses", courseArr);
	
	given()
		.contentType("application/json").body(data.toString())
	.when()
		.post("http://localhost:3000/Students")
	.then()
		.statusCode(201);
	
	}
	
	//create user using Pojo class
	//@Test
	public void CreateUserUsingPojo() {
		
		Pojoclass data = new Pojoclass();
		
		data.setFirstname("Manu");
		
		data.setLastname("Gummadi");
		
		data.setAge(7);
		
		data.setId(8);
		
		String[] coursesArr = {"C Sharp", "Sql"};
		
		data.setCourseArr(coursesArr);
		
		given()
			.contentType("application/json").body(data)
			
		.when()
			.post("http://localhost:3000/Students")
			
		.then()
			.statusCode(201).log().all();
			
	}
	
	//create user using External files
	
	@Test
	public void createuserusingexternalfiles() throws FileNotFoundException {
		
		File f = new File("C:\\Javaworks\\DemoRestAssured\\src\\main\\java\\basics\\Student.json");
		
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
	
		data.put("firstname","Vaishu");
		data.put("lastname", "baby");
		data.put("age",18);
		data.put("id", 9);
		

		
		String[] courseArr = {"Java","C"};
		
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json").body(data.toString())
		.when()
			.post("http://localhost:3000/Students")
		.then()
			.statusCode(201);
		

		
		
		
	}
	
	

}
