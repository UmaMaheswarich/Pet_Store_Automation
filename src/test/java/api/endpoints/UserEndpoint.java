package api.endpoints;
import static io.restassured.RestAssured.*;



import api.payloads.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndpoint {
	
	
	public static Response Createuser(UserPayload payload) {
		
		Response res = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.createUserUrl);
		
		return res;
	}
	
	
public static Response getUser(String username) {
		
		Response res = given()
		.header("Content-Type","application/json")
		.header("accept","application/json")
		.pathParam("username", username)
		.when()
		.get(Routes.getUserUrl);
		
		return res;
		
	}


public static Response updateUser(String username , UserPayload payload) {
	
	Response res = given()
	.header("Content-Type","application/json")
	.header("accept","application/json")
	.pathParam("username", username)
	.body(payload)
	.when()
	.put(Routes.updateUserUrl);
	
	return res;
	
}

public static Response deleteUser(String username) {
	
	Response res = given()
	
	.pathParam("username", username)
	
	.when()
	.delete(Routes.deleteUserUrl);
	
	return res;
	
}

}
