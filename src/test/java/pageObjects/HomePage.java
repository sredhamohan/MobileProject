package pageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {
	private AndroidDriver<AndroidElement> driver;
	Utils.Utilities util=new Utils.Utilities(driver);

	public HomePage() {
	}

	public HomePage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}
	//All elements within the page

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Preference']")
	public AndroidElement preference;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Text\"]")
	public AndroidElement text;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Views\"]")
	public AndroidElement views;

	

	
	//All functions within the page
	
	public void preferenceClick() {
		//preference.click();
		util.getClick(preference);
	}

	public void textClick() {
		text.click();
	}

	public void viewsClick() {
		views.click();
	}

}
