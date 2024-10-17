package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoint;
import api.payloads.UserPayload;
import io.restassured.response.Response;

public class UserTestcase {
	
	public UserPayload payload ;
	
	public Faker f ;
	
	public Logger logger;
	 
	@BeforeClass
	public void setUserPayload() {
		
				
		 f = new Faker();
		 
		 
		
		payload = new UserPayload();
		
		payload.setId(f.idNumber().hashCode());
		payload.setUsername(f.name().username());
		 payload.setFirstName(f.name().firstName());
		 payload.setLastName(f.name().lastName());
		  payload.setEmail(f.internet().safeEmailAddress());
		 payload.setPassword(f.internet().password());
		  payload.setPhone(f.phoneNumber().phoneNumber());
		
		payload.setId(0);
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void validateCreateUser() {
		
		logger.info("************The User is created ****************");
		Response res = UserEndpoint.Createuser(payload);
		
		System.out.println(this.payload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
		logger.info("************The User was created successfully ****************");
		
		
					
			
		}
	
	@Test(priority = 2)
	public void validateGetUser() {
		
		logger.info("************Reading the User****************");
		
		Response res = UserEndpoint.getUser(this.payload.getUsername());
		
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("************Get the User ****************");
	}
	
	@Test(priority = 3)
	public void validateUpdateUser() {
		
		//updating the payload.
		
		payload.setFirstName(f.name().firstName());
		payload.setLastName(f.name().lastName());
		payload.setEmail(f.internet().safeEmailAddress());
		
		System.out.println();
		
		logger.info("************The User is updating****************");
		
		Response res = UserEndpoint.updateUser(this.payload.getUsername(), payload);
		
		res.then().statusCode(200).log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("************The User was updated ****************");
		
		//get the user
		
		Response resAfterUpdate = UserEndpoint.getUser(this.payload.getUsername());
		resAfterUpdate.then().log().all();
		
	}
	
	@Test (priority = 4)
	public void validateDeleteUser() {
		
		logger.info("************The User was deleting ****************");
		
		Response res=UserEndpoint.deleteUser(this.payload.getUsername());
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	}