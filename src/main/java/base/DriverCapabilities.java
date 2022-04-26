package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.URL;

import java.util.Properties;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverCapabilities {
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	// Start appium server
	public AppiumDriverLocalService startServer() throws ExecuteException, IOException {
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {

			service = AppiumDriverLocalService.buildDefaultService();
			service.start();

		}
		return service;

	}
	// checking if the port is already in use

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

//Desired capabilities for identifying the device and application
	public static AndroidDriver<AndroidElement> capabilties() throws IOException, InterruptedException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\base\\global.properties");

		Properties prop = new Properties();
		prop.load(fis);

		// TODO Auto-generated method stub
		File applocation = new File("src/test/resources/app");
		File app = new File(applocation, prop.getProperty("applicationName"));
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("OS"));
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("device"));
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		cap.setCapability("app", app.getAbsolutePath());
		driver = new AndroidDriver<AndroidElement>(new URL(prop.getProperty("url")), cap);

		return driver;

	}
	// Screenshot

	public static String getScreenshot(String s) throws IOException {
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\Reports\\" + s + ".png";
		File destinationFile = new File(destination);
		FileUtils.copyFile(scrfile, destinationFile);
		return destination;

	}

	/*
	 * public static ExtentReports generateReport() { String reportPath =
	 * System.getProperty("user.dir" + "//Reports//extentReport.html");
	 * ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
	 * reporter.config().setTheme(Theme.DARK);
	 * reporter.config().setDocumentTitle("Mobile automationTestResults"); extent =
	 * new ExtentReports(); extent.attachReporter(reporter); return extent; }
	 */

}
