package api.endpoints;

public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//User model
	
	public static String createUserUrl = base_url+"/user";
	
	public static String getUserUrl = base_url+"/user/{username}";
	
	public static String updateUserUrl = base_url+"/user/{username}";
	
	public static String deleteUserUrl = base_url+"/user/{username}";
	
	//We can also declare variables for remaining models in petstore like Store, pet ....




}
