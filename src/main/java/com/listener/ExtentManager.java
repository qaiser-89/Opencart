package com.listener;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.helper.Utility;

public class ExtentManager 
{
	private static ExtentReports extent;
	
	public static ExtentReports getInstance()
	{
		if (extent ==null)
			createInstance();
		
		return extent;
	}
	
	public static ExtentReports createInstance()
	{
		ExtentSparkReporter htmlReporter=new ExtentSparkReporter("./Reports/Automation"+ com.helper.Utility.currentDate()+".html");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("OpenCart Automation Report");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		return extent;
	}
	
	
	
	

}
