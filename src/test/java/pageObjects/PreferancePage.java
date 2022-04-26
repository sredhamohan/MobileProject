package pageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PreferancePage {
	public AndroidDriver<AndroidElement> driver;
	 public PreferancePage() { 
	    }

	
	
	public PreferancePage(AndroidDriver<AndroidElement> driver){
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//All elements within the page
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='4. Default values']")
	public AndroidElement defaultValues;
	

	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"1. Preferences from XML\"]")
	public AndroidElement preferenceXml;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"2. Launching preferences\"]")
	public AndroidElement launchPreference;
	
	
	//All functions within the page
	
	public void defaultClick() {
		defaultValues.click();
			}
   public String defaultAassertiontext() {
	return(defaultValues.getText());
}
	
   public String launchAssertiontext() {
		return(launchPreference.getText());
	}

   public String prefXmlAssertiontext() {
		return(preferenceXml.getText());
	}
		
	

}
