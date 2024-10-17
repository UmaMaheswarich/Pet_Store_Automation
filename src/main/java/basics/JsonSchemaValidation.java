package basics;

import static io.restassured.RestAssured.when;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class JsonSchemaValidation {
	
	@Test
	public void checkJsonSchema() {
		when()
		.get("http://localhost:3000/book")
		.then()
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("books_schema.json"));
		
		
	}

}
