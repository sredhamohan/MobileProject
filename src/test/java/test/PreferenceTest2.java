
package test;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.testng.AssertJUnit;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.DriverCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.PreferancePage;

public class PreferenceTest2 extends DriverCapabilities {
	ExtentReports extent;
	ExtentTest test;
	HomePage home;
	PreferancePage prefer;
	private static Logger log = LogManager.getLogger(PreferenceTest2.class.getName());
	@BeforeClass
	public void launchServer() throws IOException, InterruptedException {
		startServer();
		capabilties();

	}

	@Test(priority = 0)
	public void preferencePageLine1() throws IOException, InterruptedException {
		
		home = new HomePage(driver);
		prefer = new PreferancePage(driver);
		home.preferenceClick();
		log.info("Preference Page Line Method - Assert the text line 1");
		Assert.assertEquals(prefer.prefXmlAssertiontext(), "1. Preferences from XML");
	
	}

	@Test
	public void preferencePageLine2() throws IOException, InterruptedException {

		home = new HomePage(driver);
		prefer = new PreferancePage(driver);

		// home.preferenceClick();
		log.info("Preference Page Line Method - Assert the text line 2");
		Assert.assertEquals(prefer.launchAssertiontext(), "2. Launching preferences");
	}

	@Test
	public void preferencePageLine3() throws IOException, InterruptedException {

		home = new HomePage(driver);
		prefer = new PreferancePage(driver);

		// home.preferenceClick();
		log.info("Preference Page Line Method - Assert the text line 3");
		Assert.assertEquals(prefer.defaultAassertiontext(), "5. Default values");
	}

	@AfterTest

	public void teardown() {
		// driver.quit();
	}

}
