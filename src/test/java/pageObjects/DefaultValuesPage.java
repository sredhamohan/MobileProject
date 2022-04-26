package pageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DefaultValuesPage {
	private AndroidDriver<AndroidElement> driver;
	Utils.Utilities util=new Utils.Utilities(driver);
	
	public DefaultValuesPage(AndroidDriver<AndroidElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	
	//All elements within the page
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Edit text preference']")
	public AndroidElement editTextPreference;
	
	@AndroidFindBy(id="android:id/edit")
	public AndroidElement enterValue;
	
	@AndroidFindBy(id="android:id/checkbox")
	public AndroidElement checkBox;
	@AndroidFindBy(id="android:id/button1")
	public AndroidElement buttonOk;
	
	
	
	//All functions within the page
	
	
	
	public void enterDefaultValue(String editValue) {
		
		 
		 util.getClick(editTextPreference);
		 enterValue.clear();
		 enterValue.sendKeys(editValue);
		 buttonOk.click();		 
	}
	public void editClick() {
		editTextPreference.click();		
		
	}
	public String getDefaultValue() {
		
		return(enterValue.getText());
		
	}
	
	
	
	

}
