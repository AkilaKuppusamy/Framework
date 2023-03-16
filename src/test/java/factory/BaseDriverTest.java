package factory;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import hooks.TestNgHooks;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.AfterTestMethod;

public class BaseDriverTest {

	WebDriver driver;
	
	BaseDriver baseDriver = new BaseDriver();
	
	//@Autowired
	//TestNgHooks testngHooks;
	//BaseDriver basedriver;
	
	@Test
	public void setUp() {
		baseDriver.setDriver("chrome");
		driver = baseDriver.getDriver();
		driver.get("https://google.com");
		System.out.println(driver.getTitle());
		}
	
/*	@Test
	public void setUp() {
		basedriver.startApp("chrome", "https://google.com");
		assertEquals("Google", basedriver.getDriver().getTitle());	
		} */
	
	@AfterTestMethod
	public void afterTest() {
		driver.quit();
	}	
}
