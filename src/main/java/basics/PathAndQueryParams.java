package basics;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class PathAndQueryParams {
	
	@Test
	public void validatePathandQueryParameters() {
		//https://reqres.in/api/users
		given()
        .pathParam("mypath","users")
		.queryParam("page",	2)
		.queryParam("id", 5)
		.when()
		.get("https://reqres.in/api/{mypath}")
		.then()
		.statusCode(200)
		.log().all();
		
	}

}
