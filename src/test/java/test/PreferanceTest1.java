package test;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.ExtentReport;
import Utils.Utilities;
import base.DriverCapabilities;

import pageObjects.DefaultValuesPage;
import pageObjects.HomePage;
import pageObjects.PreferancePage;

public class PreferanceTest1 extends DriverCapabilities {
	ExtentReports extent;
	ExtentTest test;
	private static Logger log = LogManager.getLogger(PreferanceTest1.class.getName());

	@BeforeClass
	public void launchApp() throws IOException, InterruptedException {
		service = startServer();
		capabilties();

	}

	@Test
	public void editPreference() throws IOException, InterruptedException {
		
		HomePage home = new HomePage(driver);
		PreferancePage prefer = new PreferancePage(driver);
		DefaultValuesPage defaul = new DefaultValuesPage(driver);

		log.info("Edit Preference Test - Launched the application");

		home.preferenceClick();
		prefer.defaultClick();
		log.info("clicked editpreference");
		defaul.enterDefaultValue("Lion");
		log.info("Edit Preference Test - Enter the value to be edited");
		defaul.editClick();
		log.info("Edit Preference Test - Validate the edited value is same ");
		Assert.assertEquals(defaul.getDefaultValue(), "Lion");

		log.info("edit preferenced passed");
  
	
	}

	@AfterTest

	public void teardown() {
		// driver.quit();
	}

}
