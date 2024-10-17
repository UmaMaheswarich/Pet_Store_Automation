package api.utilities;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	
	public static  ExtentReports getExtentReport() {
		
		ExtentReports report = new ExtentReports();
		
		Date d = new Date();
		String filename = d.toString().replace(":", "_" ).replace(" ", "_");
		
		File filepath = new File(".//reports//extentReports"+filename+".html");
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filepath);
		sparkReporter.config().setReportName("Petstore Api Test Report");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("User Details");
		
		report.attachReporter(sparkReporter);
		report.setSystemInfo("Author", "Uma meheswari");
		report.setSystemInfo("Project Name", "Pet Store  API Automation");
		report.setSystemInfo("Date Of Creation", filename);
		 return report;
		
		
		
		
		
	
		
	}

}
