package testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import utilities.Utilities;

public class SwiggyAppium {
	
public static AndroidDriver<MobileElement> driver;

	@BeforeClass
	public void setUp() throws MalformedURLException, InterruptedException {

		URL URL = new URL("http://127.0.0.1:4724/wd/hub");

		// Desired Capabilities
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "10");
		cap.setCapability("appPackage", "in.swiggy.android");
		cap.setCapability("appActivity", "in.swiggy.android.activities.HomeActivity");
		cap.setCapability(MobileCapabilityType.UDID, "emulator-5554");

		// Android Driver Initialization
		driver = new AndroidDriver<MobileElement>(URL, cap);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(5000);
		
	}
	
	@Test
	public void checkSetLocation() throws InterruptedException {
		
		driver.findElementById("in.swiggy.android:id/set_location_text").click();
		driver.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button").click();
		
		Thread.sleep(5000);
		
		driver.findElementById("in.swiggy.android:id/item_menu_top_header_restaurant_name1").click();
		driver.findElementById("in.swiggy.android:id/location_description").sendKeys("Sector 6, HSR Layout");
		
		Thread.sleep(3000);
		
		driver.findElementById("in.swiggy.android:id/google_place_search_title_text").click();
		assertTrue(driver.findElementById("in.swiggy.android:id/addressText").getText().contains("Sector 6, HSR Layout"));
		
	}
	
	@Test
	public void scrollDishIntoView() throws InterruptedException {
		
		driver.findElementById("in.swiggy.android:id/google_place_search_title_text1").click();
		driver.findElementById("in.swiggy.android:id/bottom_bar_explore").click();
		driver.findElementById("in.swiggy.android:id/search_query").click();
		
		Utilities.clickOnRestaurant("Faasos", driver);
		
		driver.findElementById("in.swiggy.android:id/image").click();
		
		Thread.sleep(5000);
		
		int x = (int) (driver.manage().window().getSize().getWidth() * 0.1);
		int y = (int) (driver.manage().window().getSize().getHeight() * 0.1);
		
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(x, y)).release().perform();
		Utilities.scrollUpDishToView("Jumbo Paneer Chole Wrap", driver);
		
	}
	
	@AfterClass
	public void afterTest() throws InterruptedException {
		
		Thread.sleep(3000);
		driver.quit();
		
	}

}
