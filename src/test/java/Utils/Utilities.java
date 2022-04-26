package Utils;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities {

	AndroidDriver<AndroidElement> driver;

	public Utilities(AndroidDriver<AndroidElement> driver) {

		this.driver = driver;
	}

	//Scroll in to View
	public void getscrollintoView(String text) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\")");

	}
	//identify an element using UIAutomator

	public void getuiAutomator(String text) {
		driver.findElementByAndroidUIAutomator("text(\"" + text + "\")");
	}
	//Click

	public void getClick(AndroidElement element) {
		element.click();
	}
	//Send keys

	public void getType(AndroidElement element, String text) {
		element.sendKeys(text);
	}
	//swipe an element
	public void getSwipe( AndroidDriver<AndroidElement> rdriver,AndroidElement moveFrom, AndroidElement moveTo) {

		TouchAction t =new TouchAction(rdriver);
		
		t.longPress(longPressOptions().withElement(element(moveFrom)).withDuration(ofSeconds(5))).moveTo(element(moveTo)).release().perform();
		
	}   
	
	//Tap to an element for 250 milliseconds
    public void tapByElement (AndroidElement androidElement) {
        new TouchAction(driver)
            .tap(tapOptions().withElement(element(androidElement)))
            .waitAction(waitOptions(ofMillis(250))).perform();
    }
    //Tap by coordinates
    public void tapByCoordinates (int x,  int y) {
        new TouchAction(driver)
            .tap(point(x,y))
            .waitAction(waitOptions(ofMillis(250))).perform();
    }
    //Press by element
    public void pressByElement (AndroidElement element, long seconds) {
        new TouchAction(driver)
            .press(element(element))
            .waitAction(waitOptions(ofSeconds(seconds)))
            .release()
            .perform();
    }
    //Press by coordinates
    public void pressByCoordinates (int x, int y, long seconds) {
        new TouchAction(driver)
            .press(point(x,y))
            .waitAction(waitOptions(ofSeconds(seconds)))
            .release()
            .perform();
    }
    //Horizontal Swipe by percentages
    public void horizontalSwipeByPercentage (double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage);
        int startPoint = (int) (size.width * startPercentage);
        int endPoint = (int) (size.width * endPercentage);
        new TouchAction(driver)
            .press(point(startPoint, anchor))
            .waitAction(waitOptions(ofMillis(1000)))
            .moveTo(point(endPoint, anchor))
            .release().perform();
    }
    //Vertical Swipe by percentages
    public void verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * endPercentage);
        new TouchAction(driver)
            .press(point(anchor, startPoint))
            .waitAction(waitOptions(ofMillis(1000)))
            .moveTo(point(anchor, endPoint))
            .release().perform();
    }
    //Swipe by elements
    public void swipeByElements (AndroidElement startElement, AndroidElement endElement) {
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
        new TouchAction(driver)
            .press(point(startX,startY))
            .waitAction(waitOptions(ofMillis(1000)))
            .moveTo(point(endX, endY))
            .release().perform();
    }
    //Multitouch action by using an android element
    public void multiTouchByElement (AndroidElement androidElement) {
        TouchAction press = new TouchAction(driver)
            .press(element(androidElement))
            .waitAction(waitOptions(ofSeconds(1)))
            .release();
        new MultiTouchAction(driver)
            .add(press)
            .perform();
    }

	public void clickUsingJavascriptExecutor(AndroidElement element, AndroidDriver<AndroidElement> rdriver) {
		JavascriptExecutor jse = (JavascriptExecutor) rdriver;
		jse.executeScript("arguments[0].click();", element);
		waitForSeconds(5);
	}

	public void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void explicitlyWait(AndroidElement element, AndroidDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public String readFromPropertyFile(String fileName, String propertyName) {
		InputStream input;
		Properties prop = new Properties();
		try {
			input = new FileInputStream(fileName);
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(propertyName);
	}

	public void writeToPropertyFile(String fileName, String propertyName, String valueToWrite) {
		OutputStream output;
		try {
			output = new FileOutputStream(fileName);
			Properties prop = new Properties();
			prop.setProperty(propertyName, valueToWrite);
			prop.store(output, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
