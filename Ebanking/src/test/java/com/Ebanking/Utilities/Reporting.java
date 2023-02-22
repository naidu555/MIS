package com.Ebanking.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	public static ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void OnStart(ITestContext textContext) {
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timeStamp+".html";
		htmlReporter=new ExtentHtmlReporter(System.getProperty("User.dir")+"//test-output//"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-Config.xml");
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "local host");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user", "naidu");
		htmlReporter.config().setDocumentTitle("InetBanking Test Project");
		htmlReporter.config().setReportName("Functional testing");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}
	public void OntestSuccess(ITestResult tr) {
		logger=extent.createTest(tr.getName());
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));	
	}
	public void OntestFailure(ITestResult tr) throws IOException {
		logger=extent.createTest(tr.getName());
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
	
	String screenshotPath=System.getProperty("//Screenshots//"+tr.getName()+".png");
	File f=new File(screenshotPath);
	if(f.exists()) {
		try {
		logger.fail("screenshot is below:"+logger.addScreenCaptureFromPath(screenshotPath));
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	}
	public void OnTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	public void OnFinish(ITestContext testContext) {
		extent.flush();
	}	
	
	
}
