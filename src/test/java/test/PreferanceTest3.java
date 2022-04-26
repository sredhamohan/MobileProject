package test;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.DriverCapabilities;

import pageObjects.DefaultValuesPage;
import pageObjects.HomePage;
import pageObjects.PreferancePage;

public class PreferanceTest3 extends DriverCapabilities {
	ExtentReports extent;
	ExtentTest test;
	private static Logger log = LogManager.getLogger(PreferanceTest3.class.getName());

	@BeforeClass
	public void launchApp() throws IOException, InterruptedException {
		service = startServer();

	}

	@Test(dataProvider = "pftData")
	public void defaultPreferenceEdit(String value) throws IOException, InterruptedException {

		capabilties();

		HomePage home = new HomePage(driver);
		PreferancePage prefer = new PreferancePage(driver);
		DefaultValuesPage defaul = new DefaultValuesPage(driver);
		log.info("Data Driven Test - Launched the application");
		home.preferenceClick();
		prefer.defaultClick();
		log.info("Data Driven Test - Enter the Value in the edittext preference textbox ");
		defaul.enterDefaultValue(value);
		defaul.editClick();
		log.info("Data Driven Test - Assert the value in the  edittext preference textbox ");
		Assert.assertEquals(defaul.getDefaultValue(), value);

	}

	//Reading the test Data

	@DataProvider(name = "pftData")

	public String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TestData.xlsx";
		Utils.ReadXLSData xlutil = new Utils.ReadXLSData(path);

		int totalrows = xlutil.getRowCount("defaultPreferenceEdit");
		int totalcols = xlutil.getCellCount("defaultPreferenceEdit", 1);

		String loginData[][] = new String[totalrows][totalcols];

		for (int i = 1; i <= totalrows; i++) // 1
		{
			for (int j = 0; j < totalcols; j++) // 0
			{
				loginData[i - 1][j] = xlutil.getCellData("defaultPreferenceEdit", i, j);
			}

		}

		return loginData;
	}

	@AfterTest

	public void teardown() {
		// driver.quit();
	}

}
