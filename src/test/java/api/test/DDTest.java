package api.test;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import api.endpoints.UserEndpoint;
import api.payloads.UserPayload;
import api.utilities.TestUtility;
import io.restassured.response.Response;



public class DDTest {
	
	
	public UserPayload upayload;
	public String Sheetname = "Sheet1";
	
	@DataProvider(name = "UserDataProvider")
	public Object[][] getTestData() throws InvalidFormatException{
		Object[][] data = TestUtility.getTestData(Sheetname); 
		return data;
	}

	@Test (dataProvider="UserDataProvider")
public void validateCreateUser(String Userid, String userName, String fname, String lname, String Email, String pass, String pn) {
		
		//updating the payload.
		
		
		upayload = new UserPayload();
		 // Validate and set user ID
	    try {
	        // Check if Userid ends with ".0" and strip it if necessary
	        if (Userid.endsWith(".0")) {
	            Userid = Userid.substring(0, Userid.length() - 2);
	        }
	        
	        // Parse Userid to an integer
	        int id = Integer.parseInt(Userid);
	        
	        // Set the ID in the payload
	        upayload.setId(id);
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid user ID format: " + Userid);
	        // Handle the exception, log, or rethrow depending on the logic needed
	    }
	    
		upayload.setUsername(userName);
		upayload.setFirstName(fname);
		 upayload.setLastName(lname);
		  upayload.setEmail(Email);
		 upayload.setPassword(pass);
		  upayload.setPhone(pn);
		
		upayload.setId(0);
		
		System.out.println(this.upayload.getUsername());
		
		Response res = UserEndpoint.Createuser(upayload);
	res.then().statusCode(200).log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//get the user
		
		Response resAfterUpdate = UserEndpoint.getUser(this.upayload.getUsername());
		resAfterUpdate.then().log().all();
		
		

}
	
	
	@Test (dataProvider="UserDataProvider")
	public void validateupdateUser(String Userid, String userName, String fname, String lname, String Email, String pass, String pn) {
			
			//updating the payload.
			
			
			upayload = new UserPayload();
			 // Validate and set user ID
		    try {
		        // Check if Userid ends with ".0" and strip it if necessary
		        if (Userid.endsWith(".0")) {
		            Userid = Userid.substring(0, Userid.length() - 2);
		        }
		        
		        // Parse Userid to an integer
		        int id = Integer.parseInt(Userid);
		        
		        // Set the ID in the payload
		        upayload.setId(id);
		    } catch (NumberFormatException e) {
		        System.out.println("Invalid user ID format: " + Userid);
		        // Handle the exception, log, or rethrow depending on the logic needed
		    }
		    
			upayload.setUsername(userName);
			upayload.setFirstName(fname);
			 upayload.setLastName(lname);
			  upayload.setEmail(Email);
			 upayload.setPassword(pass);
			  upayload.setPhone(pn);
			
			upayload.setId(0);
			
			System.out.println(this.upayload.getUsername());
			
			Response res = UserEndpoint.updateUser(this.upayload.getUsername(),upayload);
		res.then().statusCode(200).log().all();
			
			Assert.assertEquals(res.getStatusCode(), 200);
			
			//get the user
			
			Response resAfterUpdate = UserEndpoint.getUser(this.upayload.getUsername());
			resAfterUpdate.then().log().all();
			
			

	}

	@Test (dataProvider="UserDataProvider")	
public void validateDeleteUser(String Userid, String userName, String fname, String lname, String Email, String pass, String pn) {
		
		Response res = checkDeleteUser(userName);
		
		res.then().statusCode(200).log().all();
	}
	
	
	
	
	  public Response checkDeleteUser(String Username) {
	  
	  Response res = UserEndpoint.deleteUser(Username); 
	  return res;
	  }
	 
	
}