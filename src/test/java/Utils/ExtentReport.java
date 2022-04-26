package Utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.DriverCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ExtentReport implements IReporter {
	base.DriverCapabilities ss = new base.DriverCapabilities();

	public ExtentReports extent;
	ExtentHtmlReporter htmlReporter;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory)

	{
		//creating the configurations of Html report
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\extentReport.html");
		extent = new ExtentReports();

		extent.setSystemInfo("Envioronment", "QA");
		extent.setSystemInfo("OS", "Android");
		extent.setSystemInfo("TestingOnDevice", "Emulator");
		extent.setSystemInfo("Application UnderTest", "API_DemosAPP");

		htmlReporter.config().setReportName("Mobile Application Testing Report");
		htmlReporter.config().setDocumentTitle("Automation Testing Report");
		htmlReporter.config().setTheme(Theme.DARK);

		extent.attachReporter(htmlReporter);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), Status.PASS);

				buildTestNodes(context.getFailedTests(), Status.FAIL);

				buildTestNodes(context.getSkippedTests(), Status.SKIP);

			}
		}

		extent.flush();
		// extent.close();
	}

	private void buildTestNodes(IResultMap tests, Status status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getMethod().getMethodName());
				test.log(Status.PASS,"Appium Server Started , Application Launched");

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);
				String ScreenShotPath = null;

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
					

					try {
						ScreenShotPath = ss.getScreenshot(result.getName());

						test.log(Status.FAIL, "Screenshot Attachment" + test.addScreenCaptureFromPath(ScreenShotPath));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
					test.log(Status.PASS,"Test Case Successfully Executed");

				}

			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
