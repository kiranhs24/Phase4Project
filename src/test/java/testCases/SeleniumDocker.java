package testCases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumDocker {
	
	public static WebDriver driver;
	
	@BeforeTest
	public void BeforeTest() throws MalformedURLException {
		
		String ChromeURL =  "http://127.0.0.1:4444/wd/hub";
		URL URL = new URL(ChromeURL);
		new DesiredCapabilities();
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new RemoteWebDriver(URL, capabilities);
		
	}
	
	@Test
	public void demoSeleniumDocker() throws InterruptedException {
						
		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Thread.sleep(3000);
		driver.quit();
		
	}

}
