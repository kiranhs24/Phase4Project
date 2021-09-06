package utilities;

import java.util.List;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class Utilities {

	public static void clickOnRestaurant(String name, AndroidDriver<MobileElement> driver) throws InterruptedException {

		driver.findElementById("in.swiggy.android:id/search_query").sendKeys(name);
		Thread.sleep(3000);
		List<MobileElement> restaurantList = driver.findElementsById("in.swiggy.android:id/title");
		for (MobileElement restaurant : restaurantList) {

			if (restaurant.getText().equalsIgnoreCase(name)) {

				restaurant.click();
				break;

			}

		}

	}

	public static void scrollUpDishToView(String name, AndroidDriver<MobileElement> driver) throws InterruptedException {

		boolean isDishFound = false;

		while (!isDishFound) {

			List<MobileElement> dishes = driver
					.findElementsByXPath("//android.widget.TextView[@resource-id='in.swiggy.android:id/name']");
			for (MobileElement dish : dishes) {

				if (dish.getText().equalsIgnoreCase(name)) {

					isDishFound = true;
					System.out.println("Dish " + dish.getText() + " found");
					break;

				}

			}

			if (!isDishFound) {

				scrollUp(driver);

			}

		}

	}

	public static void scrollUp(AndroidDriver<MobileElement> driver) {

		/*
		 * Get height and width startx = 0.5 * Width starty = 0.9 * Height
		 * 
		 * endX = startX endy = 0.1 * Height
		 */

		TouchAction action = new TouchAction(driver);

		// Height and width of the screen
		int height = driver.manage().window().getSize().getHeight();
		int width = driver.manage().window().getSize().getWidth();

		// Setting startX, startY, endX and endY
		int startx = (int) (0.5 * width);
		int starty = (int) (0.9 * height);

		int endX = startx;
		int endY = (int) (0.1 * height);

		// Scroll up using TouchAction class
		/*
		 * LongPress MoveTo Release Perform
		 */

		action.longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(endX, endY)).release().perform();

	}

}
