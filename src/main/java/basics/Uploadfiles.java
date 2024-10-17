package basics;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;

import java.io.File;


public class Uploadfiles {
	
	//@Test
	public void uploadSingleFile() {
		
		File myfile = new File("C:\\Users\\UmaMaheswari\\Downloads\\manu.png");
		
		given()
		.multiPart("file", myfile)
		.contentType("multipart/form-data")
		.when()
		.post("http://localhost:8080/uploadFile")
		.then()
		.statusCode(200)
		.body("fileName",equalTo("manu.png"));
		
	}
	

	
	//@Test
	public void uploadMultipleFiles() {
		
		File myfile1 = new File("C:\\Users\\UmaMaheswari\\Downloads\\manu.png");
		File myfile2 = new File("C:\\Users\\UmaMaheswari\\Downloads\\Jirasitename.png");
		
		// for this API the following showing method won't work but for few apis we can use this type
		// we will store all files in array
		// File filearr[] = {file1, file2,file3}
		//instead of writing diff. multiparts for each file now can directly pass filearray.
		//multipart("files", filearr[]
		
		
		given()
		.multiPart("files", myfile1)
		.multiPart("files", myfile2)
		
		.contentType("multipart/form-data")
		.when()
		.post("http://localhost:8080/uploadMultipleFiles")
		.then()
		.statusCode(200)
		.body("[0].fileName",equalTo("manu.png"))
		.body("[1].fileName",equalTo("Jirasitename.png"));
		
	}
	
	@Test
	public void validatefiledownload() {
		
		given()
		.when().get("http://localhost:8080/downloadFile/manu.png")
		.then()
		.header("Content-Type","image/png");
	}

}



